public class HanoiTower {

    /*
     *      Killian Callaghan
     *      18332783
     *      Practical 3
     */

    public static void towersOfHanoi(int disk, String source, String dest, String auxiliary) {

        if(disk == 1)
        {
            System.out.println("Move disk 1 (base case) from " + source + " to " + dest);
            return;
        }

        towersOfHanoi(disk - 1, source, auxiliary, dest);
       System.out.println("Move disk " + disk + " from " +  source + " to  " + dest);
       towersOfHanoi(disk - 1, auxiliary, dest, source);

    }



            public static void main(String[] args)
            {
                int n = 2;
                towersOfHanoi(n, "A", "C", "B");
            }

}
