package com.LinkIT;

import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHBranchProtection;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.util.Optional;

public class MergeMasterWithCurrentBranch {
    private GHBranch ghBranch;
    private GHBranchProtection ghBranchProtection;
    private GHCommit ghCommit;
    private GHRepository current;

    void MergeMasterWithCurrentBranch() throws IOException {

        //$ git add –A
        //$ git commit –m "Some commit message"
        //$ git checkout master
        //Switched to branch 'master'
        //$ git merge new-branch

        ghBranch.disableProtection();
        ghBranch.merge(getMasterBranch(), "Merging Master Branch with the current Branch");

    }

//    public String resolveRemoteBranch() {
//        // Let's try use repository branch from .updatebot.yml first
//        GitRepositoryConfig config = repo.getRepositoryDetails();
//        if (config == null) {
//            LOG.warn("The repo has no config!");
//            return "master";
//        }
//        if(!Strings.empty(config.getBranch())) {
//            return config.getBranch();
//        } // Try detect Github repository and use its default branch
//        else if(repo instanceof GithubRepository) {
//            GHRepository ghRepository = GitHubHelpers.getGitHubRepository(this);
//            if (ghRepository != null) {
//                config.setBranch(ghRepository.getDefaultBranch());
//            }
//        }
//        // Fallback to master branch for Git repositories
//        if(Strings.empty(config.getBranch())) {
//            config.setBranch("master");
//        }
//        return config.getBranch();
//    }

    private GHBranch getMasterBranch() throws IOException {
        return current.getBranch("master");
    }

}
