package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.FishermanController;
import practicumopdracht.data.*;

public class MainApplication extends Application {
    private static Stage stage;
    private static FishermanDAO fishermanDAO;
    private static FishDAO fishDAO;

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

        // set the window details
        stage.setTitle(String.format("Practicumopdracht OOP2 - %s", Main.studentNaam));
        final int STAGE_WIDTH = 640;
        final int STAGE_HEIGHT = 700;
        stage.setMinWidth(STAGE_WIDTH);
        stage.setWidth(STAGE_WIDTH);
        stage.setHeight(STAGE_HEIGHT);

        // create instance of the DAO's
        fishermanDAO = new BinaryFishermanDAO();
        fishDAO = new ObjectFishDAO();

        // call the switchWindows function on start to get first the fishermanView
        switchWindows(new FishermanController());
    }

    /**
     * function to switch the views
     * @param controller
     */
    public static void switchWindows(Controller controller) {
        // to show the views in your screen
        stage.setScene(new Scene(controller.getView().getRoot()));
        stage.show();
    }

    /**
     * getter for the fishDAO
     * @return
     */
    public static FishDAO getFishDAO() {
        return fishDAO;
    }

    /**
     * getter for the fishermanDAO
     * @return
     */
    public static FishermanDAO getFishermanDAO() {
        return fishermanDAO;
    }
}
