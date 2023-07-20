package blocks;

public class IShape extends tetris.TetrisBlock implements tetris.Globals {

    public IShape() {
        super( new int[][]{{1,1,1,1}}, ISHAPE_COLOR);
    }
    @Override
    public void rotate(){
       super.rotate();
       if(this.getWidth() == 1){
           this.setX(this.getxPos()+1);
           this.setY(this.getyPos()-1);
       }else{
           this.setX(this.getxPos()-1);
           this.setY(this.getyPos()+1);
       }
    }

}
