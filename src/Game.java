import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;


public class Game extends JPanel {

    double x , y;
    int iniziale;

    public Game(){

        x = 0;
        y = this.getHeight() / 2;


        Timer timer = new Timer(1 , new GestioneEvento());
        timer.start();
        System.out.println(y);
        
    }
    
    /*

    public void Parabola(int x){

        return()
    }
    */


    public void paintComponent(Graphics g){
        super.paintComponent(g);

        x = x + 5;
        g.fillOval((int) x, (int) y, 100, 100);

    }

    public void setIniziale(int size){
        iniziale = size;

    }

    public class GestioneEvento implements ActionListener  {


        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            repaint();
        }
        
    }
    
}
