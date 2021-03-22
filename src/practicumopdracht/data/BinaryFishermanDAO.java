package practicumopdracht.data;

import practicumopdracht.models.Fisherman;

import java.io.*;
import java.time.LocalDate;

/**
 * This method is the DAO for saving fishermans to binary files
 *
 * @author Lorenzo Bindemann
 */
public class BinaryFishermanDAO extends FishermanDAO {
    private static final String FILENAME = "src/practicumopdracht/binary/fisherman.dat";

    /**
     * @return
     * @Override function to save the fisherman to binary file
     */
    @Override
    public boolean save() {
        File file = new File(FILENAME);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);

            // first line write total of fishermans for the load function
            dataOutputStream.writeInt(objects.size());

            // loop through the list to from every field to the file
            for (Fisherman fisherman : objects) {
                dataOutputStream.writeUTF(fisherman.getFirstname());
                dataOutputStream.writeUTF(fisherman.getLastname());
                dataOutputStream.writeUTF(fisherman.getDate_of_birth().toString());
                dataOutputStream.writeUTF(fisherman.getCity());
            }
            dataOutputStream.close();
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    /**
     * @return
     * @Override function to load the fisherman from binary file
     */
    @Override
    public boolean load() {
        File file = new File(FILENAME);

        // clear the list
        objects.clear();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);

            // read first int for total fishermans
            int numberOfFishermans = dataInputStream.readInt();

            // loop through the list to read every field to create a fisherman object and add to the list
            for (int i = 0; i < numberOfFishermans; i++) {
                String firstname = dataInputStream.readUTF();
                String lastname = dataInputStream.readUTF();
                LocalDate dateOfBirth = LocalDate.parse(dataInputStream.readUTF());
                String city = dataInputStream.readUTF();
                // add the fisherman to the list
                addOrUpdate(
                        new Fisherman(
                                firstname,
                                lastname,
                                dateOfBirth,
                                city
                        )
                );
            }
            dataInputStream.close();
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }
}
