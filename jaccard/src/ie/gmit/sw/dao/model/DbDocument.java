package ie.gmit.sw.dao.model;

import java.util.Set;

public class DbDocument {

    private String docName;
    private Set<Integer> docShinglesSet;

    public DbDocument() {
    }

    public DbDocument(String docName, Set<Integer> docShinglesSet) {
        this.docName = docName;
        this.docShinglesSet = docShinglesSet;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Set<Integer> getDocShinglesSet() {
        return docShinglesSet;
    }

    public void setDocShinglesSet(Set<Integer> docShinglesSet) {
        this.docShinglesSet = docShinglesSet;
    }


    @Override
    public String toString() {
        return "DbDocument{" +
                "docName='" + docName + '\'' +
                '}';
    }
}
