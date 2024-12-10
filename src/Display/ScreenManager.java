package Display;

import javax.swing.*;
import java.awt.*;

public class ScreenManager {

    private GraphicsDevice device;

    public ScreenManager() {
        GraphicsEnvironment environment =
                GraphicsEnvironment.getLocalGraphicsEnvironment();

        device = environment.getDefaultScreenDevice();
    }

    public void setFullScreen(DisplayMode displayMode, JFrame window) {
        window.setUndecorated(true);
        window.setResizable(false);


            try{
                device.setFullScreenWindow(window);
                device.setDisplayMode(displayMode);
            } catch(IllegalArgumentException exception) {
                restoreScreen();
                setWindowedScreen(window);
                exception.printStackTrace();
            }

    }

    public void setWindowedScreen(JFrame window) {

        window.setUndecorated(false);
        window.setResizable(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setSize(screenSize);

        window.setVisible(true);
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
