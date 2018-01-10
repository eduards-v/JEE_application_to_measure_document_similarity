package ie.gmit.sw.dao;

import ie.gmit.sw.dao.model.DbDocument;
import ie.gmit.sw.shingle.ShingleBuildersFactory;
import ie.gmit.sw.shingle.ShingleType;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DaoTester {

    public static void main(String[] args) {

        ShingleBuildersFactory factory = ShingleBuildersFactory.getFactory(ShingleType.K_GRAM);


        File path = new File("files","inner");
        File resource = new File(path, "resource.txt");
        List<String> resourceText = fetchFile(resource);

        Repository repo = DB4OContainer.getInstance();

        DbDocument newDoc = new DbDocument(resource.getName(), factory.getShinglesAsIntegers(resourceText));
        repo.addDocument(newDoc);

        List<DbDocument> docs = repo.getAllDocuments();

        for (DbDocument doc : docs){
            System.out.println(doc.getDocName());
        }
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
