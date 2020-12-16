package sketchpad.utils;

import sketchpad.model.canvaselement.edge.Edge;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class DeepClone {

    public static List<Edge> create(HashMap<String, Edge> edgeMap) {
        return new LinkedList<>(edgeMap.values());
    }

    public static List<String> createNoDuplicate(List<String> list) {
        LinkedList<String> deepCloneArray = new LinkedList<>();
        for(String item : list)
            if(!deepCloneArray.contains(item))
                deepCloneArray.add(item);

        return deepCloneArray;
    }

}
