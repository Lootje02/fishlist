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
            String description
    ) {
        Alert errorAlert = new Alert(type);
        errorAlert.setTitle(title);
        Object let = errorAlert.getAlertType();
        if (let == Alert.AlertType.CONFIRMATION) {
            ((Button) errorAlert.getDialogPane().lookupButton(ButtonType.OK)).setText("Ja, verwijder");
            ((Button) errorAlert.getDialogPane().lookupButton(ButtonType.OK)).setOnAction(e -> deleteFishFromList());
            ((Button) errorAlert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Nee, verwijder niet");
        }
        errorAlert.setHeaderText(description);
        errorAlert.showAndWait();
    }

    /**
     * to delete fish from the list
     */
    public void deleteFishFromList() {

    }
}
