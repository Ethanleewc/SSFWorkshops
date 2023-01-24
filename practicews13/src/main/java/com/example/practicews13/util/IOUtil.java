package com.example.practicews13.util;

import java.io.File;

public class IOUtil {

    //Method to create new file directory during startup, to be used on Main.
    public static void createDir(String path){
        File dir = new File(path);
        dir.mkdir();
    }
    
}
