package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameForm extends JFrame implements Components, Globals, KeyListener, ActionListener {

    private JPanel statusArea;
    private JPanel contentArea;
    private JButton backToHome;
    private JLabel score;
    private JLabel level;
    private GameThread thread;

    public GameForm() {
        super(GAME_TITLE);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(GAME_SIZE_X, GAME_SIZE_Y);
        this.add(tetrisgrid);
        addAllToStatus();
        backToHome.addActionListener(this);
        addKeyListener(this);
    }

    public void addAllToStatus() {
        statusArea = new JPanel();
        contentArea = new JPanel();
        backToHome = new JButton(BACKTOMENU_TEXT);
        score = new JLabel("Score: 0");
        level = new JLabel("Level: 1");
        statusArea.setBounds(0, 0, GAME_SIZE_X, 70);
        statusArea.setLayout(new BorderLayout());
        this.add(statusArea);
        statusArea.add(contentArea, BorderLayout.SOUTH);
        statusArea.add(backToHome, BorderLayout.LINE_START);
        backToHome.setFont(BUTTON_FONT);
        backToHome.setSize(BUTTON_SIZE);
        contentArea.setLayout(new FlowLayout());
        contentArea.add(score);
        contentArea.add(level);
        score.setFont(TEXT_FONT);
        level.setFont(TEXT_FONT);
        backToHome.setFocusable(false);
    }

    //create a gameThread
    public void startGame() {
        tetrisgrid.initBackgroundArray();
        thread = new GameThread(tetrisgrid, this);
        thread.start();
    }

    public void updateTheScore(int score) {
        this.score.setText("Score:" + score);
    }

    public void updateLevel(int level) {
        this.level.setText("Level:" + level);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                tetrisgrid.rotateTetrisBlock();
                break;
            case KeyEvent.VK_DOWN:
                tetrisgrid.moveTetrisBlockDown();
                break;
            case KeyEvent.VK_LEFT:
                tetrisgrid.moveTetrisBlockLeft();
                break;
            case KeyEvent.VK_RIGHT:
                tetrisgrid.moveTetrisBlockRight();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backToHome){
            this.setVisible(false);
            thread.interrupt();
            Tetris.backToMenu();
        }
    }
}



