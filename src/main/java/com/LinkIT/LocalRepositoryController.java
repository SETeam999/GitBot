package com.LinkIT;

import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.io.File;

public class LocalRepositoryController {
    private static String REPOSITORIES_BASE_DIR = "./repositories";
    private String repositoryName;
    private String repositoryRemoteUrl;
    MergeMasterWithCurrentBranch mergeMasterWithCurrentBranch = new MergeMasterWithCurrentBranch();


    boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
    ProcessBuilder processBuilder = new ProcessBuilder();

    public LocalRepositoryController(String repositoryName, String repositoryRemoteUrl) {
        this.repositoryName = repositoryName;
        this.repositoryRemoteUrl = repositoryRemoteUrl;
    }

    private void checkoutBranch(GHBranch branch) throws IOException {
        if (isWindows) {
            processBuilder.command("cmd.exe", "/c", "git checkout " + branch.getName()).start(); //find and add branch name
            // or processBuilder.command("cmd.exe", "/c", "git merge new-branch");
        } else {
            processBuilder.command("sh", "-c", "git checkout " + branch.getName()).start(); //find and add branch name
            //or processBuilder.command("sh", "-c", "git merge new-branch");
        }

    }

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

    public void pushRepository() throws IOException {
        if(isWindows){
               processBuilder.command("cmd.exe", "/c", "git add .").start();
               processBuilder.command("cmd.exe", "/c", "git commit -m 'looking for package-lock merge'").start(); //do these commands which fix package-lock conflict
               processBuilder.command("cmd.exe", "/c", "git push").start();
        }else{
                processBuilder.command("sh", "-c", "git add .").start();
                processBuilder.command("sh", "-c", "git commit -m 'looking for package-lock merge'").start(); //do these commands which fix package-lock conflict
                processBuilder.command("cmd.exe", "/c", "git push").start();
            }
        }

    public String[] getMergeConflictFiles(GHBranch branch, GHRepository r) throws IOException {
        // Checkout the branch
        checkoutBranch(branch);

        // Try to merge it with master
            mergeMasterWithCurrentBranch.MergeMasterWithCurrentBranch(branch);

        // Check if the merge succeeded

        // If success, return an empty list

        // If merging failed, get the list of files that have conflicts

        return new String[0];
    }
}