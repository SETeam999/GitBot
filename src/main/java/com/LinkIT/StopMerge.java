package com.LinkIT;

public class StopMerge {

    ProcessBuilder processBuilder = new ProcessBuilder();
    boolean isWindows = System.getProperty("os.name")
            .toLowerCase().startsWith("windows");

    public void reset_merge(){
        if(isWindows){
            processBuilder.command("cmd.exe", "/c", "git reset --hard HEAD"); //moving branch back to where it was last
        }else{
            processBuilder.command("sh", "-c", "git reset --hard HEAD"); //moving branch back to where it was last
        }
    }

    public void abort_merge(){
        if(isWindows){
            processBuilder.command("cmd.exe", "/c", "git merge --abort"); //abort the merge and try to reconstruct the pre merge state
        }else{
            processBuilder.command("sh", "-c", "git merge --abort"); //abort the merge and try to reconstruct the pre merge state
        }
    }
}
