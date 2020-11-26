package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

//Reference: https://www.youtube.com/watch?v=Kmgo00avvEw
//Reference: https://github.com/shirleywbi/BudgetApp/tree/master/src/ui/panel


public class Main implements ActionListener {
    public static void main(String[] args) {
//        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(1000,500);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
//        ImageIcon image = new ImageIcon("DogGooo Image.png");
//        JLabel label = new JLabel(image);
//        frame.add(label);


        frame.add(new GUI().createPanel());

//        try {
//            frame.add(new GUI().createPanel());
//            new LocationApp();
//            new GUI();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found");
//        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}