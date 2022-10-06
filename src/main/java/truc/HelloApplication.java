package truc;

import Model.Model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import view.View;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    static File f = new File("");
    public static String p = f.getAbsolutePath() + "/pics/";
    public static boolean planet = true;
    // true = Endor, 5
    // false = Kamino, 12

    Model model = new Model(p);

    public HelloApplication() throws IOException, SAXException {
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Angry Planets !");
        Scene scene = new Scene(new View(model));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException, SAXException {
        launch(args);
    }
}