package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Fish;

import java.io.*;

/**
 * This method <description of function>
 *
 * @author Lorenzo Bindemann
 */
public class ObjectFishDAO extends FishDAO{
    private static final String FILENAME = "src/practicumopdracht/binary/fish.obj";

    @Override
    public boolean save() {
        File file = new File(FILENAME);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeInt(objects.size());

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

    @Override
    public boolean load() {
        File file = new File(FILENAME);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            // read the total saved fishes
            int numberOfFish = objectInputStream.readInt();
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
