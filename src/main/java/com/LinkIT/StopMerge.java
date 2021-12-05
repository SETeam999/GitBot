package com.LinkIT;

import java.io.IOException;

public class StopMerge {

    ProcessBuilder processBuilder = new ProcessBuilder();
    boolean isWindows = System.getProperty("os.name")
            .toLowerCase().startsWith("windows");

    public void reset_merge() throws IOException { //not used but implemented anyway in case the company needed to use it
        if(isWindows){
            processBuilder.command("cmd.exe", "/c", "git reset --hard HEAD").start(); //moving branch back to where it was last
        }else{
            processBuilder.command("sh", "-c", "git reset --hard HEAD").start(); //moving branch back to where it was last
        }
    }

    public void abort_merge() throws IOException {
        if(isWindows){
            processBuilder.command("cmd.exe", "/c", "git merge --abort").start(); //abort the merge and try to reconstruct the pre merge state
        }else{
            processBuilder.command("sh", "-c", "git merge --abort").start(); //abort the merge and try to reconstruct the pre merge state
        }
    }
}
