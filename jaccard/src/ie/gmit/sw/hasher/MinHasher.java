package ie.gmit.sw.hasher;

import java.util.HashSet;
import java.util.Set;

public class MinHasher {

    private Set<Integer> docHashes;
    private Set<Integer> minDocHashes;
    private int[] randomHashes;

    
 
    public MinHasher(Set<Integer> docHashes, int[] randomHashes) {

        System.out.println(randomHashes.length);
        this.minDocHashes = new HashSet<>(randomHashes.length);
        this.docHashes = docHashes;
        this.randomHashes = randomHashes;

    }

    public void doMinHashing(){
        int min_selected = 0;
        int temp_hash;
        int min_hash = Integer.MAX_VALUE; // initial max int value (32 bit)
        System.out.println("Doc hashes: " + docHashes.size());

        for(int i = 0; i < randomHashes.length; i ++){
            for(int docHash : docHashes){
                temp_hash = ((docHash ^ randomHashes[i])& 0x7fffffff);
                if(temp_hash < min_hash){
                    min_selected = docHash;
                    min_hash = temp_hash;
                }
            }
            minDocHashes.add(min_hash);
            min_hash = Integer.MAX_VALUE;

            System.out.println("Doc hashes: " + docHashes.size());
            docHashes.remove(min_selected);
        }
    }

    public void setDocHashes(Set<Integer> docHashes) {
        this.docHashes = docHashes;
        this.minDocHashes.clear();
    }

    public Set<Integer> getMinDocHashes() {
        System.out.println(minDocHashes.size());
        return minDocHashes;
    }
}
