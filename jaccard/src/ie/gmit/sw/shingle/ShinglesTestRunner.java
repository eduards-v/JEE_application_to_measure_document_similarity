package ie.gmit.sw.shingle;

import ie.gmit.sw.hasher.MinHasher;

import java.io.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


public class ShinglesTestRunner {

    public static void main(String[] args) {


        //System.out.println(System.getProperty("user.dir"));

        // system safe path declaration to a file
        File dir = new File("files", "inner");
        File f = new File(dir, "upload.txt");
        File f2 = new File(dir, "resource.txt");

        //ShingleBuilder shingleBuilder;
        Set<String> shingleSetA = new TreeSet<>();
        Set<Integer> shingleHashSetA = new TreeSet<>();
        Set<String> shingleSetB = new TreeSet<>();
        Set<Integer> shingleHashSetB = new TreeSet<>();
        MinHasher minHasher;

        Set<Integer> min_hashed_shingles_A;
        Set<Integer> min_hashed_shingles_B;




        try (InputStream in = new FileInputStream(f)){

            ShingleBuilder shingleBuilder = new ShingleBuilder(5, in);

            shingleSetA = shingleBuilder.getShingleStrSet();
            shingleHashSetA = shingleBuilder.getShingleSet();


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(InputStream in = new FileInputStream(f2)){
            ShingleBuilder shingleBuilder = new ShingleBuilder(5, in);
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

        int [] random_hashes = randomHashes(200);

        minHasher = new MinHasher(shingleHashSetA, random_hashes);

        minHasher.doMinHashing();
        min_hashed_shingles_A = minHasher.getMinDocHashes();

        minHasher = new MinHasher(shingleHashSetB, random_hashes);
        minHasher.doMinHashing();
        min_hashed_shingles_B = minHasher.getMinDocHashes();

//        System.out.println("_____________Min Hashed Shingles A_____________");
//        System.out.println("Size: " + min_hashed_shingles_A.size());
//        System.out.println();
//        min_hashed_shingles_A.forEach(System.out::println);


        System.out.println("_____________Min Hashed Shingles Similarity_____________");
        System.out.println("String set A size: "+shingleSetA.size());
        System.out.println("String set B size: "+shingleSetB.size());
        System.out.println("Hash set A size before retention: "+min_hashed_shingles_A.size());
        System.out.println("Hash set B size before retention: "+min_hashed_shingles_B.size());

        shingleSetA.retainAll(shingleSetB);
        min_hashed_shingles_A.retainAll(min_hashed_shingles_B);



        System.out.println("_____________Sets after retention_____________");
        System.out.println("String Set A: " + shingleSetA.size());
        System.out.println("String Set B: " + shingleSetB.size());
        System.out.println("Minhash Set A: " + min_hashed_shingles_A.size());
        System.out.println("Minhash Set B: " + min_hashed_shingles_B.size());


    } // end of main


    private static int[] randomHashes(int size){
        int[] randoms = new int[size];
        Random r = new Random();

        //System.out.println("Randoms:" +  randoms.length);
        for(int i = 0; i < randoms.length; i++){
            randoms[i] = r.nextInt();
            //System.out.println("Randoms: [ " + randoms[i] + " ]");
        }
        //System.out.println();
        return randoms;
    }
}
