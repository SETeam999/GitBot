package com.LinkIT;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHPullRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Notification {

    void ThumbUp(GHPullRequest ghpullRequest){
        try {
            ghpullRequest.comment("\uD83D\uDC4D");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void SadFace(GHPullRequest ghpullRequest){
        try {
            ghpullRequest.comment("\uD83D\uDE1E");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void Processing(GHPullRequest ghpullRequest){
        try {
            ghpullRequest.comment("Processing...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void Inform_dependabot_updateisgood(GHPullRequest ghpullRequest){
        try {
            ghpullRequest.comment("@dependabot squash and merge");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
