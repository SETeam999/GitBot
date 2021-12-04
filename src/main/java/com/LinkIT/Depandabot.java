package com.LinkIT;

import org.kohsuke.github.GHMeta;
import org.kohsuke.github.GHPullRequest;

import java.io.IOException;

public class Depandabot {
    MergeConflictResolver merge = new MergeConflictResolver();

    public boolean isDependabot(GHPullRequest pullRequest) throws IOException {
        if(pullRequest.getUser().toString().equals("Dependabot")){
            return true;
        }
        return false;
    }

    public void mergingDependabot(GHPullRequest pullRequest) throws IOException {
        if(isDependabot(pullRequest)) {
            System.out.println("Dependabot detected");
            merge.automerge(pullRequest);
        }
    }
}
