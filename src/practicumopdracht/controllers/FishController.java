package practicumopdracht.controllers;

import practicumopdracht.MainApplication;
import practicumopdracht.views.FishView;
import practicumopdracht.views.View;

/**
 * This method <description of function>
 *
 * @author Lorenzo Bindemann
 */
public class FishController extends Controller {
    private FishView view;

    public FishController() {
        view = new FishView();
        view.getSWITCH_BUTTON().setOnAction(e -> switchToList());
    }

    public View getView() {
        return view;
    }

    public void switchToList() {
        MainApplication.switchWindows(new FishListController());
    }
}
