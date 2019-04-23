/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;

/**
 *
 * @author Senhe Hao
 */
public class Checkers extends Panel implements ActionListener{

    /**
     * @param args the command line arguments
     */
    private boolean endGame;
    
    private boolean yellowTurn;
    
    private int redPieces, yellowPieces;
    
    mainMenuPanel mainM;
    
    JFrame mainFrame;
    
    victoryPanel vicPan;
    
    customPanel mainPanel;
    
    gameBoard board;
    
    private int originalR, originalC;
    
    private boolean hasPiece;
    
    private String heldPieceColor;
    
    ArrayList<JButton> buts;
    
    ArrayList<JButton> mainMenuButtons;
    
    bottomPanel bottomPanel;
    
    bottomTextButton bot;
    
    JButton exitButton;
    
    mainMenuButton startButton;
    
    
    
    /*
    redundant
    JButton but00,but01,but02,but03,but04,but05,but06,but07;
    JButton but10,but11,but12,but13,but14,but15,but16,but17;
    JButton but20,but21,but22,but23,but24,but25,but26,but27;
    JButton but30,but31,but32,but33,but34,but35,but36,but37;
    JButton but40,but41,but42,but43,but44,but45,but46,but47;
    JButton but50,but51,but52,but53,but54,but55,but56,but57;
    JButton but60,but61,but62,but63,but64,but65,but66,but67;
    JButton but70,but71,but72,but73,but74,but75,but76,but77;
    */
    
