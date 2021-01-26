package com.threadproject.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;


@SpringBootApplication
@EnableScheduling
public class DemoApplication {

    @Autowired
    private Scanning scanning;

    @Scheduled(fixedDelayString = "60000") //Dakikada bir içindekini çalıştırıyor
    public void ScheduledFixedRate() throws ExecutionException, InterruptedException, FileNotFoundException {
        scanning.doScanning();
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SpringApplication.run(DemoApplication.class, args);
    }

<<<<<<< HEAD
=======
    public void scanning() throws ExecutionException, InterruptedException, FileNotFoundException {


        String folderPath = "/home/furkanyilmaz/Masaüstü/Files";
        File file = new File(folderPath);
        File[] listOfFiles = {};
        if (!file.isFile()) {
            listOfFiles = file.listFiles();
        }
        ExecutorService executorService = Executors.newFixedThreadPool(listOfFiles.length);
        List<FileReader> list = new ArrayList<>();
        List<Future<Map<String, Integer>>> result = new ArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            list.add(new FileReader(listOfFiles[i].getAbsolutePath()));
        }

        //callable ile task submit etme
        for (int i = 0; i < list.size(); i++) {
            //submit() hem Runnable hem de Callable taskları kabul edebilir, ancak execute() yalnızca Runnable taskları kabul edebilir.
            //submit() metodunun dönüş türü bir Future objesidir, ancak execute() yönteminin dönüş türü yoktur(void).
            result.add(executorService.submit(list.get(i)));
            //Result'lar result içine alındı.
            //System.out.println(" Task : " + i + " durum : " + result.get(i)); //Tasklar tamamlanmadı.
        }
        //"FutureTask" class'ı "Future" interfacesini implement eder.Dolayısıyla "Future" tipinden bir referans "FutureTask" nesnesini gösterebilir.
        //submit eklediğimiz her bir "Runnable" için bir "FutureTask" döndürür.
        //submit hesaplama sonucunu döndürür.execute ise birşey döndürmez.
        //result->0->outcome : outcome hashmap'tir ve dosya adı ile hesaplanan değeri döner.
        //result içerisindeki FutureTask'lar yardımıyla sonuca ulaşabiliyoruz.
        //System.out.println(result); //result içerisinde FutureTask nesneleri var.

        createFolder();
        String filePath = createFile();

        //Bu kısıma gelindiğinde tasklar tamamlanmış durumdadır.
        List<Map<String, Integer>> list2= new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            Map<String, Integer> temp = result.get(i).get(); //Futuretask'ın get() yöntemi görevin tamamlanmasını bekler ve ardından çıktı nesnesini döndürür.
            //System.out.println(" Task : " + i + " durum : " + result.get(i)); //Tasklar tamamlandı.
            list2.add(temp);
            System.out.println(temp);

        }
        write(list2.toString(), filePath);



        System.out.println();
        executorService.shutdown();
    }

    String directoryPath = "/home/furkanyilmaz/Masaüstü/ResultDirectory";
    String filePath = directoryPath + "/Result";

    private void createFolder() {

        File dir = new File(directoryPath);
        dir.mkdir(); // Klasör oluşturuluyor
    }

    private String createFile() {
        try {
            file = new File(filePath);
            file.createNewFile(); // Dosya oluşturuluyor
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    private void write(String text, String filePath) throws FileNotFoundException {
        File file = new File(filePath); // hangi dosya üzerinde işlem yapacağımızı seçiyoruz.
>>>>>>> 20ab9bd8dab6eb9eae9abe0d688137f32c74880a


}


