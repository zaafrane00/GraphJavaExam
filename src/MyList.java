/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author zaafr
 */
public class MyList extends JFrame {
    MyThread myThread;
    Canvas cv = new Canvas();
    String tab[]={"ID","NOM","GENRE"};
    DefaultTableModel model= new DefaultTableModel(tab,0);
    JTable tab1= new JTable(model);
    JScrollPane sp=new JScrollPane(tab1);


    public MyList() throws SQLException {
        super("Exam");
        myThread = new MyThread(this);
        sp.setBorder(new TitledBorder("List of Users"));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultCloseOperation(3);
        sp.getViewport().add(this.tab1);
        cv.setBackground(Color.white);
        setSize(600, 600);
        Container c = this.getContentPane();
        c.setLayout(new GridLayout(2, 1));
        c.add(this.sp);
        c.add(this.cv);
        setAlwaysOnTop(true);
        setResizable(false);
        setVisible(true);
        myThread.start();

    }
    public static void main(String[] args) throws SQLException {
        new MyList();
    }
}