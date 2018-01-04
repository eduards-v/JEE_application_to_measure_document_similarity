package ie.gmit.sw.shingle;

import java.io.*;
import java.util.HashSet;
import java.util.Set;


public class ShinglesTestRunner {

    public static void main(String[] args) {


        //System.out.println(System.getProperty("user.dir"));

        // system safe path declaration to a file
        File dir = new File("files", "inner");
        File f = new File(dir, "hello.txt");

        ShingleBuilder shingleBuilder;
        Set<String> shingleSet = new HashSet<>();
        Set<Integer> shingleHashSet = new HashSet<>();

        try (InputStream in = new FileInputStream(f)){

            shingleBuilder = new ShingleBuilder(7, in);

            shingleSet = shingleBuilder.getShingleStrSet();
            shingleHashSet = shingleBuilder.getShingleSet();


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        shingleHashSet.forEach(System.out::println);
    }
}
