    package com.LinkIT;

    import org.kohsuke.github.GHPullRequest;

    import java.io.*;

    public class MergeConflictResolver {

        ProcessBuilder processBuilder = new ProcessBuilder();
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

        public void package_lock_conflict_resolver() throws IOException {
            if(isWindows){
                processBuilder.command("cmd.exe", "/c", "rm package-lock.json").start();
                processBuilder.command("cmd.exe", "/c", "npm i").start(); //do these commands which fix package-lock conflict
            }else{
                processBuilder.command("sh", "-c", "rm package-lock.json").start();
                processBuilder.command("sh", "-c", "npm i").start(); //do these commands which fix package-lock conflict
            }
        }

        public void automerge(GHPullRequest pullRequest) throws IOException { //kept just in case
            if(isWindows){
                processBuilder.command("cmd.exe", "/c", "git add .").start();
                processBuilder.command("cmd.exe", "/c", "git commit -m 'bot merging branch to master'").start();
                processBuilder.command("cmd.exe", "/c", "git checkout master").start();
                processBuilder.command("cmd.exe", "/c", "git merge new-branch").start(); //find and add branch
            }else{
                processBuilder.command("sh", "-c", "git add .").start();
                processBuilder.command("sh", "-c", "git commit -m 'bot merging branch to master'").start();
                processBuilder.command("sh", "-c", "git checkout master").start();
                processBuilder.command("sh", "-c", "git merge new-branch").start(); //find and add branch
            }

//            if (checkTags.checkStopMergeTag(pullRequest)){
//                SM.abort_merge();
//            }
        }
    }

