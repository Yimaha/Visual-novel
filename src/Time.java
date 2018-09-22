/*
 * Student Names: Justin Cai & Henry Xue
 * Teacher Name: Mr. Benum
 * Course Code: ICS4U
 * Date: June 15, 2018
 *
 * Description:
 * Time class for the game.
 */

public class Time {

    /**
     * Internal representation of time.
     */
    private int time;

    /**
     * Initializes time to 8:00 AM.
     */
    public Time() {
        time = 16;
    }

    /**
     * Increase time by a certain amount.
     * @param change amount of time to be increased
     */
    public void increaseTime(int change) {
        time += change;
    }

    /**
     * Detects whether time has passed 8:00 PM.
     * @return true if time has passed 8:00 PM, false otherwise
     */
    public boolean isOver() {
        return time >= 40;
    }

    /**
     * Gets the human-friendly string representation of time.
     * @return the string representation of time
     */
    public String getTime() {
        return String.format("%02d:%02d", time / 2, time % 2 * 30);
    }
}
