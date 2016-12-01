/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.nebulae.course.world.labs;

import co.com.nebulae.course.entity.Xform;
import co.com.nebulae.course.world.Log;
import co.com.nebulae.course.world.WorldShape;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;

/**
 *
 * @author Andres Sanchez - nebulae.com.co
 */
public class LaunchableBall implements WorldShape {

    //dependencies
    private Xform world;
    private Xform ballXform;
    private Xform directionXform;
    private Double gravity = -9.8 / 1000;
    private Double speedMagnitud = 0d;
    private Double speedX = 0d;
    private Double speedY = 0d;
    private Double speedZ = 0d;
    private Double angleX = 0d;
    private Double angleY = 0d;
    private Double angleZ = 0d;
    private Double friction = 0.7d;
    private boolean go = false;
    private boolean floor = false;

    double traveledDistanceY = 0;
    private Sphere sphere;
    private Cylinder cylinder;

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
        sphere = new Sphere(30.0);
        sphere.setMaterial(goldenMaterial);
        ballXform.getChildren().add(sphere);

        directionXform = new Xform();
        directionXform.t.setY(100);
        cylinder = new Cylinder(5d, 200);
        cylinder.setMaterial(greyMaterial);

        cylinder.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                System.out.println("Cambio de " + (Double) oldValue + " a " + (Double) newValue);
                speedMagnitud = (Double) newValue;
            }
        });

        directionXform.getChildren().add(cylinder);
        elementsGroup.getChildren().add(ballXform);
        elementsGroup.getChildren().add(directionXform);
        world.getChildren().addAll(elementsGroup);

    }

    public Xform getElementsGroup() {
        return elementsGroup;
    }

    public Xform getOxygenXform() {
        return ballXform;
    }

    public void go() {

        speedY = speedMagnitud * Math.cos(Math.toRadians(directionXform.rx.getAngle()));
        Double forceHorizontal = speedMagnitud * Math.sin(Math.toRadians(directionXform.rx.getAngle()));

        speedX = forceHorizontal * Math.cos(Math.toRadians(90 - directionXform.ry.getAngle()));
        speedZ = forceHorizontal * Math.cos(Math.toRadians(directionXform.ry.getAngle()));
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

        if (!floor) {
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
        return sphere;
    }

    @Override
    public void redraw(Long time) {
        move(time);
    }

    @Override
    public void handleInput(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                System.out.println("");
                break;
            case A:
                System.out.println("");
                break;
            case D:
                System.out.println("");
                break;
            case S:
                System.out.println("");
                break;
            case UP:
                angleX += 1d;
                if (angleX > 360) {
                    angleX = 360d;
                }
                if (angleX < 0) {
                    angleX = 0d;
                }
                directionXform.rx.setAngle(angleX);
                break;
            case DOWN:
                angleX -= 1d;
                if (angleX > 360) {
                    angleX = 360d;
                }
                if (angleX < 0) {
                    angleX = 0d;
                }
                directionXform.rx.setAngle(angleX);
                break;
            case RIGHT:
                angleY += 1d;
                if (angleY > 360) {
                    angleY = 360d;
                }
                if (angleY < 0) {
                    angleY = 0d;
                }
                directionXform.ry.setAngle(angleY);
                break;
            case LEFT:
                angleY -= 1d;
                if (angleY > 360) {
                    angleY = 360d;
                }
                if (angleY < 0) {
                    angleY = 0d;
                }
                directionXform.ry.setAngle(angleY);
                break;
            case ENTER:
                if (event.getEventType() == event.KEY_PRESSED) {
                    double heightIncrease = cylinder.getHeight() + 1;
                    if (heightIncrease <= 10) {
                        heightIncrease = 0;
                    }
                    cylinder.setHeight(heightIncrease);
                    cylinder.setTranslateY(cylinder.getHeight() / 2);
                }

                if (event.getEventType() == event.KEY_RELEASED) {
                    elementsGroup.getChildren().remove(directionXform);
                    go();
                }

            default:
                break;
        }

    }

    @Override
    public void isVisible() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void collision() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handleControlInput(Event event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
