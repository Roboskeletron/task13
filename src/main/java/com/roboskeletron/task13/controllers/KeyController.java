package com.roboskeletron.task13.controllers;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;

public class KeyController {
    public static HashSet<KeyCode> keyInfo = new HashSet<>();
    public static void onKeyPressed(KeyEvent keyEvent) {
        keyInfo.add(keyEvent.getCode());
    }

    public static void onKeyReleased(KeyEvent keyEvent) {
        keyInfo.remove(keyEvent.getCode());
    }
}
