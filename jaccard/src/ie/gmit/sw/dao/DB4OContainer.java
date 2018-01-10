package ie.gmit.sw.dao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Predicate;
import com.db4o.ta.TransparentActivationSupport;
import com.db4o.ta.TransparentPersistenceSupport;
import ie.gmit.sw.common.File2ListFetcher;
import ie.gmit.sw.dao.model.DbDocument;
import ie.gmit.sw.shingle.ShingleBuildersFactory;
import ie.gmit.sw.shingle.ShingleType;
import xtea_db4o.XTEA;
import xtea_db4o.XTeaEncryptionStorage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class DB4OContainer implements Repository{
    private static DB4OContainer instance = new DB4OContainer();
    private ObjectContainer db = null;

    private static int init = 0; // is db initialized?


    public static DB4OContainer getInstance() {
        if(init == 0){
            init = 1;
            initDbFromFileSystem(); // init database from file system
        }
        return instance;
    }

    private DB4OContainer() {
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().add(new TransparentActivationSupport());
        config.common().add(new TransparentPersistenceSupport());
        config.common().updateDepth(7);

        config.file().storage(new XTeaEncryptionStorage("password", XTEA.ITERATIONS64));

        db = Db4oEmbedded.openFile(config, "documents.yap");



        System.out.println("\n____DB4O successfully initialized!____\n");
    }

    @Override
    public List<DbDocument> getAllDocuments() {

        List<DbDocument> documentsList = new ArrayList<>();
        ObjectSet<DbDocument> documents = db.query(DbDocument.class);
        for (DbDocument document : documents){
            documentsList.add(document);
        }
        return documentsList;
    }

    @Override
    public void addDocument(DbDocument document) {

        ObjectSet<DbDocument> result = db.query(new Predicate<DbDocument>() {
            private static final long serialVersionUID = 777L;

            public boolean match(DbDocument dbDocument) {
                return dbDocument.getDocName().equals(document.getDocName());
            }
        });

        if (result.hasNext()){
            System.out.println("Document with name '" + document.getDocName() + "' already exist!");
        }else {
            db.store(document);
            db.commit();
        }
    }


    // Should be taken away from here...
    // Initializes db from file system. Performs every time program rerun.
    private static void initDbFromFileSystem(){
        try (Stream<Path> paths = Files.walk(Paths.get("files/resources"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(path -> {

                        File file = path.toFile();
                        Set<Integer> set = ShingleBuildersFactory
                                .getFactory(ShingleType.K_GRAM)
                                .getShinglesAsIntegers(File2ListFetcher.fetchFile(file));

                        DbDocument doc = new DbDocument(path.getFileName().toString(), set);
                        System.out.println(doc);
                        getInstance().addDocument(doc);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
