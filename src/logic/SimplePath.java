package logic;

import java.util.HashSet;
import java.util.Set;

public class SimplePath {
    Double gain;

    public SimplePath(Set<Integer> vertices, Double gain) {
        this.gain = gain;
        this.vertices = vertices;
    }

    Set<Integer> vertices;

    public Double getGain() {
        return gain;
    }

    public void setGain(Double gain) {
        this.gain = gain;
    }

    public Set<Integer> getVertices() {
        return vertices;
    }

    public void setVertices(Set<Integer> vertices) {
        this.vertices = vertices;
    }

    public SimplePath getUnionIfIntersetWith(SimplePath others) {
        Set<Integer> result = new HashSet<>();
        for (Integer v : vertices) result.add(v);
        for (Integer v : others.getVertices()) result.add(v);

        if (others.equals(this) || result.size() == vertices.size() + others.getVertices().size()) {
            return null;
        } else {
            return new SimplePath(result, gain * others.getGain());
        }
    }
}
