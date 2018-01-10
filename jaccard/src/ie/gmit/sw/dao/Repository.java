package ie.gmit.sw.dao;

import ie.gmit.sw.dao.model.DbDocument;

import java.util.List;

public interface Repository {

    List<DbDocument> getAllDocuments();
    void addDocument(DbDocument document);
}
