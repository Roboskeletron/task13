package com.roboskeletron.task13.primitives;

public class Stage {
    public final double x, y, width, height;
    public Stage(String info){
        var data = info.split(" ");
        x = Double.parseDouble(data[0]);
        y = Double.parseDouble(data[1]);
        width = Double.parseDouble(data[2]);
        height = Double.parseDouble(data[3]);
    }
}
