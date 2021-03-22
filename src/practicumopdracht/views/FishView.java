package practicumopdracht.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.data.FishermanDAO;
import practicumopdracht.models.Fish;
import practicumopdracht.models.Fisherman;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * This method is the file for the view of a fish
 *
 * @author Lorenzo Bindemann
 */
public class FishView extends View{
    // listview
    private ListView<Fish> fishlist;
    // labels
    private final Label LABEL_FISHERMAN = new Label("Visser");
    private final Label LABEL_FISH_SPECIES = new Label("Vissoort");
    private final Label LABEL_FISH_LENGTH_IN_CM = new Label("Lengte van de vis");
    private final Label LABEL_WEIGHT_IN_KG = new Label("Gewicht van de vis");
    private final Label LABEL_CAUGHT_ON = new Label("Gevangen op");
    private final Label LABEL_LOCATION = new Label("Locatie");
    private final Label LABEL_BAIT = new Label("Aas");
    private final Label LABEL_PREFEED = new Label("Voor gevoerd");
    private final Label LABEL_GOT_ON_THE_SIDE = new Label("Op de kant gekregen");
    private final Label LABEL_REMARK = new Label("Opmerking");
    private final Label LABEL_WATER_TYPE = new Label("Water soort");
    // text fields
    private final TextField TEXTFIELD_FISH_SPECIES = new TextField();
    private final TextField TEXTFIELD_FISH_LENGTH_IN_CM = new TextField();
    private final TextField TEXTFIELD_WEIGHT_IN_KG = new TextField();
    private final TextField TEXTFIELD_LOCATION = new TextField();
    private final TextField TEXTFIELD_BAIT = new TextField();
    // date picker
    private final DatePicker DATEPICKER_CAUGHT_ON = new DatePicker();
    // combobox
    private final ComboBox<Fisherman> FISHERMAN_LIST = new ComboBox<>();
    private final ObservableList<String> WATERTYPE_LIST = FXCollections.observableArrayList(
        "Zoet water",
            "Zout water"
    );
    private final ComboBox WATERTYPE_COMBOBOX = new ComboBox(WATERTYPE_LIST);
    // checkboxes
    private final CheckBox PREFEED_CHECKBOX = new CheckBox();
    private final CheckBox GOT_ON_SIDE_CHECKBOX = new CheckBox();
    // textarea
    private final TextArea REMARK_TEXTAREA = new TextArea();
    // button
    private final Button NEW_BUTTON = new Button("Nieuw");
    private final Button DELETE_BUTTON = new Button("Verwijderen");
    private final Button SAVE_BUTTON = new Button("Opslaan");
    private final Button SWITCH_BUTTON = new Button("Schakelen");
    // toggleGroup
    private final ToggleGroup SORTING_TOGGLE_GROUP = new ToggleGroup();
    // radio buttons
    private final RadioButton SPECIES_AZ = new RadioButton("Soort A/Z");
    private final RadioButton SPECIES_ZA = new RadioButton("Soort Z/A");
    private final RadioButton LENGTH_LOW_TO_HIGH = new RadioButton("Lengte klein/groot");
    private final RadioButton LENGTH_HIGH_TO_LOW = new RadioButton("Lengte groot/klein");

    // root
    private Parent root;

    /**
     * constructor for fisherman view
     */
    public FishView() {
        initLayout();
    }

