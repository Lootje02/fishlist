package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import practicumopdracht.MainApplication;
import practicumopdracht.comparators.LengthComparator;
import practicumopdracht.comparators.SpeciesComparator;
import practicumopdracht.data.FishDAO;
import practicumopdracht.models.Fish;
import practicumopdracht.models.Fisherman;
import practicumopdracht.views.FishView;
import practicumopdracht.views.View;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * This method is the controller for the fish view and model
 *
 * @author Lorenzo Bindemann
 */
public class FishController extends Controller {
    private FishView view;
    private final FishDAO fishDAO;
    private Fish selectedFish;
    private Fisherman currentFisherman;

    /**
     * constructor for the fishController with all the details
     * @param fisherman
     */
    public FishController(Fisherman fisherman) {
        // set the selected fisherman in the field
        this.currentFisherman = fisherman;

        view = new FishView();

        // set list
        fishDAO = MainApplication.getFishDAO();
        refreshList("species", false);
        // set fishermans in combobox
        view.getFISHERMAN_LIST().setItems(FXCollections.observableList(
                MainApplication.getFishermanDAO().getAll()
        ));
        // if nothing selected set on start the buttons to disabled
        disableButtons();
        // set the selected fisherman in the selection
        view.getFISHERMAN_LIST().getSelectionModel().select(currentFisherman);
        // set listener to the fisherman combobox
        view.getFISHERMAN_LIST().getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldFisherman, newFisherman) -> {
                    currentFisherman = newFisherman;
                    refreshList("species", false);
                })
        );
        // set actions on buttons
        setActionsOnButtons();

        view.getFishlist().getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldFish, newFish) -> {
                    selectedFish = newFish;
                    setFishInFields();
                    // set buttons on enabled
                    enableButtons();
                })
        );
    }

    /**
     * default constructor for the fishcontroller
     */
    public FishController() {
        fishDAO = MainApplication.getFishDAO();
    }

    /**
     * function to sort and refresh the list by pressing a radiobutton
     * @param radioButton
     */
    public void toggleRadioButton(String radioButton) {
        switch (radioButton) {
            case "0":
                refreshList("species", false);
                break;
            case "1":
                refreshList("species", true);
                break;
            case "2":
                refreshList("length", false);
                break;
            case "3":
                refreshList("length", true);
                break;
        }
    }

    /**
     * function to set all the actions on the buttons of the view
     */
    public void setActionsOnButtons() {
        // toggle group (radio buttons)
        view.getSORTING_TOGGLE_GROUP().selectedToggleProperty().addListener((observableValue, oldSelected, newSelected) -> {
            toggleRadioButton(newSelected.getUserData().toString());
        });
        // buttons
        view.getNEW_BUTTON().setOnAction(e -> setAllFieldsToDefault());
        view.getSWITCH_BUTTON().setOnAction(e -> switchToList());
        view.getSAVE_BUTTON().setOnAction(e -> addToFishList());
        view.getDELETE_BUTTON().setOnAction(e -> showAlert(
                Alert.AlertType.CONFIRMATION,
                "Delete",
                "Weet je zeker dat je dit wilt verwijderen?",
                "Delete"
        ));
    }

    /**
     * function to set a selected fish in the input fields
     */
    public void setFishInFields() {
        // textfield array
        TextField[] textfields = {
                view.getTEXTFIELD_FISH_SPECIES(),

                view.getTEXTFIELD_WEIGHT_IN_KG(),
                view.getTEXTFIELD_LOCATION(),
                view.getTEXTFIELD_BAIT(),
        };
        // species
        view.getTEXTFIELD_FISH_SPECIES().setText(
                selectedFish.getFishSpecies()
        );
        // length
        view.getTEXTFIELD_FISH_LENGTH_IN_CM().setText(
                String.valueOf(selectedFish.getFishLengthInCm())
        );
        // weight
        view.getTEXTFIELD_WEIGHT_IN_KG().setText(
                String.valueOf(selectedFish.getWeightInKg())
        );
        // caught on
        view.getDATEPICKER_CAUGHT_ON().setValue(
                selectedFish.getCaughtOn()
        );
        // Location
        view.getTEXTFIELD_LOCATION().setText(
                selectedFish.getLocation()
        );
        // watertype
        view.getWATERTYPE_COMBOBOX().getSelectionModel().select(
                selectedFish.getWaterType()
        );
        // Bait
        view.getTEXTFIELD_BAIT().setText(
                selectedFish.getBait()
        );
        // prefeed
        view.getPREFEED_CHECKBOX().setSelected(
                selectedFish.isPrefeed()
        );
        // got on side
        view.getGOT_ON_SIDE_CHECKBOX().setSelected(
                selectedFish.isGotOnTheSide()
        );
        // remark
        view.getREMARK_TEXTAREA().setText(
                selectedFish.getRemark()
        );
    }

    /**
     * function to load items from textfile
     * @return
     */
    public boolean loadItemFromTextFile() {
        return fishDAO.load();
    }

    /**
     * funciton to save items to text file
     * @return
     */
    public boolean saveItemToTextFile() {
        return fishDAO.save();
    }

    /**
     * function to refresh the list fishlist
     * @param sortingType
     * @param order
     */
    public void refreshList(String sortingType, Boolean order) {
        ObservableList<Fish> fishList = FXCollections.observableList(fishDAO.getAllFor(currentFisherman));
        fishList = sortList(fishList, sortingType, order);
        view.getFishlist().setItems(fishList);
    }

    /**
     * function to sort the list on all the different types
     * @param fishList
     * @param sortingType
     * @param order
     * @return
     */
    public ObservableList<Fish> sortList(
            ObservableList<Fish> fishList,
            String sortingType,
            boolean order
    ) {
        // to check which Compartor object should be created
        String[] sortableTypes = {"species", "length"};
        Comparator<Fish> activeComparator;
        if (sortingType == sortableTypes[0]) {
            activeComparator = new SpeciesComparator(order);
        } else {
            activeComparator = new LengthComparator(order);
        }
        FXCollections.sort(fishList, activeComparator);
        return fishList;
    }

    /**
     * function to disable the buttons
     */
    public void disableButtons() {
        view.getDELETE_BUTTON().setDisable(true);
        view.getNEW_BUTTON().setDisable(true);
    }

    /**
     * function to enable the buttons
     */
    public void enableButtons() {
        view.getDELETE_BUTTON().setDisable(false);
        view.getNEW_BUTTON().setDisable(false);
    }

    /**
     * function to return the view
     * @return
     */
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
                    ("Er zijn verschillende fouten geconstateerd\n" + ErrorText),
                    "Default"
            );
        } else {
            // create object of fish
            Fish fish =
                    selectedFish != null
                            ? selectedFish
                            : createFishObject();
            // add the fish to the list
            fishDAO.addOrUpdate(fish);
            // refresh the list
            refreshList("species", false);
            // make fields default
            setAllFieldsToDefault();
            // show succes alert
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Succesvol",
                    ("Het opslaan van je vis is gelukt\n" + fish.toString()),
                    "Default"
            );
        }
    }

    /**
     * function to loop through array and set all the textfields to empty
     */
    public void setAllFieldsToDefault() {
        // clear the selected object
        selectedFish = null;
        // disable the buttons again because fields are empty
        disableButtons();
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

    /**
     * function to create a Fish object
     * @return
     */
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
                currentFisherman,
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

    /**
     * @Override function to delete an fish from the fishlist
     */
    @Override
    public void deleteItemFromList() {
        fishDAO.remove(selectedFish);
        refreshList("species", false);
    }
}
