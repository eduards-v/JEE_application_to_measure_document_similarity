package ie.gmit.sw.intersector;

import java.util.HashSet;
import java.util.Set;

public class HashcodeSetIntersector implements SetIntersector<Integer>{


    @Override
    public float getIntersection(Set<Integer> setA, Set<Integer> setB) {
        float sizeA = setA.size();
        float sizeB = setB.size();
        float sizeC;
        float intersection_rate;

        Set<Integer> intersections = new HashSet<>(setA);
        intersections.retainAll(setB);
        sizeC = intersections.size();

        intersection_rate = (sizeC / (sizeA+sizeB-sizeC))*100;

        return intersection_rate;
    }
}
