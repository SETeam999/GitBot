package com.LinkIT;

import org.kohsuke.github.GHLabel;
import org.kohsuke.github.GHPullRequest;

import java.util.Collection;

public class CheckTags {

    public boolean checkDontMergeTag(GHPullRequest pullRequest){
        Collection<GHLabel> labels =  pullRequest.getLabels();
        for(GHLabel label: labels){
            if (label.toString().equals("Dont merge")){
                return true;
            }
        }
        return false;
    }

    public boolean checkReadytoMergeTag(GHPullRequest pullRequest){
        Collection<GHLabel> labels =  pullRequest.getLabels();
        for(GHLabel label: labels){
            if (label.toString().equals("Ready to merge")){
                return true;
            }
        }
        return false;
    }

    public boolean checkStopMergeTag(GHPullRequest pullRequest){
        Collection<GHLabel> labels =  pullRequest.getLabels();
        for(GHLabel label: labels){
            if (label.toString().equals("Stop merge")){
                return true;
            }
        }
        return false;
    }
}
