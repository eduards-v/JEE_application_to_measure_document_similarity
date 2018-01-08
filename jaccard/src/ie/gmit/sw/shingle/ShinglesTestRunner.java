package ie.gmit.sw.shingle;

import ie.gmit.sw.hasher.MinHasher;

import java.io.*;
import java.util.HashSet;
import java.util.Random;
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
        MinHasher minHasher;

        Set<Integer> min_hashed_shingles;




        try (InputStream in = new FileInputStream(f)){

            shingleBuilder = new ShingleBuilder(7, in);

            shingleSet = shingleBuilder.getShingleStrSet();
            shingleHashSet = shingleBuilder.getShingleSet();


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("___________ Words Shingles ______________");
        shingleSet.forEach(System.out::println);
        System.out.println();

        minHasher = new MinHasher(shingleHashSet, randomHashes(100));

        minHasher.doMinHashing();
        min_hashed_shingles = minHasher.getMinDocHashes();

        System.out.println("_____________Min Hashed Shingles_____________");
        System.out.println("Size: " + min_hashed_shingles.size());
        System.out.println();
        min_hashed_shingles.forEach(System.out::println);


    }


    private static int[] randomHashes(int size){
        int[] randoms = new int[size];
        Random r = new Random();

        System.out.println("Randoms:" +  randoms.length);
        for(int i = 0; i < randoms.length; i++){
            randoms[i] = r.nextInt();
            System.out.println("Randoms: [ " + randoms[i] + " ]");
        }
        System.out.println();
        return randoms;
    }
}
