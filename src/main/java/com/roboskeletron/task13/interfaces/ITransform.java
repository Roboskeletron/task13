package com.roboskeletron.task13.interfaces;

import com.roboskeletron.task13.primitives.Point2D;
import com.roboskeletron.task13.primitives.Vector2D;

public interface ITransform {
    void translate(Vector2D vector);
    void translateX(int x);
    void translateY(int y);
    void setPosition(Point2D point);
    void setPosition(int x, int y);
}
