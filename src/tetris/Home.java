package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements Globals, ActionListener {
    protected JLabel welcomeText;
    protected JPanel buttonsArea;
    protected JPanel welcomeArea;
    protected JButton startGame;
    protected JButton leaderBoard;
    protected JButton endGame;

    public Home() {
        super(MENU_TITLE);
        this.setLayout(new GridLayout(2,1));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(GAME_SIZE_X,GAME_SIZE_Y);
        settingsForTextAndDisplay();
        this.add(welcomeArea);
        this.add(buttonsArea);
        startGame.addActionListener(this);
        leaderBoard.addActionListener(this);
        endGame.addActionListener(this);
        this.setVisible(true);
    }

    private void settingsForTextAndDisplay(){
        welcomeText = new JLabel(WELCOME_TEXT, SwingConstants.CENTER);
        buttonsArea = new JPanel();
        welcomeArea = new JPanel();
        startGame = new JButton(STARTGAME_TEXT);
        leaderBoard = new JButton(LEADERBOARD_TEXT);
        endGame = new JButton(ENDGAME_TEXT);
        welcomeText.setFont(HEADING_FONT);
        buttonsArea.setLayout(new FlowLayout());
        buttonsArea.add(startGame);
        startGame.setFont(BUTTON_FONT);
        buttonsArea.add(leaderBoard);
        leaderBoard.setFont(BUTTON_FONT);
        buttonsArea.add(endGame);
        endGame.setFont(BUTTON_FONT);
        startGame.setPreferredSize(BUTTON_SIZE);
        leaderBoard.setPreferredSize(BUTTON_SIZE);
        endGame.setPreferredSize(BUTTON_SIZE);
        welcomeArea.setLayout(new BorderLayout());
        welcomeArea.add(welcomeText, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startGame){
            this.setVisible(false);
            Tetris.startGame();
        }
        if(e.getSource() == leaderBoard){
            this.setVisible(false);
            Tetris.showLeaderBoard();
        }
        if(e.getSource() == endGame){
            System.exit(0);
        }

    }

}
