package ie.gmit.sw.service;

import ie.gmit.sw.intersector.IntersectorFactory;
import ie.gmit.sw.intersector.IntersectorType;
import ie.gmit.sw.intersector.SetIntersector;
import ie.gmit.sw.shingle.ShingleBuildersFactory;

import java.util.List;

public class ServiceRequestHandler {

    private SetIntersector intersector;
    private ShingleBuildersFactory shingleBuildersFactory;

    public ServiceRequestHandler(List<String> doc, IntersectorType type) {
        intersector = IntersectorFactory.getIntersector(type);

        switch (type){
            case STRING_SET_INTERSECTOR:

                break;
            case HASHCODE_SET_INTERSECTOR:

                break;
            case MIN_HASHCODE_SET_INTERSECTOR:

                break;
        }
    }

    public void execute(){


    }

}
