package com.LinkIT;

import org.jetbrains.annotations.NotNull;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

public class MergeMasterWithCurrentBranch {

    private GHRepository current;
    private int branch_merge_counter = 0;

    void MergeMasterWithBranch(@NotNull GHBranch ghBranch) throws IOException {
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
