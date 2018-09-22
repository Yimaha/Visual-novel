/*
 * Student Names: Justin Cai & Henry Xue
 * Teacher Name: Mr. Benum
 * Course Code: ICS4U
 * Date: June 15, 2018
 *
 * Description:
 * Individual plot line object in the plot file.
 */

public class PlotLine {

    // Fields of a plot line.
    private String id;
    private String text;
    private String image;
    private String[] next;
    private String[] option;
    private int time;

    /**
     * Gets the id of the plot line.
     * @return the plot line id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the text of the plot line.
     * @return the plot line text
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the image file of the plot line.
     * @return the plot line image file
     */
    public String getImage() {
        return image;
    }

    /**
     * Gets the next branches of the plot line.
     * @return the plot line branches
     */
    public String[] getNext() {
        return next;
    }

    /**
     * Detects whether the plot line contains options.
     * @return true if it contains options, false otherwise
     */
    public boolean isOption() {
        return option != null;
    }

    /**
     * Gets the options of the plot line.
     * @return the plot line options
     */
    public String[] getOption() {
        return option;
    }

    /**
     * Gets the time of the plot line.
     * @return the plot line time
     */
    public int getTime() {
        return time;
    }
}
