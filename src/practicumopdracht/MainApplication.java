package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.FishController;
import practicumopdracht.models.Fish;

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

        stage.setTitle(String.format("Practicumopdracht OOP2 - %s", Main.studentNaam));
        stage.setWidth(640);
        stage.setHeight(480);
        switchWindows(new FishController());
    }
    public static void switchWindows(Controller controller) {
        // to show the views in your screen
        stage.setScene(new Scene(controller.getView().getRoot()));
        stage.show();
    }
}
