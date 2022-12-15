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
    private NetworkController networkController;
    Player player;

    public GameController(Render render, double width, double height, NetworkController networkController){
        this.render = render;
        var entitiesList = render.getEntitiesList();
        this.width = width;
        this.height = height;
        this.networkController = networkController;

        if (networkController.isSever){
            player = new Player(new Point2D(500, 514.25), new Sprite(0), "character0");
        }
        else{
            player = new Player(new Point2D(1219, 514.25), new Sprite(0), "character1");
        }

        floorY = height - height * 0.42;
        player.setFloor(floorY);
        entitiesList.add(player);
        entitiesList.add(networkController.getPlayer());
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
