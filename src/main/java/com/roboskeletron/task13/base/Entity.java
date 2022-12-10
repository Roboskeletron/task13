package com.roboskeletron.task13.base;

import com.roboskeletron.task13.primitives.Point2D;
import com.roboskeletron.task13.primitives.Sprite;

public class Entity {
    protected Transform transform;
    protected Sprite sprite;

    public Entity(Point2D position, Sprite sprite){
        transform = new Transform(position);
        this.sprite = sprite;
    }

    public Transform getTransform(){
        return transform;
    }

    public Sprite getSprite(){
        return sprite;
    }
}
