package ie.gmit.sw.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DummyDao {

    private File path = new File("files","inner");
    private File resource = new File(path, "resource.txt");
    private List<String> resourceText;

    public DummyDao() {
        resourceText = fetchFile(resource);
    }

    public List<String> getResourceText() {
        return resourceText;
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
