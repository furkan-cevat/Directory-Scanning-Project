package com.threadproject.demo;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

//Runnuable ve Callable bir görevi farklı bir Thread ile çalıştırmak için kullanılan iki interface'dir.
//Runnable'ın run() yöntemi geri bir sonuç döndürmezken, Callable'nın call() yöntemi sonuç döndürür.
//Ek olarak call() yöntemi Exception oluşturarak işlemden çıkabilir. Runnable'nın run yöntemi exception fırlatamaz.
//Callable ile bir Thread yaratılamaz. Thread ancak Runnable ile yaratılabilir. Callable ise ExecutorService sınıfları ile çalıştırılabilir.

public class FileReader implements Callable<Map<String, Integer>> {
    private FileInputStream fstream;
    private InputStreamReader isr;
    private BufferedReader br;
    private final String fileName;

    FileReader(String fileName){
        this.fileName=fileName;
    }
    FileInputStream getFileInputStream(String fileName) throws FileNotFoundException {
        if(fstream == null) {
            fstream = new FileInputStream(fileName);
        }
        return fstream;
    }

    InputStreamReader getInputStreamReader(FileInputStream fstream) {
        if(isr == null) {
            isr =  new InputStreamReader(fstream);
        }
        return isr;
    }

    BufferedReader getBufferedReader(InputStreamReader isr) {
        if(br == null) {
            br =  new BufferedReader(isr);
        }
        return br;
    }
    @Override
    public Map<String,Integer> call() throws Exception {
        Map<String,Integer> map = new HashMap<>();
        FileInputStream fstream = getFileInputStream(this.fileName);
        InputStreamReader isr = getInputStreamReader(fstream);
        BufferedReader br = getBufferedReader(isr);

        String strLine;
        int count=0;
        while ((strLine = br.readLine()) != null)   {
            count+=Integer.parseInt(strLine);
        }
        map.put(this.fileName,count);

        fstream.close();
        br.close();
        isr.close();

        return map;
    }
}