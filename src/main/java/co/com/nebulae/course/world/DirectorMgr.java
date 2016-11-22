/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.nebulae.course.world;

import co.com.nebulae.course.world.labs.LaunchableBall;
import java.util.ArrayList;
import java.util.List;
import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Andres Sanchez - nebulae.com.co
 */
public class DirectorMgr {

    //<editor-fold defaultstate="collapsed" desc="SINGLETON">
    private DirectorMgr() {
    }

    public static DirectorMgr getInstance() {
        return DirectorMgrHolder.INSTANCE;
    }

    private static class DirectorMgrHolder {

        private static final DirectorMgr INSTANCE = new DirectorMgr();
    }

//</editor-fold>
    /**
     * dymanics objects
     */
    List<WorldShape> dynamicObjects = new ArrayList<>();
    /**
     * statics objects
     */
    List<WorldShape> staticObjects = new ArrayList<>();
    /**
     * partial Dynamics objects
     */
    List<WorldShape> partialDynamicObjects = new ArrayList<>();

    public void handleGameLoop(Long time) {
        partialDynamicObjects.stream().forEach((worldShape) -> {
            worldShape.redraw(time);
        });
    }

    public void inputEventListener(KeyEvent event) {
        
        switch (event.getCode()) {

            case SPACE:
                
                if (event.getEventType() == event.KEY_PRESSED) {
                    LaunchableBall launchableBall = new LaunchableBall();
                    launchableBall.buildElements(SceneMgr.getInstance().getWorld());
                    partialDynamicObjects.add(launchableBall);
                    break;
                }
        
            default:
                partialDynamicObjects.get(partialDynamicObjects.size() - 1).handleInput(event);
                break;

        }
    }

}
