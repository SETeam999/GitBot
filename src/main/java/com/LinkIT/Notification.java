package com.LinkIT;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHPullRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Notification {

    void ThumbUpPullRequest(GHPullRequest ghpullRequest){
        try {
            ghpullRequest.comment("\uD83D\uDE00");
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

}
