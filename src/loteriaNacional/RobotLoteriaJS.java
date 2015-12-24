/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteriaNacional;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author wizord
 */
public class RobotLoteriaJS
{

    private WebDriver driver;
    private JavascriptExecutor js;
    String url;

    RobotLoteriaJS()
    {
        driver = (WebDriver) new FirefoxDriver();
        js = (JavascriptExecutor) driver;

        this.url = "http://www.loteriasyapuestas.es/es/loteria-nacional";
        driver.get(url);
    }

    public void comprobar(int numero, float dinero)
    {
        String cargaPag = "$(document).ready( function() {  });";
        js.executeScript(cargaPag);
        String num = "$('#numero')[0].value = '" + numero + "';";
        js.executeScript(num);

        String e = "$('#cantidad')[0].value = '" + dinero + "';";
        js.executeScript(e);

        String pulsarBtn = "$(\"input[value='COMPROBAR']\").click()";
        js.executeScript(pulsarBtn);

        String text = "return jQuery('.alerta').find('p:first').text();";
        String salida = (String) js.executeScript(text);
        System.out.println(salida);
    }

    public static void main(String[] args)
    {
        RobotLoteriaJS robot = new RobotLoteriaJS();
        robot.comprobar(222, 2);
    }
}
