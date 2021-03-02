package practicumopdracht.data;

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

    public FishDAO() {
        this.objects = new ArrayList<>();
        load();
    }

    public Fish getById(int id) {
        for (Fish fish : objects) {
            if (fish.getId() == id) {
                return fish;
            }
        }
        return null;
    }

    private int getUniqueId() {
        int highestId = 0;
        for(Fish fish : objects) {
            if (fish.getId() > highestId) {
                highestId = fish.getId();
            }
        }
        return highestId;
    }

    public FishDAO getAllFor(Fisherman fisherman) {
        objects.removeIf(fish -> fish.getHoortBij() == fisherman.getId());
        return this;
    };

    @Override
    public List getAll() {
        return Collections.unmodifiableList(objects);
    }

    @Override
    public void addOrUpdate(Fish object) {
        if (object.getId() <= 0) {
            object.setId(getUniqueId());
            objects.add(object);
        } else {
            Fish foundFish = getById(object.getId());
            if (foundFish == null) {
                objects.add(object);
            } else {
                int index = objects.indexOf(foundFish);
                objects.set(index, object);
            }
        }
    }

    @Override
    public void remove(Fish object) {
        // create fisherman and remove it
        Fish foundFish = getById(object.getId());
        if (foundFish != null) {
            objects.remove(foundFish);
        }
    }

    @Override
    public abstract boolean save();

    @Override
    public abstract boolean load();
}
