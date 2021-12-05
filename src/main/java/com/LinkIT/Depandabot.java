package com.LinkIT;

import org.kohsuke.github.GHMeta;
import org.kohsuke.github.GHPullRequest;

import java.io.IOException;

public class Depandabot {

    Notification notification = new Notification();
    MergeConflictResolver merge = new MergeConflictResolver();

    public boolean isDependabot(GHPullRequest pullRequest) throws IOException {
        if(pullRequest.getUser().getName().equals("dependabot")){
            return true;
        }
        return false;
    }

    public void mergingDependabot(GHPullRequest pullRequest) throws IOException {
        if(isDependabot(pullRequest)) {
            notification.Inform_dependabot_updateisgood(pullRequest);
        }
    }
}
