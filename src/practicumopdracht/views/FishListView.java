package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * This method <description of function>
 *
 * @author Lorenzo Bindemann
 */
public class FishListView extends View{
    // button
    private final Button SWITCH_BUTTON = new Button("Switch");

    // root
    private Parent root;

    public FishListView() {
        initLayout();
    }

    public void initLayout() {
        // main VBox
        VBox fishVBox = new VBox();
        // main grid
        GridPane grid = new GridPane();

        GridPane.setRowIndex(SWITCH_BUTTON, 0);
        GridPane.setColumnIndex(SWITCH_BUTTON, 0);

        grid.setHgap(10);
        grid.setPadding(new Insets(10, 10, 10, 12));

        grid.setAlignment(Pos.CENTER);

        grid.getChildren().addAll(SWITCH_BUTTON);

        fishVBox.getChildren().addAll(grid);

        root = fishVBox;
    }

    public Button getSWITCH_BUTTON() {
        return SWITCH_BUTTON;
    }

    @Override
    public Parent getRoot() {
        return root;
    }
}
