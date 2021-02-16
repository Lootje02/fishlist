package practicumopdracht.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Fish;
import practicumopdracht.models.Fisherman;
import practicumopdracht.views.FishermanView;
import practicumopdracht.views.View;

import java.time.LocalDate;

/**
 * This method <description of function>
 *
 * @author Lorenzo Bindemann
 */
public class FishermanController extends Controller {
    private FishermanView view;

    public FishermanController() {
        view = new FishermanView();
        // set actions on buttons
        view.getSWITCH_BUTTON().setOnAction(e -> SwitchToDetails());
        view.getADD_BUTTON().setOnAction(e -> addFishermanToList());
        view.getDELETE_BUTTON().setOnAction(e -> showAlert(
                Alert.AlertType.CONFIRMATION,
                "Delete",
                "Weet je zeker dat je dit wilt verwijderen?"
        ));
    }

    public void addFishermanToList() {
        final String ErrorText = checkInputfields().toString();
        // check if there are errors or not
        if (!ErrorText.trim().equals("")) {
            // show alert if there are errors
            showAlert(
                    Alert.AlertType.ERROR,
                    "Errors gevonden",
                    ("Er zijn verschillende fouten geconstateerd\n" + ErrorText)
            );
        } else {
            // create object of fish
            Fisherman fisherman = createFishermanObject();
            // make fields default
            setAllFieldsToDefault();
            // show succes alert
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Succesvol",
                    ("Het opslaan van je visser is gelukt\n" + fisherman.toString())
            );
        }
    }

    public Fisherman createFishermanObject() {
        // set all the fields in variables
        String firstname = view.getTEXTFIELD_FIRSTNAME().getText(),
                lastname = view.getTEXTFIELD_LASTNAME().getText(),
                city = (String) view.getTEXTFIELD_CITY().getText();
        LocalDate date = view.getDATEPICKER_DATE_OF_BIRTH().getValue();
        // create Fisherman object
        Fisherman fisherman = new Fisherman(
                firstname,
                lastname,
                date,
                city
        );
        return fisherman;
    }

    public void setAllFieldsToDefault() {
        // empty textfields
        TextField[] textFields = {
                view.getTEXTFIELD_FIRSTNAME(),
                view.getTEXTFIELD_LASTNAME(),
                view.getTEXTFIELD_CITY(),
        };
        for (int i = 0; i < textFields.length; i++) {
            textFields[i].setText("");
            textFields[i].setStyle("-fx-border-color: default");
        }
        // empty date picker
        view.getDATEPICKER_DATE_OF_BIRTH().setValue(null);
        view.getDATEPICKER_DATE_OF_BIRTH().setStyle("-fx-border-color: default");
    }

    public StringBuilder checkInputfields() {
        // check if the fields are filled in
        StringBuilder errorText = new StringBuilder();
        // firstname
        if (view.getTEXTFIELD_FIRSTNAME().getText().trim().equals("")) {
            errorText.append("\t- Voornaam is niet goed ingevuld\n");
            // make input red for recognition
            view.getTEXTFIELD_FIRSTNAME().setStyle("-fx-border-color: red");
        }
        // lastname
        if (view.getTEXTFIELD_LASTNAME().getText().trim().equals("")) {
            errorText.append("\t- Achternaam is niet goed ingevuld\n");
            // make input red for recognition
            view.getTEXTFIELD_LASTNAME().setStyle("-fx-border-color: red");
        }
        // date of birth
        if (view.getDATEPICKER_DATE_OF_BIRTH().getValue() == null) {
            errorText.append("\t- Geboortedatum is niet goed ingevuld\n");
            // make input red for recognition
            view.getDATEPICKER_DATE_OF_BIRTH().setStyle("-fx-border-color: red");
        }
        // city
        if (view.getTEXTFIELD_CITY().getText().trim().equals("")) {
            errorText.append("\t- Woonplaats is niet goed ingevuld\n");
            // make input red for recognition
            view.getTEXTFIELD_CITY().setStyle("-fx-border-color: red");
        }
        return errorText;
    }

    public void SwitchToDetails() {
        MainApplication.switchWindows(new FishController());
    }

    @Override
    public View getView() {
        return view;
    }
}
