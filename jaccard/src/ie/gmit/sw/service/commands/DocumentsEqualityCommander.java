package ie.gmit.sw.service.commands;

import ie.gmit.sw.domain.Document;
import ie.gmit.sw.domain.Result;

import java.util.Collection;
import java.util.List;

public interface DocumentsEqualityCommander {

    Collection<Result> processDocumentsEquality(Collection<Document> documents);
}
