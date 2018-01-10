package ie.gmit.sw.shingle;

import java.util.List;
import java.util.Set;

public abstract class ShingleBuildersFactory {

    private static ShingleBuildersFactory k_gram_factory = null;

    abstract Set<String> getShinglesAsString(List<String> textWords, int SHINGLE_SIZE) throws IllegalArgumentException;
    abstract Set<Integer> getShinglesAsIntegers(List<String> textWords, int SHINGLE_SIZE) throws IllegalArgumentException;

    public static ShingleBuildersFactory getFactory(ShingleType type){
        ShingleBuildersFactory factory = null;
        switch (type){
            case K_GRAM:
                if (k_gram_factory == null) k_gram_factory = new KGramBuilder();
                factory = k_gram_factory;
                break;

            case K_MER:
                break;
        }


        return factory;
    }


}
