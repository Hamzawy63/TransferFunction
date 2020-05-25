package logic;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.cycle.Cycles;
import org.jgrapht.alg.cycle.JohnsonSimpleCycles;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.*;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        DefaultDirectedWeightedGraph <Integer, DefaultWeightedEdge> g = new DefaultDirectedWeightedGraph <>(DefaultWeightedEdge.class);

        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(4);

        g.addVertex(3);
        g.addVertex(5);
      //  DefaultWeightedEdge edge = g.addEdge(1, 2);
       // g.setEdgeWeight(edge, 10);
        g.setEdgeWeight(1 , 2 , 50);
        System.out.println(g);
        g.addEdge(1, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        g.addEdge(2, 2);
        g.addEdge(3, 4);

        JohnsonSimpleCycles cycle = new JohnsonSimpleCycles<Integer , DefaultWeightedEdge>(g);
      //  System.out.println(cycle.findSimpleCycles());
        AllDirectedPaths <Integer, DefaultWeightedEdge> allDirectedPaths = new AllDirectedPaths<>(g);
        List<GraphPath<Integer , DefaultWeightedEdge>> paths = allDirectedPaths.getAllPaths(1, 4 , true , 5) ;

        for(GraphPath<Integer , DefaultWeightedEdge> path : paths ) {
            System.out.println(path.getWeight());
            DefaultWeightedEdge edge = path.getEdgeList().get(0)  ;

            System.out.println(path);
        }


        System.out.println(paths);
    }
}
