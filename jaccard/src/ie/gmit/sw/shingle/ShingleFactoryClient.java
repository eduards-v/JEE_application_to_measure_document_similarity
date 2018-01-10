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

        Set<String> uploadStringSet = new HashSet<>();
        try{
            uploadStringSet = k_gram_factory.getShinglesAsString(uploadText, 1);

        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        System.out.println("\n________ Upload String Shingles Set _________\n" +
                           "Set Size: " + uploadStringSet.size());
        uploadStringSet.forEach(System.out::println);



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
