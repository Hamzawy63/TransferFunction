package gui;

import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GraphViewer graphViewer = GraphViewer.getInstance() ;
        SmartGraphPanel<String, String> graphView = graphViewer.getGraphPanel();
        graphView.setPrefSize(519.0, 554.0);
        graphView.setLayoutX(294.0);
        graphView.setLayoutY(-3.0);
//... see example below
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Single Flow Graph");
        ((Pane) root).getChildren().add(graphView);
        primaryStage.setScene(new Scene(root, 820, 554.0));
        // <VBox fx:id="graphViewer" layoutX="294.0" layoutY="" prefHeight="554.0" prefWidth="519.0" />
        // Stage stage = new Stage(StageStyle.DECORATED);
        //stage.setTitle("JavaFXGraph Visualization");
        // stage.setScene(scene);
        //stage.show();
        primaryStage.show();

//IMPORTANT - Called after scene is displayed so we can have width and height values
        graphView.init();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
