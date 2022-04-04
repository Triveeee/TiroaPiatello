import java.awt.Dimension;

import javax.swing.JFrame;

public class App  extends JFrame{

    public App(){

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(500 , 500);

        Game gioco = new Game();
        gioco.setIniziale(this.getHeight());
        System.out.println(gioco.getSize());
        this.add(gioco);

    }
    public static void main(String[] args) throws Exception {
        new App();
    }
}
