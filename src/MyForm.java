/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 *
 * @author zaafr
 */
public class MyForm extends JFrame  {

    public MyEvents evt = new MyEvents(this);
    public JLabel examen=new JLabel("Exam");
    public JLabel infoL=new JLabel("Informations");
    public JLabel nomL=new JLabel("NAME");
    public JLabel genreL=new JLabel("GENRE");
    public ButtonGroup bg= new ButtonGroup();
    public JRadioButton op1= new JRadioButton ("HOMME",true);
    public JRadioButton op2= new JRadioButton ("FEMME");


    public JTextField nomT=new JTextField();

    public JButton liste=new JButton("List");
    public JButton ajouter=new JButton("Add");

    public MyForm() throws SQLException {
        super("EXAM");
        JPanel Poption= new JPanel(new GridLayout(1,2));
        Poption.add(op1);
        Poption.add(op2);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.add(this.infoL);
        panel.add(new JLabel());
        panel.add(this.nomL);
        panel.add(this.nomT);
        panel.add(this.genreL);
        panel.add(Poption);


        JPanel pbtn = new JPanel();
        pbtn.setLayout(new FlowLayout(2));
        pbtn.add(this.liste);
        pbtn.add(this.ajouter);


        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());
        c.add("North", this.examen);
        c.add("Center", panel);
        c.add("South", pbtn);

        examen.setHorizontalAlignment(0);
        bg.add(this.op1);
        bg.add(this.op2);
        op1.setActionCommand("Homme");
        op2.setActionCommand("Femme");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        show();

        liste.addActionListener((e)-> {
            try {
                Resultat();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        ajouter.addActionListener(this.evt);

    }

    private void Resultat() throws SQLException {
        new MyList();
    }
    public static void main(String[] args) throws SQLException {
        new MyForm();
    }

}
