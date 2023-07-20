package tetris;

import java.awt.*;

public interface Globals {
    //Text
    String GAME_TITLE = "Tetris";
    String MENU_TITLE = "Hauptmenü";
    String WELCOME_TEXT = "Tetris Game";
    String STARTGAME_TEXT = "Start";
    String LEADERBOARD_TEXT = "Leaderboard";
    String ENDGAME_TEXT = "End";
    String BACKTOMENU_TEXT = "Menu";

    //Fonts
    Font HEADING_FONT = new Font("Arial", Font.BOLD, 60);
    Font BUTTON_FONT = new Font("Arial", Font.BOLD, 17);
    Font TEXT_FONT =  new Font("Arial", Font.BOLD, 20);

    //Size
    int GAME_SIZE_X = 800;
    int GAME_SIZE_Y = 800;
    Dimension BUTTON_SIZE = new Dimension(120, 50);

    //Colors
    Color BORDER_COLOR = new Color(0, 0, 0);
    Color LSHAPE_COLOR = new Color(217, 49, 15);
    Color ISHAPE_COLOR = new Color(51, 77, 245);
    Color JSHAPE_COLOR = new Color(214, 198, 24);
    Color OSHAPE_COLOR = new Color(93, 196, 33);
    Color TSHAPE_COLOR = new Color(59, 227, 213);
    Color ZSHAPE_COLOR = new Color(230, 87, 201);
    Color SSHAPE_COLOR = new Color(242, 121, 46);
}
