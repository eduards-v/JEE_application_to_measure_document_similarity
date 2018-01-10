package ie.gmit.sw.dao;

import ie.gmit.sw.common.File2ListFetcher;
import ie.gmit.sw.dao.model.DbDocument;
import ie.gmit.sw.shingle.ShingleBuildersFactory;
import ie.gmit.sw.shingle.ShingleType;

import java.io.*;
import java.util.List;

public class DaoTester {

    public static void main(String[] args) {

        ShingleBuildersFactory factory = ShingleBuildersFactory.getFactory(ShingleType.K_GRAM);


        File path = new File("files","inner");
        File resource = new File(path, "resource.txt");
        List<String> resourceText = File2ListFetcher.fetchFile(resource);

        Repository repo = DB4OContainer.getInstance();

        DbDocument newDoc = new DbDocument(resource.getName(), factory.getShinglesAsIntegers(resourceText));
        repo.addDocument(newDoc);

        List<DbDocument> docs = repo.getAllDocuments();

        for (DbDocument doc : docs){
            System.out.println(doc.getDocName());
        }
    }

}
