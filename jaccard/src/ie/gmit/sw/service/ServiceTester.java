package ie.gmit.sw.service;

import ie.gmit.sw.common.File2ListFetcher;
import ie.gmit.sw.domain.Document;
import ie.gmit.sw.domain.Result;
import ie.gmit.sw.service.commands.DocumentEqualityCommandTypes;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ServiceTester {

    public static void main(String[] args) {

        ServiceCommandsHandler service;

        List<Document> documents = new ArrayList<>();
        Collection<Result> results;

        File path = new File("files","inner");
        File resource = new File(path, "upload.txt");
        List<String> resourceText = File2ListFetcher.fetchFile(resource);

        Document document = new Document(resource.getName(), resourceText);

        documents.add(document);

        service = new ServiceCommandsHandler(documents);

        results = service.executeCommand(DocumentEqualityCommandTypes.HASHCODE_K_GRAM_CMD);

        results.forEach((result -> System.out.println(result.toString())));
    }
}
