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
    private double health;
    private double floor;
    private int lookingDirection = 1;
    private boolean isBlocking = false;
    private int framesAfterPunch = 0;
    private boolean canPunch = true;
    private double damage = 50;

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
        setBlockingState(input.block());
        updatePunch();
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

    public void takeDamage(double damage){
        if (damage > health)
            health = 0;
        else
            health-=damage;
    }

    public void setBlockingState(boolean state){
        if (state)
            canMove = false;
        else
            canMove = true;

        isBlocking = state;
    }

    public boolean getBlockingState(){
        return isBlocking;
    }

    public double getHealth(){
        return health;
    }

    private void updatePunch(){
        if (canPunch)
            framesAfterPunch = 0;
        else if (framesAfterPunch >= 60)
            canPunch = true;
        else framesAfterPunch += 1;
    }

    public boolean isCanPunch(){
        return canPunch;
    }

    public double punch(Vector2D distanceVector){
        if (!canPunch)
            return  0;

        canPunch = false;

        if (distanceVector.length() > 100 || lookingDirection == Math.signum(distanceVector.x()))
            return 0;

        return damage;
    }
}
