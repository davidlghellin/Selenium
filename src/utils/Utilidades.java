/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 *
 * @author David López González
 */
public class Utilidades
{

    /**
     * Este método obtiene el padre del WebElement que lo llamó
     * @param nodo
     * @return 
     */
    public static WebElement obtenerPadre(WebElement nodo)
    {
        // System.out.println((utils.Utilidades.obtenerPadre(nodo)).getTagName()); 
        // así sabriamos el nombre del elemento que lo contiene
        return nodo.findElement(By.xpath(".."));
    }
}
