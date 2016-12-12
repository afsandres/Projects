/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.nebulae.course.world.labs;

import co.com.nebulae.course.entity.Xform;
import co.com.nebulae.course.world.WorldShape;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;

/**
 *
 * @author Andres Sanchez - nebulae.com.co
 */
public class Tank implements WorldShape {

    //dependencies
    private Xform world;
    private Xform tankXform;
    private Xform canonXform;
    private Box skeleton;
    private Cylinder canonCylinder;
    private Double angleTankX = 0d;
    private Double angleCannonX;
    private Double angleCannonY;
    private Double angleCannonZ;
    private Double speedX = 0d;
    private Double speedZ = 0d;
    private Double directionX = 0d;
    private Double directionZ = 0d;

    final Xform elementsGroup = new Xform();

    public void buildElements(Xform world) {

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial oliveGreenMaterial = new PhongMaterial();
        oliveGreenMaterial.setDiffuseColor(Color.DARKOLIVEGREEN);
        oliveGreenMaterial.setSpecularColor(Color.OLIVE);

        tankXform = new Xform();
        skeleton = new Box(50d, 20d, 150d);
        skeleton.setTranslateX(0d);
        skeleton.setTranslateY(25d);
        skeleton.setTranslateZ(0d);
        skeleton.setMaterial(greenMaterial);
        tankXform.getChildren().add(skeleton);

        canonXform = new Xform();
        canonCylinder = new Cylinder(20d, 50d);
        canonCylinder.setTranslateX(0d);
        canonCylinder.setTranslateY(50d);
        canonCylinder.setTranslateZ(0d);
        canonCylinder.setMaterial(oliveGreenMaterial);
        canonXform.getChildren().add(canonCylinder);
        tankXform.getChildren().add(canonXform);
        elementsGroup.getChildren().add(tankXform);
        world.getChildren().addAll(elementsGroup);

    }

    public void move(Long time) {
        //MRU X
        double x = tankXform.t.getX() + directionX * speedX * time;
        if (x < -1000) {
            x = -1000;
        } else if (x > 1000) {
            x = 1000;
        }
        tankXform.t.setX(x);
        tankXform.rx.setAngle(angleTankX);
        double z = tankXform.t.getZ() + directionZ * speedZ * time;
        if (z < -1000) {
            z = -1000;
        } else if (z > 1000) {
            z = 1000;
        }
        System.out.println("Mover z " + x);
        System.err.println("posición z n" + tankXform.t.getZ());
        tankXform.t.setZ(z);
        System.out.println("Mover z n " + x);
        System.err.println("posición z n" + tankXform.t.getZ());
    }

    @Override
    public void redraw(Long time) {
        move(time);
    }

    @Override
    public void handleInput(KeyEvent event) {
        if (event.getEventType() == event.KEY_PRESSED) {
            switch (event.getCode()) {
                case W:
                    System.out.println("W");
                    directionZ = tankXform.getTranslateZ() + 1d;
                    speedZ = 0.1d;
                    break;
                case A:
                    System.out.println("A");
                    angleTankX = tankXform.rx.getAngle() + 1d;
                    directionX = tankXform.getTranslateX() + 1d;
                    speedX = 0.1d;
                    break;
                case D:
                    System.out.println("D");
                    angleTankX = tankXform.rx.getAngle() - 1d;
                    directionX = tankXform.getTranslateX() - 1d;
                    speedX = 0.1d;
                    break;
                case S:
                    System.out.println("S");
                    directionZ = tankXform.getTranslateZ() - 1d;
                    speedZ = 0.1d;
                    break;
                case UP:
                    System.out.println("UP");
                    break;
                case DOWN:
                    System.out.println("DOWN");
                    break;
                case RIGHT:
                    System.out.println("RIGHT");
                    break;
                case LEFT:
                    System.out.println("LEFT");
                    break;
                default:
                    break;
            }
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
    public void handleControlInput(Event event
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
