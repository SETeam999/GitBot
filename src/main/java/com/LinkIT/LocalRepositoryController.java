package com.LinkIT;

import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class LocalRepositoryController {
    private static String REPOSITORIES_BASE_DIR = "./repositories";
    private String repositoryName;
    private String repositoryRemoteUrl;


    boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
    ProcessBuilder processBuilder = new ProcessBuilder();


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

    public void getMergeConflictFiles(String [] mergeconflictfiles) throws IOException {
        String workingDirectory = REPOSITORIES_BASE_DIR + "/" + repositoryName;
        mergeconflictfiles = new String[(int) Files.size(Path.of(workingDirectory))]; //getting the maximum amount of files in the repository in case all the files have package lock conflicts
        File myObj = new File("filename.txt");
        Scanner myReader = new Scanner(myObj);
        int i=0;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if(Objects.equals(data, "<<<<<<<<")){
                mergeconflictfiles[i]=myObj.getName();
            }
            i++;
        }
        myReader.close();
    }
}