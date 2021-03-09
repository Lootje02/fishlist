package practicumopdracht.data;

import practicumopdracht.models.Fish;
import practicumopdracht.models.Fisherman;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * This method <description of function>
 *
 * @author Lorenzo Bindemann
 */
public class TextFishDAO extends FishDAO{
    private static final String FILENAME = "src/practicumopdracht/textfiles/fish.txt";

    @Override
    public boolean save() {
        try {
            File file = new File(FILENAME);
            PrintWriter printWriter = new PrintWriter(file);
//            // set the total objects in the file
//            printWriter.println(objects.size());

            // loop through objects to set for each item the field in the file
            for (Fish fish : objects) {
                printWriter.println(fish.getId());
                printWriter.println(fish.getHoortBij());
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
        } catch (Exception ex) {
            System.out.println(ex);
            return  false;
        }
        return true;
    }

    @Override
    public boolean load() {
        File file = new File(FILENAME);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                // read the lines from the txt file
                int id = Integer.parseInt(scanner.nextLine());
                String fishSpecies = scanner.nextLine();
                int hoortBij = Integer.parseInt(scanner.nextLine());
                int fishLengthInCm = Integer.parseInt(scanner.nextLine());
                double weightInKg = Double.parseDouble(scanner.nextLine());
                LocalDate caughtOn = LocalDate.parse(scanner.nextLine());
                String waterType = scanner.nextLine();
                String location = scanner.nextLine();
                String bait = scanner.nextLine();
                boolean prefeed = Boolean.parseBoolean(scanner.nextLine());
                boolean gotOnSide = Boolean.parseBoolean(scanner.nextLine());
                String remark = scanner.nextLine();

                // create new fisherman object
                Fish fish = new Fish(
                        fishSpecies,
                        hoortBij,
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
                // update the id of the fisherman object
                fish.setId(id);
                // add the fisherman to the list
                objects.add(fish);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }
}
