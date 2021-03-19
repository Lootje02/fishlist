package practicumopdracht.data;

import practicumopdracht.models.Fisherman;

import java.io.*;
import java.time.LocalDate;

/**
 * This method <description of function>
 *
 * @author Lorenzo Bindemann
 */
public class BinaryFishermanDAO extends FishermanDAO{
    private static final String FILENAME = "src/practicumopdracht/binary/fisherman.dat";

    @Override
    public boolean save() {
        File file = new File(FILENAME);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);

            dataOutputStream.writeInt(objects.size());

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

    @Override
    public boolean load() {
        File file = new File(FILENAME);

        // clear the list
        objects.clear();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);

            int numberOfPersons = dataInputStream.readInt();

            for (int i = 0; i < numberOfPersons; i++) {
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
