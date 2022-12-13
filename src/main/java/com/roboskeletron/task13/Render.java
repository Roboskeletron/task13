package com.roboskeletron.task13;

import com.roboskeletron.task13.base.Entity;
import com.roboskeletron.task13.primitives.Sprite;
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

    private List<Entity> entities = new ArrayList<>();

    public Render(Canvas canvas, int backgroundIndex) throws IOException {
        this.canvas = canvas;
        graphicsContext = canvas.getGraphicsContext2D();

        String backgroundResourceName = ResourceManager.getBackgroundName(backgroundIndex);
        backgroundImage = ResourceManager.getImageFromResource(backgroundResourceName);
    }

    private void drawBackground(){
        graphicsContext.drawImage(backgroundImage, 0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public List<Entity> getEntitiesList(){
        return entities;
    }

    private void drawEntities(){
        for (var entity : entities){
            var position = entity.getTransform().getPosition();
            Sprite sprite = entity.getSprite();

            if (sprite != null) {
                graphicsContext.drawImage(sprite.getImage(), position.x(), position.y());
            }
        }
    }

    public void drawFrame(){
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawBackground();
        drawEntities();
    }
}
