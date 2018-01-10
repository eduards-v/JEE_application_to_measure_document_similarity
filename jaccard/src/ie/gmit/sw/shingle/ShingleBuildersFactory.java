package ie.gmit.sw.shingle;

import java.util.List;
import java.util.Set;

public abstract class ShingleBuildersFactory {

    private static ShingleBuildersFactory k_gram_factory = null;

    protected static int SHINGLE_SIZE = 5; // default set to 5

    public abstract Set<String> getShinglesAsString(List<String> textWords);
    public abstract Set<Integer> getShinglesAsIntegers(List<String> textWords);

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

    public static void setShingleSize(int SHINGLE_SIZE) throws IllegalArgumentException {
        if (SHINGLE_SIZE <= 1) throw new IllegalArgumentException("SHINGLE_SIZE has to be greater than 1!");
        ShingleBuildersFactory.SHINGLE_SIZE = SHINGLE_SIZE;
    }


}
