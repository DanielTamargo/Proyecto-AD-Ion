package com.company.varios;

import javax.swing.*;

public class HiloCloud extends Thread {

    private JLabel cloud;
    private boolean bucle = true;
    private int foto = 0;

    public HiloCloud(JLabel cloud) {
        this.cloud = cloud;
    }

    @Override
    public void run() {
        while (bucle) {
            foto++;
            if (foto > 8)
                foto = 1;

            if (foto == 1)
                cloud.setIcon(new ImageIcon("Assets/cloud1.png"));
            else if (foto == 2)
                cloud.setIcon(new ImageIcon("Assets/cloud2.png"));
            else if (foto == 3)
                cloud.setIcon(new ImageIcon("Assets/cloud1.png"));
            else if (foto == 4)
                cloud.setIcon(new ImageIcon("Assets/cloud2.png"));
            else if (foto == 5)
                cloud.setIcon(new ImageIcon("Assets/cloud3.png"));
            else if (foto == 6)
                cloud.setIcon(new ImageIcon("Assets/cloud4.png"));
            else if (foto == 7)
                cloud.setIcon(new ImageIcon("Assets/cloud3.png"));
            else if (foto == 8)
                cloud.setIcon(new ImageIcon("Assets/cloud4.png"));

            try {
                Thread.sleep(250);
            } catch (InterruptedException ignored) { }
        }
    }

    public void parar() {
        bucle = false;
    }
}
