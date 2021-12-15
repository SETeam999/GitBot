package com.LinkIT;

import org.kohsuke.github.*;

import java.util.Map;
import java.util.List;
import java.io.IOException;

public class Main {

    private GHApp app;
    GitHub github;
    Tagging tagging = new Tagging();
    CheckTags checkTags = new CheckTags();
    Notification notification = new Notification();
    MergeConflictResolver mergeConflictResolver = new MergeConflictResolver();
    Depandabot dependabot = new Depandabot();
    LocalRepositoryController localRepositoryController = new LocalRepositoryController();
    MergingClass mergingClass = new MergingClass(tagging, checkTags, mergeConflictResolver, dependabot, localRepositoryController);

    Main() {
        app = new GHApp();

        try {
            github = new GitHubBuilder().withOAuthToken("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").build();
            GHOrganization organization = github.getOrganization("SETeam999");

            Map<String, GHRepository> repositories = organization.getRepositories();

            for (int i = 0; i < repositories.size(); i++) {
                for (String repositoryKeySets: repositories.keySet()) {
                    GHRepository repository = repositories.get(repositoryKeySets);

                    List<GHPullRequest> pullRequests = repository.getPullRequests(GHIssueState.OPEN);


                    for (GHPullRequest pullRequest : pullRequests) {
                        notification.Processing(pullRequest);
                        if (checkTags.checkDontMergeTag(pullRequest)){
                            tagging.nopermission_to_merge(pullRequest);
                        }else{
                            mergingClass.merging(pullRequest);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        // We start our app here
    }

    public static void main(String [] args){
        (new Main()).start();
    }
}

