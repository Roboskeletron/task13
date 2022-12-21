package com.roboskeletron.task13;

import com.roboskeletron.task13.Entities.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

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

            var image = player.getImage();
            double x = position.x() - image.getWidth() / 2 * player.getLookingDirection();

            graphicsContext.drawImage(image, 0, 0, image.getWidth(), image.getHeight(),
                    x, position.y(), image.getWidth() * player.getLookingDirection(), image.getHeight());
        }
    }

    private void drawHealth(){
        Player player = players.get(0);
        Player enemy = players.get(1);

        graphicsContext.setFill(Color.rgb(0, 255, 0));
        graphicsContext.fillRect(0, 0, getHealthWidth(player.getHealth()),
                canvas.getHeight() * 0.05);
        graphicsContext.setFill(Color.rgb(255, 0, 0));
        graphicsContext.fillRect(canvas.getWidth() / 2, 0, getHealthWidth(enemy.getHealth()), canvas.getHeight() * 0.05);
    }

    public void drawFrame(){
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawBackground();
        drawEntities();
        drawHealth();
    }

    private double getHealthWidth(double health){
        return (canvas.getWidth() / 2) * health / 1000;
    }
}
