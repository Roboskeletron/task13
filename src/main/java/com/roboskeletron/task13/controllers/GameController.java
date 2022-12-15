package com.roboskeletron.task13.controllers;

import com.roboskeletron.task13.Entities.Player;
import com.roboskeletron.task13.Render;
import com.roboskeletron.task13.primitives.Point2D;
import com.roboskeletron.task13.primitives.Sprite;
import javafx.animation.AnimationTimer;

public class GameController extends AnimationTimer {
    private final Render render;

    Player player = new Player(new Point2D(500, 100), new Sprite(0), "character0");

    public GameController(Render render){
        this.render = render;
        var entitiesList = render.getEntitiesList();
        entitiesList.add(player);
    }
    @Override
    public void handle(long now) {
        player.update();
        render.drawFrame();
    }
}
