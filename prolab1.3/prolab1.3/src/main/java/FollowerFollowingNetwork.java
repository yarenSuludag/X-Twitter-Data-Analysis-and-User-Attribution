import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class FollowerFollowingNetwork extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JUNG Network Visualization");

        SwingNode swingNode = new SwingNode();

        createAndSetSwingContent(swingNode);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(swingNode);

        Scene scene = new Scene(borderPane, 600, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void createAndSetSwingContent(final SwingNode swingNode) {

        SwingUtilities.invokeLater(() -> {
            Graph<String, String> graph = null;
            try {
                graph = createGraph();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // Create the layout
            Layout<String, String> layout = new CircleLayout<>(graph);

            // Create the visualization viewer with the layout
            //VisualizationViewer<String, String> vv = new VisualizationViewer<>(layout);
            VisualizationViewer<String, String> vv = new VisualizationViewer<>(new CircleLayout<>(graph));
            vv.getRenderContext().setEdgeShapeTransformer(EdgeShape.line(graph));
            vv.getRenderContext().setEdgeArrowPredicate(edge -> true);
            //vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<U, String>());
            // vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.lightGray));
            vv.getRenderContext().setVertexLabelTransformer(v -> v.toString());

            swingNode.setContent(vv);
        });
    }

    private Graph<String, String> createGraph() throws IOException {
        Graph<String, String> graph = new SparseMultigraph<>();
        User[] people = CsvReader.ReadCsv("user_dataset.csv", 5);
        for(User person : people){
            if(!graph.getVertices().contains(person.getUsername())){
                graph.addVertex(person.getUsername());
            }
            int count = 0;
            for(String follower : person.getFollowers()){
                count+= 1;

                if(!graph.getVertices().contains(follower)){
                    graph.addVertex(follower);
                    graph.addEdge(follower + "-" + person.getUsername(), follower ,person.getUsername());
                }
                if(count == 5){
                    break;
                }

            }
            count = 0;
            for(String following : person.getFollowingUsers()){
                count+= 1;
                if(!graph.getVertices().contains(following)){
                    graph.addVertex(following);
                    graph.addEdge(person.getUsername() + "-" + following, person.getUsername(), following);
                }
                if(count == 5){
                    break;
                }
            }


        }

        return graph;
    }


}
