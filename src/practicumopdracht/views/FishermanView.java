package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.data.FishermanDAO;
import practicumopdracht.models.Fisherman;

import java.lang.invoke.SwitchPoint;

/**
 * This method <description of function>
 *
 * @author Lorenzo Bindemann
 */
public class FishermanView extends View{
    // listview
    private ListView<Fisherman> fishermanList;
    // labels
    private final Label LABEL_FIRSTNAME = new Label("Voornaam");
    private final Label LABEL_LASTNAME = new Label("Achternaam");
    private final Label LABEL_DATE_OF_BIRTH = new Label("Geboortedatum");
    private final Label LABEL_CITY = new Label("Woonplaats");
    // textfields
    private final TextField TEXTFIELD_FIRSTNAME = new TextField();
    private final TextField TEXTFIELD_LASTNAME = new TextField();
    private final TextField TEXTFIELD_CITY = new TextField();
    // date picker
    private final DatePicker DATEPICKER_DATE_OF_BIRTH = new DatePicker();
    // button
    private final Button SWITCH_BUTTON = new Button("Schakelen");
    private final Button DELETE_BUTTON = new Button("Verwijder");
    private final Button NEW_BUTTON = new Button("Nieuw");
    private final Button ADD_BUTTON = new Button("Opslaan");

    // root
    private Parent root;

    public FishermanView() {
        initLayout();
    }

    public void initLayout() {
        // list VBox
        VBox fishVBox = new VBox();
        // input grid
        GridPane gridInput = new GridPane();
        gridInput.setPadding(new Insets(10,10,10,10));
        gridInput.setVgap(8);
        gridInput.setHgap(10);

        Node[] labelArray = {
                LABEL_FIRSTNAME,
                LABEL_LASTNAME,
                LABEL_DATE_OF_BIRTH,
                LABEL_CITY,
        };
        // array with the controls
        Node[] inputArray = {
                TEXTFIELD_FIRSTNAME,
                TEXTFIELD_LASTNAME,
                DATEPICKER_DATE_OF_BIRTH,
                TEXTFIELD_CITY,
        };

        // loop through the arrays and give all the node items the right position
        for (int i = 0; i < labelArray.length; i++) {
            GridPane.setConstraints(labelArray[i], 0, i);
            GridPane.setConstraints(inputArray[i], 1, i);
        }

        // save button HBox
        HBox saveHBox = new HBox();
        saveHBox.setAlignment(Pos.CENTER);
        saveHBox.setPadding(new Insets(2,8,0,8));
        final int BUTTON_WIDTH = 9000;
        ADD_BUTTON.setPrefWidth(BUTTON_WIDTH);
        saveHBox.getChildren().addAll(ADD_BUTTON);

        // set items in the grid
        gridInput.getChildren().addAll(
                LABEL_FIRSTNAME,
                LABEL_LASTNAME,
                LABEL_DATE_OF_BIRTH,
                LABEL_CITY,
                TEXTFIELD_FIRSTNAME,
                TEXTFIELD_LASTNAME,
                TEXTFIELD_CITY,
                DATEPICKER_DATE_OF_BIRTH
        );

        // list grid
        GridPane gridList = new GridPane();
        gridList.setPadding(new Insets(10, 10, 10, 10));
        gridList.setVgap(8);
        gridList.setHgap(10);

        // button grid
        GridPane gridButton = new GridPane();
        gridButton.setPadding(new Insets(10, 10, 10, 10));
        gridButton.setVgap(8);
        gridButton.setHgap(10);

        fishermanList = new ListView<>();
        fishermanList.setPrefWidth(600);

        // set the list on the right position
        GridPane.setConstraints(fishermanList, 0, 0);
        // create an array for the buttons
        Node[] buttonArray = {
                NEW_BUTTON,
                DELETE_BUTTON,
                SWITCH_BUTTON,
        };
        // set the buttons on the right position
        for (int i = 0; i < buttonArray.length; i++) {
            GridPane.setConstraints(buttonArray[i], (i), 0);
        }

        // add the items to the grids
        gridList.getChildren().addAll(
                fishermanList
        );
        gridButton.getChildren().addAll(
                DELETE_BUTTON,
                SWITCH_BUTTON,
                NEW_BUTTON
        );

        fishVBox.getChildren().addAll(
                gridInput,
                saveHBox,
                gridList,
                gridButton
        );

        root = fishVBox;
    }

    /**
     * getter for the switch button
     * @return
     */
    public Button getSWITCH_BUTTON() {
        return SWITCH_BUTTON;
    }

    public ListView<Fisherman> getFishermanList() {
        return fishermanList;
    }

    public Button getNEW_BUTTON() {
        return NEW_BUTTON;
    }

    public Button getADD_BUTTON() {
        return ADD_BUTTON;
    }

    public TextField getTEXTFIELD_FIRSTNAME() {
        return TEXTFIELD_FIRSTNAME;
    }

    public TextField getTEXTFIELD_LASTNAME() {
        return TEXTFIELD_LASTNAME;
    }

    public TextField getTEXTFIELD_CITY() {
        return TEXTFIELD_CITY;
    }

    public DatePicker getDATEPICKER_DATE_OF_BIRTH() {
        return DATEPICKER_DATE_OF_BIRTH;
    }

    public Button getDELETE_BUTTON() {
        return DELETE_BUTTON;
    }

    /**
     * function to give the view back
     * @return
     */
    @Override
    public Parent getRoot() {
        return root;
    }
}
