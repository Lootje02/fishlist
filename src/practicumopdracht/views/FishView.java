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

import java.lang.reflect.Array;

/**
 * This method <description of function>
 *
 * @author Lorenzo Bindemann
 */
public class FishView extends View{
    // listview
    private ListView<String> fishlist;
    // labels
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

    // root
    private Parent root;

    public FishView() {
        initLayout();
    }

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
        fishlist = new ListView<>();
        fishlist.getItems().addAll(
                "Lorenzo Bindemann",
                "Piet Stokbrood",
                "Jan de Vriezer"
        );
        fishlist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        fishlist.setPrefWidth(600);

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
        SAVE_BUTTON.setPrefWidth(9000);
        saveHBox.getChildren().addAll(SAVE_BUTTON);
        // add the grid to the fishVBox
        fishVBox.getChildren().addAll(
                grid,
                saveHBox,
                gridList,
                buttonHBox
        );

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

    public Button getDELETE_BUTTON() {
        return DELETE_BUTTON;
    }

    public TextField getTEXTFIELD_FISH_SPECIES() {
        return TEXTFIELD_FISH_SPECIES;
    }

    public TextField getTEXTFIELD_FISH_LENGTH_IN_CM() {
        return TEXTFIELD_FISH_LENGTH_IN_CM;
    }

    public TextField getTEXTFIELD_WEIGHT_IN_KG() {
        return TEXTFIELD_WEIGHT_IN_KG;
    }

    public TextField getTEXTFIELD_LOCATION() {
        return TEXTFIELD_LOCATION;
    }

    public TextField getTEXTFIELD_BAIT() {
        return TEXTFIELD_BAIT;
    }

    public DatePicker getDATEPICKER_CAUGHT_ON() {
        return DATEPICKER_CAUGHT_ON;
    }

    public CheckBox getPREFEED_CHECKBOX() {
        return PREFEED_CHECKBOX;
    }

    public CheckBox getGOT_ON_SIDE_CHECKBOX() {
        return GOT_ON_SIDE_CHECKBOX;
    }

    public TextArea getREMARK_TEXTAREA() {
        return REMARK_TEXTAREA;
    }
    public ComboBox getWATERTYPE_COMBOBOX() {
        return WATERTYPE_COMBOBOX;
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
