package com.company;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static class obj extends JComponent  {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            int n = 10, maxheight = 50, minheight = 500, maxarc=40, minarc = 740, xheight=0, xarc = 0;
            for (int i = 0; i<n;i++)
            {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Random rand = new Random();
                float r = (float) (rand.nextFloat()/2f+0.5f );
                float ggg = (float) (rand.nextFloat()/ 2f);
                float b = (float) (rand.nextFloat()/ 2f);
                Color randomColor = new Color(r, ggg, b);
                xheight = maxheight + (int) (Math.random() * ((minheight - maxheight) + 1));
                xarc = maxarc + (int) (Math.random() * ((minarc - maxarc) + 1));
                g.setColor(randomColor);
                g.drawLine(xarc, xheight, 330, 700);
                g.fillOval(xarc, xheight - 10, 8, 8);
                for (int gg = 0; gg < 90; gg++)
                {
                    int randomplacex = (xarc+40) + (int) (Math.random() * (((xarc - 40) - (xarc+40) + 1)));
                    int randonplacey = (xheight+40) + (int) (Math.random() * (((xheight - 40) - (xheight+40) + 1)));
                    g.setColor(randomColor.brighter().brighter());
                    g.fillOval(randomplacex, randonplacey, 8, 8);
                }
                for(int gg2 = 0; gg2 < 60; gg2++)
                {
                    g.setColor(randomColor.brighter());
                    int randomplacex1 = (xarc+40+20) + (int) (Math.random() * (((xarc - 40-20) - (xarc+40+20) + 1)));
                    int randonplacey1 = (xheight+40+20) + (int) (Math.random() * (((xheight - 40-20) - (xheight+40+20) + 1)));
                    g.fillOval(randomplacex1, randonplacey1, 9, 9);
                }
                for(int gg3 = 0; gg3 < 30; gg3++)
                {
                    g.setColor(randomColor);
                    int randomplacex2 = (xarc+40+40) + (int) (Math.random() * (((xarc - 40-40) - (xarc+40+40) + 1)));
                    int randonplacey2 = (xheight+40+40) + (int) (Math.random() * (((xheight - 40-40) - (xheight+40+40) + 1)));
                    g.fillOval(randomplacex2, randonplacey2, 10, 10);
                }
            }
        }
    }
    public static void main(String[] args) {
        // write your code here
        JFrame frame = new JFrame("Фрактал");
        frame.setVisible(true);
        frame.setSize(700,700);
        obj RR = new obj();
        frame.add(RR);
        while (true){
            frame.repaint();
        }
    }
}

