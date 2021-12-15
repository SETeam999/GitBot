package com.LinkIT;

import org.jetbrains.annotations.NotNull;
import org.kohsuke.github.GHPullRequest;

import java.io.IOException;

public class Notification {

    void ThumbUp(@NotNull GHPullRequest ghpullRequest){
        try {
            ghpullRequest.comment("\uD83D\uDC4D Conflict resolved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void Smiley(@NotNull GHPullRequest ghpullRequest){
        try {
            ghpullRequest.comment("\uD83D\uDE0A No conflict found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void SadFace(@NotNull GHPullRequest ghpullRequest){
        try {
            ghpullRequest.comment("\uD83D\uDE1E not mergeable");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void Processing(@NotNull GHPullRequest ghpullRequest){
        try {
            ghpullRequest.comment("GITBot processing...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void not_packagelock(@NotNull GHPullRequest ghpullRequest){
        try {
            ghpullRequest.comment("Cannot fix conflicts that are not package-lock yet! Please have someone fix it");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void Inform_dependabot_updateisgood(@NotNull GHPullRequest ghpullRequest){
        try {
            ghpullRequest.comment("@dependabot squash and merge");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
