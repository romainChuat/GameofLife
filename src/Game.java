/******************************************************/
/*********************GAME OF LIFE*********************/
/*********************CHUAT Romain*********************/
/*********************   2021     *********************/
/******************************************************/

import javax.swing.*;

public class Game extends JFrame {

    public Game(){
        setVisible(true);
        setSize(1300,800);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        add(new GamePanel());
    }
    public static void main (String [] args){
        new Game();
    }

}
