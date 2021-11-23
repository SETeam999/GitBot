package com.LinkIT;

import org.kohsuke.github.*;
import java.util.Map;
import java.util.List;
import java.io.IOException;

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
                for (int j = 0; j < pullRequests.size(); j++) {
                    System.out.println(pullRequests.get(j).getMergeableState());

                    if(pullRequests.get(j).getMergeableState() == "clean"){
                        System.out.println("No merge conflict");
                    }
                    if(pullRequests.get(j).getMergeableState() == "dirty"){
                        System.out.println("Merge conflict found");
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
