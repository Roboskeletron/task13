package com.roboskeletron.task13.controllers;

import com.roboskeletron.task13.Entities.Player;
import com.roboskeletron.task13.interfaces.IInput;
import com.roboskeletron.task13.primitives.NetworkPackage;
import com.roboskeletron.task13.primitives.Point2D;
import com.roboskeletron.task13.primitives.Sprite;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkController implements IInput {
    public final boolean isSever;
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private  final Socket socket;
    private byte inputInfo = 0;
    private Player player;
    private NetworkPackage networkPackage = new NetworkPackage(1);

    public NetworkController(int port) throws IOException {
        isSever = true;
        socket = new ServerSocket(port).accept();

        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();

        player = new Player(new Point2D(1219, 514.25), new Sprite(0), "character1");
    }

    public NetworkController(String address) throws IOException {
        isSever = false;

        String[] parsedAddress = address.split(":");
        address = parsedAddress[0];
        int port = Integer.parseInt(parsedAddress[1]);

        socket = new Socket(address, port);

        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();

        player = new Player(new Point2D(500, 514.25), new Sprite(0), "character0");
    }

    public Player getPlayer(){
        return player;
    }

    public void update(KeyController input) throws IOException {
        inputInfo = networkPackage.readPackage(inputStream)[0];
        player.update(this);

        networkPackage.sendPackage(input.formPackage(), outputStream);
    }

    @Override
    public boolean up() {
        return (inputInfo & 4) == 1;
    }

    @Override
    public boolean forward() {
        return (inputInfo & 1) == 1;
    }

    @Override
    public boolean back() {
        return (inputInfo & 2) == 1;
    }
}
