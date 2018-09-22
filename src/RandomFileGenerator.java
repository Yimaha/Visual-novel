/*
 * Student Names: Justin Cai & Henry Xue
 * Teacher Name: Mr. Benum
 * Course Code: ICS4U
 * Date: June 15, 2018
 *
 * Description:
 * Random file generator utility.
 */

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class RandomFileGenerator {

    /**
     * Generates 1000 folders with random names. Each folder contains random numbers of files with random names.
     */
    public static void generate() {
        Save saveFolder = new Save(GameUtility.FILE_SAVEFOLDER);
        String token = "";
        final int numberOfFolder = 1000;

        // Generates random folders.
        for (int i = 0; i < numberOfFolder; i++) {
            String tempDirName = UUID.randomUUID().toString().replace("-", ""); // uses UUID for generating random string
            token += tempDirName + " ";
            File tempDir = new File("src", tempDirName);

            // Generates random files within folders.
            if (tempDir.mkdir()) {
                for (int j = 0; j < Math.random() * 5; j++) {
                    String tempFileName = UUID.randomUUID().toString().replace("-", "");
                    try {
                        new File(tempDir, tempFileName).createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //Hide Henry into one of the file
                    if (i == 0) {
                        File simulation = new File(GameUtility.DIR_RUN_SIM);
                        GameUtility.transferFile(simulation, "Henry.dat", tempDir, UUID.randomUUID().toString().replace("-", "").substring(0, 5));
                    }
                    //Hide Justin, the Admin into one of the file
                    if (i == 500) {
                        File admin = new File(tempDir, "Justin.dat");
                        GameUtility.writeToFile(admin, "\"name\": \"Justin\"\n" +
                                "\"Authority\": \"A\"\n" +
                                "\"Gender\": \"Male\"\n" +
                                "\"Height\": \"183\"\n" +
                                "\"Weight\": \"0\"\n" +
                                "The rest of the data is only visible for the Creator");
                        Save location = new Save(GameUtility.FILE_JUSTIN_LOCATION);
                        location.enterProgress(tempDirName);
                    }
                }
            }
            saveFolder.enterProgress(token);
        }
    }
}