    betterButtons[][] buttons;
    
    
    public void initialize()
    {
        
        mainFrame = new JFrame();
        mainM = new mainMenuPanel();
        startButton = new mainMenuButton();
        mainMenuButtons = new ArrayList<JButton>();
        mainMenuButtons.add(startButton);
        mainM.setLayout(new FlowLayout());
        mainFrame.add(mainM);
        startButton.addActionListener(this);
        mainM.add(startButton);
        startButton.setPreferredSize(new Dimension(250, 60));
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        mainFrame.setVisible(true);
    }
    
    
    public void initializeGame()
    {
        endGame = false;
        buttons = new betterButtons[8][8];
        mainPanel = new customPanel();
        mainPanel.setLayout(new GridLayout(8,8));
        board = new gameBoard();
        redPieces = 12;
        yellowPieces = 12;
        originalR = 0; 
        originalC = 0;
        hasPiece = false;
        heldPieceColor = "none";
        yellowTurn = true;
        bot = new bottomTextButton();
        exitButton = new JButton("Exit Game");
        bottomPanel = new bottomPanel();
        bot.setText("Forfeit");
        bot.addActionListener(this);
        bottomPanel.add(bot);
        bottomPanel.add(exitButton);
        bottomPanel.setLayout(new FlowLayout());
        bot.setPreferredSize(new Dimension(300, 40));
        exitButton.setPreferredSize(new Dimension(300, 40));
        buts = new ArrayList<JButton>();
        buts.add(bot);
        buts.add(exitButton);
        
        
        for(int r = 0; r < buttons.length; r++)
        {
            for(int c = 0; c < buttons.length; c++)
            {
                buttons[r][c] = new betterButtons(r,c);
                buttons[r][c].addActionListener(this);
                buttons[r][c].setPreferredSize(new Dimension(10, 10));
            }
        }
        
        for(int i = 0; i < buttons.length; i++)
        {
            for(int c = 0; c < buttons.length; c++)
            {
                mainPanel.add(buttons[i][c]);
                buttons[i][c].repaint();
            }
        }
        
        
        mainPanel.setListeners();
        mainFrame = new JFrame();
        mainFrame.add(mainPanel);
        mainFrame.add(bottomPanel,BorderLayout.SOUTH);
        mainFrame.repaint();
        mainPanel.repaint();
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        bottomPanel.setListeners();
        bottomPanel.repaint();
        mainFrame.setVisible(true);
        mainPanel.setVisible(true);
        bottomPanel.setVisible(true);

        
    }
    
    
    public Checkers()
    {
        initializeGame();
    }
        
    
    public static void main(String[] args) {
        // TODO code application logic here
        Checkers checke = new Checkers();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object sourceOne = null;
        Object sourceTwo = null;
        Object actionSource = e.getSource();
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class bottomPanel extends JPanel implements ActionListener
    {
        public void setListeners()
        {
            for(int i = 0; i < buts.size(); i++)
            {
            buts.get(i).addActionListener(this);
            }
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object actionSource = e.getSource();
            if(actionSource == bot)
            {
                if(endGame)
                {
                    mainFrame.remove(vicPan);
                    mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
                    initializeGame();
                    
                }
                else
                {
                    if(yellowTurn)
                    {
                        yellowPieces = 0;
                        endGame = true;
                mainPanel.repaint();
                yellowTurn = true;
                vicPan = new victoryPanel();
                mainFrame.add(vicPan);
                mainFrame.repaint();
                vicPan.repaint();
                bottomPanel.repaint();
                vicPan.setVisible(true);
                mainPanel.setVisible(false);
                    }
                    else
                    {
                        redPieces = 0;
                        endGame = true;
                mainPanel.repaint();
                yellowTurn = false;
                vicPan = new victoryPanel();
                mainFrame.add(vicPan);
                mainFrame.repaint();
                vicPan.repaint();
                bottomPanel.repaint();
                vicPan.setVisible(true);
                mainPanel.setVisible(false);
                    }
                }
            }
            else if(actionSource == exitButton)
            {
                mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
                System.exit(0);
            }
            
            if(!endGame)
            {
                bot.setText("Forfeit");
                
            }
            else
            {
                bot.setText("New Game");
            }
            
        }
        
    }
    
    
    class betterButtons extends JButton
    {
        int buttonRow;
        int buttonColumn;
        
        String ultraColor;
        
        
        public betterButtons(int row, int col)
        {
            super();
            buttonRow = row;
            buttonColumn = col;
            ultraColor = "";
        }
        
        
        
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            String colour = board.getTile(buttonRow,buttonColumn).getColor();
            if(colour.equals("white"))
            {
                g.setColor(Color.white);
                g.fillRect(0,0,1000,1000);
            }
            else if(colour.equals("black"))
            {
                g.setColor(Color.black);
                g.fillRect(0,0,1000,1000);
            }
            
            String piece = board.getTile(buttonRow, buttonColumn).getCurrentPiece();
            switch (piece)
            {
                case "red": g.setColor(Color.RED);
                g.fillOval(60, 17, 50, 50);
                break;
                case "yellow": g.setColor(Color.yellow);
                g.fillOval(60, 17, 50, 50);
                break;
                case "redK": g.setColor(Color.RED);
                g.fillOval(68, 18, 50, 50);
                g.setColor(Color.white);
                g.fillOval(80, 30, 25, 25);
                break;
                case "yellowK": g.setColor(Color.yellow);
                g.fillOval(68, 18, 50, 50);
                g.setColor(Color.white);
                g.fillOval(80, 30, 25, 25);
                break;
            }
            
            
                        
        }
    }
    
