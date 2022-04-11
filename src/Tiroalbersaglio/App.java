package Tiroalbersaglio;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class App extends JFrame{

    private Image pistola [];
    private Image background;

    private boolean checked = false;
    private boolean checkednull = false;
    private boolean reverse = false;

    Rectangle rectangle = new Rectangle();

    private JLabel coment = new JLabel("100");
    private JComboBox  <String> scelta  = new JComboBox <String>();

    private int x , y;

    private Game game = new Game();
    private JSettingPanel settingpanel = new JSettingPanel();

    private Timer timer = new Timer(1 , new GestioneEventi());
    private Timer timer2 = new Timer(250 , new Action());



    public App() {
        pistola = new Image[2];

        try {
            pistola[0] = ImageIO.read(new File("immagini/pistola1.png"));
            pistola[1] = ImageIO.read(new File("immagini/pistola2.png"));
            background = ImageIO.read(new File("immagini/background.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        timer.start();
        timer2.start();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(750 , 750);

        Container container = this.getContentPane();
        container.add(game);
        container.add(settingpanel , BorderLayout.NORTH);

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
            this.setBackground(Color.white);

            scelta.addItem("Hard");
            scelta.addItem("Medium");
            scelta.addItem("Easy");

            this.add(coment);

            scelta.setPreferredSize(new Dimension(200 , 50));
            coment.setFont(font);
            coment.setVisible(false);

        }


        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            coment.setLocation(new Point(x - 10 , y - 10));
            g.drawImage(background, 0, 0, getWidth(), getHeight(), Color.white, this);

            if(checked == false){
                g.fillOval(x, y, 100, 100);
                if(checkednull == false){
                    g.drawImage(pistola[0], (getWidth() / 2) - 350  , getHeight() - 430 , this);
                }
            }
            else{
                g.setColor(Color.RED);
                g.fillOval(x, y, 120, 120);
                //g.clearRect((getWidth() / 2) - 150 , getHeight() - 150, pistola[0].getWidth(this) / 2, pistola[0].getHeight(this) / 2);
                g.drawImage(pistola[1], (int) (getWidth() / 2) - 350  , getHeight() - 450 , this);
                timer.stop();
            }

            if(checkednull == true){
                //g.clearRect((getWidth() / 2) - 150 , getHeight() - 150, pistola[0].getWidth(this) / 2 , pistola[0].getWidth(this) / 2);
                g.drawImage(pistola[1], (int) (getWidth() / 2) - 350  , getHeight() - 450 , this);
            }

        
        }
    }

    public class GestioneEventi implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stubi 
            
            y = (int) ((game.getHeight() - 100) / 2);
            addPoint(setDifficult(settingpanel.getChooise()));
            if(x >= (getWidth() - 100)){
                reverse = true;
            }
            if(x <= 0)
                reverse = false;


            rectangle.setLocation(new Point(x , y));
            repaint();
        }

    }

    public class GestioneMouse extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
            if(e.getSource().equals(game) && rectangle.contains(e.getPoint()) == false && settingpanel.getBullets() > 0){
                checkednull = true;
                timer2.start();
                repaint();
            }
            if(rectangle.contains(e.getPoint()) && settingpanel.getBullets() > 0){
                settingpanel.setPoints(settingpanel.getPoints() + 100);
                checked = true;
                coment.setVisible(true);
                timer2.start();
                timer.stop();
                repaint();
        }
        settingpanel.setBullets(settingpanel.getBullets() - 1);
        }
    }


    public class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

                if(checked == true ){
                    coment.setVisible(false);
                    checked = false;
                    timer2.stop();
                    timer.start();
                }

                if(checkednull == true){
                    checkednull = false;
                    timer2.stop();
                }
            
        }
    }
    public static void main(String[] args) {
        new App();
    }
    
}
