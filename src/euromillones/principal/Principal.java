package euromillones.principal;

import euromillones.robot.RobotEuromillones;

/**
 *
 * @author wizord
 */
public class Principal
{

    public static void main(String[] args)
    {
        RobotEuromillones robot = new RobotEuromillones();
        robot.ir();
        int[][] m = robot.obternerSemana();
        for (int i = 0; i < m.length; i++)
        {
            for (int j = 0; j < m[1].length; j++)
            {
                if(j<5)
                    System.out.print("Circulos:  ");
                else 
                    System.out.print  ("Estrellas: ");
                System.out.print(m[i][j] +"\t");
            }
        }
    }
}
