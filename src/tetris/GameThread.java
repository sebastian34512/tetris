package tetris;

public class GameThread extends Thread {
    //run code in a seperate thread
    private Spielfeld area;
    protected int score = 0;
    protected int currentLevel = 1;
    protected int scorePerLevel = 3;
    private GameForm gf;
    private TetrisBlock lastBlock;

    private int pause = 1000;
    private int lowerPause = 100;

    public GameThread(Spielfeld area, GameForm gf) {
        this.area = area;
        this.gf = gf;
        gf.updateTheScore(score);
        gf.updateLevel(currentLevel);
    }

    //when a thread is started, the run method is called
    //the code which should be executed must be added to the run method
    @Override
    public void run() {
        //infinite loop
       while (true) {
           area.generateBlocks();
           while(area.moveBlockDown()){
               try {
                   Thread.sleep(pause);
               } catch (InterruptedException e) {
                   return;
               }
           }
           if(area.isGameOver()){
               Tetris.gameEnd(score);
               break;
           }
           area.moveBlockToBackground();
           this.score += area.clearFullLines();
           gf.updateTheScore(score);

           int whichLevel = this.score / this.scorePerLevel + 1;
           if (whichLevel > this.currentLevel){
               this.currentLevel = whichLevel;
               gf.updateLevel(this.currentLevel);
               this.pause -= this.lowerPause;
           }
       }
    }
}

