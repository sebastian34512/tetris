package blocks;

public class SShape extends tetris.TetrisBlock implements tetris.Globals {
    public SShape() {
        super( new int[][]{{0,1,1},{1,1,0}}, SSHAPE_COLOR);
    }
}
