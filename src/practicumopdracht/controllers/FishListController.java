package practicumopdracht.controllers;

import practicumopdracht.MainApplication;
import practicumopdracht.views.FishListView;
import practicumopdracht.views.View;

/**
 * This method <description of function>
 *
 * @author Lorenzo Bindemann
 */
public class FishListController extends Controller {
    private FishListView view;

    public FishListController() {
        view = new FishListView();
        view.getSWITCH_BUTTON().setOnAction(e -> SwitchToDetails());
    }

    @Override
    public View getView() {
        return view;
    }

    public void SwitchToDetails() {
        MainApplication.switchWindows(new FishController());
    }
}
