package com.LinkIT;

import org.kohsuke.github.*;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import java.util.Objects;

public class Main {

    private GHApp app;
    GitHub github;

    Main() {

        app = new GHApp();

        try {
            github = new GitHubBuilder().withOAuthToken("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")
                    .build();
            GHOrganization organization = github.getOrganization("SETeam999");

            Map<String, GHRepository> repositories = organization.getRepositories();
            for (int i = 0; i < repositories.size(); i++) {
                GHRepository repository = repositories.get(repositories.keySet().toArray()[i]);
                List<GHPullRequest> pullRequests = repository.getPullRequests(GHIssueState.OPEN);
                for (GHPullRequest pullRequest : pullRequests) {
                    System.out.println(pullRequest.getMergeableState());

                    if (Objects.equals(pullRequest.getMergeableState(), "clean")) {
                        pullRequest.addLabels("No merge conflict");
                    }
                    if (Objects.equals(pullRequest.getMergeableState(), "dirty")) {
                        pullRequest.addLabels("No merge conflict");
                    }
                    // Detect if we can merge
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
