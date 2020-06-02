package logic;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.cycle.JohnsonSimpleCycles;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.*;

public class GraphAdapter {
    int v;
    DefaultDirectedWeightedGraph<Integer, DefaultWeightedEdge> g;
    List<Path> paths;
    List<Cycle> cycles;
    int source  ;
    int target ;

    public GraphAdapter(int v , int source , int target) {
        this.source = source ;
        this.target = target ;
        /**source and target should be less than or equal the v*/
        this.v = v;
        g = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        for (int i = 1; i <= v; i++) g.addVertex(i);
    }

    public void addEdge(int sourceVertex, int targetVertex, double weight) {
        DefaultWeightedEdge edge = g.addEdge(sourceVertex, targetVertex);
        g.setEdgeWeight(edge, weight);
    }

    private void fillThePaths() {
        if (g.inDegreeOf(1) != 0 && g.outDegreeOf(v) != 0) {
            System.out.println("Sorry your input node shouldn't have indegree edges and output node shouldn't have outdegree edges");
            return;
        }
        AllDirectedPaths<Integer, DefaultWeightedEdge> allDirectedPaths = new AllDirectedPaths<>(g);
        List<GraphPath<Integer, DefaultWeightedEdge>> paths = allDirectedPaths.getAllPaths(source, target, true, v);
      //  System.out.println(paths);
        List<Path> result = new ArrayList<>();
        outer:
        for (GraphPath<Integer, DefaultWeightedEdge> path : paths) {
            Double gain = 1.0;
            Set<Integer> vertices = new HashSet<>();
            for (DefaultWeightedEdge edge : path.getEdgeList()) {
                if (g.getEdgeSource(edge).equals(g.getEdgeTarget(edge))) {
                    continue outer;
                }
                vertices.add(g.getEdgeSource(edge));
                vertices.add(g.getEdgeTarget(edge));
                gain *= g.getEdgeWeight(edge);
            }
            Path newPath = new Path(vertices, gain, 1.0);
            Double nonTouchedLoopGains = 1.0;
            boolean ok = false ;
            for (Cycle cycle : cycles) {
                if (cycle.degree > 1) break;
                if (!newPath.isIntersectedWith(cycle)) {
                    ok = true ;
                    nonTouchedLoopGains *= cycle.gain;
                }
               // allLoopsGain *= cycle.gain;
            }
            if (ok)
                newPath.setDelta(1 - nonTouchedLoopGains );
            result.add(newPath);

        }
        this.paths = result;
    }

    private void fillTheCycles() {
        List<Cycle> result = new ArrayList<>();

        /**Get the first degree cycles **/
        /////////////////////////////////////////////////////////////////////////////////
      //  System.out.println(g);
        JohnsonSimpleCycles johnsonSimpleCycles = new JohnsonSimpleCycles<>(g);
        List<List<Integer>> cycles = johnsonSimpleCycles.findSimpleCycles();
        //System.out.println(cycles);
        for (List<Integer> cycle : cycles) {
            Set<Integer> vertices = new HashSet<>();
            Double gain = 1.0;
            int n = cycle.size();
            if (cycle.size() == 0) System.out.println("Error in getting cycles ");
            vertices.add(cycle.get(0));
            for (int i = 1; i < n; i++) {
                DefaultWeightedEdge edge = g.getEdge(cycle.get(i - 1), cycle.get(i));
                vertices.add(cycle.get(i));
                gain *= g.getEdgeWeight(edge);
            }
            gain *= g.getEdgeWeight(g.getEdge(cycle.get(n - 1), cycle.get(0)));
            result.add(new Cycle(vertices, gain, 1));
        }
//        System.out.println(result);
        /////////////////////////////////////////////////////////////////////////////////
        int n = result.size(); // size of one degree cycle
        int nxt = 0;
        while (true) {
            boolean ok = false;
            int sz = result.size();
            int st = nxt ;
            for (int i = 0; i < n; i++) {
                for (int j = st; j < sz; j++) {
                    if(j <= i ) continue;
                    Cycle union = result.get(i).getUnionIfDisjoint(result.get(j));
                    if (union != null) {
                        if (!ok) {
                            ok = true;
                            nxt = result.size();
                        }
                        result.add(union);
                    }
                }
            }
            if (!ok) {
                break;
            }
        }
        this.cycles = result;
    }


    public MasonMetaInformation fillMasonInformation() {
        fillTheCycles();
        fillThePaths();
        return new MasonMetaInformation(paths, cycles);
    }


    public static void main(String[] args) {
        GraphAdapter graphAdapter = new GraphAdapter(6,1,6);
        graphAdapter.addEdge(1, 2, 1);
        graphAdapter.addEdge(2, 5, 3);
        graphAdapter.addEdge(2, 3, 5);
        graphAdapter.addEdge(3, 2, -7);
        graphAdapter.addEdge(3, 4, 11);
        graphAdapter.addEdge(4, 5, 23);
        graphAdapter.addEdge(5, 4, -13);
        graphAdapter.addEdge(5, 2, -17);
        graphAdapter.addEdge(5, 6, 19);
        graphAdapter.fillTheCycles();
        graphAdapter.fillThePaths();
        System.out.println(graphAdapter.paths);
        System.out.println("<><><><><><><><><><><><><><><><><><>");
        System.out.println(graphAdapter.cycles);

    }


}
