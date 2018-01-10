package ie.gmit.sw.shingle;

import java.util.HashSet;
import java.util.Set;

public class StringSetIntersector implements SetIntersector<String> {


    @Override
    public float getIntersection(Set<String> setA, Set<String> setB) {
        float sizeA = setA.size();
        float sizeB = setB.size();
        float sizeC;
        float intersection_rate;
        Set<String> intersections = new HashSet<>(setA);
        intersections.retainAll(setB);
        sizeC = intersections.size();

        System.out.println("Set A: " + String.valueOf(sizeA) + " Set B: " + String.valueOf(sizeB) + " Set C: " + String.valueOf(sizeC));
        intersection_rate = (sizeC / (sizeA+sizeB-sizeC))*100;
        System.out.println(intersection_rate);

        return intersection_rate;
    }
}
