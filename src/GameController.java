/*
 * Student Names: Justin Cai & Henry Xue
 * Teacher Name: Mr. Benum
 * Course Code: ICS4U
 * Date: June 15, 2018
 *
 * Description:
 * Controller for game screen.
 */

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.util.Optional;

public class GameController {

    // Fields for the game.
    private Plot plot;
    private PlotLine line;
    private Time time;
    private Voice voice;
    private boolean clickFrozen; // true - freezes click
    private int level;

    // Corresponding elements in the fxml file.
    @FXML
    private Text text;
    @FXML
    private ImageView image;
    @FXML
    private Text timeDisplay;
    @FXML
    private Button optionA;
    @FXML
    private Button optionB;

    /**
     * Initializes game according to the specified action.
     *
     * @param action two possible values: "New" or "Load"
     */
    public void init(String action) {
        plot = new Plot();
        time = new Time();
        voice = new Voice();

        if (action.equals("New")) {
            line = plot.getLine("AA1"); // first line of the plot
        } else { // if (action.equals("Load"))
            Save save = new Save(GameUtility.FILE_SAVE);
            line = plot.getLine(save.getProgress()[0]);
            level = Integer.parseInt(save.getProgress()[1]);
            if (level == 2) {
                ifPassStage();
            }
        }

        if (new File(GameUtility.FILE_FE).exists()) { // Check if the final ending is already achieved, so the game not work anymore
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR!EF^DFDEAFA12LJB2ABV*%OA5VD");
            alert.setContentText("Thank you, This terrifying cycle ends here - Henry");
            alert.setHeaderText(null);
            Optional<ButtonType> result = alert.showAndWait();
            try {
                Thread.sleep((long) (Math.random() * 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.exit();
        }

        showLine();
    }

    /**
     * Event handler for clicking the text box.
     */
    @FXML
    private void next() {
        if (clickFrozen) {
            return;
        }
        if (!line.getNext()[0].equals("NO"))
            line = plot.getLine(line.getNext()[0]);
        if (line.getId().equals("AC0"))
            System.exit(0);

        showLine();
    }

    /**
     * Updates and displays the GUI elements.
     */
    private void showLine() {
        time.increaseTime(line.getTime());

        // Jumps to bad end if time is over.
        if (time.isOver()) {
            line = plot.getLine("AB0");
        }
        if (line.getText() == null) {
            line = plot.getLine(line.getNext()[0]);
        }

        image.setImage(new Image(line.getImage()));
        timeDisplay.setText("Time " + time.getTime());
        animateText(line.getText());
        voice.play(line.getId() + ".m4a");
        if (level == 2) {
            actionToTake();
        }
    }

    /**
     * Creates "typewriter" animation for the specified text.
     *
     * @param plotText text to be animated.
     */
    private void animateText(String plotText) {
        new Transition() {
            {
                setCycleDuration(Duration.millis(10 * plotText.length()));
                setInterpolator(Interpolator.LINEAR);
                clickFrozen = true;
                setOnFinished(e -> postAnimation()); // callback
            }

            protected void interpolate(double fraction) {
                int n = (int) (plotText.length() * fraction);
                text.setText(plotText.substring(0, n));
            }
        }.play();
    }

    /**
     * Callback method after animation is finished playing.
     */
    private void postAnimation() {
        if (line.getNext() == null) {
            return;
        }

        // Shows buttons if the current line contains options.
        if (line.isOption()) {
            optionA.setText(line.getOption()[0]);
            optionA.setVisible(true);
            if (line.getOption().length != 1) {
                optionB.setText(line.getOption()[1]);
                optionB.setVisible(true);
            }
        } else {
            clickFrozen = false;
        }
    }

    /**
     * Event handler for selecting an option.
     *
     * @param event contains information on which option is selected
     */
    @FXML
    private void selectOption(ActionEvent event) {
        String selection = (String) ((Node) event.getSource()).getUserData();
        if (selection.equals("A")) {
            line = plot.getLine(line.getNext()[0]);
        } else { // if (selection.equals("B"))
            line = plot.getLine(line.getNext()[1]);
        }

        // Hides buttons after selection
        optionA.setVisible(false);
        optionB.setVisible(false);
        clickFrozen = false;
        showLine();
    }

    /**
     * Event handler for pressing the save button.
     */
    @FXML
    private void save() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Saving...");
        alert.setContentText("Are you sure you are going to save?");
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Save save = new Save(GameUtility.FILE_SAVE);
            save.enterProgress(line.getId(), level);
        }
    }

// Everything beyond this line is designed for Chapter 2/////////////////////////////////////////////////////////////////////////


    /**
     * Check if the game entered Chapter 2, specifically implemented on ImageView
     */
    @FXML
    private void chapterTwoEntrance() {
        if (line.getImage().equals("ConcreteWall.jpg")) {
            line = plot.getLine("AC3");
            level = 2;
            optionA.setVisible(false);
            optionB.setVisible(false);
            showLine();
        }
    }

    /**
     * Chapter 2 has a very different experience when compared to Chapter 1,
     * at different stage of the game, the game itself throws different type of challenge at the player
     * To do so, it is impossible to have a general statement to check if the player passed a any level.
     * The method is used every time player re-enter the game using the load button,
     * so it runs once to check if player beat the current stage or not.
     */
    private void ifPassStage() {
        int stage = Integer.parseInt(line.getId().substring(2));
        if (stage == 3 || stage == 5 || stage == 6 || stage == 7) {
            if (new File(GameUtility.FILE_HENRY).exists()) {
                line = plot.getLine("AC" + (stage + 1));
                if (stage != 3) {
                    Save saveFolder = new Save(GameUtility.FILE_SAVEFOLDER);
                    GameUtility.deleteFolder("src", saveFolder.getProgress());
                    saveFolder.enterProgress("");
                }
            }
        } else if (stage == 4) {
            if (GameUtility.ifStringExist("\"Authority\": \"S\"", new File(GameUtility.FILE_HENRY)))
                line = plot.getLine("AC5");
        } else if (stage == 9) {
            Save location = new Save(GameUtility.FILE_JUSTIN_LOCATION);
            File token = new File("src", location.getProgress()[0]);
            String[] files = token.list();
            boolean justinExist = false;
            for (String temp : files) {
                if (temp.equals("Justin.dat")) {
                    justinExist = true;
                }
            }
            if (!justinExist) {
                line = plot.getLine("AC10");
            }
            Save saveFolder = new Save(GameUtility.FILE_SAVEFOLDER);
            GameUtility.deleteFolder("src", saveFolder.getProgress());
            saveFolder.enterProgress("");
        } else if (stage == 11) {
            if (GameUtility.ifStringExist("\"Authority\": \"A\"", new File(GameUtility.FILE_HENRY)))
                line = plot.getLine("AC0");
        }
    }

    /**
     * Base on the stage in Chapter Two, different functionality is executed to confuse player
     * There are mainly 3 things, which would be explained within the methods
     */
    private void actionToTake() {
        int stage = Integer.parseInt(line.getId().substring(2));
        if (stage == 3) {
            //Transfer Henry from one folder to another
            File file = new File(GameUtility.FILE_HENRY);
            file.renameTo(new File("Blackbox/Henry.dat"));
        } else if (stage == 5 || stage == 6 || stage == 7 || stage == 9) {
            //randomly generate 1000 folder and placed Henry in one of them.
            Save saveFolder = new Save(GameUtility.FILE_SAVEFOLDER);
            if (saveFolder.getProgress()[0].equals(" "))
                RandomFileGenerator.generate();
        }
        else if (line.getId().equals ("AC0"))
        {
            //If player beat the game, generate a file telling the computer that the final ending has been achieved
            clickFrozen = false;
            File finalEnding = new File(GameUtility.FILE_FE);
            try {
                finalEnding.createNewFile();
            } catch (Exception e) {}
        }
    }
}
