package nl.nicolassoftware.phonowave.frontend.desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Hello World!");
            primaryStage.setFullScreen(true);
            primaryStage.show();
        }
        catch(Exception ex)
        {
            ex.printStackTrace(System.out);
        }
    }
}
