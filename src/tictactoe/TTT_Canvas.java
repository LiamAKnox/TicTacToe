package tictactoe;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class TTT_Canvas extends JPanel implements MouseListener, MouseMotionListener{
    int mouseX, mouseY;
    char[] filled = new char[9];
    boolean player_turn, winner;
    Font font = new Font("Arial", Font.BOLD, 70);

    public TTT_Canvas() {//initializes mouseListeners
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setFont(this.font);
        g2D.setColor(Color.BLACK);
            
        //creates the standard empty board
        Rectangle2D.Double line1 = new Rectangle2D.Double(145, 0, 3, 450);
        Rectangle2D.Double line2 = new Rectangle2D.Double(295, 0, 3, 450);
        Rectangle2D.Double line3 = new Rectangle2D.Double(0, 150, 450, 3);
        Rectangle2D.Double line4 = new Rectangle2D.Double(0, 300, 450, 3);
        g2D.fill(line1);
        g2D.fill(line2);
        g2D.fill(line3);
        g2D.fill(line4);

        //shows which squares are chosen by which player
        for (int tile = 0; tile < 9; tile++) {
            int x = (tile % 3) * 150;
            int y = (tile / 3) * 150;
            if (this.filled[tile] != 0) {
                g2D.drawString(this.filled[tile] + "", x + 48, y + 100);
            } 
        }
        
        for (int i = 0; i < 9; i++) {
            if (this.filled[i] == 0) {
                break;
            }
            if (i == 8) {
                g2D.drawString("Draw",  130, 500);
            }
        }

        //checks for a winner
        for (int i = 0; i < 3; ++i) {
            if (this.filled[i * 3] == this.filled[i * 3 + 1] && this.filled[i * 3] == this.filled[i * 3 + 2] && this.filled[i * 3] != 0) {//row winner
                winner = true;
            } else if (this.filled[i] == this.filled[i + 3] && this.filled[i] == this.filled[i + 6] && this.filled[i] != 0) {//column winner
                winner = true;
            } else if (this.filled[0] == this.filled[4] && this.filled[0] == this.filled[8] && this.filled[0] != 0) {//diagonal winner
                winner = true;
            } else if (this.filled[2] == this.filled[4] && this.filled[2] == this.filled[6] && this.filled[2] != 0) {//diagonal winner
                winner = true;
            }

            if (winner) {//if there is a winner, ouput a statement
                g2D.setColor(Color.BLACK);
                if (player_turn == false) {//O player
                    g2D.drawString("O Wins",  85, 500);
                } else {
                    g2D.drawString("X Wins",  85, 500);
                }
                break;
            }
        }
    }


    @Override
    public void mouseMoved (MouseEvent arg0) {//keep track of mouse coordinates
        this.mouseX = arg0.getX();
        this.mouseY = arg0.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!winner) {//if winner has yet to be decided
            int x = this.mouseX / 150;
            int y = this.mouseY / 150;
            

            if (x >= 0 && x < 3 && y >= 0 && y < 3) {//player gets the tile they clicked
                if (this.filled[3 * y + x] != 0) {
                    return;
                }
                if (this.player_turn == false) {//x player
                    this.filled[3 * y + x] = 'X';
                } else {
                    this.filled[3 * y + x] = 'O';
                }
            }
            this.player_turn = !this.player_turn;

            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}
}