package com.LinkIT;

import org.kohsuke.github.GHBranch;

import java.nio.file.Files;
import java.nio.file.Path;

public class LocalRepositoryController {
    private static String REPOSITORIES_BASE_DIR = "./repositories";
    private String repositoryName;
    private String repositoryRemoteUrl;

    boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
    ProcessBuilder processBuilder = new ProcessBuilder();

    public LocalRepositoryController(String repositoryName, String repositoryRemoteUrl) {
        this.repositoryName = repositoryName;
        this.repositoryRemoteUrl = repositoryRemoteUrl;
    }

    private void checkoutBranch() {

    }

    // public static void main(String[] argv) {
    //   LocalRepositoryController controller = new LocalRepositoryController("something", "https://github.com/SETeam999/something.git");
    //     controller.pullRepository();
    // }

    public void pullRepository(){
        GHBranch branch;
        // Make sure that the folder to pull the repository into exists
        System.out.println(Files.exists(Path.of(REPOSITORIES_BASE_DIR)));
        if(Files.exists("git cat-file -e "+ branch :<filename>)){

        }

        // Pull the code into the folder
    }
    public void pushRepository(){
        if(isWindows){
               processBuilder.command("cmd.exe", "/c", "git add .");
               processBuilder.command("cmd.exe", "/c", "git commit -m 'looking for package-lock merge'"); //do these commands which fix package-lock conflict
               processBuilder.command("cmd.exe", "/c", "git push");
        }else{
                processBuilder.command("sh", "-c", "git add .");
                processBuilder.command("sh", "-c", "git commit -m 'looking for package-lock merge'"); //do these commands which fix package-lock conflict
                processBuilder.command("cmd.exe", "/c", "git push");
            }
        }

    public String[] getMergeConflictFiles(String branchName){
        // Checkout the branch
        git checkout master

        // Try to merge it with master
        git pull origin master

        // Check if the merge succeeded

        // If success, return an empty list

        // If merging failed, get the list of files that have conflicts
        return new String[0];
    }
}
