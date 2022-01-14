/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zaafr
 */
public class MyThread extends Thread{
    Graphics g;
    MyList myList;
    MyConnection myConnection;


    public MyThread(MyList myList) throws SQLException {
        this.myList = myList;
        this.myConnection = new MyConnection();
    }


    public void run() {
        while(true) {
            try {
                this.myConnection.fillTable(this.myList.model);
                Thread.sleep(1000);
                axesXY(this.myConnection.refresh(this.myList.model, (String)null), "Genre", "Nbr");
                this.histogramme(this.myConnection.refresh(this.myList.model, (String)null));

            } catch (SQLException F) {
                System.err.println("34:Error executing query: " + F);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void axesXY(int nbrPersonnes, String labelX, String labelY) {
        int unite = 210 / nbrPersonnes;
        int y = nbrPersonnes * unite;
        g = this.myList.cv.getGraphics();
        g.setColor(Color.black);
        g.drawLine(30, 260, 500, 260);
        g.drawLine(490, 255, 500, 260);
        g.drawLine(490, 265, 500, 260);
        g.drawLine(30, 30, 30, 260);
        g.drawLine(30, 30, 25, 40);
        g.drawLine(30, 30, 35, 40);
        g.setColor(Color.red);
        g.drawString(labelX, 510, 265);
        g.drawString(labelY, 24, 25);
        g.setColor(Color.black);
        g.drawLine(20, 260 - y, 40, 260 - y);
        g.drawString(String.valueOf(nbrPersonnes), 45, 260 - y + 5);
    }

    public void histogramme(int nbrPersonnes) {
        try {
            int unite = 210 / nbrPersonnes;
            int nbrHomme = this.myConnection.refresh(this.myList.model, "homme");
            int nbrFemme = this.myConnection.refresh(this.myList.model, "femme");
            int yh = nbrHomme * unite;
            int yf = nbrFemme * unite;
            g = this.myList.cv.getGraphics();
            g.setColor(Color.pink);
            g.fillRect(200, 260 - yf, 60, yf);
            g.setColor(Color.blue);
            g.fillRect(100, 260 - yh, 60, yh);
            g.drawString("Homme", 110, 278);
            g.drawString("Femme", 210, 278);
            g.setColor(Color.red);
            g.drawString(String.valueOf(nbrHomme), 110, 75);
            g.drawString(String.valueOf(nbrFemme), 210, 75);
        } catch (SQLException G) {
            System.err.println("82: Error executing query: " + G);
        }
    }
}
