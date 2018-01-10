package ie.gmit.sw.domain;

import java.util.List;

public class Document {

    private String docName;
    private List<String> allDocWords;

    public Document() {
    }

    public Document(String docName, List<String> allDocWords) {
        this.docName = docName;
        this.allDocWords = allDocWords;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public List<String> getAllDocWords() {
        return allDocWords;
    }

    public void setAllDocWords(List<String> allDocWords) {
        this.allDocWords = allDocWords;
    }

    @Override
    public String toString() {
        return "Document{" +
                "docName='" + docName + '\'' +
                ", allDocWords=" + allDocWords +
                '}';
    }
}
