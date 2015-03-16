/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaswissbytes;

/**
 *
 * @author pedro
 */
public class Punto {
    private int x;
    private int y;
    private String direccion;
    
    public Punto() {
    }

    
    
    public Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
}
