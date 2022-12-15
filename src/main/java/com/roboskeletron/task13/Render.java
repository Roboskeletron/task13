package com.roboskeletron.task13;

import com.roboskeletron.task13.Entities.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Render {
    private final GraphicsContext graphicsContext;
    private final Canvas canvas;
    private Image backgroundImage;

    private List<Player> players = new ArrayList<>();

    public Render(Canvas canvas, int backgroundIndex) {
        this.canvas = canvas;
        graphicsContext = canvas.getGraphicsContext2D();

        String backgroundResourceName = ResourceManager.getBackgroundName(backgroundIndex);
        backgroundImage = ResourceManager.getImageFromResource(backgroundResourceName);
    }

    private void drawBackground(){
        graphicsContext.drawImage(backgroundImage, 0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public List<Player> getEntitiesList(){
        return players;
    }

    private void drawEntities(){
        for (var player : players){
            var position = player.getTransform().getPosition();

            graphicsContext.drawImage(player.getImage(), position.x(), position.y());
        }
    }

    public void drawFrame(){
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawBackground();
        drawEntities();
    }
}
