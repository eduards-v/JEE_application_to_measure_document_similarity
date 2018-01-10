package ie.gmit.sw.service.commands;

import ie.gmit.sw.dao.DB4OContainer;
import ie.gmit.sw.dao.Repository;
import ie.gmit.sw.dao.model.DbDocument;
import ie.gmit.sw.domain.Document;
import ie.gmit.sw.domain.Result;
import ie.gmit.sw.intersector.IntersectorFactory;
import ie.gmit.sw.intersector.IntersectorType;
import ie.gmit.sw.intersector.SetIntersector;
import ie.gmit.sw.shingle.ShingleBuildersFactory;
import ie.gmit.sw.shingle.ShingleType;

import java.util.*;

public class HashcodeKGramsEqualityCommand implements DocumentsEqualityCommander{

    private ShingleBuildersFactory shingleBuilder;
    private SetIntersector intersector;
    private Map<String, Set<Integer>> docsToProcess;
    private List<DbDocument> dbDocuments;
    private Collection<Result> results;
    private Repository repository;

    public HashcodeKGramsEqualityCommand() {

        // Get appropriate factory to build shingles (K_GRAM is used here)
        shingleBuilder = ShingleBuildersFactory.getFactory(ShingleType.K_GRAM);
        // Get appropriate intersector, hashcode set intersector is used here.
        intersector = IntersectorFactory.getInstance().getIntersector(IntersectorType.HASHCODE_SET_INTERSECTOR);

        docsToProcess = new HashMap<>();
        results = new ArrayList<>();

        // get all documents from DB4O database
        repository = DB4OContainer.getInstance();
        dbDocuments = repository.getAllDocuments();
    }


    // Method that is exposed to a client through interface. Encapsulates
    // complex logic of document equality processing.
    @Override
    public Collection<Result> processDocumentsEquality(List<Document> documents) {
        prepDocuments(documents);
        processIntersection();
        return results;
    }

    // Prepare uploaded documents for comparing. Use ShingleBuilder here
    // because document comes with list of all words, but need Set
    // of integers (hashcode from each word).
    //
    private void prepDocuments(List<Document> documents){
        Set<Integer> temp;
        for (Document document : documents){
            temp = shingleBuilder.getShinglesAsIntegers(document.getAllDocWords());
            docsToProcess.put(document.getDocName(), temp);
        }
    }

    // Process intersection of uploaded documents against documents stored in DB.
    // Consider to use Callable and Future in combination with invokeAll method
    // of ExecutorService to improve performance, as multiple threads will be spawned
    // for each document to process.
    private void processIntersection(){

        docsToProcess.forEach((k, v) ->
                dbDocuments.forEach(dbDocument ->{
                        float intersection = intersector.getIntersection(v, dbDocument.getDocShinglesSet());
                        processResult(k, dbDocument.getDocName(), intersection);
                }));
    }

    // Add result from each document iteration and stores it in Collection
    // that is returned to a client after all processing is done.
    private void processResult(String docA, String docB, float intersection){
        Result result = new Result(docA, docB, intersection);
        results.add(result);
    }

}
