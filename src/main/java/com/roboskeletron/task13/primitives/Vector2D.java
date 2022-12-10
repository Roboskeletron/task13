package com.roboskeletron.task13.primitives;

public record Vector2D(int x, int y) {
    public Vector2D multiply(int value){
        int x = this.x * value;
        int y = this.y * value;

        return new Vector2D(x, y);
    }

    public Vector2D add(int value){
        int x = this.x + value;
        int y = this.y + value;

        return new Vector2D(x, y);
    }

    public Vector2D add(Vector2D vector){
        int x = this.x + vector.x();
        int y = this.y + vector.y();

        return new Vector2D(x, y);
    }

    public Vector2D subtract(int value){
        return this.add(-value);
    }

    public Vector2D subtract(Vector2D vector){
        int x = this.x - vector.x();
        int y = this.y - vector.y();

        return new Vector2D(x, y);
    }
}
