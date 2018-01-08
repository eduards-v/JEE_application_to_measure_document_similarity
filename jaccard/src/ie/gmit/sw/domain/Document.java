package ie.gmit.sw.domain;

import java.util.Set;

public class Document {

    private int docId;
    private String docName;
    private Set<Integer> docShingles;

    public Document() { }

    public Document(String docName, Set<Integer> docShingles) {
        this.docName = docName;
        this.docShingles = docShingles;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Set<Integer> getDocShingles() {
        return docShingles;
    }

    public void setDocShingles(Set<Integer> docShingles) {
        this.docShingles = docShingles;
    }


    @Override
    public String toString() {
        return "Document{" +
                "docId=" + docId +
                ", docName='" + docName + '\'' +
                ", docShingles=" + docShingles +
                '}';
    }
}
