/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteriaNacional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author David López González
 */
public class RobotLoteria
{

    WebDriver driver;
    String url;

    public RobotLoteria()
    {
        this.driver = new FirefoxDriver();
        this.url = "http://www.loteriasyapuestas.es/es/loteria-nacional";
        driver.get(url);
    }

    public void comprobar(int numero, float dinero)
    {
        // Introducimos el número y la cantidad de dinero en sus respectivos campos
        WebElement n = driver.findElement(By.id("numero"));
        n.sendKeys(numero + "");
        WebElement e = driver.findElement(By.id("cantidad"));
        // Borramos el contenido por defecto de la cantidad
        e.clear();
        e.sendKeys(dinero + "");
        
        // Nos posicionamos, para que todo funcione correcto
        // Si lo quito --> falla
        driver.findElement(By.className("cuerpoRegion"));
        
        // Como no tenemos ninguna forma de identificar al botón vamos a hacerlo con el cssSelector a mano:
        // etiqueta con value = 'nombre' 
        // La siguiente forma sería usando xpath --> no me ha funcionado
        // driver.findElement(By.xpath("//input[contains(text(), 'COMPROBAR')]")).click();
        driver.findElement(By.cssSelector("input[value='COMPROBAR']")).click();

        // nos aseguraremos de que esta accesible el mensaje de salida 
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("alerta"))));
        System.out.println(driver.findElement(By.className("alerta")).findElement(By.tagName("p")).getText());
    }

    public static void main(String[] args)
    {
        RobotLoteria robot = new RobotLoteria();
        robot.comprobar(222, 2);
    }
}
