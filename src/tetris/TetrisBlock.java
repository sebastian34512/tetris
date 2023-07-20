package tetris;
import java.awt.*;
import java.util.Random;

// a tetris block is a grid with colered and non-colored cells
// color or not --> int (1 is colored, 0 is not colored) --> array
public class TetrisBlock {
    private int [][] figur;
    private Color color;
    private int xPos, yPos;
    private int [][][] figuren;
    private int currentRotationActive;

    //the constructor initializes the shape & color of the tetris block
    public TetrisBlock(int[][] shape, Color color) {
        this.figur = shape;
        this.color = color;
        initShapes();
    }
    //init shapes for rotation
    private void initShapes(){
        figuren = new int[4][][];
        //loop for each rotation
        for(int i = 0; i<4; i++){
            int rows = figur[0].length;
            int columns = figur.length;
            figuren[i] = new int[rows][columns];
            for( int y = 0; y <rows; y++){
                for(int x = 0; x<columns;x++){
                    figuren[i][y][x] = figur[columns-x-1][y];
                }
            }
            figur = figuren[i];
        }

    }
    //return the shape of the block
    public int[][] getFigur() {
        return figur;
    }
    //return the color of the block
    public Color getColor() {
        return color;
    }
    //height = number of rows ( tipp: int[rows][columns])
    public int getHeight(){
        return figur.length;
    }
    // width: the number of columns in a row (tipp: [0][columns])
    public int getWidth(){
        return figur[0].length;
    }
    //get width & height
    public int getxPos() {
        return xPos;
    }
    public int getyPos() {
        return yPos;
    }
    //moving the block
    public void moveDown(){
        yPos++;
    }
    public void moveRight(){
        xPos++;
    }
    public void moveLeft(){
        xPos--;
    }
    public void rotate(){
        currentRotationActive++;
        if(currentRotationActive > 3) currentRotationActive = 0;
        figur = figuren[currentRotationActive];
    }
    public void reverseRotate(){
        currentRotationActive--;
        if(currentRotationActive < 0) currentRotationActive = 3;
        figur = figuren[currentRotationActive];
    }
    //spawn the element above the area
    public void spawn(int gridWidth){
        Random number = new Random();
        //bound - from 0 to the bound
        xPos = number.nextInt(gridWidth-getWidth());
        currentRotationActive = number.nextInt(4);
        figur = figuren[currentRotationActive];
        yPos=0-getHeight();
    }
    public int getBottomEdge(){
       return yPos + getHeight();
    }
    public int getLeft(){
       return xPos;
    }
    public int getRight(){
        return xPos + getWidth();
    }
    //set position
    public void setX(int newX){
        this.xPos = newX;
    }
    public void setY(int newY){
        this.yPos = newY;
    }
}
