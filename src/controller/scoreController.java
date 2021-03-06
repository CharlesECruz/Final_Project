package controller;

import controller.gameController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import controller.menuController;

import java.net.URL;
import java.util.ResourceBundle;

public class scoreController implements Initializable {

    @FXML
    public ProgressIndicator progressScore;
    @FXML
    public Label scoreLabel;
    @FXML
    public TextField timerTextField;
    @FXML
    public TextField timerQuestionTextField;

    /**
     * Method to update the score in the progress cirle bar and update the messages for the user
     */

    private void updateScoreMessages() {
        timerTextField.setText(timeTotalString());
        timerQuestionTextField.setText(String.format("%.2f seconds", calculateTime()));
        if (calculateScore() == 1.0) {
            scoreLabel.setText("Awesome job! You are totally ready for the Test.");
        } else if (calculateScore() >= 0.7 && calculateScore() < 1.0) {
            scoreLabel.setText("Good Job! But not enough, Keep Practicing and you will be ready");
        } else if (calculateScore() >= 0.1 && calculateScore() < 0.6) {
            scoreLabel.setText("Terrible Job! Go practice more!");
        } else {
            scoreLabel.setText("Awful job! You got 0 correct answers!");
        }

    }

    /**
     * Method that prints the time with the score
     * @return
     */
    private String timeTotalString() {
        int hours = (gameController.timeTotal / 60) / 60;
        int minutes = (gameController.timeTotal / 60) % 60;
        int seconds = gameController.timeTotal % 60;
        return hours + ":" + minutes + ":" + seconds;
    }

    /**
     * Method to calculate the average time per question based in the times.
     * @return
     */
    public double calculateTime() {
        return gameController.timeTotal * 1.0 / menuController.getNumberQuestionGame();
    }

    /**
     * This Method calculates the score based in the correct answers and the quantity of questions
     * @return score
     */
    public Double calculateScore() {
        Double score = gameController.score * 1.0 / menuController.getNumberQuestionGame();

        return score;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progressScore.setProgress(calculateScore());
        updateScoreMessages();
    }
}
