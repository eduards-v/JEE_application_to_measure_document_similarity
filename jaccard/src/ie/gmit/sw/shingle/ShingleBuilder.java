package ie.gmit.sw.shingle;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class ShingleBuilder{

    private List<String> wordsList;
    private List<List<String>> shingleParts;
    private Set<Integer> shingleSet;
    private Set<String> shingleStrSet;
    private final int SHINGLE_SIZE;
    private final InputStream in;

    public ShingleBuilder(int shingle_size, InputStream in) {
        this.wordsList = new ArrayList<>();
        this.shingleParts = new ArrayList<>();
        this.SHINGLE_SIZE = shingle_size;
        this.shingleSet = new HashSet<>();
        this.shingleStrSet = new HashSet<>();
        this.in = in;

        System.out.println("Inside ShingleBuilder Constructor");

        // start building
        initShingleBuilder();
    }

    private void initShingleBuilder(){

        System.out.println("Inside init");
        // BufferedInputStream dramatically reduce number of calls
        // to JNI(Java Native Interface) of the filesystem.
        // Improves performance by ~90%
        InputStream bis = new BufferedInputStream(in);

        // BufferedReader to read underlying input stream
        BufferedReader br = new BufferedReader(new InputStreamReader(bis));

        // get all lines from buffered reader as a Stream
        Stream<String> stream = br.lines();
        // for each line in a stream, split into words and add all words to list
        stream.forEach((String line) -> wordsList.addAll(Arrays.asList(line.split(" "))));


        // chop List of all words into shingle parts
        chopIntoShingleParts();
        // populates shingle set from chopped shingle parts
        createShingleSet();

    }
    // adopted from https://stackoverflow.com/questions/2895342/java-how-can-i-split-an-arraylist-in-multiple-small-arraylists
    // chop the list of an elements into list of sublists of specific size (SHINGLE_SIZE)
    private void chopIntoShingleParts(){
        final int N = wordsList.size();
        for(int i = 0; i < N; i += SHINGLE_SIZE){
            this.shingleParts.add( new ArrayList<>(
                    wordsList.subList(i, Math.min(N, i + SHINGLE_SIZE)))
            );
        }
    }


    private void createShingleSet(){
        for(List<String> part : shingleParts){
            shingleStrSet.add(String.join(" ", part));
            String shingle = String.join(" ", part);
            shingleSet.add((shingle.hashCode() & 0x7fffffff) % shingleParts.size());
        }
    }

    public Set<Integer> getShingleSet() {
        return shingleSet;
    }

    public Set<String> getShingleStrSet() {
        return shingleStrSet;
    }
}
