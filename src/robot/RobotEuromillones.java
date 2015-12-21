package robot;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author wizord
 */
public class RobotEuromillones
{

    WebDriver driver;
    String url;

    public RobotEuromillones()
    {
        this.driver = new FirefoxDriver();
        this.url = "http://www.euromillones.com.es/resultados-anteriores.html";
    }

    public boolean ir()
    {
        try
        {
            driver.get(url);
        } catch (Exception e)
        {
            return false;
        }
        return false;
    }

    public void obternerSemana()
    {
        WebElement div = driver.findElement(By.id("historicoeuromillones"));
        System.out.println(div.getTagName());
        System.out.println(div.getLocation());

        // buscamos todas las filas 
        List<WebElement> filas = div.findElements(By.tagName("ul"));
        System.out.println("Sorteos que comprobaremos:  " + filas.size());
        System.out.println("---------------------------------");

        for (WebElement f : filas)
        {
            List<WebElement> circulos = f.findElements(By.className("eurobola"));
            for (WebElement n : circulos)
            {
                System.out.println("Circulos  :\t" + n.getText());
            }
            List<WebElement> estrellas = f.findElements(By.className("euroestrella"));
            for (WebElement n : estrellas)
            {
                System.out.println("Estrellas :\t" + n.getText());
            }
            System.out.println("---------------------------------");
        }
    }
}
