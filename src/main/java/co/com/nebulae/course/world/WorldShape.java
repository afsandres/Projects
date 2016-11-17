/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.nebulae.course.world;

import javafx.scene.input.KeyCode;

/**
 *
 * @author Andres Sanchez - nebulae.com.co
 */
public interface WorldShape {
   
    public void redraw(Long time);
    
    public void handleInput(E k);
    
    
}
