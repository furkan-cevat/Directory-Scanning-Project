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
    private FileOutputStream fos;
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

    public FileOutputStream getFileOutputStream() throws FileNotFoundException {
        if(null == fos) {
            fos = new FileOutputStream(file);
        }
        return fos;
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
            FileOutputStream fos = getFileOutputStream();
            for (String a : arr){
                fos.write(a.getBytes());
                fos.write("\n".getBytes(StandardCharsets.UTF_8));
            }
            fos.write("\n".getBytes(StandardCharsets.UTF_8));
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
