/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Exceptions.NumeroInvalidoExcepcion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author rodol
 */
public class Tombola {

    private static Tombola instancia;
    private ArrayList<Integer> numerosDisponibles;
    private ArrayList<Integer> numerosExtraidos;
    private Random random;
    private int ultimoNumeroExtraido;

    public ArrayList<Integer> getNumerosDisponibles() {
        return numerosDisponibles;
    }

    public ArrayList<Integer> getNumerosExtraidos() {
        return numerosExtraidos;
    }

    public int getUltimoNumeroExtraido() {
        return ultimoNumeroExtraido;
    }

    private Tombola() {
        this.numerosDisponibles = new ArrayList();
        this.numerosExtraidos = new ArrayList();
        this.random = new Random();
        this.ultimoNumeroExtraido = -1;
        inicializarNumeros();
    }

    public static Tombola getInstancia() {
        if (instancia == null) {
            instancia = new Tombola();
        }
        return instancia;
    }
    
    private void inicializarNumeros() {
        numerosDisponibles.clear();
        for (int i = 1; i <= 75; i++) {
            numerosDisponibles.add(i);
        }
        Collections.shuffle(numerosDisponibles, random);
    }
    
    public int extraerNumeroAleatorio() {
        if (numerosDisponibles.isEmpty()) {
            return -1;
        }
        
        int numero = numerosDisponibles.remove(0);
        numerosExtraidos.add(numero);
        ultimoNumeroExtraido = numero;
        return numero;
    }
    
    public boolean extraerNumeroManual(int numero) throws NumeroInvalidoExcepcion {
        if (numero < 1 || numero > 75) {
            throw new NumeroInvalidoExcepcion();
        }

        if (numerosExtraidos.contains(numero)) {
            throw new IllegalStateException("El número " + numero + " ya fue extraído");
        }

        numerosDisponibles.remove(Integer.valueOf(numero));
        numerosExtraidos.add(numero);
        ultimoNumeroExtraido = numero;
        return true;
    }

    public void reiniciar() {
        numerosExtraidos.clear();
        ultimoNumeroExtraido = -1;
        inicializarNumeros();
    }
    
    public int getCantidadDisponibles() {
        return numerosDisponibles.size();
    }

     public int getCantidadExtraidos() {
        return numerosExtraidos.size();
    }
     
     public boolean hayNumerosDisponibles() {
        return !numerosDisponibles.isEmpty();
    }
     
}
