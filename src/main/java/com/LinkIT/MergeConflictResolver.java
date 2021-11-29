    package com.LinkIT;

    import org.kohsuke.github.GHPullRequest;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.util.function.Consumer;

    public class MergeConflictResolver {

        StopMerge SM;

        ProcessBuilder processBuilder = new ProcessBuilder();
        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");

        public void package_lock_conflict(){
            if(isWindows){
                processBuilder.command("cmd.exe", "/c", "rm package-lock.json");
                processBuilder.command("cmd.exe", "/c", "npm i");
            }else{
                processBuilder.command("sh", "-c", "rm package-lock.json");
                processBuilder.command("sh", "-c", "npm i");
            }
        }

        public void automerge(GHPullRequest pullRequest){
            if(isWindows){
                processBuilder.command("cmd.exe", "/c", "git merge <name>");
            }else{
                processBuilder.command("sh", "-c", "git merge <name>");
            }
            if(pullRequest.getLabels().equals("Stop Merge")){
                SM.abort_merge();
            }
        }

    }
