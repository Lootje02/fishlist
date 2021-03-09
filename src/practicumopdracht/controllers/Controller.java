package practicumopdracht.controllers;


import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import practicumopdracht.views.View;

/**
 * This method <description of function>
 *
 * @author Lorenzo Bindemann
 */
public abstract class Controller {
//    private final FishController FISH_CONTROLLER = new FishController();
//    private final FishermanController FISHERMAN_CONTROLLER = new FishermanController();

    public abstract View getView();

    /**
     * generic function to show an alert
     * @param type
     * @param title
     * @param description
     */
    public void showAlert(
            Alert.AlertType type,
            String title,
            String description,
            String typeAlert
    ) {
        Alert errorAlert = new Alert(type);
        // set the default items for the alert
        errorAlert.setTitle(title);
        errorAlert.setHeaderText(description);
        Object let = errorAlert.getAlertType();
        // check if the type is a confirmation than change the functions and buttons
        if (let == Alert.AlertType.CONFIRMATION) {
            switch (typeAlert) {
                case "Delete" :
                    // set items to delete
                    ((Button) errorAlert.getDialogPane().lookupButton(ButtonType.OK)).setText("Ja, verwijder");
                    ((Button) errorAlert.getDialogPane().lookupButton(ButtonType.OK)).setOnAction(e -> deleteItemFromList());
                    ((Button) errorAlert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Nee, verwijder niet");
                    break;
                case "Exit" :
                    // set items to exit application
                    ((Button) errorAlert.getDialogPane().lookupButton(ButtonType.OK)).setText("Ja, sla eerst op");
                    ((Button) errorAlert.getDialogPane().lookupButton(ButtonType.OK)).setOnAction(e -> {
                        saveDetails();
                        exitApplication();
                    });
                    ((Button) errorAlert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Nee, sluit af");
                    ((Button) errorAlert.getDialogPane().lookupButton(ButtonType.CANCEL)).setOnAction(e -> exitApplication());
                    break;
                case "Save" :
                    // set items to exit application
                    ((Button) errorAlert.getDialogPane().lookupButton(ButtonType.OK)).setText("Ja, sla op");
                    ((Button) errorAlert.getDialogPane().lookupButton(ButtonType.OK)).setOnAction(e -> saveDetails());
                    ((Button) errorAlert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Nee, sla niet op");
                    break;
                case "Load" :
                    // set items to exit application
                    ((Button) errorAlert.getDialogPane().lookupButton(ButtonType.OK)).setText("Ja, laad gegevens");
                    ((Button) errorAlert.getDialogPane().lookupButton(ButtonType.OK)).setOnAction(e -> loadDetails());
                    ((Button) errorAlert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Nee, laad geen gegevens");
            }
        }
        // show the alert
        errorAlert.show();
    }

    /**
     * to delete fish from the list
     */
    public abstract void deleteItemFromList();

    public void exitApplication() {
        final int DEFAULT_EXIT_STATUS = 1;
        System.exit(DEFAULT_EXIT_STATUS);
    }

    public void saveDetails() {
        final FishController FISH_CONTROLLER = new FishController();
        final FishermanController FISHERMAN_CONTROLLER = new FishermanController();
        // save fisherman details
        boolean saveFisherman = FISHERMAN_CONTROLLER.saveItemToTextFile();
        // save fish details
        boolean saveFish = FISH_CONTROLLER.saveItemToTextFile();
        if (saveFish && saveFisherman) {
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Opslaan gelukt",
                    "Het opslaan van de data naar de text file is gelukt",
                    "Default"
            );
        } else {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Laden mislukt",
                    "Het opslaan van de data naar de text file is mislukt",
                    "Default"
            );
        }
    }
    public void loadDetails() {
        final FishController FISH_CONTROLLER = new FishController();
        final FishermanController FISHERMAN_CONTROLLER = new FishermanController();
        // save fisherman details
        boolean loadFisherman = FISHERMAN_CONTROLLER.loadItemFromTextFile();
        // save fish details
        boolean loadFish = FISH_CONTROLLER.loadItemFromTextFile();

        if (loadFish && loadFisherman) {
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Laden gelukt",
                    "Het laden van de text file is gelukt",
                    "Default"
            );
        } else {
            showAlert(
                  Alert.AlertType.ERROR,
                  "Laden mislukt",
                  "Het laden van de text file is mislukt",
                  "Default"
            );
        }
    }
}
