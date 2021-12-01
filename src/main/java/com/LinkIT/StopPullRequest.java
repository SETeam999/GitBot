package com.LinkIT;

import org.kohsuke.github.GHPullRequest;

public class StopPullRequest {

    public void stopPullRequest(GHPullRequest pullRequest){
        pullRequest.close(); //how to stop pullrequest
    }
}
