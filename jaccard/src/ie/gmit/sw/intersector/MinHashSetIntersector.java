package ie.gmit.sw.intersector;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MinHashSetIntersector implements SetIntersector<Integer> {


    @Override
    public float getIntersection(Set<Integer> setA, Set<Integer> setB) {
        float sizeA, sizeB, sizeC, intersection_rate;
        int sizeR = (setA.size()+setB.size())/2;
        System.out.println("Randoms size: "+sizeR);
        int [] randomHashes = randomHashes(sizeR);
        Set<Integer> minHashedSetA, minHashedSetB;


        minHashedSetA = getMinHashes(setA, randomHashes);
        minHashedSetB = getMinHashes(setB, randomHashes);
        sizeA = minHashedSetA.size();
        sizeB = minHashedSetB.size();
        System.out.println(" Set A: " + sizeA + " Set B: " + sizeB);

        Set<Integer> intersections = new HashSet<>(minHashedSetA);
        intersections.retainAll(minHashedSetB);
        sizeC = intersections.size();
        System.out.println("Intersection size: " + sizeC + " Set A: " + sizeA + " Set B: " + sizeB);
        intersection_rate = (sizeC / (sizeA+sizeB-sizeC))*100;

        return intersection_rate;
    }

    private Set<Integer> getMinHashes(Set<Integer> set, int[] randomHashes ){
        Set<Integer> min_hashed = new HashSet<>();
        Set<Integer> set_copy = new HashSet<>(set);
        int min_selected = 0;
        int temp_hash;
        int min_hash = Integer.MAX_VALUE; // initial max int value (32 bit)

        for(int i = 0; i < randomHashes.length; i++) {
            if (set_copy.isEmpty()) break;

            for (int hashcode : set_copy){
                temp_hash = hashcode ^ randomHashes[i];
                if(temp_hash < min_hash){
                    min_selected = hashcode;
                    min_hash = temp_hash;
                }
            }
            min_hashed.add(min_hash);
            min_hash = Integer.MAX_VALUE;
            set_copy.remove(min_selected);
        }

        return min_hashed;
    }


    private int[] randomHashes(int size){
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
