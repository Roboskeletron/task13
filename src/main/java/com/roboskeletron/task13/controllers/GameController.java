package com.roboskeletron.task13.controllers;

import com.roboskeletron.task13.Entities.Character;
import com.roboskeletron.task13.Render;
import com.roboskeletron.task13.base.Entity;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private final Render render;

    public GameController(Render render){
        this.render = render;
    }
}
