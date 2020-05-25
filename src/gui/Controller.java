package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import logic.GraphAdapter;
import logic.MasonMetaInformation;

public class Controller {
    @FXML
    private TextField numberOfNodes;
    @FXML
    private TextField sourceNode;
    @FXML
    private TextField sinkNode;
    @FXML
    private ListView listView;
    @FXML
    private ListView answerTextArea;
    @FXML
    private Button addEdgeBtn;
    @FXML
    private Button solveBtn;
    @FXML
    private TextField addEdgeTextField;

    private GraphViewer graphViewer = GraphViewer.getInstance();
    private GraphAdapter graphAdapter;

    private boolean fillGraphInformation(String s1, String s2, String s3) {
        if (graphAdapter == null) {
            graphAdapter = new GraphAdapter(Integer.valueOf(numberOfNodes.getText()));
        }
        try {
            Integer v1 = Integer.valueOf(s1);
            Integer v2 = Integer.valueOf(s2);
            Double w = Double.valueOf(s3);
            graphAdapter.addEdge(v1, v2, w);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @FXML
    private void addNewEdge(ActionEvent actionEvent) {
        String[] s = addEdgeTextField.getText().split("\\s+");
        if (s.length == 3) {
            if (!fillGraphInformation(s[0], s[1], s[2])) {
                System.out.println("corrupted input ");
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Input not valid");
                errorAlert.setContentText("vertices should be between 1 :" + numberOfNodes.getText() + "\n"
                        + "You should not repeat edges twice\n ");
                errorAlert.showAndWait();
                return;
            }
            graphViewer.addEdge(s[0], s[1], s[2]);
            addEdgeTextField.setText("");
            listView.setEditable(true);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s[0] + "\t");
            stringBuilder.append(s[1] + "\t");
            stringBuilder.append(s[2] + "\t");
            stringBuilder.append("\n");
            listView.getItems().add(stringBuilder.toString());
        }
    }

    @FXML
    private void solveBtnAction(ActionEvent actionEvent) {
        MasonMetaInformation masonMetaInformation = graphAdapter.fillMasonInformation();
        answerTextArea.getItems().add(masonMetaInformation.getTransferFunction());
    }

    @FXML
    private void checkEntries(MouseEvent mouseEvent) {
        if (sourceNode.getText().length() == 0 || sinkNode.getText().length() == 0 || numberOfNodes.getText().length() == 0) {
            addEdgeTextField.setDisable(true);
            listView.setDisable(true);
            solveBtn.setDisable(true);
            addEdgeBtn.setDisable(true);
            return;
        }
        try {
            int no = Integer.valueOf(numberOfNodes.getText());
            int souce = Integer.valueOf(sourceNode.getText());
            int sink = Integer.valueOf(sinkNode.getText());
            if (souce <= no && sink <= no) {
                addEdgeTextField.setDisable(false);
                listView.setDisable(false);
                solveBtn.setDisable(false);
                addEdgeBtn.setDisable(false);
                numberOfNodes.setDisable(true);
                sourceNode.setDisable(true);
                sinkNode.setDisable(true);
            }
        } catch (Exception e) {
            System.out.println("Exception in controller \"Check Entry method\"");
        }
    }
}
