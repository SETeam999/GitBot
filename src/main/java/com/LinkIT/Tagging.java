    package com.LinkIT;

    import org.kohsuke.github.GHLabel;
    import org.kohsuke.github.GHPullRequest;

    import java.io.IOException;
    import java.util.Collection;
    import java.util.List;

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

        public void not_mergeable(GHPullRequest pullRequest) throws IOException {
            pullRequest.addLabels("Not mergeable");
        }

        public void permission_to_merge(GHPullRequest pullRequest) throws IOException {
            pullRequest.addLabels("Did not get permission to merge ");
        }

        public void mark_branch(GHPullRequest pullRequest) throws IOException{
            pullRequest.addLabels("Branch Marked"); //how to mark a branch (does not support labels)
        }
        public boolean checkDontMergeTag(GHPullRequest pullRequest){
            Collection<GHLabel> labels =  pullRequest.getLabels();
            for(GHLabel label: labels){
                if (label.toString().equals("Dont merge")){
                    return true;
                }
            }
            return false;
        }
    }