package ie.gmit.sw.shingle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KGramBuilder extends ShingleBuildersFactory {


    @Override
    Set<String> getShinglesAsString(List<String> textWords, final int SHINGLE_SIZE) {
        Set<String> shingles = new HashSet<>();

        if (SHINGLE_SIZE <= 1) throw new IllegalArgumentException("SHINGLE_SIZE has to be greater than 1!");

        for(List<String> part : chopIntoShingleParts(textWords, SHINGLE_SIZE)){
            shingles.add(String.join(" ", part));
        }
        return shingles;
    }

    @Override
    Set<Integer> getShinglesAsIntegers(List<String> textWords, final int SHINGLE_SIZE) {
        Set<Integer> shingles = new HashSet<>();

        if (SHINGLE_SIZE <= 1) throw new IllegalArgumentException("SHINGLE_SIZE has to be greater than 1!");

        for(List<String> part : chopIntoShingleParts(textWords, SHINGLE_SIZE)){
            shingles.add(String.join(" ", part).hashCode());
        }
        return shingles;
    }

    // adopted from https://stackoverflow.com/questions/2895342/java-how-can-i-split-an-arraylist-in-multiple-small-arraylists
    // chop the list of an elements into list of sublists of specific size (SHINGLE_SIZE)
    private List<List<String>> chopIntoShingleParts(List<String> allWords, final int SHINGLE_SIZE){
        List<List<String>> shingleParts = new ArrayList<>();
        List<String> temp;
        final int N = allWords.size();
        for(int i = 0; i < N; i ++){
            temp = new ArrayList<>(allWords.subList(i, Math.min(N, i + SHINGLE_SIZE)));
            if(temp.size()==SHINGLE_SIZE) shingleParts.add(temp);
        }


        //System.out.println(shingleParts);
        return shingleParts;
    }
}
