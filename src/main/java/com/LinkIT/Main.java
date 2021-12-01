    package com.LinkIT;

    import org.kohsuke.github.*;
    import java.util.Map;
    import java.util.List;
    import java.io.IOException;
    import java.util.Objects;

    public class Main {

        private GHApp app;
        GitHub github;
        Tagging t;
        MergeConflictResolver MCR;
        StopMerge SM;
        int count_merged = 0;

        Main() {
            app = new GHApp();

            try {
                github = new GitHubBuilder().withOAuthToken("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")
                        .build();
                GHOrganization organization = github.getOrganization("SETeam999");

                Map<String, GHRepository> repositories = organization.getRepositories(); //what is the string part

                for (int i = 0; i < repositories.size(); i++) {
                    GHRepository repository = repositories.get(repositories.keySet().iterator().next()); //repositories.keySet().toArray()[i]
                    List<GHPullRequest> pullRequests = repository.getPullRequests(GHIssueState.OPEN);

                    for (GHPullRequest pullRequest : pullRequests) {
                        if (Objects.equals(pullRequest.getMergeableState(), "clean")) {
                            t.noconflict(pullRequest);
                        }
                        if (Objects.equals(pullRequest.getMergeableState(), "dirty")) {
                            t.conflict(pullRequest);
                            if (pullRequest.getMergeable()) {
                                MCR.package_lock_conflict();
                                t.conflict_resolved(pullRequest);
                            }
                        }
                    if(pullRequest.getLabels().equals("Ready to merge")){
                        MCR.automerge(pullRequest);
                        t.merge_in_process(pullRequest);
                    }
                    if(pullRequest.isMerged()){
                        count_merged++;
                    }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }



        }

        public void stopPullRequest(GHPullRequest pullRequest){
            pullRequest.close();
        }

        public void start() {
            // We start our app here
        }

        public static void main(String [] args){
            (new Main()).start();
        }
    }
