package com.example.a2dam.proyectoquicktrade.model;

/**
 * Created by 2dam on 15/12/2017.
 */

public class Discos {

    private String titulo;
    private String artista;
    private String anyo;

    public Discos(String titulo, String artista, String anyo) {
        this.titulo = titulo;
        this.artista = artista;
        this.anyo = anyo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAnyo() {
        return anyo;
    }

    public void setAnyo(String anyo) {
        this.anyo = anyo;
    }

    @Override
    public String toString() {
        return "Discos{" +
                "titulo='" + titulo + '\'' +
                ", artista='" + artista + '\'' +
                ", anyo='" + anyo + '\'' +
                '}';
    }
}
