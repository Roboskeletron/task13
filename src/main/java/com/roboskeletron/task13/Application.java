package com.roboskeletron.task13;

import com.roboskeletron.task13.controllers.GameController;
import com.roboskeletron.task13.controllers.KeyController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setFullScreenExitHint("");

        double height = Screen.getPrimary().getBounds().getHeight();
        double width = Screen.getPrimary().getBounds().getWidth();

        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, width, height);

        primaryStage.setScene(scene);
        Render render = new Render(canvas, 1);
        GameController gameController = new GameController(render);
        scene.setOnKeyPressed(KeyController::onKeyPressed);
        scene.setOnKeyReleased(KeyController::onKeyReleased);

        gameController.start();

        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
