package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Fish;

import java.io.*;

/**
 * This method is the DAO for saving Fishes to a file as an object
 *
 * @author Lorenzo Bindemann
 */
public class ObjectFishDAO extends FishDAO {
    private static final String FILENAME = "src/practicumopdracht/binary/fish.obj";

    /**
     * save function to save object to list
     *
     * @return
     */
    @Override
    public boolean save() {
        File file = new File(FILENAME);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // first line in file for the amount of fishes for the load function
            objectOutputStream.writeInt(objects.size());

            // loop through the list to set all the fishes in the file
            for (Fish fish : objects) {
                // set the index of the fisherman in de object file
                objectOutputStream.writeInt(MainApplication.getFishermanDAO().getIdFor(fish.getHoortBij()));
                objectOutputStream.writeObject(fish);
            }
            objectOutputStream.close();
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    /**
     * load function for the fishes from an object file
     *
     * @return
     */
    @Override
    public boolean load() {
        File file = new File(FILENAME);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            // read the total saved fishes
            int numberOfFish = objectInputStream.readInt();
            // loop through the file to get all the fishes
            for (int i = 0; i < numberOfFish; i++) {
                // read the index of the fisherman
                int hoortBij = objectInputStream.readInt();
                Fish fish = (Fish) objectInputStream.readObject();
                // convert the fisherman index to a fisherman object
                fish.setHoortBij(MainApplication.getFishermanDAO().getById(hoortBij));
                addOrUpdate(fish);
            }
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }
}
