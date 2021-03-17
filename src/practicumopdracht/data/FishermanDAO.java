package practicumopdracht.data;

import practicumopdracht.models.Fisherman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This method is the data object for fisherman
 *
 * @author Lorenzo Bindemann
 */
public abstract class FishermanDAO implements DAO<Fisherman>{
    protected List<Fisherman> objects;

    public FishermanDAO() {
        objects = new ArrayList<>();
        load();
    }

    public int getIdFor(Fisherman object) {
        try {
            return objects.indexOf(object);
        } catch (Exception ex) {
            return -1;
        }
    }

    public Fisherman getById(int id) {
        try {
            return objects.get(id);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Fisherman> getAll() {
        return Collections.unmodifiableList(objects);
    }

    @Override
    public void addOrUpdate(Fisherman object) {
        // find the index of the selected object
        final int FISHERMAN_INDEX = objects.indexOf(object);
        // check if the object exist in the list
        if (!objects.contains(object)) {
            objects.add(object);
        } else {
            objects.set(FISHERMAN_INDEX, object);
        }
    }

    @Override
    public void remove(Fisherman object) {
        // create fisherman and remove it
        final int FISHERMAN_INDEX = objects.indexOf(object);
        Fisherman foundFisherman = getById(FISHERMAN_INDEX);
        if (foundFisherman != null) {
            objects.remove(foundFisherman);
        }
    }

    @Override
    public abstract boolean save();

    @Override
    public abstract boolean load();
}
