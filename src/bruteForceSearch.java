// Java program for Naive Pattern Searching
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 *      Killian Callaghan
 *      18332783
 *      Practical 7
 */

public class bruteForceSearch {

    public static void search(String text, String pattern)
    {
        int lengthOfText = text.length();
        int lengthOfPattern = pattern.length();

        for( int i = 0; i <= lengthOfText - lengthOfPattern ; i++)
        {
            int j;
            for( j=0;j<lengthOfPattern;j++){
                if( text.charAt(i+j) != pattern.charAt(j)){
                    break;
                }
            }

            if( j == lengthOfPattern )
                System.out.println("Pattern found at index: " + i);
        }

        //System.out.println("Pattern found at index: " + lengthOfText);

    }


    public static void main(String[] args) throws FileNotFoundException {
        //alter to take text file in..

        File f=new File("C:\\Users\\killi\\OneDrive\\Documents\\College\\Year 2\\Algorithms\\Practical\\Text.txt");
        Scanner in=new Scanner(f);

        String txt = in.nextLine(); //"ABABDABACDABABCABAB";
        String pat = "ABABCABAB";
        search(txt, pat);
        in.close();
    }
}


