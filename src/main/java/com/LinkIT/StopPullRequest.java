package com.LinkIT;

import org.kohsuke.github.GHPullRequest;

import java.io.IOException;

public class StopPullRequest {

    public void stopPullRequest(GHPullRequest pullRequest) throws IOException {
        pullRequest.close(); //how to stop pullrequest
    }
}
