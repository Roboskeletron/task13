package com.roboskeletron.task13.controllers;

import com.roboskeletron.task13.Entities.Player;
import com.roboskeletron.task13.Logger;
import com.roboskeletron.task13.Render;
import com.roboskeletron.task13.base.Transform;
import com.roboskeletron.task13.interfaces.IInput;
import com.roboskeletron.task13.primitives.Point2D;
import com.roboskeletron.task13.primitives.Sprite;
import com.roboskeletron.task13.primitives.Vector2D;
import javafx.animation.AnimationTimer;

import java.io.IOException;

public class GameController extends AnimationTimer {
    private final Render render;
    private double width;
    private double height;
    private double floorY;
    private NetworkController networkController;
    private IInput input;
    Player player;
    Logger logger = new Logger("damage_logger.txt");

    public GameController(Render render, double width, double height, NetworkController networkController, IInput input){
        this.render = render;
        var entitiesList = render.getEntitiesList();
        this.width = width;
        this.height = height;
        this.networkController = networkController;
        this.input = input;

        if (networkController.isSever){
            player = new Player(new Point2D(500, 514.25), new Sprite(0), "character0");
        }
        else{
            player = new Player(new Point2D(1219, 514.25), new Sprite(0), "character1");
        }

        floorY = height - height * 0.42;
        player.setFloor(floorY);
        networkController.getPlayer().setFloor(floorY);
        entitiesList.add(player);
        entitiesList.add(networkController.getPlayer());
    }
    @Override
    public void handle(long now) {
        try {
            if (!player.isAlive() || !networkController.getPlayer().isAlive()){
                this.stop();
                return;
            }
            player.update(input);
            networkController.update((KeyController) input);
            normalizePosition(player.getTransform());
            normalizePosition(networkController.getPlayer().getTransform());
            
            if (input.punch())
                enforceDamage(player, networkController.getPlayer());

            if (networkController.punch())
                enforceDamage(networkController.getPlayer(), player);
            render.drawFrame();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void enforceDamage(Player agent, Player target) throws IOException {
        if (agent.getBlockingState())
            return;

        Point2D agentPosition = agent.getTransform().getPosition();
        Point2D targetPosition = target.getTransform().getPosition();

        Vector2D distanceVector = Transform.getDistanceVector(agentPosition, targetPosition);

        double damage = player.punch(distanceVector);

        logger.writeLine(target.getName() + " took damage");
        if (target.getLookingDirection() != agent.getLookingDirection())
            target.takeDamage(damage * 2);
        else if (target.getBlockingState())
            target.takeDamage(damage / 2);
        else
            target.takeDamage(damage);
    }

    private void normalizePosition(Transform transform){
        double x = transform.getPosition().x();
        double y = transform.getPosition().y();

        if (x < 0)
            x = 0;
        if (x > width - width * 0.054)
            x = width - width * 0.054;

        if (y < 0)
            y = 0;
        if (y > floorY)
            y = floorY;

        transform.setPosition(x, y);
    }
}
