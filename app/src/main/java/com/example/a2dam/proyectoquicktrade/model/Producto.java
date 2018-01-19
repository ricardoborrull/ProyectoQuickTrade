package com.example.a2dam.proyectoquicktrade.model;

/**
 * Created by 2dam on 15/12/2017.
 */

public class Producto {

    private String nombre;
    private String descripcion;
    private String categoria;
    private String precio;
    private String user;
    private String key;

    public Producto(){

    }

    public Producto(String nombre, String descripcion, String categoria, String precio, String user, String key) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.user = user;
        this.key = key;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombrep='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precio='" + precio + '\'' +
                ", userp='" + user + '\'' +
                ", key='" + key + '\'' +
                '}';
    }


}
