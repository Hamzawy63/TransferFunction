package gui;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;
import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class GraphViewer {
    private static GraphViewer graphViewer;
    private  Graph<String, String> g;
    private  SmartPlacementStrategy strategy;
    private  SmartGraphPanel<String, String> graphView;
    private  Set<Pair<String, String>> edges;
    private  Set<String> vertices;
    private  String spaces = "";
    private GraphViewer() {
        g = new GraphEdgeList<>();
        strategy = new SmartCircularSortedPlacementStrategy();
        graphView = new SmartGraphPanel<>(g, strategy);
        edges = new HashSet<>();
        vertices = new HashSet<>();
    }

    public SmartGraphPanel getGraphPanel() {
        return graphView;
    }

    public  void addEdge(String v1, String v2, String weight) {
        weight += spaces ;
        spaces+=" ";
        if (!edges.contains(new Pair(v1, v2))) {
            if (!vertices.contains(v1)) {
                vertices.add(v1);
                g.insertVertex(v1);

            }
            if (!vertices.contains(v2)) {
                vertices.add(v2);
                g.insertVertex(v2);
            }
            g.insertEdge(v1, v2, weight);
            edges.add(new Pair<>(v1, v2));
            graphView.update();
        }
    }

    public static GraphViewer getInstance() {
        if (graphViewer == null) {
            graphViewer = new GraphViewer();
        }
        return graphViewer;
    }

}
