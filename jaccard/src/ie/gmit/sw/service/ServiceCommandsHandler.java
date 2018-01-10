package ie.gmit.sw.service;


import ie.gmit.sw.domain.Document;
import ie.gmit.sw.domain.Result;
import ie.gmit.sw.service.commands.DocumentEqualityCommandTypes;
import ie.gmit.sw.service.commands.DocumentEqualityCommandsFactory;
import ie.gmit.sw.service.commands.DocumentsEqualityCommander;

import java.util.Collection;
import java.util.List;

public class ServiceCommandsHandler {

    private DocumentsEqualityCommander command;
    private List<Document> uploadedDocs;

    public ServiceCommandsHandler(List<Document> uploadedDocs) {
        this.uploadedDocs = uploadedDocs;
    }

    // Function that delegates document equality comparing to a specific command object
    // Each command encapsulates it's own logic of how to compare documents.
    // Some use DAO where documents are stored as integer shingles, other commands
    // may use file system to obtain string shingles of documents stored.
    public Collection<Result> executeCommand(DocumentEqualityCommandTypes commandType){
        command = DocumentEqualityCommandsFactory.getInstance().getCommand(commandType);

        return command.processDocumentsEquality(uploadedDocs);
    }
}
