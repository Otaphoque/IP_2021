package view;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import static truc.HelloApplication.p;
import static truc.HelloApplication.planet;

public class GamePane extends GridPane {
    public QuadPane QPane = new QuadPane();

    Rectangle rectangle;

    final ImageView Endor = new ImageView(new Image("file:" + p + "Endor.jpg"));
    final ImageView Kamino = new ImageView(new Image("file:" + p + "Kamino.jpg"));

    final ImageView Grid = new ImageView(new Image("file:" + p + "Grid.png"));
    boolean grid = false;
    Group gridGroup = new Group(Grid);

    ImageView imageView;
    Group group;
    Group imageGroup;

    public GamePane() {


        this.setAlignment(Pos.CENTER);
        this.setPrefSize(800, 600);

        if (planet == true) {
            this.imageView = Endor  ;
        } else {
            this.imageView = Kamino;
        }
        this.getChildren().addAll(this.imageView, this.QPane, this.setAlien());
    }

    public GridPane setAlien() {
        GridPane alienPane = new GridPane();
        alienPane.setPrefSize(800, 600);
        Rectangle alienRectangle = new Rectangle(40, 60);
        alienRectangle.setFill(new ImagePattern(new Image("file:" + p + "Alien.png")));
        alienPane.getChildren().add(alienRectangle);
        alienRectangle.setLayoutX(210);
        alienRectangle.setLayoutY(435);
        return alienPane;
    }

    public void setEndor() {
        planet = true;
        imageView = Endor;
        this.updateImage();
    }

    public void setKamino() {
        planet = false;
        imageView = Kamino;
        this.updateImage();
    }

    public void setYesGrid() {
        this.grid = true;
        this.updateImage();
    }

    public void setNoGrid() {
        this.grid = false;
        this.updateImage();
    }

    public void updateImage() {
        this.imageGroup = new Group(this.imageView);
        if (grid) {
            this.group = new Group(this.imageGroup, this.gridGroup,this.QPane);
        } else {
            this.group = new Group(this.imageGroup,this.QPane);
        }
        this.getChildren().add(this.group);
    }




}
