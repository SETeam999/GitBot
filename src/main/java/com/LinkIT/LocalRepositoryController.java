package com.LinkIT;

public class LocalRepositoryController {
    private static String REPOSITORIES_BASE_DIR = "./repositories";
    private String repositoryName;
    private String repositoryRemoteUrl;

    public LocalRepositoryController(String repositoryName, String repositoryRemoteUrl) {
        this.repositoryName = repositoryName;
        this.repositoryRemoteUrl = repositoryRemoteUrl;
    }

    private void checkoutBranch() {

    }

    public void pullRepository(){
        // Make sure that the folder to pull the repository into exists

        // Pull the code into the folder
    }
    public void pushRepository(){
        // Push the repository into github
    }

    public String[] getMergeConflictFiles(String branchName){
        // Checkout the branch

        // Try to merge it with master

        // Check if the merge succeeded

        // If success, return an empty list

        // If merging failed, get the list of files that have conflicts
        return new String[0];
    }
}
