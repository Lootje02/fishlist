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
        for (Fisherman fisherman : objects) {
            if (fisherman.getId() == id) {
                return fisherman;
            }
        }
        return null;
    }

    private int getUniqueId() {
       int highestId = 0;
       for(Fisherman fisherman : objects) {
           if (fisherman.getId() > highestId) {
               highestId = fisherman.getId();
           }
       }
       return highestId+1;
    }

    @Override
    public List<Fisherman> getAll() {
        return Collections.unmodifiableList(objects);
    }

    @Override
    public void addOrUpdate(Fisherman object) {
        if (object.getId() <= 0) {
            object.setId(getUniqueId());
            objects.add(object);
        } else {
            Fisherman foundFisherman = getById(object.getId());
            if (foundFisherman == null) {
                objects.add(object);
            } else {
                int index = objects.indexOf(foundFisherman);
                objects.set(index, object);
            }
        }
    }

    @Override
    public void remove(Fisherman object) {
        // create fisherman and remove it
        Fisherman foundFisherman = getById(object.getId());
        if (foundFisherman != null) {
            objects.remove(foundFisherman);
        }
    }

    @Override
    public abstract boolean save();

    @Override
    public abstract boolean load();
}
