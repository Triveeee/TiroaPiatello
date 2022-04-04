package Tiroaalbersaglio;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class App extends JFrame{


    Image pistola [];
    private boolean checked = false;
    private boolean checkednull = false;
    Rectangle rectangle = new Rectangle();

    JLabel coment = new JLabel("Bravo");
    JComboBox  <String> scelta  = new JComboBox <String>();
    boolean reverse = false;
    private int x , y;
    Game game = new Game();
    Timer timer = new Timer(1 , new GestioneEventi());
    Timer timer2 = new Timer(1000 , new Action());



    public App() {
        pistola = new Image[2];

        try {
            pistola[0] = ImageIO.read(new File("immagini/pistola1.png"));
            pistola[1] = ImageIO.read(new File("immagini/pistola2.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        timer.start();
        timer2.start();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(500 , 500);


        this.add(game);

        rectangle.setSize(new Dimension(100 , 100));
    }

    public void addPoint(int add){
        if(reverse == false)
            x = x + add;
        else
            x = x - add; 
    }
    public int setDifficult(String difficult) {


        int x = 0;

        if(difficult.equals("Hard")){
            x = 20;

        }
        if(difficult.equals("Medium")){
            x = 10;

        }
        if(difficult.equals("Easy")){
            x = 5;

        }

        return(x);


    }

    

    public class Game extends JPanel{

        public Game() {

            Font font = new Font("Impact" , Font.LAYOUT_RIGHT_TO_LEFT , 20);
            this.addMouseListener(new GestioneMouse());


            /*
            for(int i =  1 ; i < 10 ; i++){
                scelta.addItem(i);
            }
            */

            scelta.addItem("Hard");
            scelta.addItem("Medium");
            scelta.addItem("Easy");

            this.add(new JLabel("Livello "));
            this.add(scelta);
            this.add(coment);
            coment.setFont(font);
            coment.setVisible(false);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            coment.setLocation(new Point(x - 10 , y - 10));

            if(checked == false){
                g.fillOval(x, y, 100, 100);
                g.drawImage(pistola[0], (getWidth() / 2) - 150  , getHeight() - 250 ,  this);
            }
            else{
                g.setColor(Color.RED);
                g.fillOval(x, y, 120, 120);
                g.drawImage(pistola[1], (getWidth() / 2) - 150  , getHeight() - 250 , this);
                timer.stop();
            }

        
        }
    }

    public class GestioneEventi implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stubi 
            
            y = ((getHeight() - 100) / 2);
            addPoint(setDifficult((String) scelta.getSelectedItem()));
            if(x >= (getWidth() - 100)){
                reverse = true;
            }
            if(x <= 0)
                reverse = false;


            coment.setLocation(new Point(x - 10 , y - 10));
            rectangle.setLocation(new Point(x , y));
            repaint();
            
        }

    }

    public class GestioneMouse extends MouseAdapter{

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

            if(rectangle.contains(e.getPoint())){
                checked = true;
                coment.setVisible(true);
                timer2.start();
                timer.stop();
                repaint();

            }
        }

    }
    public class Action implements ActionListener{

        int time = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

                coment.setVisible(false);
                checked = false;
                timer2.stop();
                timer.start();
            }
            
    }



    public static void main(String[] args) {
        new App();
    }
    
}
