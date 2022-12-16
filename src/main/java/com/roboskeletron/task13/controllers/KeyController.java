package com.roboskeletron.task13.controllers;

import com.roboskeletron.task13.interfaces.IInput;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;

public class KeyController implements IInput {
    public HashSet<KeyCode> keyInfo = new HashSet<>();
    public void onKeyPressed(KeyEvent keyEvent) {
        keyInfo.add(keyEvent.getCode());
    }

    public void onKeyReleased(KeyEvent keyEvent) {
        keyInfo.remove(keyEvent.getCode());
    }

    @Override
    public boolean up() {
        return keyInfo.contains(KeyCode.W);
    }

    @Override
    public boolean forward() {
        return keyInfo.contains(KeyCode.D);
    }

    @Override
    public boolean back() {
        return keyInfo.contains(KeyCode.A);
    }

    public byte[] formPackage(){
        byte data = 0;

        if (up())
            data+=4;
        if (forward())
            data+=1;
        if (back())
            data+=2;

        return new byte[]{data};
    }
}
