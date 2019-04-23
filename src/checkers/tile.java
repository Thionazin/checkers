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
public class tile {
    
    
    String tileColor, piece;
    
    public tile(String color, String pieceName)
    {
        tileColor = color;
        piece = pieceName;
        
    }
    
    public void setPiece(String pieceName)
    {
        piece = pieceName;
    }
    public void removePiece()
    {
        piece = "none";
    }
    
    public String getCurrentPiece()
    {
        return piece;
    }
    
    public String getColor()
    {
        return tileColor;
    }
    
    public void setColor(String color)
    {
        tileColor = color;
    }
    
    
}
