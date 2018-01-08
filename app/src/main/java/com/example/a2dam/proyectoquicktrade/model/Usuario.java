package com.example.a2dam.proyectoquicktrade.model;

import java.util.ArrayList;

/**
 * Created by 2dam on 18/12/2017.
 */

public class Usuario {

    private String user;
    private ArrayList <Producto> productos;
    private String nombre;
    private String apellidos;
    private String correo;
    private String direccion;
    private String password;

    public Usuario(){

    }

    public Usuario(String user, ArrayList<Producto> productos, String nombre, String apellidos, String correo, String direccion, String password) {
        this.user = user;
        this.productos = productos;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.direccion = direccion;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "user='" + user + '\'' +
                ", productos=" + productos +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
