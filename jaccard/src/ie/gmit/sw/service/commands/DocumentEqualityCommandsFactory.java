package ie.gmit.sw.service.commands;

public class DocumentEqualityCommandsFactory {
    private static DocumentEqualityCommandsFactory instance = new DocumentEqualityCommandsFactory();

    public static DocumentEqualityCommandsFactory getInstance() {
        return instance;
    }

    private DocumentEqualityCommandsFactory() {
    }

    public DocumentsEqualityCommander getCommand(DocumentEqualityCommandTypes type){

        DocumentsEqualityCommander command = null;

        switch (type){
            case STRING_K_GRAM_CMD:
                command = new StringKGramsEqualityCommand();
                break;
            case HASHCODE_K_GRAM_CMD:
                command = new HashcodeKGramsEqualityCommand();
                break;


            case STRING_K_MER_CMD:
                break;
            case INTEGER_K_MER_CMD:
                break;
        }

        return command;
    }

}
