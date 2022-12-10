package com.roboskeletron.task13.Entities;

import com.roboskeletron.task13.base.Entity;
import com.roboskeletron.task13.primitives.Point2D;
import com.roboskeletron.task13.primitives.Sprite;

public class Character extends Entity {
    private String name;
    private int health;

    public Character(Point2D position, Sprite sprite, String name) {
        super(position, sprite);
        this.name = name;
        health = 1000;
    }

    public String getName(){
        return name;
    }

    public boolean isAlive(){
        return health > 0;
    }
}
