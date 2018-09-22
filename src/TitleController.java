/*
 * Student Names: Justin Cai & Henry Xue
 * Teacher Name: Mr. Benum
 * Course Code: ICS4U
 * Date: June 15, 2018
 *
 * Description:
 * Controller for title screen.
 */

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class TitleController {

    /**
     * Initializes game and sets to game screen.
     * @param event contains information on the invoker of this method
     */
    @FXML
    private void init(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Scene scene = node.getScene();
            String action = (String) node.getUserData();

            // Loads game screen.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = loader.load();
            scene.setRoot(root);

            // Initializes the game by invoking the init method.
            GameController controller = loader.getController();
            controller.init(action);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exits the game.
     */
    @FXML
    private void exit() {
        Platform.exit();
    }
}
