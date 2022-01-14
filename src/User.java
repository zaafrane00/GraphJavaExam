/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zaafr
 */
public class User {
    private int id;
    private String nom;
    private String genre;

    public User(int id, String nom, String genre) {
        this.id = id;
        this.nom = nom;
        this.genre = genre;
    }

    public User(User p) {
        this.id = p.id;
        this.nom = p.nom;
        this.genre = p.genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    String getClassName(){
        return this.getClass().getSimpleName();
    }
}
