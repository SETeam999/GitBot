package com.LinkIT;

import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHBranchProtection;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.util.Optional;

public class MergeMasterWithCurrentBranch {

    private GHRepository current;
    private int branch_merge_counter = 0;

    void MergeMasterWithCurrentBranch(GHBranch ghBranch) throws IOException {
        ghBranch.disableProtection();
        ghBranch.merge(getMasterBranch(), "Merging Master Branch with the current Branch");
        branch_merge_counter++;
    }

    public int getBranchMergeCounter(){
        return branch_merge_counter;
    }

    private GHBranch getMasterBranch() throws IOException {
        return current.getBranch("master");
    }

}
