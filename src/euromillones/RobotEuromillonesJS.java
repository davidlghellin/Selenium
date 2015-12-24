/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euromillones;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author David López González
 */
public class RobotEuromillonesJS
{

    private WebDriver driver;
    private JavascriptExecutor js;
    String url;

    public RobotEuromillonesJS()
    {
        driver = (WebDriver) new FirefoxDriver();
        js = (JavascriptExecutor) driver;

        this.url = "http://www.euromillones.com.es/resultados-anteriores.html";
        driver.get(url);
    }

    public void pintarSemana()
    {
        String cargaPag = "$(document).ready( function() {  });";
        js.executeScript(cargaPag);

        // Calculamos el total de sorteos antiguos
        String filas = "return $('#historicoeuromillones').find(\"ul\").size();";
        long nFilas = (long) js.executeScript(filas);
        System.out.println(nFilas + "");

        for (int i = 0; i < nFilas; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                String nCirculos = "return $('#historicoeuromillones').find(\"ul\").eq(" + i + ").find('.eurobola').eq(" + j + ").text();";
                String texto = (String) js.executeScript(nCirculos);
                System.out.print(texto + "\t");
            }
            System.out.print(">>\t");
            for (int j = 0; j < 2; j++)
            {
                String nCirculos = "return $('#historicoeuromillones').find(\"ul\").eq(" + i + ").find('.euroestrella').eq(" + j + ").text();";
                String texto = (String) js.executeScript(nCirculos);
                System.out.print(texto + "\t");
            }
            System.out.println("");
        }
    }

    public int[][] obternerSemana()
    {
        // Calculamos el total de sorteos antiguos
        String filas = "return $('#historicoeuromillones').find(\"ul\").size();";
        long nFilas = (long) js.executeScript(filas);
        int[][] matrix = new int[(int) nFilas][7];

        for (int i = 0; i < nFilas; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                String nCirculos = "return $('#historicoeuromillones').find(\"ul\").eq(" + i + ").find('.eurobola').eq(" + j + ").text();";
                String texto = (String) js.executeScript(nCirculos);
                matrix[i][j] = Integer.parseInt(texto);
            }
            for (int j = 0; j < 2; j++)
            {
                String nCirculos = "return $('#historicoeuromillones').find(\"ul\").eq(" + i + ").find('.euroestrella').eq(" + j + ").text();";
                String texto = (String) js.executeScript(nCirculos);
                matrix[i][5 + j] = Integer.parseInt(texto);
            }
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
        RobotEuromillonesJS robot = new RobotEuromillonesJS();
        //robot.pintarSemana();

        int[][] m = robot.obternerSemana();
        robot.pintar(m);
    }
}
