package tetris;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Spielfeld extends JPanel implements Globals, Components{

    private int anzahlRowsInGrid;
    private int anzahlColumnsInGrid;
    private int sizeOfACell;
    private TetrisBlock tetrisBlock;
    private Color[][] background;
    private int currentNumber;

    public Spielfeld(int columns) {
        this.setLayout(null);
        this.setBounds(204, 150, 392,504 );
        this.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 2));
        anzahlColumnsInGrid = columns;
        sizeOfACell = this.getBounds().width / anzahlColumnsInGrid;
        anzahlRowsInGrid = this.getBounds().height / sizeOfACell;
        background = new Color[anzahlRowsInGrid][anzahlColumnsInGrid];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int r = 0; r < anzahlRowsInGrid; r++){
            for (int c = 0; c < anzahlColumnsInGrid; c++){
                g.drawRect(c* sizeOfACell,r* sizeOfACell, sizeOfACell, sizeOfACell);
            }
        }

        drawBackground(g);
        drawBlock(g);
    }

    private void drawBlock(Graphics g){
        int height = tetrisBlock.getHeight();
        int width = tetrisBlock.getWidth();
        Color colorOfShape = tetrisBlock.getColor();
        int [][] figur = tetrisBlock.getFigur();

        for(int rows = 0; rows < height; rows++){
            for (int cols = 0; cols < width; cols++){
                if(figur[rows][cols] == 1){
                    int x = (tetrisBlock.getxPos() + cols)* sizeOfACell;
                    int y = (tetrisBlock.getyPos() + rows)* sizeOfACell;
                    drawGridSquare(g, colorOfShape, x, y);
                }
            }
        }
    }

    private void drawBackground(Graphics g){
        Color bgColor;
        for(int  rows = 0; rows < anzahlRowsInGrid; rows++){
            for (int columns = 0; columns < anzahlColumnsInGrid; columns++){
                bgColor = background[rows][columns];
                if(bgColor != null){
                    int x = columns* sizeOfACell;
                    int y = rows* sizeOfACell;
                    drawGridSquare(g, bgColor, x, y);
                }
            }
        }
    }

    //function for generating blocks
    public void generateBlocks(){
        Random number = new Random();
        int randomBlock = number.nextInt(differentBlocks.length);
        while(randomBlock == this.currentNumber){
            randomBlock = number.nextInt(differentBlocks.length);
        }
        tetrisBlock = differentBlocks[randomBlock];
        tetrisBlock.spawn(anzahlColumnsInGrid);
        this.currentNumber = randomBlock;
    }

    public boolean isGameOver(){
        if(tetrisBlock.getyPos() < 0){
            tetrisBlock = null;
            return true;
        }
        return false;
    }
    //for the moveBlockDown method we need something to call it in regular time intervals --> Thread
    public boolean moveBlockDown(){
        if(!checkIfMoveDown()){
            return false;
        }
            tetrisBlock.moveDown();
            clearFullLines();
            //repaint = calling the paint component method again --> you paint it at the new position
            repaint();
            return true;
    }
    //function to check every element of the current shape and move it to the "background"
    public void moveBlockToBackground(){
        int [][]shape = tetrisBlock.getFigur();
        int h = tetrisBlock.getHeight();
        int w = tetrisBlock.getWidth();
        int xPos = tetrisBlock.getxPos();
        int yPos = tetrisBlock.getyPos();
        Color color = tetrisBlock.getColor();
        for(int r = 0; r < h; r++){
            for (int c = 0; c < w; c++){
                //if its a colored cell
                if(shape[r][c] == 1){
                    background[r+yPos][c+xPos] = color;
                }
            }
        }
    }

    public boolean checkIfMoveDown(){
        if(tetrisBlock.getBottomEdge() == anzahlRowsInGrid){
            return false;
        }
        int[][] figur = tetrisBlock.getFigur();
        int widthOfFigur = tetrisBlock.getWidth();
        int heightOfFigur = tetrisBlock.getHeight();

        for(int column = 0; column < widthOfFigur; column++){
            for(int row = heightOfFigur-1; row >= 0; row--){
                if(figur[row][column] != 0){
                  int x = column + tetrisBlock.getxPos();
                  int y = row + tetrisBlock.getyPos() + 1;
                  if(y < 0){
                      //break statement only terminates the loop
                      break;
                  }
                  if(background[y][x] != null){
                      return false;
                  }
                    break;
                }
            }
        }

        return true;
    }

    public boolean checkOverlap(){
        int[][] figur = tetrisBlock.getFigur();
        int widthOfFigur = tetrisBlock.getWidth();
        int heightOfFigur = tetrisBlock.getHeight();

        for(int column = 0; column < widthOfFigur; column++){
            for(int row = heightOfFigur-1; row >= 0; row--){
                if(figur[row][column] != 0){
                    int x = column + tetrisBlock.getxPos();
                    int y = row + tetrisBlock.getyPos();
                    if(y < 0) break;
                    if(background[y][x] != null) return true;
                    break;
                }
            }
        }
        return false;
    }

    public boolean checkIfOutLeft(){
        if(tetrisBlock.getLeft() == 0){
            return false;
        }
        int[][] figur = tetrisBlock.getFigur();
        int widthOfFigur = tetrisBlock.getWidth();
        int heightOfFigur = tetrisBlock.getHeight();

        for(int row = 0; row < heightOfFigur; row++){
            for(int column = 0; column < widthOfFigur; column++){
                if(figur[row][column] != 0){
                    int x = column + tetrisBlock.getxPos() -1 ;
                    int y = row + tetrisBlock.getyPos();
                    if(y < 0){
                        //break statement only terminates the loop
                        break;
                    }
                    if(background[y][x] != null){
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    public boolean checkIfOutRight(){
        if(tetrisBlock.getRight() >= anzahlColumnsInGrid ){
            return false;
        }
        int[][] figur = tetrisBlock.getFigur();
        int widthOfFigur = tetrisBlock.getWidth();
        int heightOfFigur = tetrisBlock.getHeight();

        for(int row = 0; row < heightOfFigur; row++) {
            for (int column = widthOfFigur - 1; column >= 0; column--) {
                if (figur[row][column] != 0) {
                    int x = column + tetrisBlock.getxPos() + 1;
                    int y = row + tetrisBlock.getyPos();
                    if (y < 0) {
                        //break statement only terminates the loop
                        break;
                    }
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    public int clearFullLines(){
        boolean lineFullWithBlocks;
        int linesCleared = 0;
        for(int row = anzahlRowsInGrid-1; row>=0; row--){
            lineFullWithBlocks = true;
            for(int column = 0; column < anzahlColumnsInGrid; column++){
                if(background[row][column] == null){
                    lineFullWithBlocks = false;
                    break;
                }
            }
            if(lineFullWithBlocks){
                linesCleared++;
                clearTheLine(row);
                shiftDown(row);
                clearTheLine(0);
                row++;

                repaint();
            }
        }
        return linesCleared;
    }

    private void clearTheLine(int line){
        for(int blocks = 0; blocks<anzahlColumnsInGrid;blocks++){
            background[line][blocks] = null;
        }
    }

    private void shiftDown(int line){
        for(int row = line; row > 0; row--){
            for(int column = 0; column  < anzahlColumnsInGrid; column++){
                background[row][column] = background[row-1][column];
            }
        }
    }

    private void drawGridSquare(Graphics g, Color c, int x, int y){
        g.setColor(c);
        g.fillRect(x, y, sizeOfACell, sizeOfACell);
        g.setColor(Color.black);
        g.drawRect(x, y, sizeOfACell, sizeOfACell);
    }

    public void moveTetrisBlockRight(){
        if(tetrisBlock == null) return;
        if(checkIfOutRight()) {
            tetrisBlock.moveRight();
            repaint();
        }
    }

    public void moveTetrisBlockLeft(){
        if(tetrisBlock == null) return;
        if(checkIfOutLeft()) {
            tetrisBlock.moveLeft();
            repaint();
        }
    }

    public void rotateTetrisBlock(){
        if(tetrisBlock == null) return;
        tetrisBlock.rotate();
        if(tetrisBlock.getLeft() < 0){
            tetrisBlock.setX(0);
        };
        if(tetrisBlock.getRight() >= anzahlColumnsInGrid){
            tetrisBlock.setX(anzahlColumnsInGrid - tetrisBlock.getWidth());
        };
        if(tetrisBlock.getBottomEdge() >= anzahlRowsInGrid){
            tetrisBlock.setY(anzahlRowsInGrid- tetrisBlock.getHeight());
        }
        if(checkOverlap()){
            tetrisBlock.reverseRotate();
        }
        repaint();
    }

    public void moveTetrisBlockDown(){
        if(tetrisBlock == null) return;
        while(checkIfMoveDown()){
            tetrisBlock.moveDown();
        }
        repaint();
    }

    public void initBackgroundArray(){
        background = new Color[anzahlRowsInGrid][anzahlColumnsInGrid];
    }

}

