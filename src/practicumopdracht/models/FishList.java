package practicumopdracht.models;

import java.util.ArrayList;

/**
 * This method creates the list of all the fishes
 *
 * @author Lorenzo Bindemann
 */
public class FishList {
    private String naam;
    private ArrayList<Fish> fishList;

    /**
     * Constructor
     * @param naam
     */
    public FishList(String naam) {
        this.naam = naam;
    }

    /**
     * Add fish to the arraylist
     * @param fish
     */
    public void addFishToList(Fish fish) {
        fishList.add(fish);
    }
}
