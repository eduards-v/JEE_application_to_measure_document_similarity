package ie.gmit.sw.intersector;

public class IntersectorFactory {
    private static IntersectorFactory instance;

    private IntersectorFactory() {}

    public static IntersectorFactory getInstance() {
        if(instance == null) instance = new IntersectorFactory();
        return instance;
    }

    public SetIntersector getIntersector(IntersectorType type){
        SetIntersector intersector = null;

        switch(type){
            case STRING_SET_INTERSECTOR:
                intersector = new StringSetIntersector();
                break;
            case HASHCODE_SET_INTERSECTOR:
                intersector = new HashcodeSetIntersector();
                break;
            case MIN_HASHCODE_SET_INTERSECTOR:
                intersector = new MinHashSetIntersector();
                break;
        }

        return intersector;
    }


}
