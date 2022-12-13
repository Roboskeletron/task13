package com.roboskeletron.task13.controllers;

import com.roboskeletron.task13.Entities.Character;
import com.roboskeletron.task13.Render;
import com.roboskeletron.task13.primitives.Point2D;
import com.roboskeletron.task13.primitives.Sprite;

public class GameController {
    private final Render render;

    public GameController(Render render){
        this.render = render;
        var entitiesList = render.getEntitiesList();
        entitiesList.add(new Character(new Point2D(50, 50), new Sprite(0), "character0"));
        render.drawFrame();
    }
}
