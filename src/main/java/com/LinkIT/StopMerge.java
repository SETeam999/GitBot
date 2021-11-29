package com.LinkIT;

public class StopMerge {

    ProcessBuilder processBuilder = new ProcessBuilder();
    boolean isWindows = System.getProperty("os.name")
            .toLowerCase().startsWith("windows");

    public void reset_merge(){
        if(isWindows){
            processBuilder.command("cmd.exe", "/c", "git reset --hard HEAD");
        }else{
            processBuilder.command("sh", "-c", "git reset --hard HEAD");
        }
    }

    public void abort_merge(){
        if(isWindows){
            processBuilder.command("cmd.exe", "/c", "git merge --abort");
        }else{
            processBuilder.command("sh", "-c", "git merge --abort");
        }
    }
}
