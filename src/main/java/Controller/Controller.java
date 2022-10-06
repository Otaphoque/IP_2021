package Controller;

import javafx.animation.PathTransition;
import javafx.scene.shape.Circle;
import javafx.scene.shape.QuadCurve;
import javafx.util.Duration;
import view.GamePane;
import view.QuadPane;
import view.View;

public class Controller {
    GamePane gPane = new GamePane();
   // QuadPane qCurve= new QuadPane();
   double f;
    QuadCurve q;
    Circle ball;

    private View view;

    public Controller (View view) {
        this.view = view;
    }

    public void startButton() {
        this.view.gamePane.QPane.startAnimate();
    }

}


