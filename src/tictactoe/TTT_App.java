package tictactoe;

import javax.swing.*;

public class TTT_App extends JFrame{
    TTT_App() {//creates the JFrame and sets size, location, name, visibility and adds to JPanel
        super("TicTacToe");
        
        TTT_Canvas canvas = new TTT_Canvas();
        add(canvas);
        setSize(450,550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}