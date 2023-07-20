package tetris;

import javax.swing.*;

public class Tetris implements Components {

    public static void startGame() {
        gameForm.setVisible(true);
        gameForm.startGame();
    }

    public static void showLeaderBoard() {
        leaderboardForm.setVisible(true);
    }

    public static void backToMenu(){
        homeForm.setVisible(true);
    }

    public static void gameEnd(int score){
        String user = JOptionPane.showInputDialog("Game Over! Enter Your Name");
        leaderboardForm.addPlayer(user, score);
        gameForm.setVisible(false);
    }

    public static void main(String[] args) {
        homeForm.setVisible(true);
    }
}

