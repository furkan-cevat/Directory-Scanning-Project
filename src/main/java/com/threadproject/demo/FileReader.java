package com.threadproject.demo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

//Runnuable ve Callable bir görevi farklı bir Thread ile çalıştırmak için kullanılan iki interface'dir.
//Runnable'ın run() yöntemi geri bir sonuç döndürmezken, Callable'nın call() yöntemi sonuç döndürür.
//Ek olarak call() yöntemi Exception oluşturarak işlemden çıkabilir. Runnable'nın run yöntemi exception fırlatamaz.
//Callable ile bir Thread yaratılamaz. Thread ancak Runnable ile yaratılabilir. Callable ise ExecutorService sınıfları ile çalıştırılabilir.
public class FileReader implements Callable<Map<String, Integer>> {

    String fileName;

    FileReader(String fileName){
        this.fileName=fileName;
    }
    @Override
    public Map<String,Integer> call() throws Exception {

        Map<String,Integer> map = new HashMap<String,Integer>();
        FileInputStream fstream = new FileInputStream(this.fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        int count=0;
        while ((strLine = br.readLine()) != null)   {
            count+=Integer.parseInt(strLine);
        }
        map.put(this.fileName,count);

        fstream.close();

        return map;
    }
}