    class bottomTextButton extends JButton
    {
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
        }
    }
    
    class victoryPanel extends JPanel
    {
        @Override
        public void paintComponent(Graphics g)
        {
            g.setColor(Color.gray);
            g.fillRect(0,0,10000,10000);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 86));
            if(yellowPieces == 0)
            {
            g.setColor(Color.red);
            g.drawString("Red Wins!", getWidth()/2-200, getHeight()/2);
            }
            else if(redPieces == 0)
            {
            g.setColor(Color.yellow);
            g.drawString("Yellow Wins!", getWidth()/2-200, getHeight()/2);    
            }
        }
    }
    
    
    class customPanel extends JPanel implements ActionListener
    {
        
        public void setListeners()
        {
            for(int r = 0; r < buttons.length; r++)
            {
                for(int c = 0; c < buttons.length; c++)
                {
                    buttons[r][c].addActionListener(this);
                }
            }
        }
        
        
        /*
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            if(endGame == true)
            {
                g.setColor(Color.orange);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
                if(redPieces == 0)
                {
                g.drawString("Yellow Wins", getWidth()/2, getHeight()/2);
                }
                else
                {
                g.drawString("Red Wins", getWidth()/2, getHeight()/2);
                }
                
            }
        }
        */
        

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            
            
            
            
            Object actionSource = e.getSource();
            for(int r = 0; r < buttons.length; r++)
            {
                for(int c = 0; c < buttons.length; c++)
                {
                    if(buttons[r][c] == actionSource)
                    {
                        if(board.getTile(r,c).getCurrentPiece().equals("red") && hasPiece == false && yellowTurn  == false)
                        {
                        Toolkit tools = Toolkit.getDefaultToolkit();
                        Image curse = new ImageIcon("red.png").getImage();
                        Point point = new Point(10,10);
                        Cursor cursor = tools.createCustomCursor(curse, point, "myCursor");
                        setCursor(cursor);
                        originalR = r;
                        originalC = c;
                        hasPiece = true;
                        board.getTile(r,c).setPiece("none");
                        heldPieceColor = "red";
                        }
                        else if(board.getTile(r,c).getCurrentPiece().equals("yellow") && hasPiece == false && yellowTurn == true)
                        {
                        Toolkit tools = Toolkit.getDefaultToolkit();
                        Image curse = new ImageIcon("yellow.png").getImage();
                        Point point = new Point(10,10);
                        Cursor cursor = tools.createCustomCursor(curse, point, "myCursor");
                        setCursor(cursor);
                        originalR = r;
                        originalC = c;
                        hasPiece = true;
                        board.getTile(r,c).setPiece("none");
                        heldPieceColor = "yellow";
                        }
                        else if(board.getTile(r,c).getCurrentPiece().equals("redK") && hasPiece == false && yellowTurn  == false)
                        {
                        Toolkit tools = Toolkit.getDefaultToolkit();
                        Image curse = new ImageIcon("redK.png").getImage();
                        Point point = new Point(10,10);
                        Cursor cursor = tools.createCustomCursor(curse, point, "myCursor");
                        setCursor(cursor);
                        originalR = r;
                        originalC = c;
                        hasPiece = true;
                        board.getTile(r,c).setPiece("none");
                        heldPieceColor = "redK";
                        }
                        else if(board.getTile(r,c).getCurrentPiece().equals("yellowK") && hasPiece == false && yellowTurn == true)
                        {
                        Toolkit tools = Toolkit.getDefaultToolkit();
                        Image curse = new ImageIcon("yellowK.png").getImage();
                        Point point = new Point(10,10);
                        Cursor cursor = tools.createCustomCursor(curse, point, "myCursor");
                        setCursor(cursor);
                        originalR = r;
                        originalC = c;
                        hasPiece = true;
                        board.getTile(r,c).setPiece("none");
                        heldPieceColor = "yellowK";
                        }
                        else if(hasPiece == true && board.getTile(r,c).getCurrentPiece().equals("none"))
                        {
                            if(r == originalR && c == originalC)
                            {
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                board.getTile(r,c).setPiece(heldPieceColor);
                                originalR = 0;
                                originalC = 0;
                                hasPiece = false;
                                heldPieceColor = "none";
                            }
                            else if(heldPieceColor.equals("red"))
                            {
                                if((r-originalR == 1) && (c-originalC == 1 || c-originalC == -1))
                                {
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                if(r == 7)
                                {
                                    board.getTile(r,c).setPiece("redK");
                                }
                                else
                                {
                                board.getTile(r,c).setPiece("red");
                                }
                                originalR = 0;
                                originalC = 0;
                                hasPiece = false;
                                heldPieceColor = "none";
                                yellowTurn = true;
                                }
                                else if(((r-originalR == 2) && (c-originalC == 2) && ((board.getTile(originalR+1,originalC+1).getCurrentPiece().equals("yellow")) || board.getTile(originalR+1,originalC+1).getCurrentPiece().equals("yellowK"))))
                                {
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                board.getTile(originalR+1,originalC+1).setPiece("none");
                                buttons[originalR+1][originalC+1].repaint();
                                if(r == 7)
                                {
                                    board.getTile(r,c).setPiece("redK");
                                    yellowTurn = true;
                                }
                                else
                                {
                                board.getTile(r,c).setPiece("red");
                                
                                }
                                
                                originalR = 0;
                                originalC = 0;
                                hasPiece = false;
                                heldPieceColor = "none";
                                
                                yellowPieces--;
                                 if((board.getTile(r+1,c+1).getCurrentPiece().equals("yellow")) && (board.getTile(r+2,c+2).getCurrentPiece().equals("none")) || (board.getTile(r+1,c-1).getCurrentPiece().equals("yellow") && board.getTile(r+2,c-2).getCurrentPiece().equals("none")))
                                            {
                                                yellowTurn = false;
                                            }
                                            else
                                            {
                                                out.println("hail mao");
                                                yellowTurn = true;
                                            }
                                            
                                //yellowTurn = true;
                                }
                                else if(((r-originalR == 2) && (c-originalC == -2) && ((board.getTile(originalR+1,originalC-1).getCurrentPiece().equals("yellow")) || board.getTile(originalR+1,originalC-1).getCurrentPiece().equals("yellowK"))))
                                {
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                board.getTile(originalR+1,originalC-1).setPiece("none");
                                buttons[originalR+1][originalC-1].repaint();
                                if(r == 7)
                                {
                                    board.getTile(r,c).setPiece("redK");
                                    yellowTurn = true;
                                }
                                else
                                {
                                board.getTile(r,c).setPiece("red");
                                
                                        
                                }
                                /*
                                if((board.getTile(r,c).equals("red")) && r == 7)
                                {
                                    board.getTile(r,c).setPiece("redK");
                                }
                                */
                                originalR = 0;
                                originalC = 0;
                                hasPiece = false;
                                heldPieceColor = "none";
                                yellowPieces--;
                                if((board.getTile(r+1,c+1).getCurrentPiece().equals("yellow")) && (board.getTile(r+2,c+2).getCurrentPiece().equals("none")) || (board.getTile(r+1,c-1).getCurrentPiece().equals("yellow") && board.getTile(r+2,c-2).getCurrentPiece().equals("none")))
                                            {
                                                yellowTurn = false;
                                            }
                                            else
                                            {
                                                out.println("hail mao");
                                                yellowTurn = true;
                                            }
                                         
                                //yellowTurn = true;
                                }
                                
                            }
                            else if(heldPieceColor.equals("yellow"))
                            {
                                if((r-originalR == -1) && (c-originalC == 1 || c-originalC == -1))
                                {
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                if(r == 0)
                                {
                                    board.getTile(r,c).setPiece("yellowK");
                                    yellowTurn = false;
                                }
                                else
                                {
                                board.getTile(r,c).setPiece("yellow");
                                }
                                originalR = 0;
                                originalC = 0;
                                hasPiece = false;
                                heldPieceColor = "none";
                                yellowTurn = false;
                                }
                                else if(((r-originalR == -2) && (c-originalC == 2) && ((board.getTile(originalR-1,originalC+1).getCurrentPiece().equals("red")) || board.getTile(originalR+1,originalC+1).getCurrentPiece().equals("redK"))))
                                {
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                if(r == 0)
                                {
                                    board.getTile(r,c).setPiece("yellowK");
                                    yellowTurn = false;
                                }
                                else
                                {
                                board.getTile(r,c).setPiece("yellow");
                                }
                                board.getTile(originalR-1,originalC+1).setPiece("none");
                                buttons[originalR-1][originalC+1].repaint();
                                originalR = 0;
                                originalC = 0;
                                hasPiece = false;
                                heldPieceColor = "none";
                                redPieces--;
                                
                                            if((board.getTile(r-1,c+1).getCurrentPiece().equals("red")) && (board.getTile(r-2,c+2).getCurrentPiece().equals("none")) || (board.getTile(r-1,c-1).getCurrentPiece().equals("red") && board.getTile(r-2,c-2).getCurrentPiece().equals("none")))
                                            {
                                                yellowTurn = true;
                                            }
                                            else
                                            {
                                                out.println("hail mao");
                                                yellowTurn = false;
                                            }
                                               
                                //yellowTurn = false;
                                }
                                else if(((r-originalR == -2) && (c-originalC == -2) && ((board.getTile(originalR-1,originalC-1).getCurrentPiece().equals("red")) || board.getTile(originalR+1,originalC-1).getCurrentPiece().equals("redK"))))
                                {
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                if(r == 0)
                                {
                                    board.getTile(r,c).setPiece("yellowK");
                                    yellowTurn = false;
                                }
                                else
                                {
                                board.getTile(r,c).setPiece("yellow");
                                }
                                board.getTile(originalR-1,originalC-1).setPiece("none");
                                buttons[originalR-1][originalC-1].repaint();
                                originalR = 0;
                                originalC = 0;
                                hasPiece = false;
                                heldPieceColor = "none";
                                redPieces--;
                                if((board.getTile(r-1,c+1).getCurrentPiece().equals("red")) && (board.getTile(r-2,c+2).getCurrentPiece().equals("none")) || (board.getTile(r-1,c-1).getCurrentPiece().equals("red") && board.getTile(r-2,c-2).getCurrentPiece().equals("none")))
                                            {
                                                yellowTurn = true;
                                            }
                                            else
                                            {
                                                out.println("hail mao");
                                                yellowTurn = false;
                                            }
                                            
                                //yellowTurn = false;
                                }
                                
                            }
                            else if(heldPieceColor.equals("redK"))
                            {
                                if((r-originalR == 1 || r-originalR == -1) && (c-originalC == 1 || c-originalC == -1))
                                {
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                board.getTile(r,c).setPiece("redK");
                                originalR = 0;
                                originalC = 0;
                                hasPiece = false;
                                heldPieceColor = "none";
                                yellowTurn = true;
                                }
                                else if(((r-originalR == -2) && (c-originalC == 2) && ((board.getTile(originalR-1,originalC+1).getCurrentPiece().equals("yellow")) || board.getTile(originalR-1,originalC+1).getCurrentPiece().equals("yellowK"))))
                                {
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                board.getTile(r,c).setPiece("redK");
                                board.getTile(originalR-1,originalC+1).setPiece("none");
                                buttons[originalR-1][originalC+1].repaint();
                                originalR = 0;
                                originalC = 0;
                                hasPiece = false;
                                heldPieceColor = "none";
                                yellowPieces--;
                                if(((board.getTile(r-1,c+1).getCurrentPiece().equals("yellow")) && (board.getTile(r-2,c+2).getCurrentPiece().equals("none"))) || (board.getTile(r-1,c-1).getCurrentPiece().equals("yellow") && board.getTile(r-2,c-2).getCurrentPiece().equals("none")) || ((board.getTile(r+1,c+1).getCurrentPiece().equals("yellow")) && (board.getTile(r+2,c+2).getCurrentPiece().equals("none"))) || ((board.getTile(r+1,c-1).getCurrentPiece().equals("yellow")) && (board.getTile(r+2,c-2).getCurrentPiece().equals("none"))))
                                            {
                                                yellowTurn = false;
                                            }
                                            else
                                            {
                                                out.println("hail mao");
                                                yellowTurn = true;
                                            }
                                //yellowTurn = true;
                                }
                                else if(((r-originalR == -2) && (c-originalC == -2) && ((board.getTile(originalR-1,originalC-1).getCurrentPiece().equals("yellow")) || board.getTile(originalR-1,originalC-1).getCurrentPiece().equals("yellowK"))))
                                {
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                board.getTile(r,c).setPiece("redK");
                                board.getTile(originalR-1,originalC-1).setPiece("none");
                                buttons[originalR-1][originalC-1].repaint();
                                originalR = 0;
                                originalC = 0;
                                hasPiece = false;
                                heldPieceColor = "none";
                                yellowPieces--;
                                if(((board.getTile(r-1,c+1).getCurrentPiece().equals("yellow")) && (board.getTile(r-2,c+2).getCurrentPiece().equals("none"))) || (board.getTile(r-1,c-1).getCurrentPiece().equals("yellow") && board.getTile(r-2,c-2).getCurrentPiece().equals("none")) || ((board.getTile(r+1,c+1).getCurrentPiece().equals("yellow")) && (board.getTile(r+2,c+2).getCurrentPiece().equals("none"))) || ((board.getTile(r+1,c-1).getCurrentPiece().equals("yellow")) && (board.getTile(r+2,c-2).getCurrentPiece().equals("none"))))
                                            {
                                                yellowTurn = false;
                                            }
                                            else
                                            {
                                                out.println("hail mao");
                                                yellowTurn = true;
                                            }
                                //yellowTurn = true;
                                }
                                else if(((r-originalR == 2) && (c-originalC == 2) && ((board.getTile(originalR+1,originalC+1).getCurrentPiece().equals("yellow")) || board.getTile(originalR+1,originalC+1).getCurrentPiece().equals("yellowK"))))
                                {
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                board.getTile(r,c).setPiece("redK");
                                board.getTile(originalR+1,originalC+1).setPiece("none");
                                buttons[originalR+1][originalC+1].repaint();
                                originalR = 0;
                                originalC = 0;
                                hasPiece = false;
                                heldPieceColor = "none";
                                yellowPieces--;
                                            if(((board.getTile(r-1,c+1).getCurrentPiece().equals("yellow")) && (board.getTile(r-2,c+2).getCurrentPiece().equals("none"))) || (board.getTile(r-1,c-1).getCurrentPiece().equals("yellow") && board.getTile(r-2,c-2).getCurrentPiece().equals("none")) || ((board.getTile(r+1,c+1).getCurrentPiece().equals("yellow")) && (board.getTile(r+2,c+2).getCurrentPiece().equals("none"))) || ((board.getTile(r+1,c-1).getCurrentPiece().equals("yellow")) && (board.getTile(r+2,c-2).getCurrentPiece().equals("none"))))
                                            {
                                                yellowTurn = false;
                                            }
                                            else
                                            {
                                                out.println("hail mao");
                                                yellowTurn = true;
                                            }
                                //yellowTurn = true;
                                }
                                else if(((r-originalR == 2) && (c-originalC == -2) && ((board.getTile(originalR+1,originalC-1).getCurrentPiece().equals("yellow")) || board.getTile(originalR+1,originalC-1).getCurrentPiece().equals("yellowK"))))
                                {
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                board.getTile(r,c).setPiece("redK");
                                board.getTile(originalR+1,originalC-1).setPiece("none");
                                buttons[originalR+1][originalC-1].repaint();
                                originalR = 0;
                                originalC = 0;
                                hasPiece = false;
                                heldPieceColor = "none";
                                yellowPieces--;
                                if(((board.getTile(r-1,c+1).getCurrentPiece().equals("yellow")) && (board.getTile(r-2,c+2).getCurrentPiece().equals("none"))) || (board.getTile(r-1,c-1).getCurrentPiece().equals("yellow") && board.getTile(r-2,c-2).getCurrentPiece().equals("none")) || ((board.getTile(r+1,c+1).getCurrentPiece().equals("yellow")) && (board.getTile(r+2,c+2).getCurrentPiece().equals("none"))) || ((board.getTile(r+1,c-1).getCurrentPiece().equals("yellow")) && (board.getTile(r+2,c-2).getCurrentPiece().equals("none"))))
                                            {
                                                yellowTurn = false;
                                            }
                                            else
                                            {
                                                out.println("hail mao");
                                                yellowTurn = true;
                                            }
                                //yellowTurn = true;
                                }
                                
                            }
                            else if(heldPieceColor.equals("yellowK"))
                            {
                                if((r-originalR == 1 || r-originalR == -1) && (c-originalC == 1 || c-originalC == -1))
                                {
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                board.getTile(r,c).setPiece("yellowK");
                                originalR = 0;
                                originalC = 0;
                                hasPiece = false;
                                heldPieceColor = "none";
                                yellowTurn = false;
                                
                                }
                                else if(((r-originalR == -2) && (c-originalC == 2) && ((board.getTile(originalR-1,originalC+1).getCurrentPiece().equals("red")) || board.getTile(originalR-1,originalC+1).getCurrentPiece().equals("redK"))))
                                {
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                board.getTile(r,c).setPiece("yellowK");
                                board.getTile(originalR-1,originalC+1).setPiece("none");
                                buttons[originalR-1][originalC+1].repaint();
                                originalR = 0;
                                originalC = 0;
                                hasPiece = false;
                                heldPieceColor = "none";
                                redPieces--;
                                //yellowTurn = false;
                                if(((board.getTile(r-1,c+1).getCurrentPiece().equals("red")) && (board.getTile(r-2,c+2).getCurrentPiece().equals("none"))) || ((board.getTile(r-1,c-1).getCurrentPiece().equals("red") && board.getTile(r-2,c-2).getCurrentPiece().equals("none"))) || ((board.getTile(r+1,c+1).getCurrentPiece().equals("red")) && (board.getTile(r+2,c+2).getCurrentPiece().equals("none"))) || ((board.getTile(r+1,c-1).getCurrentPiece().equals("red")) && (board.getTile(r+2,c-2).getCurrentPiece().equals("none"))))
                                            {
                                                yellowTurn = true;
                                            }
                                            else
                                            {
                                                out.println("hail mao");
                                                yellowTurn = false;
                                            }   
                                }
                                
                                else if(((r-originalR == -2) && (c-originalC == -2) && ((board.getTile(originalR-1,originalC-1).getCurrentPiece().equals("red")) || board.getTile(originalR-1,originalC-1).getCurrentPiece().equals("redK"))))
                                {
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                board.getTile(r,c).setPiece("yellowK");
                                board.getTile(originalR-1,originalC-1).setPiece("none");
                                buttons[originalR-1][originalC-1].repaint();
                                originalR = 0;
                                originalC = 0;
                                hasPiece = false;
                                heldPieceColor = "none";
                                redPieces--;
                                //yellowTurn = false;
                                if(((board.getTile(r-1,c+1).getCurrentPiece().equals("red")) && (board.getTile(r-2,c+2).getCurrentPiece().equals("none"))) || ((board.getTile(r-1,c-1).getCurrentPiece().equals("red") && board.getTile(r-2,c-2).getCurrentPiece().equals("none"))) || ((board.getTile(r+1,c+1).getCurrentPiece().equals("red")) && (board.getTile(r+2,c+2).getCurrentPiece().equals("none"))) || ((board.getTile(r+1,c-1).getCurrentPiece().equals("red")) && (board.getTile(r+2,c-2).getCurrentPiece().equals("none"))))
                                            {
                                                yellowTurn = true;
                                            }
                                            else
                                            {
                                                out.println("hail mao");
                                                yellowTurn = false;
                                            }
                                }
                                
                                else if(((r-originalR == 2) && (c-originalC == 2) && ((board.getTile(originalR+1,originalC+1).getCurrentPiece().equals("red")) || board.getTile(originalR+1,originalC+1).getCurrentPiece().equals("redK"))))
                                {
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                board.getTile(r,c).setPiece("yellowK");
                                board.getTile(originalR+1,originalC+1).setPiece("none");
                                buttons[originalR+1][originalC+1].repaint();
                                originalR = 0;
                                originalC = 0;
                                hasPiece = false;
                                heldPieceColor = "none";
                                redPieces--;
                                //yellowTurn = false;
                                if(((board.getTile(r-1,c+1).getCurrentPiece().equals("red")) && (board.getTile(r-2,c+2).getCurrentPiece().equals("none"))) || ((board.getTile(r-1,c-1).getCurrentPiece().equals("red") && board.getTile(r-2,c-2).getCurrentPiece().equals("none"))) || ((board.getTile(r+1,c+1).getCurrentPiece().equals("red")) && (board.getTile(r+2,c+2).getCurrentPiece().equals("none"))) || ((board.getTile(r+1,c-1).getCurrentPiece().equals("red")) && (board.getTile(r+2,c-2).getCurrentPiece().equals("none"))))
                                            {
                                                yellowTurn = true;
                                            }
                                            else
                                            {
                                                out.println("hail mao");
                                                yellowTurn = false;
                                            }
                                }
                                
                                else if(((r-originalR == 2) && (c-originalC == -2) && ((board.getTile(originalR+1,originalC-1).getCurrentPiece().equals("red")) || board.getTile(originalR+1,originalC-1).getCurrentPiece().equals("redK"))))
                                {
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                board.getTile(r,c).setPiece("yellowK");
                                board.getTile(originalR+1,originalC-1).setPiece("none");
                                buttons[originalR+1][originalC-1].repaint();
                                originalR = 0;
                                originalC = 0;
                                hasPiece = false;
                                heldPieceColor = "none";
                                yellowPieces--;
                                //yellowTurn = false;
                                if(((board.getTile(r-1,c+1).getCurrentPiece().equals("red")) && (board.getTile(r-2,c+2).getCurrentPiece().equals("none"))) || ((board.getTile(r-1,c-1).getCurrentPiece().equals("red") && board.getTile(r-2,c-2).getCurrentPiece().equals("none"))) || ((board.getTile(r+1,c+1).getCurrentPiece().equals("red")) && (board.getTile(r+2,c+2).getCurrentPiece().equals("none"))) || ((board.getTile(r+1,c-1).getCurrentPiece().equals("red")) && (board.getTile(r+2,c-2).getCurrentPiece().equals("none"))))
                                            {
                                                yellowTurn = true;
                                            }
                                            else
                                            {
                                                out.println("hail mao");
                                                yellowTurn = false;
                                            }
                                }
                                }
                                
                            }
                        }
                    }
                }
                

            /*
            
            */
            
            
            if(redPieces == 0)
            {
                endGame = true;
                mainPanel.repaint();
                yellowTurn = false;
                vicPan = new victoryPanel();
                mainFrame.add(vicPan);
                mainFrame.repaint();
                vicPan.repaint();
                bottomPanel.repaint();
                vicPan.setVisible(true);
                mainPanel.setVisible(false);
            }
            else if(yellowPieces == 0)
            {
                endGame = true;
                mainPanel.repaint();
                yellowTurn = true;
                vicPan = new victoryPanel();
                mainFrame.add(vicPan);
                mainFrame.repaint();
                vicPan.repaint();
                bottomPanel.repaint();
                vicPan.setVisible(true);
                mainPanel.setVisible(false);
            }
            
            
        }
        
        
        
        
    }
    
    class mainMenuPanel extends JPanel implements ActionListener
    {
        public void addActionListeners()
        {
            for(int i = 0; i < mainMenuButtons.size(); i++)
            {
                mainMenuButtons.get(i).addActionListener(this);
            }
        }
        
        
        
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(Color.orange);
            g.fillRect(0,0,10000,10000);
            g.setFont(new Font("TimesRoman", Font.BOLD, 94));
            g.setColor(Color.black);
            g.drawString("Checkers", getWidth()/2-200, getHeight()/2-200);
        }
        

        @Override
        public void actionPerformed(ActionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Object source = e.getSource();
        if(source == startButton)
        {
            mainFrame.remove(mainM);
            initializeGame();
        }
        }
        
    }
    
    class mainMenuButton extends JButton
    {
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(Color.white);
            g.fillRect(0,0,10000,10000);
        }
    }
    
    
}
