package com.threadproject.demo;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class FileOperations {
    public File file;
    String directoryPath = "/home/furkanyilmaz/Masaüstü/ResultDirectory";
    String filePath = directoryPath + "/Result";

    public void createFolder() {
        File dir = new File(directoryPath);
        dir.mkdir(); // Klasör oluşturuluyor
    }

    public String createFile() {
        try {
            file = new File(filePath);
            file.createNewFile(); // Dosya oluşturuluyor
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    public void write(String text, String filePath) throws FileNotFoundException {
        File file = new File(filePath); // hangi dosya üzerinde işlem yapacağımızı seçiyoruz.

        String temp = text.toString();
        String[] arr = temp.split(",");

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
