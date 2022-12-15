package com.roboskeletron.task13.Entities;

import com.roboskeletron.task13.base.Transform;
import com.roboskeletron.task13.controllers.KeyController;
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

    public void update(){
        updatePosition(KeyController.keyInfo.contains(KeyCode.D), KeyController.keyInfo.contains(KeyCode.A),
                KeyController.keyInfo.contains(KeyCode.S), KeyController.keyInfo.contains(KeyCode.W));
    }
    private void updatePosition(boolean forward, boolean back, boolean up, boolean down){
        if (!canMove)
            return;

        double x = (forward ? 1 : 0)
                + (back ? -1 : 0);

        double y = (up ? 1 : 0)
                + (down ? -1 : 0);

        x*=acceleration;
        y*=acceleration;

        if (x == 0 && movementDirection.x() != 0){
            x = acceleration * -Math.signum(movementDirection.x());
        }

        if (y == 0 && movementDirection.y() != 0){
            y = acceleration * -Math.signum(movementDirection.y());
        }

        velocityX+=x;
        velocityY+=y;

        velocityX = normalizeVelocity(velocityX);
        velocityY = normalizeVelocity(velocityY);
        
        

        movementDirection = new Vector2D(velocityX, velocityY);

        transform.translate(movementDirection);
    }

    public double normalizeVelocity(double origin) {
        double abs = Math.abs(origin);

        if (abs < acceleration)
            return 0;

        return abs <= maxVelocity ? origin : maxVelocity * Math.signum(origin);
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
}
