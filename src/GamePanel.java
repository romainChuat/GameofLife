/******************************************************/
/*********************GAME OF LIFE*********************/
/*********************CHUAT Romain*********************/
/*********************   2021     *********************/
/******************************************************/

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener  {
    int xPanel = 1300;
    int yPanel = 800;
    int resolution = 10; //size of the cells
    int xWidth = xPanel/resolution;
    int yHeight= yPanel/resolution;
    int [] [] tab = new int[xWidth][yHeight];   //current tab
    int [] [] next = new int[xWidth][yHeight];  //next tab (calculate with the alive neighbors)
    Timer time;

    public GamePanel(){
        setLocation(0,0);
        setSize(xPanel,yPanel);
        time = new Timer(150,this);
        /**Button Start Listener**/
        JButton buttonPlay = new JButton("START");
        buttonPlay.addActionListener(this::startListener);
        add(buttonPlay);
        /**Button Spawn**/
        JButton buttonSpawn = new JButton("SPAWN");
        buttonSpawn.addActionListener(this::SpawnListener);
        add(buttonSpawn);
        /**Button Play/Paused**/
        JButton buttonPaused = new JButton("PLAY/PAUSED");
        buttonPaused.addActionListener(this::playPauseListener);
        add(buttonPaused);
        /**Button Clear**/
        JButton buttonClear = new JButton("CLEAR");
        buttonClear.addActionListener(this::clearListener);
        add(buttonClear);
    }
    /**
     * Start button Listener
     **/
    public void startListener(ActionEvent e) {
        spawn();
        time.start();
    }
    /**
     * Play/Paused button Listener
     **/
    public void playPauseListener(ActionEvent e) {
        if(time.isRunning())
            time.stop();
        else
        if(time.isCoalesce()){
            time.start();
        }else{
            spawn();
            time.start();
        }
    }
    /**
     * Spawn button Listener
     **/
    public void SpawnListener(ActionEvent e) {
        spawn();
        repaint();
    }
    /**
     * Clear button Listener
     **/
    public void clearListener(ActionEvent e) {
        clear();
        repaint();
    }
    /**
     * Clear the cells
     **/
    public  void clear(){
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < (yHeight); j++) {
                next[i][j] = 0;
            }
        }
    }
    /**
     *  Define the color of the Panel (Background)
     **/
    public void paintComponent(Graphics g) {
        setBackground(Color.cyan);
        super.paintComponent(g);
        grid(g);
        display(g);
    }
    /**
     * Create the grid
     **/
    private void grid (Graphics g){
        for (int i = 0; i < xPanel; i += resolution) {
            for (int j = 0; j < yPanel; j += resolution) {
                g.drawLine(i, 0, i, yPanel);
                g.drawLine(0, j, xPanel, j);
            }
        }
    }
    /**
     * filled randomly the tab
     * **/
    public void spawn(){
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < (yHeight); j++) {
                if ((int) (Math.random() * 5) == 0){
                    next[i][j] = 1;
                }
            }
        }
    }
    /**
     *  filled the cells of the grid
     **/
    private void display(Graphics g) {
        g.setColor(Color.black);
        copyArray();
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < yHeight ; j++) {
                int posX = i * resolution;
                int posY = j * resolution;
                if(tab[i][j] ==1 ) {
                    g.fillRect(posX, posY, resolution, resolution);
                }
            }
        }
    }
    /**
     * Copy the array tab into next
     **/
    private void copyArray(){
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < yHeight ; j++) {
                tab[i][j] = next[i][j];
            }
        }
    }
    /**
     * Calculate the sum of alive neighbors
     **/
    private int Neighbors( int x, int y) {
        int sum = 0;
        sum += tab [(x + xWidth - 1 )% xWidth ] [(y + yHeight - 1) % yHeight];
        sum += tab [(x + xWidth) % xWidth] [(y + yHeight -1  ) % yHeight];
        sum += tab[(x  + xWidth + 1 ) % xWidth] [(y + yHeight - 1) % yHeight] ;
        sum += tab  [(x + xWidth - 1 )% xWidth] [(y + yHeight) % yHeight];
        sum += tab  [(x + xWidth + 1 )% xWidth] [(y + yHeight) % yHeight];
        sum += tab  [(x + xWidth - 1 )% xWidth] [ (y + yHeight + 1 )% yHeight];
        sum += tab  [(x + xWidth )% xWidth] [(y + yHeight + 1) % yHeight];
        sum += tab  [(x + xWidth + 1)% xWidth] [(y+ yHeight + 1 )% yHeight];
        return sum;
    }
    /**
     *  Kill and make the cells appear when needed
     **/
    public void actionPerformed(ActionEvent e){
        int aliveNeighbors;
        for (int i = 0; i < tab.length; i ++) {
            for (int j = 0; j < yHeight ; j ++) {
                aliveNeighbors = Neighbors(i,j) ;
                int state = tab[i][j];
                if(state == 0 && aliveNeighbors == 3) {
                    next [i] [j] = 1;
                }
                else if (state == 1 && (aliveNeighbors < 2 || aliveNeighbors > 3)) {
                    next [i] [j] = 0;
                }
                else if(aliveNeighbors ==2) {
                    next[i][j] = tab[i][j];
                }
            }
        }
        repaint();
    }
}







