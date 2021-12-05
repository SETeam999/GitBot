package com.LinkIT;

import org.kohsuke.github.GHBranch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.io.File;

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

    private void runCommand(String workingDirectory, String ...args) throws IOException {
        File WorkingDirectoryFile = new File(workingDirectory);
        processBuilder
                .command(args)
                .directory(WorkingDirectoryFile)
                .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                .redirectInput(ProcessBuilder.Redirect.INHERIT)
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .start();
    }

    public void pullRepository(){
        try {
            String workingDirectory = REPOSITORIES_BASE_DIR + "/" + repositoryName;
            if (!Files.exists(Path.of(workingDirectory))) {
                // Make sure that the folder to pull the repository into exists
                Files.createDirectory(Path.of(REPOSITORIES_BASE_DIR));
                Files.createDirectory(Path.of(workingDirectory));
            }
            if (isWindows) {
                runCommand(workingDirectory, "cmd.exe", "/c", "git fetch");
                runCommand(workingDirectory, "cmd.exe", "/c", "git checkout HEAD " + REPOSITORIES_BASE_DIR);
            } else {
                runCommand(workingDirectory, "sh", "-c", "git fetch");
                runCommand(workingDirectory, "sh", "-c", "git checkout HEAD " + REPOSITORIES_BASE_DIR);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }

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
        // Try to merge it with master
        if(isWindows){
            processBuilder.command("cmd.exe", "/c", "git checkout " + branchName); //find and add branch name
            processBuilder.command("cmd.exe", "/c", "git pull origin master");
            // or processBuilder.command("cmd.exe", "/c", "git merge new-branch");
        }else{
            processBuilder.command("sh", "-c", "git checkout " + branchName); //find and add branch name
            processBuilder.command("sh", "-c", "git pull origin master");
            //or processBuilder.command("sh", "-c", "git merge new-branch");
        }

        // Check if the merge succeeded

        // If success, return an empty list

        // If merging failed, get the list of files that have conflicts
        return new String[0];
    }
}
