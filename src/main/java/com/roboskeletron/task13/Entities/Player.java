package com.roboskeletron.task13.Entities;

import com.roboskeletron.task13.base.Transform;
import com.roboskeletron.task13.controllers.KeyController;
import com.roboskeletron.task13.interfaces.IInput;
import com.roboskeletron.task13.primitives.Point2D;
import com.roboskeletron.task13.primitives.Sprite;
import com.roboskeletron.task13.primitives.Vector2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Player {
    private Transform transform;
    private Sprite sprite;
    private String name;
    private int health;
    private Vector2D movementDirection = new Vector2D(0, 0);
    private double velocityX = 0;
    private double velocityY = 0;
    private double maxVelocity = 4;
    private double acceleration = 0.25;
    public boolean canMove = true;
    private double gravity = 0.5;
    private double jumpVelocity = 20;
    private double floor;

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
        if (!canMove && isAlive())
            return;

        double xAcceleration = (input.forward() ? 1 : 0)
                + (input.back() ? -1 : 0);

        double yAcceleration = 0;

        xAcceleration*=acceleration;

        if (xAcceleration == 0 && movementDirection.x() != 0){
            xAcceleration = acceleration * -Math.signum(movementDirection.x());
        }

        if (!isInAir()){
            velocityY = 0;
        }

        if (movementDirection.y() == 0 && !isInAir()){
            yAcceleration = -(input.up() ? 1 : 0) * jumpVelocity;
        }

        if (isInAir()) {
            yAcceleration = gravity;
        }

        velocityX+=xAcceleration;
        velocityY+=yAcceleration;

        if (velocityY > 0){
            velocityY = velocityY + 0;
        }
        velocityX = normalizeVelocity(velocityX, maxVelocity, acceleration);
        velocityY = normalizeVelocity(velocityY, jumpVelocity, gravity);

        movementDirection = new Vector2D(velocityX, velocityY);

        transform.translate(movementDirection);
    }

    public double normalizeVelocity(double origin, double max, double acceleration) {
        double abs = Math.abs(origin);

        if (abs < acceleration)
            return 0;

        return abs <= max ? origin : max * Math.signum(origin);
    }

    public double getVelocity(){
        return maxVelocity;
    }

    public double getAcceleration(){
        return acceleration;
    }

    public void setAcceleration(double acceleration){
        if (acceleration > 0 && acceleration <= 1)
            this.acceleration = acceleration;
    }

    public void setVelocity(double velocity){
        if (velocity > 0)
            maxVelocity = velocity;
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

    public boolean isInAir(){
        return transform.getPosition().y() < floor;
    }
}
