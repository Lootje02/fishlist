package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import practicumopdracht.MainApplication;
import practicumopdracht.data.FishDAO;
import practicumopdracht.models.Fish;
import practicumopdracht.models.Fisherman;
import practicumopdracht.views.FishView;
import practicumopdracht.views.View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * This method <description of function>
 *
 * @author Lorenzo Bindemann
 */
public class FishController extends Controller {
    private FishView view;
    private FishDAO fishDAO;
    private Fish selectedFish;
    private Fisherman currentFisherman;

    public FishController(Fisherman fisherman) {
        this.currentFisherman = fisherman;
        view = new FishView();

        // set list
        fishDAO = MainApplication.getFishDAO();
        refreshList();
        // set fishermans in combobox
        view.getFISHERMAN_LIST().setItems(FXCollections.observableList(
                MainApplication.getFishermanDAO().getAll()
        ));
        view.getFISHERMAN_LIST().getSelectionModel().select(currentFisherman);
        view.getFISHERMAN_LIST().getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldFisherman, newFisherman) -> {
                    this.currentFisherman = newFisherman;
                }
        );
        // set actions on buttons
        view.getSWITCH_BUTTON().setOnAction(e -> switchToList());
        view.getSAVE_BUTTON().setOnAction(e -> addToFishList());
        view.getDELETE_BUTTON().setOnAction(e -> showAlert(
                Alert.AlertType.CONFIRMATION,
                "Delete",
                "Weet je zeker dat je dit wilt verwijderen?"
        ));

        view.getFishlist().getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldFish, newFish) -> {
                    selectedFish = newFish;
                })
        );
    }

    public void refreshList() {
        ObservableList<Fish> fishList = FXCollections.observableList(fishDAO.getAll());
        view.getFishlist().setItems(fishList);
    }

    public View getView() {
        return view;
    }

    /**
     * function to switch between the list and the detail page
     */
    public void switchToList() {
        MainApplication.switchWindows(new FishermanController());
    }

    /**
     * function to add items to the fish list
     */
    public void addToFishList() {
        final String ErrorText = checkValidation().toString();
        // check if there are errors or not
        if (!ErrorText.trim().equals("")) {
            // show alert if there are errors
            showAlert(
                Alert.AlertType.ERROR,
                "Error gevonden",
                ("Er zijn verschillende fouten geconstateerd\n" + ErrorText)
            );
        } else {
            // create object of fish
            Fish fish = createFishObject();
            // make fields default
            setAllFieldsToDefault();
            // show succes alert
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Succesvol",
                    ("Het opslaan van je vis is gelukt\n" + fish.toString())
            );
        }
    }

    /**
     * function to loop through array and set all the textfields to empty
     */
    public void setAllFieldsToDefault() {
        // empty textfields
        TextField[] textFields = {
                view.getTEXTFIELD_FISH_SPECIES(),
                view.getTEXTFIELD_FISH_LENGTH_IN_CM(),
                view.getTEXTFIELD_WEIGHT_IN_KG(),
                view.getTEXTFIELD_LOCATION(),
                view.getTEXTFIELD_BAIT(),
        };
        for (int i = 0; i < textFields.length; i++) {
            textFields[i].setText("");
            textFields[i].setStyle("-fx-border-color: default");
        }
        // empty date picker
        view.getDATEPICKER_CAUGHT_ON().setValue(null);
        view.getDATEPICKER_CAUGHT_ON().setStyle("-fx-border-color: default");
        // empty waterType
        view.getWATERTYPE_COMBOBOX().getSelectionModel().clearSelection();
        view.getWATERTYPE_COMBOBOX().setStyle("-fx-border-color: default");
        // checkboxes
        CheckBox[] checkboxes = {
                view.getPREFEED_CHECKBOX(),
                view.getGOT_ON_SIDE_CHECKBOX(),
        };
        final boolean DEFAULT_VALUE = false;
        for (int i = 0; i < checkboxes.length; i++) {
            checkboxes[i].setSelected(DEFAULT_VALUE);
        }
        // empty textarea
        view.getREMARK_TEXTAREA().setText("");
    }

    /**
     * function to check the form validation
     */
    public StringBuilder checkValidation() {
        // check if the fields are filled in
        StringBuilder errorText = new StringBuilder();
        // fish species
        if (view.getTEXTFIELD_FISH_SPECIES().getText().trim().equals("")) {
            errorText.append("\t- Vissoort is niet goed ingevuld\n");
            // make input red for recognition
            view.getTEXTFIELD_FISH_SPECIES().setStyle("-fx-border-color: red");
        }
        // date
        if (view.getDATEPICKER_CAUGHT_ON().getValue() == null) {
            errorText.append("\t- Datum is niet goed ingevuld\n");
            // make input red for recognition
            view.getDATEPICKER_CAUGHT_ON().setStyle("-fx-border-color: red");
        }
        // waterType
        if (view.getWATERTYPE_COMBOBOX().getSelectionModel().getSelectedItem() == null) {
            errorText.append("\t- Water type is niet goed ingevuld\n");
            // make input red for recognition
            view.getWATERTYPE_COMBOBOX().setStyle("-fx-border-color: red");
        }
        return errorText;
    }

    public Fish createFishObject() {
        // create variables to set in the fish object
        String fishSpecies = view.getTEXTFIELD_FISH_SPECIES().getText(),
               location = view.getTEXTFIELD_LOCATION().getText(),
               waterType = (String) view.getWATERTYPE_COMBOBOX().getSelectionModel().getSelectedItem(),
               bait = view.getTEXTFIELD_BAIT().getText(),
               remark = view.getREMARK_TEXTAREA().getText();
        LocalDate date = view.getDATEPICKER_CAUGHT_ON().getValue();
        int lengthInCm;
        double weightInKg;
        boolean prefeed = view.getPREFEED_CHECKBOX().isSelected(),
                gotOnside = view.getGOT_ON_SIDE_CHECKBOX().isSelected();

        // try to set length in variable
        final int NO_INPUT = 0;
        try {
            lengthInCm = Integer.parseInt(view.getTEXTFIELD_FISH_LENGTH_IN_CM().getText());
        } catch (Exception ex) {
            // if is not filled in set 0
            lengthInCm = 0;
        }
        // try to set weight in variable
        try {
            weightInKg = Double.parseDouble(view.getTEXTFIELD_WEIGHT_IN_KG().getText());
        } catch (Exception ex) {
            weightInKg = NO_INPUT;
        }
        // create object of input
         final Fish FISH = new Fish(
                fishSpecies,
                 currentFisherman.getId(),
                lengthInCm,
                weightInKg,
                date,
                location,
                waterType,
                bait,
                prefeed,
                gotOnside,
                remark
        );
        return FISH;
    }
}
