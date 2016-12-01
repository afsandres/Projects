/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.nebulae.course.world;

import javafx.event.Event;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Andres Sanchez - nebulae.com.co
 */
public interface WorldShape {

    public void redraw(Long time); 

    public void handleInput(KeyEvent event);
     
    public void handleControlInput(Event event);

    public void isVisible();

    public void collision();
}
