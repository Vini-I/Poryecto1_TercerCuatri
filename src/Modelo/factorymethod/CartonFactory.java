/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.factorymethod;

import Modelo.Carton;

/**
 *
 * @author rodol
 */
public abstract class CartonFactory {
    public abstract Carton crearCarton(String id) throws Exception;
}
