package com.LinkIT;

import org.jetbrains.annotations.NotNull;
import org.kohsuke.github.GHPullRequest;

import java.io.IOException;

public class Depandabot {

    Notification notification = new Notification();

    public boolean isDependabot(@NotNull GHPullRequest pullRequest) throws IOException {
        return pullRequest.getUser().getName().equals("dependabot");
    }

    public void mergingDependabot(GHPullRequest pullRequest) throws IOException {
        if(isDependabot(pullRequest)) {
            notification.Inform_dependabot_updateisgood(pullRequest);
        }
    }
}
