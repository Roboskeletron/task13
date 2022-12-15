package com.roboskeletron.task13;

import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Scanner;

public class ResourceManager {
    public static Image getImageFromResource(String name){
        var imageStream = ResourceManager.class.getResourceAsStream(name);
        return new Image(imageStream);
    }

    public static String getBackgroundName(int index) {
        var stream = ResourceManager.class.getResourceAsStream("background/backgroundList.txt");

        Scanner scanner = new Scanner(stream);

        while (scanner.hasNextLine()){
            var line = scanner.nextLine();
            var data = line.split(" ");
            if (Integer.parseInt(data[0]) == index){
                return data[1];
            }
        }

        return  null;
    }

    public static String getCharacterDir(int id){
        var stream = ResourceManager.class.getResourceAsStream("sprites/characters.txt");

        Scanner scanner = new Scanner(stream);
        while (scanner.hasNextLine()){
            var line = scanner.nextLine();
            var data = line.split(" ");

            if (Integer.parseInt(data[0]) == id){
                return "sprites/" + data[1] + "/";
            }
        }

        return null;
    }
}