    /**
     * function to create a grid with the list and form
     */
    private void initLayout() {
        // main VBox
        VBox fishVBox = new VBox();
        // grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // array with the labels
        Node[] labelArray = {
                LABEL_FISHERMAN,
                LABEL_FISH_SPECIES,
                LABEL_FISH_LENGTH_IN_CM,
                LABEL_WEIGHT_IN_KG,
                LABEL_CAUGHT_ON,
                LABEL_LOCATION,
                LABEL_WATER_TYPE,
                LABEL_BAIT,
                LABEL_PREFEED,
                LABEL_GOT_ON_THE_SIDE,
                LABEL_REMARK,
        };
        // array with the controls
        Node[] inputArray = {
                FISHERMAN_LIST,
                TEXTFIELD_FISH_SPECIES,
                TEXTFIELD_FISH_LENGTH_IN_CM,
                TEXTFIELD_WEIGHT_IN_KG,
                DATEPICKER_CAUGHT_ON,
                TEXTFIELD_LOCATION,
                WATERTYPE_COMBOBOX,
                TEXTFIELD_BAIT,
                PREFEED_CHECKBOX,
                GOT_ON_SIDE_CHECKBOX,
                REMARK_TEXTAREA,
        };
        // loop through the arrays and give all the node items the right position
        for (int i = 0; i < labelArray.length; i++) {
            GridPane.setConstraints(labelArray[i], 0, i);
            GridPane.setConstraints(inputArray[i], 1, i);
        }

        // list grid
        GridPane gridList = new GridPane();
        gridList.setPadding(new Insets(10, 10, 10, 10));
        gridList.setVgap(8);
        gridList.setHgap(10);

        // create listview
        final int FISHLIST_WIDTH = 600;
        fishlist = new ListView<>();
        fishlist.setPrefWidth(FISHLIST_WIDTH);

        // set radio buttons
        HBox sortingHBox = new HBox();
        sortingHBox.setPadding(new Insets(2,8,0,8));
        // radio button array
        RadioButton[] radioButtonArray = {
                SPECIES_AZ,
                SPECIES_ZA,
                LENGTH_LOW_TO_HIGH,
                LENGTH_HIGH_TO_LOW,
        };
        int valueOfRadioButton = 0;
        final int FIRST_ITEM = 0;
        for (RadioButton radioButton : radioButtonArray) {
            // add readable value to radiobutton
            radioButton.setUserData(valueOfRadioButton);
            // add radio button to togglegroup
            SORTING_TOGGLE_GROUP.getToggles().add(radioButton);
            // set selected item on start
            if (valueOfRadioButton == FIRST_ITEM) {
                SORTING_TOGGLE_GROUP.selectToggle(radioButton);
            }
            valueOfRadioButton++;
            // set padding to radiobutton
            radioButton.setPadding(new Insets(10,10,10,10));
            // add radio button to the HBox
            sortingHBox.getChildren().add(radioButton);

        }
        // set the list on the right position
        GridPane.setConstraints(fishlist, 0, 0);
        gridList.getChildren().addAll(fishlist);


        // create HBox for all the buttons
        HBox buttonHBox = new HBox();
        buttonHBox.setPadding(new Insets(5,5,5,5));
        buttonHBox.getChildren().addAll(
                NEW_BUTTON,
                DELETE_BUTTON,
                SWITCH_BUTTON
        );
        // add all the items to the grid
        grid.getChildren().addAll(
                LABEL_FISHERMAN,
                LABEL_FISH_SPECIES,
                LABEL_FISH_LENGTH_IN_CM,
                LABEL_WEIGHT_IN_KG,
                LABEL_CAUGHT_ON,
                LABEL_LOCATION,
                LABEL_WATER_TYPE,
                LABEL_BAIT,
                LABEL_PREFEED,
                LABEL_GOT_ON_THE_SIDE,
                LABEL_REMARK,
                FISHERMAN_LIST,
                TEXTFIELD_FISH_SPECIES,
                TEXTFIELD_FISH_LENGTH_IN_CM,
                TEXTFIELD_WEIGHT_IN_KG,
                DATEPICKER_CAUGHT_ON,
                TEXTFIELD_LOCATION,
                WATERTYPE_COMBOBOX,
                TEXTFIELD_BAIT,
                PREFEED_CHECKBOX,
                GOT_ON_SIDE_CHECKBOX,
                REMARK_TEXTAREA
        );

        // save button HBox
        HBox saveHBox = new HBox();
        saveHBox.setAlignment(Pos.CENTER);
        saveHBox.setPadding(new Insets(2,8,0,8));
        final int BUTTON_WIDTH = 9000;
        SAVE_BUTTON.setPrefWidth(BUTTON_WIDTH);
        saveHBox.getChildren().addAll(SAVE_BUTTON);
        // add the grid to the fishVBox
        fishVBox.getChildren().addAll(
                grid,
                saveHBox,
                gridList,
                sortingHBox,
                buttonHBox
        );
        // set the full grid in the root element
        root = fishVBox;
    }

    /**
     * getter function for the switch button
     * @return
     */
    public Button getSWITCH_BUTTON() {
        return SWITCH_BUTTON;
    }

    /**
     * getter function for the save button
     * @return
     */
    public Button getSAVE_BUTTON() {
        return SAVE_BUTTON;
    }

    /**
     * getter for the delete_button
     * @return
     */
    public Button getDELETE_BUTTON() {
        return DELETE_BUTTON;
    }

    /**
     * getter for the new_button
     * @return
     */
    public Button getNEW_BUTTON() {
        return NEW_BUTTON;
    }

    /**
     * getter for the fisherman_list
     * @return
     */
    public ComboBox<Fisherman> getFISHERMAN_LIST() {
        return FISHERMAN_LIST;
    }

    /**
     * getter for the fishlist
     * @return
     */
    public ListView<Fish> getFishlist() {
        return fishlist;
    }

    /**
     * getter for the textfield species
     * @return
     */
    public TextField getTEXTFIELD_FISH_SPECIES() {
        return TEXTFIELD_FISH_SPECIES;
    }

    /**
     * getter for the textfield fish length
     * @return
     */
    public TextField getTEXTFIELD_FISH_LENGTH_IN_CM() {
        return TEXTFIELD_FISH_LENGTH_IN_CM;
    }

    /**
     * getter for the textfield weight in kg
     * @return
     */
    public TextField getTEXTFIELD_WEIGHT_IN_KG() {
        return TEXTFIELD_WEIGHT_IN_KG;
    }

    /**
     * getter for the textfield location
     * @return
     */
    public TextField getTEXTFIELD_LOCATION() {
        return TEXTFIELD_LOCATION;
    }

    /**
     * getter for the textfield bait
     * @return
     */
    public TextField getTEXTFIELD_BAIT() {
        return TEXTFIELD_BAIT;
    }

    /**
     * getter for the datepicker for the caught on
     * @return
     */
    public DatePicker getDATEPICKER_CAUGHT_ON() {
        return DATEPICKER_CAUGHT_ON;
    }

    /**
     * getter for the checkbox for prefeed
     * @return
     */
    public CheckBox getPREFEED_CHECKBOX() {
        return PREFEED_CHECKBOX;
    }

    /**
     * getter for the checkbox got_on_side
     * @return
     */
    public CheckBox getGOT_ON_SIDE_CHECKBOX() {
        return GOT_ON_SIDE_CHECKBOX;
    }

    /**
     * getter for the textarea remark
     * @return
     */
    public TextArea getREMARK_TEXTAREA() {
        return REMARK_TEXTAREA;
    }

    /**
     * getter for the combobox for the watertype
     * @return
     */
    public ComboBox getWATERTYPE_COMBOBOX() {
        return WATERTYPE_COMBOBOX;
    }

    /**
     * getter for toggle group to read the selected item
     * @return
     */
    public ToggleGroup getSORTING_TOGGLE_GROUP() {
        return SORTING_TOGGLE_GROUP;
    }

    /**
     * getter function for the view
     * @return
     */
    @Override
    public Parent getRoot() {
        return root;
    }
}
