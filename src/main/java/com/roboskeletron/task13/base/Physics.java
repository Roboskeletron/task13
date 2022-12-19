package com.roboskeletron.task13.base;

import com.roboskeletron.task13.primitives.Vector2D;

public abstract class Physics {
    protected double velocityX = 0;
    protected double velocityY = 0;
    protected Vector2D movementDirection = new Vector2D(0, 0);
    protected double acceleration = 0.25;
    protected double friction = 0.1;
    protected double gravity = 0.5;
    protected double jumpVelocity = 20;
    protected boolean canMove = true;
    protected double maxVelocity = 4;

    public double getAcceleration(){
        return acceleration;
    }

    public double getFriction(){
        return friction;
    }

    public double getGravity(){
        return gravity;
    }

    public double getMaxVelocity(){
        return maxVelocity;
    }

    protected void setValue(double origin, double value){
        if (value > 0)
            origin = value;
    }

    public void  setAcceleration(double value){
        setValue(acceleration, value);
    }

    public void setGravity(double value){
        setValue(gravity, value);
    }

    public void setFriction(double value){
        setValue(friction, value);
    }

    public void setJumpVelocity(double value){
        setValue(jumpVelocity, value);
    }

    public void setMaxVelocity(double value){
        setValue(maxVelocity, value);
    }

    protected double evalVelocity(double velocity, double acceleration){
        return normalizeVelocity(velocity + acceleration, acceleration);
    }

    protected Vector2D evalMovement(){
        if (!canMove)
            return new Vector2D(0, 0);

        double friction = -this.friction * Math.signum(velocityX);
        double acceleration = this.acceleration * movementDirection.x();

        velocityX = evalVelocity(velocityX, acceleration + friction);

        velocityX = Math.abs(velocityX) > maxVelocity ? (Math.signum(velocityX) * maxVelocity) : velocityX;

        double jumpAcceleration = isInAir() ? gravity : this.jumpVelocity * movementDirection.y();

        velocityY = evalVelocity(velocityY, jumpAcceleration);

        return new Vector2D(velocityX, velocityY);
    }

    public abstract boolean isInAir();

    public double normalizeVelocity(double velocity, double acceleration){
        if (Math.abs(velocity) < acceleration)
            return 0;

        return velocity;
    }
}
