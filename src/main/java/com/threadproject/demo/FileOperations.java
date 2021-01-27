package com.threadproject.demo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class FileOperations {
    private File file;
    private File outputDirectory;
    private String directoryPath = "/home/furkanyilmaz/Masaüstü/ResultDirectory";

    @PostConstruct
    public void outputFileCreate(){
        createOutputFolder();
        createOutputFile();
    }

    public void createOutputFile(){
        if(null == file) {
            file = new File(directoryPath + "/Result");
        }
    }
    public File getOutputFile(){
        return file;
    }

    public void createOutputFolder() {
        if(null == outputDirectory) {
            outputDirectory = new File(directoryPath);
            outputDirectory.mkdir();
        }
    }

    public void write(String text, String filePath) {
        file = getOutputFile(); // hangi dosya üzerinde işlem yapacağımızı seçiyoruz.
        String[] arr = text.split(",");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            for (String a : arr){
                fos.write(a.getBytes());
                fos.write("\n".getBytes(StandardCharsets.UTF_8));
            }
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
