    package com.LinkIT;

    import org.kohsuke.github.GHBranch;
    import org.kohsuke.github.GHPullRequest;

    import java.io.IOException;

    public class Tagging {
        public void noconflict(GHPullRequest pullRequest) throws IOException {
            pullRequest.addLabels("No merge conflict.");
        }

        public void conflict(GHPullRequest pullRequest) throws IOException {
            pullRequest.addLabels("Merge conflict found.");
        }

        public void conflict_resolved(GHPullRequest pullRequest) throws IOException {
            pullRequest.addLabels("Merge conflict resolved.");
        }

        public void merge_in_process(GHPullRequest pullRequest) throws IOException {
            pullRequest.addLabels("Merge in process.");
        }

        public void mark_branch(GHBranch branch) throws IOException{
            branch.("Branch Marked"); //how to mark a branch (does not support labels)
        }
    }


