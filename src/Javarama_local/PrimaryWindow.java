package Javarama_local;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.net.URLConnection;

/**
 * lessonBuilder.java
 *
 * @author Isaac Hutchinson
 * @version 0.0.1
 */
public class PrimaryWindow extends Application {
    private boolean connection;

    public PrimaryWindow() {

    }

    /**
     * The start method sets the primary stage to the home scene after testing for a network connection. If connection
     * fails a network error screen is set as the current scene.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            URL url = new URL("http://www.google.com");
            URLConnection testConnection = url.openConnection();
            testConnection.connect();
            connection = true;
        } catch (Exception e) {
            connection = false;
        }

        if (connection) {
            primaryStage.setTitle("Javarama");
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("HomeScreenLayout.fxml"));
            Pane myPane = (Pane) myLoader.load();
            Controller controller = (Controller) myLoader.getController();
            controller.setPrevStage(primaryStage);
            Scene myScene = new Scene(myPane);

            primaryStage.setScene(myScene);
            primaryStage.show();
        } else {
            primaryStage.setTitle("Javarama Error: Networking");
            StackPane stackPane = new StackPane();
            Image neterror = new Image("Javarama_local/Images/nerror.jpeg");
            ImageView imageView = new ImageView(neterror);
            stackPane.getChildren().add(imageView);

            primaryStage.setScene(new Scene(stackPane, 1280, 720));
            primaryStage.show();
        }

    }


}
