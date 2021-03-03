package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import practicumopdracht.MainApplication;
import practicumopdracht.data.DAO;
import practicumopdracht.data.FakeFishermanDAO;
import practicumopdracht.data.FishermanDAO;
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
    private FishermanDAO fishermanDAO;
    private Fisherman selectedFisherman;

    public FishermanController() {
        view = new FishermanView();

        // set list
        fishermanDAO = MainApplication.getFishermanDAO();
        refreshList();

        // set actions on buttons
        setActionsOnButtons();
        // if nothing selected set on start the buttons to disabled
        disableButtons();
        // set select item
        view.getFishermanList().getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldFisherman, newFisherman) -> {
                    selectedFisherman = newFisherman;
                    // set item in inputfields
                    setItemInFields();
                    // set buttons on enabled
                    enableButtons();
                }
        );
    }

    /**
     * call the setter function for all the fields of the fisherman modal
     */
    public void updateProfile() {
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
            // firstname
            selectedFisherman.setFirstname(
                    view.getTEXTFIELD_FIRSTNAME().getText()
            );
            // lastname
            selectedFisherman.setLastname(
                    view.getTEXTFIELD_LASTNAME().getText()
            );
            // date of birth
            selectedFisherman.setDate_of_birth(
                    view.getDATEPICKER_DATE_OF_BIRTH().getValue()
            );
            // city
            selectedFisherman.setCity(
                    view.getTEXTFIELD_CITY().getText()
            );
            // add fisherman to list
            fishermanDAO.addOrUpdate(selectedFisherman);
            // refresh the list
            refreshList();
            // make fields default
            setAllFieldsToDefault();
            // show succes alert
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Succesvol",
                    ("Het aanpassen van je visser is gelukt\n" + selectedFisherman.toString())
            );
            // after changing the values update the list
            refreshList();
        }
    }

    /**
     * function to set actions on all the buttons
     */
    public void setActionsOnButtons() {
        view.getEDIT_BUTTON().setOnAction(e -> updateProfile());
        view.getSWITCH_BUTTON().setOnAction(e -> SwitchToDetails());
        view.getADD_BUTTON().setOnAction(e -> addFishermanToList());
        view.getDELETE_BUTTON().setOnAction(e -> showAlert(
                Alert.AlertType.CONFIRMATION,
                "Delete",
                "Weet je zeker dat je dit wilt verwijderen?"
        ));
    }

    /**
     * call the getters and set the values in the inputfields
     */
    public void setItemInFields() {
        // firstname
        view.getTEXTFIELD_FIRSTNAME().setText(
                selectedFisherman.getFirstname()
        );
        // lastname
        view.getTEXTFIELD_LASTNAME().setText(
                selectedFisherman.getLastname()
        );
        // date of birth
        view.getDATEPICKER_DATE_OF_BIRTH().setValue(
                selectedFisherman.getDate_of_birth()
        );
        // city
        view.getTEXTFIELD_CITY().setText(
                selectedFisherman.getCity()
        );
    }

    public void disableButtons() {
        view.getDELETE_BUTTON().setDisable(true);
        view.getSWITCH_BUTTON().setDisable(true);
        view.getEDIT_BUTTON().setDisable(true);
    }

    public void enableButtons() {
        view.getDELETE_BUTTON().setDisable(false);
        view.getSWITCH_BUTTON().setDisable(false);
        view.getEDIT_BUTTON().setDisable(false);
    }

    public void refreshList() {
        ObservableList<Fisherman> fishermanList = FXCollections.observableList(fishermanDAO.getAll());
        view.getFishermanList().setItems(fishermanList);
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
            // add fisherman to list
            fishermanDAO.addOrUpdate(fisherman);
            // refresh the list
            refreshList();
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

    /**
     * function to check all the input fields on error
     * @return
     */
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

    public void setFishermanList() {
//        FakeFishermanDAO.load();
    }

    /**
     * function to switch the views
     */
    public void SwitchToDetails() {
        MainApplication.switchWindows(
                new FishController(
                        this.selectedFisherman
                )
        );
    }

    @Override
    public View getView() {
        return view;
    }
}
