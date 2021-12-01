    package com.LinkIT;

    import org.kohsuke.github.GHPullRequest;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.util.Objects;
    import java.util.function.Consumer;

    public class MergeConflictResolver {

        StopMerge SM;
        ProcessBuilder processBuilder = new ProcessBuilder();
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows"); //windows has different commands

        public void package_lock_conflict(){
            if(isWindows){
                processBuilder.command("cmd.exe", "/c", "rm package-lock.json");
                processBuilder.command("cmd.exe", "/c", "npm i"); //do these commands which fix package-lock conflict
            }else{
                processBuilder.command("sh", "-c", "rm package-lock.json");
                processBuilder.command("sh", "-c", "npm i"); //do these commands which fix package-lock conflict
            }
        }

        public void automerge(GHPullRequest pullRequest){
            if(isWindows){
                processBuilder.command("cmd.exe", "/c", "git merge <name>"); //auto merge, we need the name of the branch
            }else{
                processBuilder.command("sh", "-c", "git merge <name>"); //auto merge, we need the name of the branch
            }
            if(Objects.equals(pullRequest.getLabels().toString(), "Stop Merge")){
                SM.abort_merge(); //stop merge
            }
        }

    }
