package com.LinkIT;

import org.jetbrains.annotations.NotNull;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.*;

public class LocalRepositoryController {

    public String[] getBranchesNames(GHPullRequest pullRequest) throws IOException { //not needed now, but might be needed in the future
        Map<String, GHBranch> branches = pullRequest.getRepository().getBranches();
        String[] branchNames = new String[branches.size()];

        for (int i = 0; i < branches.size(); i++) {
            for (String branchesKeySets : branches.keySet()) {
                GHBranch branch = branches.get(branchesKeySets);
                for (int j = 0; j < branchNames.length; j++) {
                    branchNames[j] = branch.getName();
                }
            }
        }
        return branchNames;
    }

    public boolean getMergeConflictFiles(@NotNull GHPullRequest pullRequest) throws IOException {
        PagedIterable<GHCommit> iterable = pullRequest.getRepository().listCommits();
        GHCommit[] commits = iterable.toArray();
        for (GHCommit currentCommit : commits) {
            for (int j = 0; j < currentCommit.getFiles().size(); j++) {
                if (currentCommit.getFiles().get(j).getFileName() == "package-lock.json") {
                    return true;
                }
            }
        }
        return false;
    }
}