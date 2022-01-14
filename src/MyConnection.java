/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zaafr
 */
public class MyConnection {
    ResultSet res;
    Statement stm=null;
    private Connection connection;
    private String serverURI="com.mysql.cj.jdbc.Driver";

    public MyConnection() {
        connexion();
    }

    public void connexion() {
        try {
            Class.forName(serverURI);
            String password = System.getProperty("database.password");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/exam", "root", password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void createTable(String tableName) throws SQLException {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS "+tableName+" ("
                + "id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                + "nom VARCHAR(50) NULL DEFAULT NULL,"
                + "genre VARCHAR(50) NULL DEFAULT NULL,"
                + "PRIMARY KEY (id),"
                + "UNIQUE INDEX id (id)"
                + ")"
                + "COLLATE='latin1_swedish_ci';";

        Statement stm = connection.createStatement();
        stm.execute(sqlCreate);
    }


    void addUser( User personne) throws SQLException {
        String table="users";
        createTable(table);
        stm=connection.createStatement();
        String req="";
        req="insert into "+table+"(nom,genre)values('"+personne.getNom()+"','"+personne.getGenre()+"')";
        System.out.println(req);
        stm.executeUpdate(req);
    }


    void updateUser( User personne, String table) throws SQLException {
        stm=connection.createStatement();
        String req="";
        req="update "+table+" set nom= "+personne.getNom()+",adress="+personne.getGenre()+"where id="+personne.getId();
        stm.executeUpdate(req);
    }

    void deleteUser( User personne, String table) throws SQLException {
        stm=connection.createStatement();
        String req="";
        req="delete from "+table+"where id="+personne.getId();
        stm.executeUpdate(req);
    }

    public int addUser(String nom, String genre) throws SQLException {
        String table="users";
        createTable(table);
        stm=connection.createStatement();
        try {
            int resUpd = stm.executeUpdate("INSERT INTO "+table+" (nom, genre) VALUES ('" + nom + "','" + genre + "')");
            return resUpd;
        } catch (SQLException e) {
            System.err.println("89:Error executing query: " + e);
            return 0;
        }
    }


    void fillTable(DefaultTableModel model) throws SQLException {
        String table="users";
        createTable(table);
        stm=connection.createStatement();
        try {
            res = stm.executeQuery("SELECT * FROM "+table);
            Object[] line = new Object[model.getColumnCount()];

            while(res.next()) {
                for(int i = 0; i < line.length; ++i) {
                    line[i] = res.getString(i + 1);
                }

                model.addRow(line);
            }
        } catch (SQLException E) {
            System.err.println("111:Error executing query: " + E);
        }

    }




    public int refresh(DefaultTableModel model, String genre) throws SQLException {
        String table="users";
        String req;
        int i;
        if (genre != null) {
            i = 0;
            req = "select * from "+table+" where genre ='" + genre +"'";
            model.setRowCount(0);
            for(this.res = stm.executeQuery(req); this.res.next(); ++i) {
            }
        } else {
            i = 0;
            //req = "select * from"+table;
            req = "select * from " + table;
            model.setRowCount(0);
            for(this.res = stm.executeQuery(req); this.res.next(); ++i) {
            }
        }

        return i;
    }


}