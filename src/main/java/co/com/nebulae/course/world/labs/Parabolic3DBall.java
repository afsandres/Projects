/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.nebulae.course.world.labs;

import co.com.nebulae.course.entity.Xform;
import co.com.nebulae.course.world.Log;
import co.com.nebulae.course.world.WorldShape;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

/**
 *
 * @author Andres Sanchez - nebulae.com.co
 */
public class Parabolic3DBall implements WorldShape{

    //dependencies
    private Xform world;
    private Xform ballXform;
    private Double gravity = -9.8 / 1000;
    private Double speedMagnitud = 2d;
    private Double speedX = 0d;
    private Double speedY = 0d;
    private Double speedZ = 0d;
    private Double angleX = 60d;
    private Double angleY = 20d;
    private Double angleZ = 70d;
    private Double friction = 0.7d;
    private boolean go = false;
    private boolean floor = false;
    
    double traveledDistanceY = 0;
    private Sphere oxygenSphere;

    final Xform elementsGroup = new Xform();

    public void buildElements(Xform world) {
        Log.print("build ball ... ");
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial whiteMaterial = new PhongMaterial();
        whiteMaterial.setDiffuseColor(Color.WHITE);
        whiteMaterial.setSpecularColor(Color.LIGHTBLUE);

        final PhongMaterial greyMaterial = new PhongMaterial();
        greyMaterial.setDiffuseColor(Color.DARKGREY);
        greyMaterial.setSpecularColor(Color.GREY);

        final PhongMaterial goldenMaterial = new PhongMaterial();
        goldenMaterial.setDiffuseColor(Color.DARKGOLDENROD);
        goldenMaterial.setSpecularColor(Color.GOLDENROD);

        ballXform = new Xform();
        ballXform.t.setY(150d);
        ballXform.t.setX(-500d);
        ballXform.t.setZ(500d);
        oxygenSphere = new Sphere(40.0);
        oxygenSphere.setMaterial(goldenMaterial);
        ballXform.getChildren().add(oxygenSphere);

        elementsGroup.getChildren().add(ballXform);
        world.getChildren().addAll(elementsGroup);
    }
    
    

    public Xform getElementsGroup() {
        return elementsGroup;
    }

    public Xform getOxygenXform() {
        return ballXform;
    }

    public void go() {
        ballXform.t.setX(-500);
        ballXform.t.setZ(120);
        ballXform.t.setY(250);

        speedY = speedMagnitud * Math.cos(Math.toRadians(angleX));
        speedX = speedMagnitud * Math.cos(Math.toRadians(angleY));
        speedZ = speedMagnitud * Math.cos(Math.toRadians(angleZ));
        speedX = speedX * (1 - friction);
        speedY = speedY * (1 - friction);

        go = true;
    }

    public void move(Long time) {
        if (!go) {
            return;
        }

        if (ballXform.t.getX() > 500 || ballXform.t.getX() < -500) {
            speedX = speedX * (-1) * friction;
        }

        if (ballXform.t.getZ() > 500 || ballXform.t.getZ() < -500) {
            speedZ = speedZ * (-1) * friction;
        }

        if (ballXform.t.getY() < 0 && speedY < 0) {
            speedY = speedY * (-1) * friction;
      
            if (Math.abs(speedY) < 0.1) {
                floor = true;
            }
        }

        if(!floor){
        Double y = ballXform.t.getY() + (speedY * time) + (0.5 * (gravity * (time ^ 2)));
        speedY = speedY + (gravity * time);
        ballXform.t.setY(y);
        }

        double traveledDistanceX = (speedX * time);
        double x = ballXform.t.getX() + traveledDistanceX;
        ballXform.t.setX(x);

        double traveledDistanceZ = (speedZ * time);
        double z = ballXform.t.getZ() + traveledDistanceZ;
        ballXform.t.setZ(z);

    }

    public Sphere getOxygenSphere() {
        return oxygenSphere;
    }
    
    @Override
    public void handleInput(KeyCode k) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void redraw(Long time) {
        move(time);
    }

}
