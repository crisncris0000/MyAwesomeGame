package Game;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        DisplayMode displayMode;

        if(args.length == 3) {
            displayMode = new DisplayMode(
                    Integer.parseInt(args[0]),
                    Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]),
                    DisplayMode.REFRESH_RATE_UNKNOWN
            );
        } else {
            displayMode =
                    new DisplayMode(
                            800,
                            600,
                            16,
                            DisplayMode.REFRESH_RATE_UNKNOWN
                    );
        }
    }
}