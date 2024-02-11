package ScreenManager;

import javax.swing.*;
import java.awt.*;

public class SimpleScreenManager {

    private GraphicsDevice device;

    public SimpleScreenManager() {
        GraphicsEnvironment environment =
                GraphicsEnvironment.getLocalGraphicsEnvironment();

        device = environment.getDefaultScreenDevice();
    }

    public void setFullScreen(DisplayMode displayMode, JFrame window) {
        window.setUndecorated(false);
        window.setResizable(false);

        device.setFullScreenWindow(window);

        if(displayMode != null && device.isDisplayChangeSupported()) {
            try{
                device.setDisplayMode(displayMode);
            } catch(IllegalArgumentException exception) {

            }
        }
    }

    public Window getFullScreenWindow() {
        return device.getFullScreenWindow();
    }

    public void restoreScreen() {
        Window window = device.getFullScreenWindow();

        if(window != null) {
            window.dispose();
        }

        device.setFullScreenWindow(null);
    }

}
