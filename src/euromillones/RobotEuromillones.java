package euromillones;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Robot obtiene datos de la página del sorteo de los Euromillones
 *
 * @author David López González
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
        
        //Euromillones - Sorteo 1 del  1 de enero de 2016
        String text="Euromillones ";
        JavascriptExecutor  js = (JavascriptExecutor) driver;
      //  String prueba = "return jQuery('#historicoeuromillones').find('h3:contains(\"Euromillones - Sorteo 2 del 5 de ene\")').text()";
        String prueba= "return jQuery('#historicoeuromillones').find('h3:contains(\"Euro\")').text()";
 
        String a=(String)js.executeScript(prueba); 
        System.out.println(a);
 
      //  WebElement obt=(WebElement)js.executeScript(prueba); 
        //System.out.println(obt);
        //System.out.println(obt.getTagName());
       // System.out.println(obt.getLocation());
        ///
        
        
List<WebElement> divprue = driver.findElements(By.xpath("//li[contains(text(), \"' + 6 + '\")]'"));
        System.out.println("tamaño"+divprue.size());
        for (WebElement w : divprue)
        {
            System.out.println(w.getText());
        }

        
        ////
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

    public void pintar(int[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[1].length; j++)
            {
                if (j < 5)
                {
                    System.out.print("Circulos:  ");
                }
                else
                {
                    System.out.print("Estrellas: ");
                }
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println("");
        }
    }
    
    public static void main(String[] args)
    {
        RobotEuromillones robot = new RobotEuromillones();
        robot.ir();
        robot.pintarSemana();
        int[][] m = robot.obternerSemana();
        robot.pintar(m);
    }
}
