package ie.gmit.sw.shingle;

import java.util.Set;

@FunctionalInterface
public interface SetIntersector<T> {

    float getIntersection(Set<T> setA, Set<T> setB);

}
