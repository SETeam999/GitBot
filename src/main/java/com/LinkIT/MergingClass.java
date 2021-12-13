package com.LinkIT;

import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHPullRequestFileDetail;

import java.io.IOException;
import java.util.Objects;

public class MergingClass {

    int count_merged = 0;
    Tagging tagging;
    CheckTags checkTags = new CheckTags();
    MergeConflictResolver mergeConflictResolver;
    Depandabot dependabot;
    LocalRepositoryController localRepositoryController;

    public MergingClass(Tagging tagging, CheckTags checkTags, MergeConflictResolver mergeConflictResolver) {
        this.tagging = tagging;
        this.checkTags = checkTags;
        this.mergeConflictResolver = mergeConflictResolver;
    }

    public void merging(GHPullRequest pullRequest) throws IOException {
        tagging.mark_branch(pullRequest);
        String mergeableState = pullRequest.getMergeableState();
        handleCleanMergeableState(pullRequest, mergeableState);
        handleDirtyMergeableState(pullRequest, mergeableState);
        handleReadytoMerge(pullRequest);
        if(pullRequest.isMerged()){
            count_merged++; //counting merges
        }
    }

    private void handleReadytoMerge(GHPullRequest pullRequest) throws IOException {
        if(checkTags.checkReadytoMergeTag(pullRequest)){ //if code receives label ready to merge
            dependabot.mergingDependabot(pullRequest); //auto merge with dependabot
            tagging.merge_in_process(pullRequest); //tag merge in process
            if(pullRequest.isMerged()){
                tagging.is_merged(pullRequest);
            }
        }
        else{
            tagging.permission_to_merge(pullRequest);
        }
    }

    private void handleCleanMergeableState(GHPullRequest pullRequest, String mergeableState) throws IOException {
        if (Objects.equals(mergeableState, "clean")) {
            tagging.noconflict(pullRequest); //tag no merge conflict found
                if (!pullRequest.getMergeable()) {
                    tagging.not_mergeable(pullRequest);
                }
            }
        }

    private void handleDirtyMergeableState(GHPullRequest pullRequest, String mergeableState) throws IOException {
        String [] mergeconflictfiles = new String[pullRequest.getChangedFiles()];
        if (Objects.equals(mergeableState, "dirty")) {
            tagging.conflict(pullRequest); //tag merge conflict found
            localRepositoryController.pullRepository();
            localRepositoryController.getMergeConflictFiles(mergeconflictfiles);
            for (String mergeconflictfile : mergeconflictfiles) {
                if (Objects.equals(pullRequest.getRepository().getName(), mergeconflictfile)) {
                    mergeConflictResolver.package_lock_conflict_resolver(); //package-lock merge conflict resolver
                    tagging.conflict_resolved(pullRequest); //tag conflict resolved
                } else {
                    tagging.not_mergeable(pullRequest);
                }
            }

        }
    }
}
