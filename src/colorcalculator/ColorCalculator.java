package colorcalculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author amiel
 */



public class ColorCalculator {
    private JFrame f;
    
    private JPanel p1; // labels, text fields, and buttons    
    private JLabel l1;
    private JTextField t1;
    private JLabel l2;
    private JTextField t2;
    private JLabel l3;
    private JTextField t3;
    private JLabel l4;
    private JTextField t4;
    private JButton bCompute, bClear;
    
    private JPanel p2; // color
            
    public ColorCalculator()  {
        f = new JFrame("Color Calculator");
        p1 = new JPanel();
        p2 = new JPanel()   {
            protected void paintComponent(Graphics g)   {
                g.setColor( getBackground() );
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        
        l1 = new JLabel("Red: ");
        l2 = new JLabel("Green: ");
        l3 = new JLabel("Blue: ");
        l4 = new JLabel("Alpha: ");
        
        t1 = new JTextField("255", 15);
        t2 = new JTextField("255", 15);
        t3 = new JTextField("255", 15);
        t4 = new JTextField("255", 15);
        
        bCompute = new JButton("Compute");
        bClear = new JButton("Clear");
    }
    
    public void startApp()  {
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        p1.add(l3);
        p1.add(t3);
        p1.add(l4);
        p1.add(t4);
        p1.add(bCompute);
        p1.add(bClear);
        p2.setOpaque(false);
        p2.setBackground(new Color(
            Integer.parseInt(t1.getText()),
            Integer.parseInt(t2.getText()),
            Integer.parseInt(t3.getText()),
            Integer.parseInt(t4.getText())));
        
        f.add(p1);
        f.add(p2);
        
        p1.setLayout(new GridLayout(5,2));
        f.setLayout(new GridLayout(2,1));
        
        bCompute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)  {
                try {
                    p2.setBackground(new Color(
                        Integer.parseInt(t1.getText()),
                        Integer.parseInt(t2.getText()),
                        Integer.parseInt(t3.getText()),
                        Integer.parseInt(t4.getText())));
                }
                catch (IllegalArgumentException iae)    {
                    JOptionPane.showMessageDialog(null, "All inputs must be integers ranging from 0 to 255.", "Error!", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        bClear.addActionListener(new ActionListener()   {
            public void actionPerformed(ActionEvent e)  {
                t1.setText("255");
                t2.setText("255");
                t3.setText("255");
                t4.setText("255");
                p2.setBackground(new Color(255,255,255,255));
            }
        });
        
        f.addWindowListener(new MyCloseButtonHandler());
   
        f.pack();
        f.setVisible(true);
    }
    
    public static void main(String[] args) {
        ColorCalculator cc = new ColorCalculator();
        cc.startApp();
    }
    
    private class MyCloseButtonHandler extends WindowAdapter    {
        public void windowClosing(WindowEvent we)   {
            System.exit(0);
        }
    }
}

