package com.roboskeletron.task13.Entities;

import com.roboskeletron.task13.base.Physics;
import com.roboskeletron.task13.base.Transform;
import com.roboskeletron.task13.controllers.KeyController;
import com.roboskeletron.task13.interfaces.IInput;
import com.roboskeletron.task13.primitives.Point2D;
import com.roboskeletron.task13.primitives.Sprite;
import com.roboskeletron.task13.primitives.Vector2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Player extends Physics {
    private Transform transform;
    private Sprite sprite;
    private String name;
    private int health;
    private double floor;
    private int lookingDirection = 1;

    public Player(Point2D position, Sprite sprite, String name) {
        transform = new Transform(position);
        this.sprite = sprite;
        this.name = name;
        health = 1000;
    }

    public String getName(){
        return name;
    }

    public boolean isAlive(){
        return health > 0;
    }

    public void update(IInput input){
        updatePosition(input);
    }

    private void updatePosition(IInput input){
        double directionX = (input.forward() ? 1 : 0) + (input.back() ? -1 : 0);
        double directionY = input.up() ? -1 : 0;

        movementDirection = new Vector2D(directionX, directionY);

        transform.translate(evalMovement());
    }

    public Transform getTransform(){
        return transform;
    }

    public Image getImage(){
        return sprite.getImage(movementDirection);
    }

    public void setFloor(double y){
        if (y > 0)
            floor = y;
    }

    @Override
    public boolean isInAir() {
        return transform.getPosition().y() < floor;
    }

    public int getLookingDirection(){
        if (movementDirection.x() > 0)
            lookingDirection = 1;

        else if (movementDirection.x() < 0)
            lookingDirection = -1;

        return lookingDirection;
    }
}
