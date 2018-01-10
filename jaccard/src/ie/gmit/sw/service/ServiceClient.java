package ie.gmit.sw.service;

import ie.gmit.sw.dao.DB4OContainer;
import ie.gmit.sw.intersector.IntersectorType;
import ie.gmit.sw.shingle.ShingleType;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ServiceClient {

    public static void main(String[] args) {
        DB4OContainer dao = DB4OContainer.getInstance();

        File path = new File("files","inner");
        File upload = new File(path, "upload.txt");
        List<String> resourceText = fetchFile(upload);
        float intersection;

        ServiceRequestHandler service = new ServiceRequestHandler(resourceText,
                                                                 IntersectorType.STRING_SET_INTERSECTOR,
                                                                 ShingleType.K_GRAM);



        System.out.println("\n________ Intersection between Upload and Resource String Sets _________\n");
        intersection = service.execute();
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
