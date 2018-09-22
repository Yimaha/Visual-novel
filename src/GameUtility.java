/*
 * Student Names: Justin Cai & Henry Xue
 * Teacher Name: Mr. Benum
 * Course Code: ICS4U
 * Date: June 15, 2018
 *
 * Description:
 * File operation utilities.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameUtility {

    // Constants of paths & file names
    public static final String DIR_BLACKBOX = "Blackbox/";
    public static final String DIR_RUN_SIM = "Running Simulation/";
    public static final String DIR_RES = "res/";
    public static final String FILE_SAVE = DIR_RES + "save.dat";
    public static final String FILE_SAVEFOLDER = DIR_RES + "saveFolder.dat";
    public static final String FILE_HENRY = DIR_RUN_SIM + "Henry.dat";
    public static final String FILE_FE = DIR_RES + "FE.dat";
    public static final String FILE_JUSTIN_LOCATION = DIR_RES + "DDD.dat";


    /**
     * used to reset the game to it's original state where nothing has been changed
     * This method is designed for game tester and developer, but not suppose to be actually used in anywhere within the game
     * Player after completing the final ending is not expected to play anymore
     */
    public static void reset() {
        emptyFolder(DIR_BLACKBOX);
        emptyFolder(DIR_RUN_SIM);
        try {
            FileWriter writer = new FileWriter(FILE_HENRY);
            writer.write("\"name\": \"Henry\"\n\"Authority\": \"T\"\n\"Gender\": \"Male\"\n\"Height\": \"176\"\n\"Weight\": \"-1\"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Save token = new Save(FILE_SAVEFOLDER);
        deleteFolder("src", token.getProgress());
        File finalEnding = new File(FILE_FE);
        finalEnding.delete();
        File DDD = new File(FILE_JUSTIN_LOCATION);
        DDD.delete();
        token.enterProgress("");
        token = new Save(FILE_SAVE);
        token.enterProgress("AA1", 1);
    }

    /**
     * Used to delete a certain amount of folder
     * @param parent the parent of the folder
     * @param root the name(s) of the folder
     */
    public static void deleteFolder(String parent, String[] root) {
        for (String s : root) {
            File token = new File(parent, s);
            String[] files = token.list();
            for (String temp : files) {
                //construct the file structure
                File fileDelete = new File(token, temp);
                //recursive delete
                fileDelete.delete();
            }
            token.delete();
        }

    }

    /**
     * Used to empty a specific folder.
     * @param filename
     */
    public static void emptyFolder(String filename) {
        File token = new File(filename);
        String[] files = token.list();
        for (String temp : files) {
            //construct the file structure
            File fileDelete = new File(token, temp);
            //recursive delete
            fileDelete.delete();
        }
    }

    /**
     * Used to transfer a file from one folder to another
     * @param originalFolder
     * @param originalFilename
     * @param newFolder
     * @param newFilename
     */
    public static void transferFile(File originalFolder, String originalFilename, File newFolder, String newFilename) {
        File token = new File(originalFolder, originalFilename);
        token.renameTo(new File(newFolder, newFilename));
    }

    /**
     * used to write content to Files, very crucial for recording data.
     * @param target
     * @param content
     */
    public static void writeToFile(File target, String content) {
        try {
            FileWriter writer = new FileWriter(target);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * check if a String exists in the targeting folder
     * @param targetString
     * @param target
     * @return
     */
    public static boolean ifStringExist(String targetString, File target) {
        try {
            FileReader reader = new FileReader(target);
            BufferedReader output = new BufferedReader(reader);
            String token;
            while ((token = output.readLine()) != null) {
                if (token.equals(targetString)) {
                    reader.close();
                    output.close();
                    return true;
                }
            }
            reader.close();
            output.close();
            return false;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        reset();
    }
}
