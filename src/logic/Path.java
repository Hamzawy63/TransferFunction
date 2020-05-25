package logic;

import java.util.Set;

public class Path {
    Set<Integer> vertices;
    Double gain;
    Double delta; // 1 - product of gains of non touched loops

    public Path(Set<Integer> vertices, Double gain, Double delta) {
        this.vertices = vertices;
        this.gain = gain;
        this.delta = delta;
    }

    public Set<Integer> getVertices() {
        return vertices;
    }

    public void setVertices(Set<Integer> vertices) {
        this.vertices = vertices;
    }

    public Double getGain() {
        return gain;
    }

    public void setGain(Double gain) {
        this.gain = gain;
    }

    public Double getDelta() {
        return delta;
    }

    public void setDelta(Double delta) {
        this.delta = delta;
    }

    public Boolean isIntersectedWith(Cycle others) {
        Set<Integer> otherSet = others.getVertices();
        for (Integer i : vertices) {
            if (otherSet.contains(i))
                return true;
        }
        return false;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{[vertices : ");
        for (Integer i : vertices) stringBuilder.append(i + ",");
        stringBuilder.append("]");
        stringBuilder.append("[Gain :" + gain + "]");
        stringBuilder.append("[delta : " + delta + "]}\n");
        return stringBuilder.toString();
    }

}
