package com.threadproject.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class Scanning {

    ExecutorService executorService;

    //PostConstruct annotation'u, herhangi bir initalization gerçekleştirmek için bağımlılık
    // enjeksiyonu yapıldıktan sonra çalıştırılması gereken bir yöntemde kullanılır.
    @PostConstruct
    public void initExecutorService(){
        System.out.println("initExecutorService");
        executorService = Executors.newCachedThreadPool();
    }


    @Autowired
    private FileOperations fileOperations;

    public void doScanning() throws ExecutionException, InterruptedException, FileNotFoundException {

        System.out.println(executorService);

        String folderPath = "/home/furkanyilmaz/Masaüstü/Files";
        File file = new File(folderPath);
        File[] listOfFiles = {};
        if (!file.isFile()) {
            listOfFiles = file.listFiles();
        }

        List<FileReader> list = new ArrayList<>();
        List<Future<Map<String, Integer>>> result = new ArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            list.add(new FileReader(listOfFiles[i].getAbsolutePath()));
        }
        //Set<Thread> threadSet1 = Thread.getAllStackTraces().keySet(); //31 thread var
        //callable ile task submit etme
        for (int i = 0; i < list.size(); i++) {
            //submit() hem Runnable hem de Callable taskları kabul edebilir, ancak execute() yalnızca Runnable taskları kabul edebilir.
            //submit() metodunun dönüş türü bir Future objesidir, ancak execute() yönteminin dönüş türü yoktur(void).
            result.add(executorService.submit(list.get(i)));
            //Result'lar result içine alındı.
            //System.out.println(" Task : " + i + " durum : " + result.get(i)); //Tasklar tamamlanmadı.
        }
        //Set<Thread> threadSet2 = Thread.getAllStackTraces().keySet(); //38 thread var.7 thread 7 dosyaya atanan threadler
        //"FutureTask" class'ı "Future" interfacesini implement eder.Dolayısıyla "Future" tipinden bir referans "FutureTask" nesnesini gösterebilir.
        //submit eklediğimiz her bir "Runnable" için bir "FutureTask" döndürür.
        //submit hesaplama sonucunu döndürür.execute ise birşey döndürmez.
        //result->0->outcome : outcome hashmap'tir ve dosya adı ile hesaplanan değeri döner.
        //result içerisindeki FutureTask'lar yardımıyla sonuca ulaşabiliyoruz.
        //System.out.println(result); //result içerisinde FutureTask nesneleri var.

        fileOperations.createFolder();
        String filePath = fileOperations.createFile();

        //Bu kısıma gelindiğinde tasklar tamamlanmış durumdadır.
        List<Map<String, Integer>> list2= new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            Map<String, Integer> temp = result.get(i).get(); //Futuretask'ın get() yöntemi görevin tamamlanmasını bekler ve ardından çıktı nesnesini döndürür.
            //System.out.println(" Task : " + i + " durum : " + result.get(i)); //Tasklar tamamlandı.
            list2.add(temp);
            System.out.println(temp);

        }
        fileOperations.write(list2.toString(), filePath);

        System.out.println();
    }
}
