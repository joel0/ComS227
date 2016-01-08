
package examples;

import mini3.ConwayTransform;
import mini3.GridUtil;
import mini3.ITransform;

/**
 * Console-based animation of Conway's Game of Life.
 */
public class LifeTest
{
  public static void main(String[] args) throws InterruptedException
  {
    // blinker
    int[][] test1 = {
        {0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0},
    };
    
    // glider
    int[][] test2 = {
        {1, 0, 1, 0, 0, 0, 0},
        {0, 1, 1, 0, 0, 0, 0},
        {0, 1, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
    };

    ITransform t = new ConwayTransform();
    int[][] data = test1;
    
    while (true)
    {
      printZeroOneValuedArray(data);
      System.out.println();
      Thread.sleep(500);
      data = GridUtil.applyAll(data, t);
    }
  }

  
  /**
   * Displays the contents of a 2d array
   * @param arr
   */
  public static void printArray(int[][] arr)
  {
    for (int row = 0; row < arr.length; row += 1)
    {
      for (int col = 0; col < arr[0].length; col += 1)
      {
        System.out.printf("%3d", arr[row][col]);
      }
      System.out.println();
    }
  }
  
  /**
   * Prints the contents of a 2d array containing only zeros
   * and ones using the characters '-' and '*'
   * @param arr
   */
  public static void printZeroOneValuedArray(int[][] arr)
  {
    char[] c= {'-', '*'};
    
    for (int row = 0; row < arr.length; row += 1)
    {
      for (int col = 0; col < arr[0].length; col += 1)
      {
        System.out.printf("%2s", c[arr[row][col]]);
      }
      System.out.println();
    }
  }
}
