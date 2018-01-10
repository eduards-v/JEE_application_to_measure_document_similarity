package ie.gmit.sw.dao;

import ie.gmit.sw.common.File2ListFetcher;

import java.io.*;
import java.util.List;


public class DummyDao {

    private File path = new File("files","inner");
    private File resource = new File(path, "resource.txt");
    private List<String> resourceText;

    public DummyDao() {
        resourceText = File2ListFetcher.fetchFile(resource);
    }

    public List<String> getResourceText() {
        return resourceText;
    }

}
