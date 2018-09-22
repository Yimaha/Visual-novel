/*
 * Student Names: Justin Cai & Henry Xue
 * Teacher Name: Mr. Benum
 * Course Code: ICS4U
 * Date: June 15, 2018
 *
 * Description:
 * Saves/loads game progress.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Save {

    //field of Save class
    private File save;

    /**
     * Constructor that declare variable save
     * @param target
     */
    public Save(String target) {
        save = new File(target);
    }

    /**
     * The Content of the saving are usually separated by a space,
     * and the getProgress() method would return all element using a String Array
     * @return Array containing all element in targeting file. usually .dat file
     */
    public String[] getProgress() {
        String token;
        String[] progress = null;
        try {
            FileReader reader = new FileReader(save);
            BufferedReader output = new BufferedReader(reader);
            token = output.readLine();
            reader.close();
            output.close();
            //if the target File is empty
            if (token.equals(" ")) {
                //return an Array with one element
                String[] a = {" "};
                return a;
            }
            progress = token.split(" ");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return progress;
    }

    /**
     * Used to enter progress, usually a save file
     * @param progress the actual progress
     * @param level Which level are you on, One or Two?
     */
    public void enterProgress(String progress, int level) {
        try {
            FileWriter writer = new FileWriter(save);
            writer.write(progress + " " + level);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Similar to other but don't require entering level
     * @param progress the actual progress
     */
    public void enterProgress(String progress) {
        try {
            FileWriter writer = new FileWriter(save);
            writer.write(progress + " ");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
