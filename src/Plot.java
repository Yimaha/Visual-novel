/*
 * Student Names: Justin Cai & Henry Xue
 * Teacher Name: Mr. Benum
 * Course Code: ICS4U
 * Date: June 15, 2018
 *
 * Description:
 * Class for representing plot.
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import com.google.gson.Gson;

public class Plot {

    /**
     * An array of individual plot lines.
     */
    private PlotLine[] plotLines;

    /**
     * The current position in the plot.
     */
    private int position;

    /**
     * Decodes the plot file and initializes the plot line array.
     */
    public Plot() {
        // Loads encoded plot file into a byte array.
        byte[] encodedPlot = null;
        try {
            encodedPlot = Files.readAllBytes(new File("res/plot.dat").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Decodes plot file into a JSON string.
        String jsonPlot = new String(Base64.getDecoder().decode(encodedPlot));

        // Coverts JSON string into Java objects.
        plotLines = new Gson().fromJson(jsonPlot, PlotLine[].class);
    }

    /**
     * Finds a specified line in the plot and returns its position.
     * @param id the specified line to be found
     * @return the position of the specified line
     */
    private int find(String id) {
        for (int i = 0; i < plotLines.length; i++) { // basic linear search
            if (plotLines[i].getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets the specified plot line by id.
     * @param id the id of the specified plot line
     * @return the specified plot line
     */
    public PlotLine getLine(String id) {
        position = find(id);
        return plotLines[position];
    }
}
