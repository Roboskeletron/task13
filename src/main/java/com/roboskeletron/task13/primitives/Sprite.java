package com.roboskeletron.task13.primitives;

import com.roboskeletron.task13.ResourceManager;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sprite {
    private List<Stage> stages = new ArrayList<>();
    private Image image;
    private int stageIndex = 0;
    private int imagesPerSecond =  60 / 8;
    private int frameNumber = 0;

    public Sprite(int spriteId){
        String directory = ResourceManager.getCharacterDir(spriteId);

        image = ResourceManager.getImageFromResource(directory + "sprite.png");

        initStages(directory);
    }

    private void initStages(String dir){
        var stream = ResourceManager.class.getResourceAsStream(dir + "stages.txt");

        Scanner scanner = new Scanner(stream);

        while (scanner.hasNextLine()){
            var line = scanner.nextLine();
            stages.add(new Stage(line));
        }
    }

    private Image getSprite(int index){
        var pixelReader = image.getPixelReader();

        var stage = stages.get(index);
        int width = (int) stage.width;
        int height = (int) stage.height;

        int[] buffer = new int[(int) (stage.width * stage.height)];

        PixelFormat.Type type = pixelReader.getPixelFormat().getType();

        WritablePixelFormat<IntBuffer> format;

        if(type == PixelFormat.Type.INT_ARGB_PRE)
        {
            format = PixelFormat.getIntArgbPreInstance();
        }
        else
        {
            format = PixelFormat.getIntArgbInstance();
        }
            pixelReader.getPixels((int) stage.x, (int) stage.y, width, height,
                    format, buffer, 0, width);

            WritableImage sprite = new WritableImage(width, height);
            sprite.getPixelWriter().setPixels(0, 0, width, height,
                    format, buffer, 0, width);

            return sprite;
    }

    public Image getImage(Vector2D direction){
        if (direction.y() != 0 || direction.x() != 0){
            Image stage = getSprite(stageIndex);
            changeStage();
            return stage;
        }

        stageIndex = 0;

        return  getSprite(0);
    }

    private void changeStage(){
        if (frameNumber >= imagesPerSecond) {
            stageIndex += 1;

            if (stageIndex >= stages.size())
                stageIndex = 0;

            frameNumber = 0;
            return;
        }

        frameNumber++;
    }

    public int getImagesPerSecond(){
        return 60 / imagesPerSecond;
    }

    public void setImagesPerSecond(int count){
        imagesPerSecond = 60 / count;
    }
}
