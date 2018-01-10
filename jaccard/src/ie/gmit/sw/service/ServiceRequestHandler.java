package ie.gmit.sw.service;

import ie.gmit.sw.dao.DummyDao;
import ie.gmit.sw.intersector.IntersectorFactory;
import ie.gmit.sw.intersector.IntersectorType;
import ie.gmit.sw.intersector.SetIntersector;
import ie.gmit.sw.shingle.ShingleBuildersFactory;
import ie.gmit.sw.shingle.ShingleType;

import java.util.List;
import java.util.Set;


public class ServiceRequestHandler {

    private SetIntersector intersector;
    private ShingleBuildersFactory shingleBuildersFactory;
    private List<String> docA, docB;
    private Set<String> docASet, docBSet;

    public ServiceRequestHandler(List<String> doc,
                                 IntersectorType intersectorType,
                                 ShingleType shingleType) {
        DummyDao dao = new DummyDao();
        docB = dao.getResourceText();
        this.docA = doc;
        IntersectorFactory intersectorFactory = IntersectorFactory.getInstance();
        this.intersector = intersectorFactory.getIntersector(intersectorType);
        shingleBuildersFactory = ShingleBuildersFactory.getFactory(shingleType);
    }

    public float execute() {
        docASet = prepStingSet(docA);
        docBSet = prepStingSet(docB);

        return intersector.getIntersection(docASet, docBSet);
    }

    private Set<String> prepStingSet(List<String> target){
        return shingleBuildersFactory.getShinglesAsString(target);
    }

    private Set<Integer> prepIntegerSet(List<String> target){
        return shingleBuildersFactory.getShinglesAsIntegers(target);
    }

}
