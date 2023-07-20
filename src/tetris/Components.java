package tetris;

import blocks.*;

public interface Components {
    Spielfeld tetrisgrid = new Spielfeld(14);
    GameForm gameForm = new GameForm();
    Home homeForm = new Home();
    Leaderboard leaderboardForm = new Leaderboard();
    TetrisBlock[] differentBlocks = new TetrisBlock[]{
            new IShape(), new JShape(), new LShape(), new OShape(), new SShape(), new TShape(), new ZShape()
    };

}
