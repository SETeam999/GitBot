    package com.LinkIT;

    import org.kohsuke.github.*;

    import java.util.Collection;
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
        MergingClass mergingClass = new MergingClass(tagging, checkTags, mergeConflictResolver);
        

        Main() {
            app = new GHApp();

            try {
                github = new GitHubBuilder().withOAuthToken("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")
                        .build();
                GHOrganization organization = github.getOrganization("SETeam999");

                Map<String, GHRepository> repositories = organization.getRepositories(); //what is the string part

                for (int i = 0; i < repositories.size(); i++) {
                    for (String repositoryKeySets: repositories.keySet()) {
                        GHRepository repository = repositories.get(repositoryKeySets);

                        List<GHPullRequest> pullRequests = repository.getPullRequests(GHIssueState.OPEN);

                        for (GHPullRequest pullRequest : pullRequests) {
                            if (!checkTags.checkDontMergeTag(pullRequest)){
                                notification.Processing(pullRequest);
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
