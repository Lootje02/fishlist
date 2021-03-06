package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Fish;
import practicumopdracht.models.Fisherman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * This method is a DAO to save a Fish to a text file
 *
 * @author Lorenzo Bindemann
 */
public class TextFishDAO extends FishDAO {
    private static final String FILENAME = "src/practicumopdracht/textfiles/fish.txt";

    /**
     * save function to save a all the Fishes to a text file
     *
     * @return
     */
    @Override
    public boolean save() {
        try {
            File file = new File(FILENAME);
            PrintWriter printWriter = new PrintWriter(file);

            // loop through objects to set for each item the field in the file
            for (Fish fish : objects) {
                int fishermanIndex = MainApplication.getFishermanDAO().getIdFor(fish.getHoortBij());
                printWriter.println(fishermanIndex);
                printWriter.println(fish.getFishSpecies());
                printWriter.println(fish.getFishLengthInCm());
                printWriter.println(fish.getWeightInKg());
                printWriter.println(fish.getCaughtOn());
                printWriter.println(fish.getWaterType());
                printWriter.println(fish.getLocation());
                printWriter.println(fish.getBait());
                printWriter.println(fish.isPrefeed());
                printWriter.println(fish.isGotOnTheSide());
                printWriter.println(fish.getRemark());
            }
            // close the printerWrite !Important
            printWriter.close();
        } catch(FileNotFoundException ex) {
            // file not found
            System.out.println("Bestand niet gevonden: " + ex);
            return false;
        } catch(Exception ex) {
            // other errors
            System.err.print(ex);
            return false;
        }
        return true;
    }

    /**
     * load function to load all the fishes from the text file
     *
     * @return
     */
    @Override
    public boolean load() {
        File file = new File(FILENAME);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                // read the lines from the txt file
                int index = Integer.parseInt(scanner.nextLine());
                String fishSpecies = scanner.nextLine();
                int fishLengthInCm = Integer.parseInt(scanner.nextLine());
                double weightInKg = Double.parseDouble(scanner.nextLine());
                LocalDate caughtOn = LocalDate.parse(scanner.nextLine());
                String waterType = scanner.nextLine();
                String location = scanner.nextLine();
                String bait = scanner.nextLine();
                boolean prefeed = Boolean.parseBoolean(scanner.nextLine());
                boolean gotOnSide = Boolean.parseBoolean(scanner.nextLine());
                String remark = scanner.nextLine();

                Fisherman fisherman = MainApplication.getFishermanDAO().getById(index);

                // create new fisherman object
                Fish fish = new Fish(
                        fishSpecies,
                        fisherman,
                        fishLengthInCm,
                        weightInKg,
                        caughtOn,
                        waterType,
                        location,
                        bait,
                        prefeed,
                        gotOnSide,
                        remark
                );
                // add the fisherman to the list
                addOrUpdate(fish);
            }
        } catch(FileNotFoundException ex) {
            // file not found
            System.out.println("Bestand niet gevonden: " + ex);
            return false;
        } catch(Exception ex) {
            // other errors
            System.err.print(ex);
            return false;
        }
        return true;
    }
}
