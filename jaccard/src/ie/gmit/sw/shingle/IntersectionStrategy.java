package ie.gmit.sw.shingle;

import java.util.Set;

public interface IntersectionStrategy<T> {

    float getIntersection(Set<T> SetA, Set<T> SetB);

}
