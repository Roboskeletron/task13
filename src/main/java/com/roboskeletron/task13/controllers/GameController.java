package com.roboskeletron.task13.controllers;

import com.roboskeletron.task13.Entities.Player;
import com.roboskeletron.task13.Render;
import com.roboskeletron.task13.base.Transform;
import com.roboskeletron.task13.primitives.Point2D;
import com.roboskeletron.task13.primitives.Sprite;
import javafx.animation.AnimationTimer;

public class GameController extends AnimationTimer {
    private final Render render;
    private double width;
    private double height;
    private double floorY;
    Player player = new Player(new Point2D(500, 514.25), new Sprite(0), "character0");

    public GameController(Render render, double width, double height){
        this.render = render;
        var entitiesList = render.getEntitiesList();
        entitiesList.add(player);
        this.width = width;
        this.height = height;

        floorY = height - height * 0.42;
        player.setFloor(floorY);
    }
    @Override
    public void handle(long now) {
        player.update();
        normalizePosition(player.getTransform());
        render.drawFrame();
    }

    private void normalizePosition(Transform transform){
        double x = transform.getPosition().x();
        double y = transform.getPosition().y();

        if (x < 0)
            x = 0;
        if (x > width - width * 0.054)
            x = width - width * 0.054;

        if (y < 0)
            y = 0;
        if (y > floorY)
            y = floorY;

        transform.setPosition(x, y);
    }
}
