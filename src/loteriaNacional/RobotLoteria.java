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
 * @author wizord
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
        WebElement n = driver.findElement(By.id("numero"));
        n.sendKeys(numero + "");
        WebElement e = driver.findElement(By.id("cantidad"));
        // Borramos el contenido por defecto de la cantidad
        e.clear();
        e.sendKeys(dinero + "");
        WebElement div = driver.findElement(By.className("cuerpoRegion"));
        // Como no tenemos ninguna forma de identificar al botón vamos a hacerlo con el cssSelector a mano:
        // etiqueta con value = 'nombre' 
        // La siguiente forma sería usando xpath --> no me ha funcionado
        // driver.findElement(By.xpath("//input[contains(text(), 'COMPROBAR')]")).click();
        driver.findElement(By.cssSelector("input[value='COMPROBAR']")).click();

        // nos aseguraremos de que esta accesible el mensaje de salida 
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("alerta"))));
        System.out.println(driver.findElement(By.className("alerta")).getText());
    }

    public static void main(String[] args)
    {
        RobotLoteria robot = new RobotLoteria();
        robot.comprobar(222, 2);
    }
}
