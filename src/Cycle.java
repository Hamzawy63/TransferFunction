import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cycle {
    Set<Integer> vertices;
    Double gain;
    Integer degree;

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

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Cycle(Set<Integer> vertices, Double gain, Integer degree) {
        this.vertices = vertices;
        this.gain = gain;
        this.degree = degree;
    }

    public Cycle getUnionIfDisjoint(Cycle others) {
        Set<Integer> result = new HashSet<>();
        for (Integer v : vertices) result.add(v);
        for (Integer v : others.getVertices()) result.add(v);

        if (others.equals(this) || result.size() != vertices.size() + others.getVertices().size()) {
            return null;
        } else {
            return new Cycle(result, gain * others.getGain(), degree + others.getDegree());
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{[vertices : ");
        for (Integer i : vertices) stringBuilder.append(i + ",");
        stringBuilder.append("]");
        stringBuilder.append("[Gain :" + gain + "]");
        stringBuilder.append("[Degree : " + degree + "]}\n");
        return stringBuilder.toString();
    }
}
