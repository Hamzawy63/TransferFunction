import java.util.List;

public class MasonMetaInformation<V> {
    List<Cycle> cycles;
    List<Path> paths;

    public MasonMetaInformation(List<Path> paths, List<Cycle> cycles) {
        this.paths = paths;
        this.cycles = cycles;

    }

    public double getTransferFunction() {
//        System.out.println(paths);
//        System.out.println(cycles);
        double delta = 1;
        double numerator = 0; // elbst

        int sign = -1;
        double sum = 0;
        int last = 1;
        for (Cycle cycle : cycles) {
            if (cycle.getDegree() > last) {
                delta += sign * sum;
                sign *= -1;
                last = cycle.getDegree();
                sum = 0;
            }
            sum += cycle.getGain();
        }
        delta += sign * sum ;
        for (Path path : paths) {
            numerator += path.getDelta() * path.getGain();
        }
        return numerator / delta;
    }
}
