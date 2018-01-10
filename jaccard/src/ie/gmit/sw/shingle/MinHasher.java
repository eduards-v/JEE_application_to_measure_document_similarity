package ie.gmit.sw.shingle;

import java.util.HashSet;
import java.util.Set;

public class MinHasher {

    private Set<Integer> docHashes;
    private Set<Integer> minDocHashes;
    private int[] randomHashes;

    
 
    public MinHasher(Set<Integer> docHashes, int[] randomHashes) {

        this.minDocHashes = new HashSet<>(randomHashes.length);
        this.docHashes = docHashes;
        this.randomHashes = randomHashes;

    }

    public void doMinHashing(){
        int min_selected = 0;
        int temp_hash;
        int min_hash = Integer.MAX_VALUE; // initial max int value (32 bit)
        System.out.println("Min-hashing hashed doc: " + docHashes.size());

        for(int i = 0; i < randomHashes.length; i++){
            if (docHashes.isEmpty()) break;

            for(int docHash : docHashes){
                                                        // eliminating negative hashcode value
                temp_hash = docHash ^ randomHashes[i];
                if(temp_hash < min_hash){
                    min_selected = docHash;
                    min_hash = temp_hash;
                }
            }

            minDocHashes.add(min_hash);
            min_hash = Integer.MAX_VALUE;

            docHashes.remove(min_selected);
        } // end of random hashes loop
        System.out.println("Processed min-hash doc size: " + minDocHashes.size());
    } // end of method

    public void setDocHashes(Set<Integer> docHashes) {
        this.docHashes = docHashes;
        this.minDocHashes.clear();
    }

    public Set<Integer> getMinDocHashes() {
        return minDocHashes;
    }
}
