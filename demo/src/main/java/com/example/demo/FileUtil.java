package com.example.demo;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static void createFileIfNotExists(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
    }
}