package com.roboskeletron.task13;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Logger {
    private OutputStream stream;
    private int frame = 1;

    public Logger(String fileName) {
        try {
            stream = new FileOutputStream(fileName);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeLine(String text) throws IOException {
        String line = frame + " " + text + "\n";

        stream.write(line.getBytes());
        stream.flush();
        frame++;
    }
}
