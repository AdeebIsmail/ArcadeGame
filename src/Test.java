public class Test {
    public static void main(String[] args)
    {
        int[][] test = {{1, 2, 3, 4, 5},
                        {6, 7, 8, 9, 10},
                        {11,11,11,11,1},
                     {2,2,2,2,2}};
        for (int i  = 0; i < test.length; i++)
        {
            for (int j = 0; j < test[0].length; j++)
            {
                System.out.println(i + " " + j + " " + test[i][j]);
                try {
                    Thread.sleep(1000);
                } catch(Exception e) {}
            }
        }
    }
}
