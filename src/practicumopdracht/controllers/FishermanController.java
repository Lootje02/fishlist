package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import practicumopdracht.MainApplication;
import practicumopdracht.comparators.FirstnameComparator;
import practicumopdracht.data.DAO;
import practicumopdracht.data.FakeFishermanDAO;
import practicumopdracht.data.FishDAO;
import practicumopdracht.data.FishermanDAO;
import practicumopdracht.models.Fish;
import practicumopdracht.models.Fisherman;
import practicumopdracht.views.FishermanView;
import practicumopdracht.views.View;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

/**
 * This method is the controller for the fisherman view and fisherman model
 *
 * @author Lorenzo Bindemann
 */
public class FishermanController extends Controller {
    private FishermanView view;
    private FishermanDAO fishermanDAO;
    private Fisherman selectedFisherman;

    /**
     * contructor for the fishermanController
     */
    public FishermanController() {
        view = new FishermanView();

        // set list
        fishermanDAO = MainApplication.getFishermanDAO();

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
        refreshList(false);
    }

    /**
     * function to set actions on all the buttons
     */
    public void setActionsOnButtons() {
        // toolbar
        // file buttons
        view.getITEM_EXIT().setOnAction(e -> showAlert(
                Alert.AlertType.CONFIRMATION,
                "Afsluiten",
                "Wil je voor het afsluiten de data nog opslaan?",
                "Exit"
        ));
        view.getITEM_SAVE().setOnAction(e -> showAlert(
                Alert.AlertType.CONFIRMATION,
                "Opslaan",
                "Weet je zeker dat je de gegevens wilt opslaan?",
                "Save"
        ));
        view.getITEM_LOAD().setOnAction(e -> showAlert(
                Alert.AlertType.CONFIRMATION,
                "Opslaan",
                "Weet je zeker dat je de gegevens wilt laden?",
                "Load"
        ));
        // sort buttons
        view.getSORT_AZ().setOnAction(e -> refreshList(false));
        view.getSORT_ZA().setOnAction(e -> refreshList(true));
        // other buttons
        view.getNEW_BUTTON().setOnAction(e -> setAllFieldsToDefault());
        view.getSWITCH_BUTTON().setOnAction(e -> SwitchToDetails());
        view.getADD_BUTTON().setOnAction(e -> addFishermanToListOrUpdate());
        view.getDELETE_BUTTON().setOnAction(e -> showAlert(
                Alert.AlertType.CONFIRMATION,
                "Delete",
                "Weet je zeker dat je dit wilt verwijderen?",
                "Delete"
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

    /**
     * function to load fishermans from a text file
     *
     * @return
     */
    public boolean loadItemFromTextFile() {
        return fishermanDAO.load();
    }

    /**
     * function to save fishermans to a text file
     *
     * @return
     */
    public boolean saveItemToTextFile() {
        return fishermanDAO.save();
    }

    /**
     * function to disable buttons
     */
    public void disableButtons() {
        view.getDELETE_BUTTON().setDisable(true);
        view.getSWITCH_BUTTON().setDisable(true);
        view.getNEW_BUTTON().setDisable(true);
    }

    /**
     * function to enable buttons
     */
    public void enableButtons() {
        view.getDELETE_BUTTON().setDisable(false);
        view.getSWITCH_BUTTON().setDisable(false);
        view.getNEW_BUTTON().setDisable(false);
    }

    public void refreshList(boolean sortingType) {
        ObservableList<Fisherman> fishermanList = FXCollections.observableArrayList(fishermanDAO.getAll());
        Comparator<Fisherman> activeComparator = new FirstnameComparator(sortingType);
        FXCollections.sort(fishermanList, activeComparator);
        view.getFishermanList().setItems(fishermanList);
    }

    /**
     * function to add a Fisherman to the list or update it
     */
    public void addFishermanToListOrUpdate() {
        final String ErrorText = checkInputfields().toString();
        // check if there are errors or not
        if (!ErrorText.trim().equals("")) {
            // show alert if there are errors
            showAlert(
                    Alert.AlertType.ERROR,
                    "Errors gevonden",
                    ("Er zijn verschillende fouten geconstateerd\n" + ErrorText),
                    "Default"
            );
        } else {
            // create object of fish
            Fisherman fisherman =
                    selectedFisherman != null
                            ? createUpdatedFishermanObject(selectedFisherman)
                            : createFishermanObject();
            // add fisherman to list
            fishermanDAO.addOrUpdate(fisherman);
            // refresh the list
            refreshList(false);
            // make fields default
            setAllFieldsToDefault();
            // show succes alert
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Succesvol",
                    ("Het opslaan van je visser is gelukt\n" + fisherman.toString()),
                    "Default"
            );
        }
    }

    /**
     * function to update selected fisherman
     * @param fisherman
     * @return
     */
    public Fisherman createUpdatedFishermanObject(Fisherman fisherman) {
        // get fisherman details
        String firstname = view.getTEXTFIELD_FIRSTNAME().getText(),
                lastname = view.getTEXTFIELD_LASTNAME().getText(),
                city = (String) view.getTEXTFIELD_CITY().getText();
        LocalDate date = view.getDATEPICKER_DATE_OF_BIRTH().getValue();
        // update the details
        fisherman.setFirstname(firstname);
        fisherman.setLastname(lastname);
        fisherman.setDate_of_birth(date);
        fisherman.setCity(city);
        // return the updated version
        return fisherman;
    }

    /**
     * function to create a fisherman object
     *
     * @return
     */
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

    /**
     * function to clear all the fields
     */
    public void setAllFieldsToDefault() {
        // set selected object to null
        selectedFisherman = null;
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
     *
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

    /**
     * @return
     * @Override function to return the view
     */
    @Override
    public View getView() {
        return view;
    }

    /**
     * @Override functio nto delete fisherman from the list
     */
    @Override
    public void deleteItemFromList() {
        // create FishDao to get all the fishes for the selected fisherman
        FishDAO fishDAO = MainApplication.getFishDAO();
        List<Fish> fishForSelectedFisherman = fishDAO.getAllFor(selectedFisherman);
        // delete all the fishes
        for (Fish fish : fishForSelectedFisherman) {
            fishDAO.remove(fish);
        }
        // remove the fisherman from the list
        fishermanDAO.remove(selectedFisherman);
        refreshList(false);
    }
}
