package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Fish;
import practicumopdracht.models.Fisherman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This method is the parent for all the other fish DAO's
 *
 * @author Lorenzo Bindemann
 */
public abstract class FishDAO implements DAO<Fish> {
    protected List<Fish> objects;

    /**
     * constructor for the FishDAO
     */
    public FishDAO() {
        this.objects = new ArrayList<>();

        load();
    }

    /**
     * function to get the object fish object from the list by index
     *
     * @param id
     * @return
     */
    public Fish getById(int id) {
        try {
            return objects.get(id);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * function to get all the fishes for the selected fisherman
     *
     * @param fisherman
     * @return
     */
    public List<Fish> getAllFor(Fisherman fisherman) {
        List<Fish> fishList = new ArrayList<>();
        for (Fish fish : objects) {
            // check if fish is part of fisherman
            if (fish.getHoortBij().equals(fisherman)) {
                fishList.add(fish);
            }
        }
        return fishList;
    }

    ;

    /**
     * @return
     * @Override function to get all the fishes
     */
    @Override
    public List getAll() {
        return Collections.unmodifiableList(objects);
    }

    /**
     * @param object
     * @Override function to add or update fishes in the list
     */
    @Override
    public void addOrUpdate(Fish object) {
        final int FISH_INDEX = objects.indexOf(object);
        final int NOT_IN_LIST = -1;
        // check if the object exist in the list
        if (FISH_INDEX == NOT_IN_LIST) {
            objects.add(object);
        } else {
            objects.set(FISH_INDEX, object);
        }
    }

    /**
     * @param object
     * @Override function to remove fishes from the list
     */
    @Override
    public void remove(Fish object) {
        // create fisherman and remove it
        final int FISH_INDEX = objects.indexOf(object);
        Fish foundFish = getById(FISH_INDEX);
        if (foundFish != null) {
            objects.remove(foundFish);
        }
    }

    /**
     * abstract class for saving
     *
     * @return
     */
    @Override
    public abstract boolean save();

    /**
     * abstract class for loading
     *
     * @return
     */
    @Override
    public abstract boolean load();
}
