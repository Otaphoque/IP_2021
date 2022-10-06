package view;

import Controller.Controller;
import Model.Model;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static truc.HelloApplication.p;

public class View extends GridPane {

    static Font font = Font.loadFont("file:" + p + "Nazalization.otf", 15);

    Controller controller = new Controller(this);
    Model model;
    public GamePane gamePane = new GamePane();


    Label gravity = new CustomLabel("");
    Label name = new CustomLabel("");

    public View(Model model) {
        this.model = model;

        this.add(this.topMenu(gamePane), 0, 0);
        GridPane mainGrid = new GridPane();
        mainGrid.setPadding(new Insets(10, 10, 10, 10));
        mainGrid.add(this.titleBox(), 0, 1);
        mainGrid.add(this.gamePane, 0, 2);
        mainGrid.add(this.bottomMenu(), 0, 3);

        this.add(mainGrid, 0, 1);
    }


    public HBox titleBox() {
        HBox box = new HBox();
        box.setPadding(new Insets(5, 5, 10, 5));
        box.setAlignment(Pos.TOP_RIGHT);
        ImageView title = new ImageView(new Image("file:" + p + "Title.png"));
        box.getChildren().add(title);
        return box;
    }

    public HBox topMenu(GamePane gamepane) {
        HBox box = new HBox();
        MenuBar menuBar = new TopMenuManager(gamePane, this);
        box.getChildren().add(menuBar);

        return box;
    }



    public HoBox bottomMenu() {
        HoBox box = new HoBox();


//        TextField velocity = new CustomTextField("Velocity");
       this.setGravityAndPlanet();
       Button start = new CustomButton("Start");
      start.setOnAction(event -> controller.startButton());
      VeBox v1 = new VeBox();
      v1.getChildren().addAll(this.name, this.gravity, start);

    box.getChildren().addAll(setVslider(), setAslider(), v1);

        return box;
    }

    public void setGravityAndPlanet() {
        this.gravity.setText("Gravity : " + Integer.toString(model.current_Gravity()));
        this.name.setText("Planet : " + model.current_Planet());
    }

    public VeBox setVslider(){
        VeBox v0 = new VeBox();

        //Velocity Slider
        Label VelocityTitle = new CustomLabel("Velocity");
        Label VelocityNumber = new CustomLabel("");

        VelocityNumber.setTextFill(Color.BLACK);

        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(10);
        slider.setValue(5);

        slider.valueProperty().addListener(
                new ChangeListener<Number>() {

                    public void changed(ObservableValue<? extends Number >
                                                observable, Number oldValue, Number newValue)
                    {

                        VelocityNumber.setText(Integer.toString(newValue.intValue()));
                        gamePane.QPane.setV0(newValue.doubleValue());
                    }
                });
        v0.getChildren().addAll(VelocityTitle,slider,VelocityNumber);
        return v0;
    }

    public VeBox setAslider(){
        VeBox v0 = new VeBox();

        //Angle Slider
        Label AngleTitle = new CustomLabel("Angle");
        Label AngleNumber = new CustomLabel("");

        AngleNumber.setTextFill(Color.BLACK);

        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(90);
        slider.setValue(45);

        slider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number >
                                                observable, Number oldValue, Number newValue)
                    {
                        AngleNumber.setText(Integer.toString(newValue.intValue()));
                        gamePane.QPane.setAngle(newValue.doubleValue());

                    }
                });
        v0.getChildren().addAll(AngleTitle, slider, AngleNumber);
        return v0;
    }

    public class HoBox extends HBox {
        public HoBox() {
            this.setAlignment(Pos.CENTER);
            this.setPadding(new Insets(5, 5, 5, 5));
            this.setSpacing(10);
        }
    }

    public class VeBox extends HBox {
        public VeBox() {
            this.setAlignment(Pos.CENTER);
            this.setPadding(new Insets(5, 5, 5, 5));
            this.setSpacing(10);
        }
    }

    public class CustomButton extends Button {
        public CustomButton(String str) {
            this.setText(str);
            this.setFont(font);
            this.setStyle("-fx-background-color: white;");
        }
    }

    public class CustomLabel extends Label {
        public CustomLabel(String str) {
            this.setText(str);
            this.setFont(font);
            this.setStyle("-fx-background-color: white;");
        }
    }

    public class CustomTextField extends TextField {
        public CustomTextField(String str) {
            this.setText(str);
            this.setFont(font);
            this.setStyle("-fx-background-color: white;");
        }
    }

}
