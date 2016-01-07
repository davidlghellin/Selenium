package loteriaNavidad2015;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author David López González
 */
public class RobotNavidad2015
{

    WebDriver driver;
    String url;

    public RobotNavidad2015()
    {
        this.driver = new FirefoxDriver();
        this.url = "http://www.laloterianavidad.com/";
        driver.get(url);
    }

    public void comprobar(int numero, float dinero)
    {
        //<input class="casilla_introduccion" type="text" value="Número" name="pIdNum" id="pIdNum" onkeypress="javascript:iSubmitEnter(event, -1);" onblur="javascript:textoInicialNumero(1,this);" onfocus="textoInicialNumero(2,this);" maxlength="5" tabindex="1">
        WebElement n = driver.findElement(By.id("pIdNum"));
        n.sendKeys(numero + "");
        //<input class="casilla_introduccion" type="text" value="Importe (€)" name="pIdNum2" id="pIdNum2" onkeypress="javascript:iSubmitEnter(event, -1);" onblur="javascript:textoInicialImporte(1,this);" onfocus="textoInicialImporte(2,this);" tabindex="2">
        WebElement e = driver.findElement(By.id("pIdNum2"));
        e.clear();
        e.sendKeys(dinero + "");

        //<input class="boton_comprobar" type="button" value="Comprobar" onclick="javascript:Aceptar();" title="Comprobar lotería" name="Comprobar" tabindex="3">
        driver.findElement(By.name("Comprobar")).click();
        String txtSi = driver.findElement(By.className("loteria_premios")).findElement(By.id("premiado")).getText();
        String txtNo = driver.findElement(By.className("loteria_premios")).findElement(By.id("noPremiado")).getText();
        
        if (txtNo.length() != 0)        // no nos ha tocado nada
            System.out.println(txtNo);
        else if (txtSi.length() != 0)   // nos ha tocado algo de premio   
            System.out.println(txtSi);
        
    }

    public static void main(String[] args)
    {
        RobotNavidad2015 robot = new RobotNavidad2015();
        robot.comprobar(222, 20);
    }
}
