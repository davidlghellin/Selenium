package robot;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Robot que imprime por pantalla los últimos sorteos que hay en la web de
 * Euromillones
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

    public void pintarSemana()
    {
        WebElement div = driver.findElement(By.id("historicoeuromillones"));
        System.out.println(div.getTagName());
        System.out.println(div.getLocation());

        // Buscamos todas las filas 
        List<WebElement> filas = div.findElements(By.tagName("ul"));
        System.out.println("Sorteos que comprobaremos:  " + filas.size());
        System.out.println("---------------------------------");

        // Para cada fila, que contiene los números que han salido extraeremos los 
        // datos correspondientes
        for (WebElement f : filas)
        {
            // Números que contienen los circulos
            List<WebElement> circulos = f.findElements(By.className("eurobola"));
            for (WebElement n : circulos)
            {
                System.out.println("Circulos  :\t" + n.getText());
            }
            // Números que contienen las estrellas
            List<WebElement> estrellas = f.findElements(By.className("euroestrella"));
            for (WebElement n : estrellas)
            {
                System.out.println("Estrellas :\t" + n.getText());
            }
            System.out.println("---------------------------------");
        }
    }

    public int[][] obternerSemana()
    {
        WebElement div = driver.findElement(By.id("historicoeuromillones"));
        System.out.println(div.getTagName());
        System.out.println(div.getLocation());

        // Buscamos todas las filas 
        List<WebElement> filas = div.findElements(By.tagName("ul"));

        int[][] matrix = new int[filas.size()][7];
        // Para cada fila, que contiene los números que han salido extraeremos los 
        // datos correspondientes
        int i = 0;
        int j = 0;
        for (WebElement f : filas)
        {
            // Números que contienen los circulos
            List<WebElement> circulos = f.findElements(By.className("eurobola"));
            for (WebElement n : circulos)
            {
                matrix[i][j++] = Integer.parseInt(n.getText());
            }
            // Números que contienen las estrellas
            List<WebElement> estrellas = f.findElements(By.className("euroestrella"));
            for (WebElement n : estrellas)
            {
                matrix[i][j++] = Integer.parseInt(n.getText());
            }
            i++;
            j = 0;
        }
        return matrix;
    }
}
