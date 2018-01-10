package ie.gmit.sw.dao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ta.TransparentActivationSupport;
import com.db4o.ta.TransparentPersistenceSupport;
import xtea_db4o.XTEA;
import xtea_db4o.XTeaEncryptionStorage;

public class DB4OContainer {
    private static DB4OContainer instance = new DB4OContainer();
    private ObjectContainer db = null;


    public static DB4OContainer getInstance() {
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
}
