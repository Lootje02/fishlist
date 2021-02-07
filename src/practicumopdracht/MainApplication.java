package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) {
        if(!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

        // grid
        HBox hbox1 = new HBox();
        VBox vbox1 = new VBox();

        // title
        Label title = new Label("Visvangsten");
        title.setStyle("-fx-font-size: 20px;");
        hbox1.getChildren().addAll(title);

        // textfields
        TextField categoryFish = new TextField("Vis soort");
        vbox1.getChildren().addAll(categoryFish);

        // pane
        hbox1.getChildren().addAll(vbox1);
        StackPane pane = new StackPane();
        pane.getChildren().addAll(hbox1);




        pane.setStyle("-fx-border-color: red; -fx-background-color: lightgray");

        Scene scene = new Scene(pane, 800, 1000);
        stage.setTitle(String.format("Practicumopdracht OOP2 - %s", Main.studentNaam));
        stage.setScene(scene);
        stage.show();
    }
}
