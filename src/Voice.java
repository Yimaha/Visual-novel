/*
 * Student Names: Justin Cai & Henry Xue
 * Teacher Name: Mr. Benum
 * Course Code: ICS4U
 * Date: June 15, 2018
 *
 * Description:
 * Voice control class.
 */

import javafx.scene.media.AudioClip;

import java.io.File;

public class Voice {

    /**
     * Field for current voice clip.
     */
    private AudioClip clip;

    /**
     * Plays the specified voice file.
     * @param fileName name of the voice file
     */
    public void play(String fileName) {
        if (clip != null) { // if the clip is currently being played
            clip.stop();
        }

        clip = new AudioClip(new File("res/Voice/" + fileName).toURI().toString());
        clip.play();
    }
}
