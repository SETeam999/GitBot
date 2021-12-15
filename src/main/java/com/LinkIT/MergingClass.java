package com.LinkIT;

import org.jetbrains.annotations.NotNull;
import org.kohsuke.github.GHPullRequest;

import java.io.IOException;
import java.util.Objects;

public class MergingClass {

    int count_merged = 0;
    Tagging tagging;
    Notification notification = new Notification();
    CheckTags checkTags;
    MergeConflictResolver mergeConflictResolver;
    Depandabot dependabot;
    LocalRepositoryController localRepositoryController = new LocalRepositoryController();

    public MergingClass(Tagging tagging, CheckTags checkTags, MergeConflictResolver mergeConflictResolver, Depandabot dependabot, LocalRepositoryController localRepositoryController) {
        this.tagging = tagging;
        this.checkTags = checkTags;
        this.mergeConflictResolver = mergeConflictResolver;
        this.dependabot = dependabot;
        this.localRepositoryController = localRepositoryController;
    }

    public void merging(GHPullRequest pullRequest) throws IOException {
        tagging.mark_branch(pullRequest);
        String mergeableState = pullRequest.getMergeableState();
        handleCleanMergeableState(pullRequest, mergeableState);
        handleDirtyMergeableState(pullRequest, mergeableState);
        handleReadytoMerge(pullRequest);
        if (pullRequest.isMerged()) {
            count_merged++; //counting merges
        }
    }

    private void handleReadytoMerge(GHPullRequest pullRequest) throws IOException {
        if (checkTags.checkReadytoMergeTag(pullRequest)) { //if code receives label ready to merge from company
            dependabot.mergingDependabot(pullRequest); //auto merge using dependabot
//            mergeConflictResolver.automerge(pullRequest);
            if (pullRequest.isMerged()) {
                tagging.is_merged(pullRequest);
            }
        }else if(!pullRequest.isMerged()) {
            tagging.failed_to_merge(pullRequest);
        }

    }

    private void handleCleanMergeableState(GHPullRequest pullRequest, String mergeableState) throws IOException {
        if (Objects.equals(mergeableState, "clean")) {
            tagging.noconflict(pullRequest); //tag no merge conflict found
            notification.Smiley(pullRequest);
            if (!pullRequest.getMergeable()) {
                tagging.not_mergeable(pullRequest);
                notification.SadFace(pullRequest);
            }
        }
    }


    private void handleDirtyMergeableState(@NotNull GHPullRequest pullRequest, String mergeableState) throws IOException {
        if (Objects.equals(mergeableState, "dirty")) {
            tagging.conflict(pullRequest); //tag merge conflict found
            localRepositoryController.getMergeConflictFiles(pullRequest);
            if(localRepositoryController.getMergeConflictFiles(pullRequest)) { //check if file is package-lock
                if (pullRequest.getMergeable()) {
                    mergeConflictResolver.package_lock_conflict_resolver(); //package-lock merge conflict resolver
                    tagging.conflict_resolved(pullRequest); //tag conflict resolved
                    notification.ThumbUp(pullRequest);
                } else {
                    tagging.not_mergeable(pullRequest);
                    notification.SadFace(pullRequest);
                }
            } else{
                notification.not_packagelock(pullRequest);
            }
        }
    }
}