package com.LinkIT;

import org.jetbrains.annotations.NotNull;
import org.kohsuke.github.GHLabel;
import org.kohsuke.github.GHPullRequest;

import java.util.Collection;

public class CheckTags {

    public boolean checkDontMergeTag(@NotNull GHPullRequest pullRequest){
        Collection<GHLabel> labels =  pullRequest.getLabels();
        for(GHLabel label: labels){
            if (label.toString().equals("dont merge")){
                return true;
            }
        }
        return false;
    }

    public boolean checkReadytoMergeTag(@NotNull GHPullRequest pullRequest){
        Collection<GHLabel> labels =  pullRequest.getLabels();
        for(GHLabel label: labels){
            if (label.toString().equals("ready to merge")){
                return true;
            }
        }
        return false;
    }

    public boolean checkStopMergeTag(@NotNull GHPullRequest pullRequest){ //left for the company for later use
        Collection<GHLabel> labels =  pullRequest.getLabels();
        for(GHLabel label: labels){
            if (label.toString().equals("stop merge")){
                return true;
            }
        }
        return false;
    }
}
