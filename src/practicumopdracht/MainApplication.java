package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.FishermanController;

public class MainApplication extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) {
        if(!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);
            return;
        }
        // to set the parameter stage in the MainApplication stage property
        MainApplication.stage = stage;

        // icon
        stage.getIcons().add(new Image("https://is2-ssl.mzstatic.com/image/thumb/Purple62/v4/7c/96/aa/7c96aa23-1215-93e9-b719-95d30fe6603a/source/512x512bb.jpg"));

        stage.setTitle(String.format("Practicumopdracht OOP2 - %s", Main.studentNaam));
        stage.setMinWidth(640);
        stage.setWidth(640);
        stage.setHeight(700);
        switchWindows(new FishermanController());
    }
    public static void switchWindows(Controller controller) {
        // to show the views in your screen
        stage.setScene(new Scene(controller.getView().getRoot()));
        stage.show();
    }
}
