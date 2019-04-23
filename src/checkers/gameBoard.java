/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

/**
 *
 * @author wywang
 */
public class gameBoard {
    
    tile[][] tileBoard;
    
    public gameBoard()
    {
        tileBoard = new tile[8][8];
        for(int r = 0; r < tileBoard.length; r++)
        {
            for(int c = 0; c < tileBoard.length; c++)
            {
                tileBoard[r][c] = new tile("","none");
                if(r%2 == 0)
                {
                    if(c%2 == 1)
                    {
                        tileBoard[r][c].setColor("black");
                    }
                    else
                    {
                        tileBoard[r][c].setColor("white");
                    }
                }
                else
                {
                    if(c%2 == 1)
                    {
                        tileBoard[r][c].setColor("white");
                    }
                    else
                    {
                        tileBoard[r][c].setColor("black");
                    }
                }
                if(tileBoard[r][c].getColor().equals("black") && (r<3))
                {
                    tileBoard[r][c].setPiece("red");
                }
                else if(tileBoard[r][c].getColor().equals("black") && (r>4))
                {
                    tileBoard[r][c].setPiece("yellow");
                }
            }
        }
    }
    
    public boolean hasUnit(int row, int column)
    {
        if(tileBoard[row][column].getCurrentPiece().equals("none"))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public void changeTileUnit(int row, int column, String unit)
    {
        
    }
    
    public tile getTile(int row, int col)
    {
        try
        {
        return tileBoard[row][col];
        }
        catch (Exception e)
        {
            return new tile("","");
        }
    }
    
    
}
