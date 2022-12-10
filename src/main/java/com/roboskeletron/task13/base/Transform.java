package com.roboskeletron.task13.base;

import com.roboskeletron.task13.interfaces.ITransform;
import com.roboskeletron.task13.primitives.Point2D;
import com.roboskeletron.task13.primitives.Vector2D;

public class Transform implements ITransform {
    private Point2D position;

    public Transform(Point2D startPosition){
        position = startPosition;
    }

    @Override
    public void translate(Vector2D vector) {
        int x = position.x() + vector.x();
        int y = position.y() + vector.y();

        position = new Point2D(x, y);
    }

    @Override
    public void translateX(int x) {
        x += position.x();
        int y = position.y();

        position = new Point2D(x, y);
    }

    @Override
    public void translateY(int y) {
        int x = position.x();
        y += position.y();

        position = new Point2D(x, y);
    }

    @Override
    public void setPosition(Point2D point) {
        position = point;
    }

    @Override
    public void setPosition(int x, int y) {
        position = new Point2D(x, y);
    }

    public Point2D getPosition(){
        return position;
    }
}
