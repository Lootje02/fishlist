package practicumopdracht.views;

import javafx.geometry.Insets;
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
    // labels
    private final Label LABEL_FISH_SPECIES = new Label("Vissoort");
    private final Label LABEL_FISH_LENGTH_IN_CM = new Label("Lengte van de vis");
    private final Label LABEL_WEIGHT_IN_KG = new Label("Gewicht van de vis");
    private final Label LABEL_CAUGHT_ON = new Label("Gevangen op");
    private final Label LABEL_LOCATION = new Label("Locatie");
    private final Label LABEL_BAIT = new Label("Aas");
    private final Label LABEL_PREFEED = new Label("Voor gevoerd");
    private final Label LABEL_GOT_ON_THE_SIDE = new Label("Op de kant gekregen");
    // text fields
    private final TextField TEXTFIELD_FISH_SPECIES = new TextField();;
    private final TextField TEXTFIELD_FISH_LENGTH_IN_CM = new TextField();;
    private final TextField TEXTFIELD_WEIGHT_IN_KG = new TextField();;
    private final TextField TEXTFIELD_LOCATION = new TextField();;
    private final TextField TEXTFIELD_BAIT = new TextField();;
    // date picker
    private final DatePicker DATEPICKER_CAUGHT_ON = new DatePicker();
    // checkboxes
    private final CheckBox PREFEED_CHECKBOX = new CheckBox();
    private final CheckBox GOT_ON_SIDE_CHECKBOX = new CheckBox();
    // button
    private final Button SWITCH_BUTTON = new Button("Switch");
    private final Button DELETE_BUTTON = new Button("Verwijderen");

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

        // array with finals
        Node[] labelArray = new Node[]{
                LABEL_FISH_SPECIES,
                LABEL_FISH_LENGTH_IN_CM,
                LABEL_WEIGHT_IN_KG,
                LABEL_CAUGHT_ON,
                LABEL_LOCATION,
                LABEL_BAIT,
                LABEL_PREFEED,
                LABEL_GOT_ON_THE_SIDE,
        };

        Node[] inputArray = new Node[]{
                TEXTFIELD_FISH_SPECIES,
                TEXTFIELD_FISH_LENGTH_IN_CM,
                TEXTFIELD_WEIGHT_IN_KG,
                DATEPICKER_CAUGHT_ON,
                TEXTFIELD_LOCATION,
                TEXTFIELD_BAIT,
                PREFEED_CHECKBOX,
                GOT_ON_SIDE_CHECKBOX,
        };

        for (int i = 0; i < labelArray.length; i++) {
            GridPane.setConstraints(labelArray[i], 0, i);
            GridPane.setConstraints(inputArray[i], 1, i);
        }


        grid.getChildren().addAll(
                LABEL_FISH_SPECIES,
                LABEL_FISH_LENGTH_IN_CM,
                LABEL_WEIGHT_IN_KG,
                LABEL_CAUGHT_ON,
                LABEL_LOCATION,
                LABEL_BAIT,
                LABEL_PREFEED,
                LABEL_GOT_ON_THE_SIDE,
                TEXTFIELD_FISH_SPECIES,
                TEXTFIELD_FISH_LENGTH_IN_CM,
                TEXTFIELD_WEIGHT_IN_KG,
                DATEPICKER_CAUGHT_ON,
                TEXTFIELD_LOCATION,
                TEXTFIELD_BAIT,
                PREFEED_CHECKBOX,
                GOT_ON_SIDE_CHECKBOX
        );
        fishVBox.getChildren().addAll(
                grid
        );

        root = fishVBox;
    }

    public Button getSWITCH_BUTTON() {
        return SWITCH_BUTTON;
    }

    public Button getDELETE_BUTTON() {
        return DELETE_BUTTON;
    }

    @Override
    public Parent getRoot() {
        return root;
    }
}
