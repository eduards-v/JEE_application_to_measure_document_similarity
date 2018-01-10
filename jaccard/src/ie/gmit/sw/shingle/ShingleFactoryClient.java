package ie.gmit.sw.shingle;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class ShingleFactoryClient {

    public static void main(String[] args) {

        File path = new File("files","inner");
        File upload = new File(path, "upload.txt");
        File resource = new File(path, "resource.txt");

        List<String> uploadText = fetchFile(upload);
        List<String> resourceText = fetchFile(resource);

        System.out.println("Upload word count: " + uploadText.size()
                          + "\nResource word count: " + resourceText.size());


        ShingleBuildersFactory k_gram_factory = ShingleBuildersFactory.getFactory(ShingleType.K_GRAM);

        int shingle_size = 5;
        Set<String> uploadStringSet = new HashSet<>();
        Set<String> resourceStringSet = new HashSet<>();
        try{
            uploadStringSet = k_gram_factory.getShinglesAsString(uploadText, shingle_size);
            resourceStringSet = k_gram_factory.getShinglesAsString(resourceText, shingle_size);

        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }


        System.out.println("\n________ Resource String Shingles Set _________\n" +
                "Set cardinality: " + resourceStringSet.size() + "\n");
        System.out.println("\n________ Upload String Shingles Set _________\n" +
                "Set cardinality: " + uploadStringSet.size() + "\n");

        System.out.println("\n________ Intersection between Upload and Resource Sets _________\n");
        IntersectionStrategy intersector = new StringSetIntersection();
        float intersection = intersector.getIntersection(resourceStringSet, uploadStringSet);
        uploadStringSet.retainAll(resourceStringSet);
        System.out.println("Sets intersection rate: " + intersection + "%\n");





    }

    private static List<String> fetchFile(File file){
        List<String> allWords = new ArrayList<>();

        try(InputStream in = new BufferedInputStream(new FileInputStream(file))){

            // BufferedReader to read underlying input stream
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            // get all lines from buffered reader as a Stream
            Stream<String> stream = br.lines();

            // for each line in a stream, split into words and add all words to list
            stream.forEach((String line) -> allWords.addAll(Arrays.asList(line.split(" "))));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allWords;
    }
}
