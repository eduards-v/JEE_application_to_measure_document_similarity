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
        File f2 = new File(dir, "db_sample.txt");

        //ShingleBuilder shingleBuilder;
        Set<String> shingleSetA = new HashSet<>();
        Set<Integer> shingleHashSetA = new HashSet<>();
        Set<String> shingleSetB = new HashSet<>();
        Set<Integer> shingleHashSetB = new HashSet<>();
        MinHasher minHasher;

        Set<Integer> min_hashed_shingles_A;
        Set<Integer> min_hashed_shingles_B;




        try (InputStream in = new FileInputStream(f)){

            ShingleBuilder shingleBuilder = new ShingleBuilder(7, in);

            shingleSetA = shingleBuilder.getShingleStrSet();
            shingleHashSetA = shingleBuilder.getShingleSet();


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(InputStream in = new FileInputStream(f2)){
            ShingleBuilder shingleBuilder = new ShingleBuilder(7, in);
            shingleSetB = shingleBuilder.getShingleStrSet();
            shingleHashSetB = shingleBuilder.getShingleSet();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        System.out.println("___________ Words Shingles ______________");
//        shingleSetA.forEach(System.out::println);
//        System.out.println();

        minHasher = new MinHasher(shingleHashSetA, randomHashes(200));

        minHasher.doMinHashing();
        min_hashed_shingles_A = minHasher.getMinDocHashes();

        minHasher.setDocHashes(shingleHashSetB);
        minHasher.doMinHashing();
        min_hashed_shingles_B = minHasher.getMinDocHashes();

//        System.out.println("_____________Min Hashed Shingles A_____________");
//        System.out.println("Size: " + min_hashed_shingles_A.size());
//        System.out.println();
//        min_hashed_shingles_A.forEach(System.out::println);

        System.out.println("_____________Min Hashed Shingles Similarity_____________");
        boolean similar = shingleSetA.retainAll(shingleSetB);

        System.out.println("Are docs similar: " + similar);
        System.out.println(shingleSetA.size());
        System.out.println(shingleSetB.size());
        shingleSetA.forEach(System.out::println);
        System.out.println(shingleSetA.size());


//        System.out.println("_____________Set A after retention_____________");
//        System.out.println("A: " + min_hashed_shingles_A.size());
//        System.out.println("B: " + min_hashed_shingles_B.size());
//        min_hashed_shingles_B.forEach(System.out::println);


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
