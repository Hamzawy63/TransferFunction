import java.util.Objects;

public class Pair<K extends Comparable<K>, V extends Comparable<V>> {
    private K first;
    private V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Pair<?, ?> other = (Pair<?, ?>) obj;
        return other.first.equals(this.first) && other.second.equals(this.second);
    }

    @Override
    public String toString() {
        System.out.println("First = " + first + " Second = " + second);
        return super.toString();
    }

    public static void main(String[] args) {
        Pair<Integer, String> p1 = new Pair<>(1,  "3");
        Pair<Integer, String> p2 = new Pair<>(1, "3");
        System.out.println(p1.equals(p2));
    }
}
