package view;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;
import static truc.HelloApplication.planet;
import static truc.HelloApplication.p;


public class QuadPane extends GridPane {

    int StartScrX = 50;
    int StartScrY = 435;
    final double pi = Math.PI;
    int g = -12;
    double vInitial = 100;
    double theta = 85;
    double t, x, y,vx,vy;
    QuadCurve path;
    Circle  ball = new Circle(50, 350, 10);




    public int setG(int g) {
        if (planet == true) {
            return -5 ;
        } else {
            return -12;
        }
    }

    public void setV0(double vInitial) {
        this.vInitial = vInitial;
    }

    public void setAngle(double theta) {
        this.theta = theta;
    }

    public QuadPane(){
        this.setG(g);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setAlignment(Pos.BOTTOM_LEFT);
        this.setPrefSize(800,600);
        this.ball.setFill(Color.LAVENDER);
        this.getChildren().add(ball);
    }


    QuadCurve setQuadCurve() {

        path = new QuadCurve();

        vx = vInitial*Math.cos(pi*theta/180);
        vy = vInitial*Math.sin(pi*theta/180);

        t = -2* vy /g;                              // time, taken from formula DeltaY = vt + 1/2(gt^2)         --> DeltaY = 0
        x = vx *t;                                  // DeltaX, taken from formula DeltaX = vt + 1/2(at^2)       --> a = 0
        y = -((vy*vy)/(2*g));                       // MaxHeight, taken form formula Vf^2 = Vi^2 + 2g(Yf-Yi)    --> Vf = 0, Yi = 0

        double controlPointX = -vx*vy/g;            // control point X of the quad curve, middle point of DeltaX


        double controlPointY = y;                   // control point Y of the curve, is the same as controlPointX because the starting angle is 45°
        System.out.println(controlPointX+"=x" + controlPointY + "=y");
        System.out.println(vx +" = vx ");
        System.out.println(vy +" = vy ");

        // Setting the starting point where the ball is situated on the pane
        path.setStartX(StartScrX);
        path.setStartY(StartScrY);

        // Setting the control points in place according the where the ball is situated on the pane
        path.setControlX(StartScrX+controlPointX);
        path.setControlY(StartScrY-controlPointY);

        // Setting the end point relative to where the ball is situated on the pane / the starting point
        path.setEndX(StartScrX+x);
        path.setEndY(StartScrY);

        path.setFill(Color.TRANSPARENT);
        System.out.println(this.path.toString());
        return path;
    }

    void animate() {
        PathTransition pt = new PathTransition(Duration.seconds(this.t), this.path, this.ball);
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pt.setCycleCount(1);
        pt.setAutoReverse(false);
        System.out.println(this.path.toString());
        pt.setOnFinished(event -> this.didYouWin());
        pt.play();
    }

    public void startAnimate(){
        this.getChildren().add(this.setQuadCurve());
        this.animate();
    }

    public void didYouWin() {
        if ((this.path.getEndX() > 189) && (this.path.getEndX() < 231)) {
            Stage winningStage = new Stage();
            VBox winningBox = new VBox();
            winningBox.setPadding(new Insets(10, 10, 10, 10));
            winningBox.setAlignment(Pos.CENTER);
            winningBox.getChildren().add(new ImageView(new Image("file:" + p + "Win.jpeg")));
            winningStage.setScene(new Scene(winningBox));
            winningStage.setTitle("You won!");
            winningStage.show();
        }
    }

    public void setAlien() {
        Rectangle rectangle = new Rectangle(40, 60);
        rectangle.setFill(Color.BLACK);
        rectangle.setLayoutX(700);
        rectangle.setLayoutY(200);

        this.getChildren().add(rectangle);
    }

}