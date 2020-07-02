/*
 *      Killian Callaghan
 *      18332783
 *      Practical 7
 */

public class RunLengthEncoding {


    // Perform Run Length Encoding (RLE) data compression algorithm
    // on String str
    public static String RLE(String str)
    {
        // stores output String
        String result = "";
        int count;

        for (int i = 0; i < str.length(); i++)
        {
            // count occurrences of character at index i
            count = 1;
            while (i + 1 < str.length() && str.charAt(i) == str.charAt(i+1))
            {
                count++;
                i++;
            }

            // append current character and its count to the result
            result += str.charAt(i) + String.valueOf(count);
        }

        return result;
    }

        public static void main(String[] args)
        {
            String s = "wwwwaaadexxxxxxywww";
            System.out.println("String before RLE compression: " + s);
            String compressed = RLE(s);
            System.out.println("String after RLE compression: " + compressed);
        }

}
