package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static truc.HelloApplication.p;

public class TopMenuManager extends MenuBar {

    GamePane gamePane;
    View view;

    public TopMenuManager(GamePane gamePane, View view) {
        this.gamePane = gamePane;
        this.view = view;

        this.getMenus().add(this.menumenu());
        this.getMenus().add(this.planetmenu(gamePane));
        this.getMenus().add(this.gridmenu());
    }

    public Menu menumenu() {
        MenuItem help = new MenuItem("Help (show formula)");
        MenuItem credits = new MenuItem("Credits");
        Menu menu = new Menu("Menu");
        menu.getItems().add(help);
        menu.getItems().add(credits);

        help.setOnAction(e -> {
            Stage formulaStage = new Stage();
            GridPane formulaGrid = new GridPane();
            formulaGrid.setAlignment(Pos.CENTER);
            formulaGrid.setPadding(new Insets(10, 10, 10, 10));
            formulaGrid.getChildren().add(new ImageView(new Image("file:" + p + "Formula.jpg")));
            formulaStage.setScene(new Scene(formulaGrid));
            formulaStage.setTitle("Formula");
            formulaStage.show();
        });

        credits.setOnAction(e -> {
            Stage formulaStage = new Stage();
            VBox creditsBox = new VBox();
            creditsBox.setPadding(new Insets(10, 10, 10, 10));
            creditsBox.setAlignment(Pos.CENTER);
            creditsBox.getChildren().add(new ImageView(new Image("file:" + p + "Title.png")));
            creditsBox.getChildren().add(new ImageView(new Image("file:" + p + "Credits.jpg")));
            formulaStage.setScene(new Scene(creditsBox));
            formulaStage.setTitle("Credits");
            formulaStage.show();
        });

        return menu;
    }

    public Menu planetmenu(GamePane gamePane) {
        CheckMenuItem endor = new CheckMenuItem("Endor");
        endor.setSelected(true);
        CheckMenuItem kamino = new CheckMenuItem("Kamino");
        Menu planet = new Menu("Planet");
        planet.getItems().add(endor);
        planet.getItems().add(kamino);

        endor.setOnAction(e -> {
            if (endor.isSelected()) {
                kamino.setSelected(false);
                this.gamePane.setEndor();
                this.view.setGravityAndPlanet();
            }
        });

        kamino.setOnAction(e -> {
            if (kamino.isSelected()) {
                endor.setSelected(false);
                this.gamePane.setKamino();
                this.view.setGravityAndPlanet();
            }
        });

        return planet;
    }

    public Menu gridmenu() {
        CheckMenuItem yesGrid = new CheckMenuItem("Grid");
        CheckMenuItem noGrid = new CheckMenuItem("No Grid");
        noGrid.setSelected(true);
        Menu grid = new Menu("Grid");
        grid.getItems().add(yesGrid);
        grid.getItems().add(noGrid);

        yesGrid.setOnAction(e -> {
            if (yesGrid.isSelected()) {
                noGrid.setSelected(false);
                this.gamePane.grid = true;
                this.gamePane.setYesGrid();
            }
        });

        noGrid.setOnAction(e -> {
            if (noGrid.isSelected()) {
                yesGrid.setSelected(false);
                this.gamePane.grid = false;
                this.gamePane.setNoGrid();
            }
        });

        return grid;
    }

}
