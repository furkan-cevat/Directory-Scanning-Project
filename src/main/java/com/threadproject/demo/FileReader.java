package com.threadproject.demo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

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