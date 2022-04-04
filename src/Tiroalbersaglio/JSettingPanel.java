package Tiroalbersaglio;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

public class JSettingPanel extends JPanel{

    private JLabel Labelbullets = new JLabel();
    private JLabel LabelPoints = new JLabel();
    private JComboBox  <String> scelta  = new JComboBox <String>();
    private int bullets = 0;
    private int points;

    public JSettingPanel(){

        scelta.addItem("Hard");
        scelta.addItem("Medium");
        scelta.addItem("Easy");

        Font font = new Font("Times New Romans" , Font.BOLD , 20);
        LabelPoints.setBorder(BorderFactory.createLineBorder(Color.black, 5 , false));
        LabelPoints.setHorizontalAlignment(JLabel.CENTER);
        LabelPoints.setFont(font);
        Labelbullets.setBorder(BorderFactory.createLineBorder(Color.black, 5 , false));
        Labelbullets.setHorizontalAlignment(JLabel.CENTER);
        Labelbullets.setFont(font);
        this.setBullets(50);
        this.setPoints(0);


        this.setBackground(Color.gray);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(Labelbullets);
        this.add(scelta);
        this.add(LabelPoints);
        this.setPreferredSize(new Dimension(100 , 100));

        Labelbullets.setMaximumSize(new Dimension(200 , 200));
        scelta.setMaximumSize(new Dimension(200 , 200));
        LabelPoints.setMaximumSize(new Dimension(200 , 200));
        
    }

    public void setBullets(int bullets) {
        if(bullets < 0)
            this.bullets = 0;
        else
            this.bullets = bullets;
        Labelbullets.setText("<html> <font size = 5 color = red > proiettili <br> </font>" +  this.bullets + "</html>");
    }

    public void setPoints(int points){
        this.points = points;
        LabelPoints.setText("<html> <font size = 5 color = red > punti <br> </font>" +  this.points + "</html>");
    }

    public String getChooise(){
        return (String) (scelta.getSelectedItem());
    }

    public int getBullets() {
        return(bullets);
    }

    public int getPoints(){
        return(points);
    }
    
    
}
