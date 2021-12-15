    package com.LinkIT;

    import org.jetbrains.annotations.NotNull;
    import org.kohsuke.github.GHLabel;
    import org.kohsuke.github.GHPullRequest;

    import java.io.IOException;
    import java.util.Collection;

    public class Tagging {
        public void noconflict(@NotNull GHPullRequest pullRequest) throws IOException {
            pullRequest.addLabels("No merge conflict.");
        }

        public void conflict(@NotNull GHPullRequest pullRequest) throws IOException {
            pullRequest.addLabels("Merge conflict found.");
        }

        public void conflict_resolved(@NotNull GHPullRequest pullRequest) throws IOException {
            pullRequest.addLabels("Merge conflict resolved.");
        }

        public void merge_in_process(@NotNull GHPullRequest pullRequest) throws IOException {
            pullRequest.addLabels("Merge in process.");
        }

        public void not_mergeable(@NotNull GHPullRequest pullRequest) throws IOException {
            pullRequest.addLabels("Not mergeable");
        }

        public void is_merged(@NotNull GHPullRequest pullRequest) throws IOException {
            pullRequest.addLabels("Is merged");
        }

        public void nopermission_to_merge(@NotNull GHPullRequest pullRequest) throws IOException {
            pullRequest.addLabels("No merge permission");
        }

        public void mark_branch(@NotNull GHPullRequest pullRequest) throws IOException {
            pullRequest.addLabels("Branch Marked");
        }

        public void delete_tag(@NotNull GHPullRequest pullRequest, String string) throws IOException{
            Collection<GHLabel> labels = pullRequest.getLabels();
            GHLabel [] labelsarray = new GHLabel[labels.size()];
            for (GHLabel ghLabel : labelsarray) {
                if (ghLabel.toString().equals(string)) {
                    ghLabel.delete();
                }
            }
        }
    }