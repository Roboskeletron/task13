package com.roboskeletron.task13.primitives;

public record Vector2D(double x, double y) {
    public Vector2D multiply(double value){
        double x = this.x * value;
        double y = this.y * value;

        return new Vector2D(x, y);
    }

    public Vector2D add(double value){
        double x = this.x + value;
        double y = this.y + value;

        return new Vector2D(x, y);
    }

    public Vector2D add(Vector2D vector){
        double x = this.x + vector.x();
        double y = this.y + vector.y();

        return new Vector2D(x, y);
    }

    public Vector2D subtract(double value){
        return this.add(-value);
    }

    public Vector2D subtract(Vector2D vector){
        double x = this.x - vector.x();
        double y = this.y - vector.y();

        return new Vector2D(x, y);
    }

    public double length(){
        return Math.sqrt(x * x + y * y);
    }
}
