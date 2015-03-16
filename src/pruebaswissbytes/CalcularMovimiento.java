/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaswissbytes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pedro
 */
public class CalcularMovimiento {

    List<String> listaruta = new ArrayList<>();
    //EL PUNTO A ES DONDE SE ENCUENTRA EL VEHICULO
    Punto a = new Punto();
    //EL PUNTO B ES HACIA DONDE ESTA APUNTANDO EL VEHICULO
    Punto b = new Punto();
    //bs, bn,be,bo son los 4 puntos alrededor del punto A
    Punto bs = new Punto();
    Punto bn = new Punto();
    Punto be = new Punto();
    Punto bo = new Punto();

    String direccion = "";

    public String calcular(String coordenadas, String posicion, String ruta) {
        nuevo();
        String delims = "[ ]";
        String[] tokens = coordenadas.split(delims);
        int x = 0;
        int y = 0;
        //OBTENGO LOS VALORES X Y, PARA LA SUPERFICIE
        for (int i = 0; i < tokens.length; i++) {
            if (i == 0) {
                x = Integer.valueOf(tokens[i]);
            } else {
                y = Integer.valueOf(tokens[i]);
            }
        }
        tokens = posicion.split(delims);
        a = new Punto();
        //OBTENGO LA POSICION DEL VEHICULO
        for (int i = 0; i < tokens.length; i++) {
            if (i == 0) {
                a.setX(Integer.valueOf(tokens[i]));
            }
            if (i == 1) {
                a.setY(Integer.valueOf(tokens[i]));
            }
            if (i == 2) {
                direccion = tokens[i];
            }
        }
        //EXPRESION REGULAR PARA VALIDAR EL SENTIDO DEL VEHICULO
        if (!direccion.matches("^[NSEO]*$")) {
            return "-1";
        }
        calcularB(a);
        listarRuta(ruta);
        
        
        boolean sw = false;
        //recorro mi ruta
        for (int i = 0; i < listaruta.size(); i++) {
            System.out.println(listaruta.get(i));
            if (direccion.equalsIgnoreCase("N") && listaruta.get(i).equalsIgnoreCase("L") && !sw) {
                b.setX(bo.getX());
                b.setY(bo.getY());
                direccion = "O";
                sw = true;
            }
            if (direccion.equalsIgnoreCase("N") && listaruta.get(i).equalsIgnoreCase("R") && !sw) {
                b.setX(be.getX());
                b.setY(be.getY());
                direccion = "E";
                sw = true;
            }
            if (direccion.equalsIgnoreCase("S") && listaruta.get(i).equalsIgnoreCase("L") && !sw) {
                b.setX(be.getX());
                b.setY(be.getY());
                direccion = "E";
                sw = true;
            }
            if (direccion.equalsIgnoreCase("S") && listaruta.get(i).equalsIgnoreCase("R") && !sw) {
                b.setX(bo.getX());
                b.setY(bo.getY());
                direccion = "O";
                sw = true;
            }
            if (direccion.equalsIgnoreCase("E") && listaruta.get(i).equalsIgnoreCase("L") && !sw) {
                b.setX(bn.getX());
                b.setY(bn.getY());
                direccion = "N";
                sw = true;
            }
            if (direccion.equalsIgnoreCase("E") && listaruta.get(i).equalsIgnoreCase("R") && !sw) {
                b.setX(bs.getX());
                b.setY(bs.getY());
                direccion = "S";
                sw = true;
            }
            if (direccion.equalsIgnoreCase("O") && listaruta.get(i).equalsIgnoreCase("L") && !sw) {
                b.setX(bs.getX());
                b.setY(bs.getY());
                direccion = "S";
                sw = true;
            }
            if (direccion.equalsIgnoreCase("O") && listaruta.get(i).equalsIgnoreCase("R") && !sw) {
                b.setX(bn.getX());
                b.setY(bn.getY());
                direccion = "N";
                sw = true;
            }
            
            
            if (listaruta.get(i).equalsIgnoreCase("M")) {
                if (i == 0) {
                    if (direccion.equalsIgnoreCase("N")) {
                        b.setX(bn.getX());
                        b.setY(bn.getY());
                    }
                    if (direccion.equalsIgnoreCase("S")) {
                        b.setX(bs.getX());
                        b.setY(bs.getY());
                    }
                    if (direccion.equalsIgnoreCase("E")) {
                        b.setX(be.getX());
                        b.setY(be.getY());
                    }
                    if (direccion.equalsIgnoreCase("O")) {
                        b.setX(bo.getX());
                        b.setY(bo.getY());
                    }
                }
                sw = false;
                a.setX(b.getX());
                a.setY(b.getY());
                calcularB(a);
                if (direccion.equalsIgnoreCase("N")) {
                    b.setX(bn.getX());
                    b.setY(bn.getY());
                }
                if (direccion.equalsIgnoreCase("S")) {
                    b.setX(bs.getX());
                    b.setY(bs.getY());
                }
                if (direccion.equalsIgnoreCase("E")) {
                    b.setX(be.getX());
                    b.setY(be.getY());
                }
                if (direccion.equalsIgnoreCase("O")) {
                    b.setX(bo.getX());
                    b.setY(bo.getY());
                }

            }
            System.out.println("a:(" + a.getX() + "," + a.getY() + ") D:" + direccion);
            System.out.println("b:(" + b.getX() + "," + b.getY() + ") D:" + direccion);
            sw = false;
        }
        
        if(a.getX()>x||a.getY()>y||a.getX()<0||a.getY()<0){
            return "el punto final es:"+a.getX()+" "+a.getY()+" "+direccion+", el vehiculo se sale de la superficie";
        }
        
        return "el punto final es: "+a.getX() + " " + a.getY() + " " + direccion;

    }

    public void calcularB(Punto a) {
        //METODO PARA OBTENER LOS CUATRO PUNTOS ALREDEDOR DEL PUNTO A
        //PARA SABER LOS POSIBLES MOVIMIENTOS DEL VEHICULO
        System.out.println("calculo nuevo a:(" + a.getX() + "," + a.getY() + ") D:" + direccion);
        bn.setX(a.getX());
        bn.setY(a.getY() + 1);

        bs.setX(a.getX());
        bs.setY(a.getY() - 1);

        be.setX(a.getX() + 1);
        be.setY(a.getY());

        bo.setX(a.getX() - 1);
        bo.setY(a.getY());
        System.out.println("bn:(" + bn.getX() + "," + bn.getY() + ")");
        System.out.println("bs:(" + bs.getX() + "," + bs.getY() + ")");
        System.out.println("be:(" + be.getX() + "," + be.getY() + ")");
        System.out.println("bo:(" + bo.getX() + "," + bo.getY() + ")");
    }

    public void listarRuta(String s) {
        //METO TODOS LOS CARACTERES DE LA RUTA EN UNA LISTA DE STRING
        for (int i = 0; i < s.length(); i++) {
            listaruta.add(s.charAt(i) + "");
        }
    }

    public void nuevo() {
        //INICIALIZO LOS PUNTOS
        listaruta = new ArrayList<>();
        bn = new Punto();
        bs = new Punto();
        be = new Punto();
        bo = new Punto();
    }
}
