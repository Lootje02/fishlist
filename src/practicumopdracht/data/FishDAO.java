package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Fish;
import practicumopdracht.models.Fisherman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This method <description of function>
 *
 * @author Lorenzo Bindemann
 */
public abstract class FishDAO implements DAO<Fish>{
    protected List<Fish> objects;
    protected FishermanDAO fishermanDAO;

    public FishDAO() {
        this.objects = new ArrayList<>();
        fishermanDAO = MainApplication.getFishermanDAO();

        load();
    }

    public Fish getById(int id) {
        try {
            return objects.get(id);
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Fish> getAllFor(Fisherman fisherman) {
        List<Fish> fishList = new ArrayList<>();
        for (Fish fish : objects) {
            // check if fish is part of fisherman
            if (fish.getHoortBij().equals(fisherman)) {
                fishList.add(fish);
            }
        }
        return fishList;
    };

    @Override
    public List getAll() {
        return Collections.unmodifiableList(objects);
    }

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

    @Override
    public void remove(Fish object) {
        // create fisherman and remove it
        final int FISH_INDEX = objects.indexOf(object);
        Fish foundFish = getById(FISH_INDEX);
        if (foundFish != null) {
            objects.remove(foundFish);
        }
    }

    @Override
    public abstract boolean save();

    @Override
    public abstract boolean load();
}
