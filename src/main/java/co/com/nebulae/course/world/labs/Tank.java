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

    final Xform elementsGroup = new Xform();

    public void buildElements(Xform world) {

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial oliveGreenMaterial = new PhongMaterial();
        oliveGreenMaterial.setDiffuseColor(Color.DARKOLIVEGREEN);
        oliveGreenMaterial.setSpecularColor(Color.OLIVE);

        tankXform = new Xform();
        skeleton = new Box(100d, 50d, 50d);
        skeleton.setTranslateX(50d);
        skeleton.setTranslateY(0d);
        skeleton.setTranslateZ(360d);
        skeleton.setMaterial(greenMaterial);
        tankXform.getChildren().add(skeleton);

        canonXform = new Xform();
        canonCylinder = new Cylinder(20d, 100d);
        canonCylinder.setTranslateX(50d);
        canonCylinder.setTranslateY(5d);
        canonCylinder.setTranslateZ(360d);
        canonXform.getChildren().add(canonCylinder);

        elementsGroup.getChildren().add(tankXform);
        elementsGroup.getChildren().add(canonXform);
        world.getChildren().addAll(elementsGroup);

    }

    public void move(Long time) {

    }


    @Override
    public void redraw(Long time) {
        move(time);
    }

    @Override
    public void handleInput(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                System.out.println("W");
                break;
            case A:
                System.out.println("A");
                break;
            case D:
                System.out.println("D");
                break;
            case S:
                System.out.println("S");
                break;
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
