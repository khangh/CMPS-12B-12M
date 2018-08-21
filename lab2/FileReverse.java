//------------------------------------------------------------------------------
// FileReverse.java
// Combines FileTokens.java and reverseArray1 code in Reverse.java
//------------------------------------------------------------------------------
import java.io.*;
import java.util.Scanner;

class FileReverse {
    // Define return string so it can be used by main() and stringReverse()
    public static String returnString = "";

    public static void main(String[] args) throws IOException {
        // check number of command line arguments is at least 2
        if (args.length < 2) {
            System.out.println("Usage: FileCopy <input file> <output file>");
            System.out.println();
            System.exit(1);
        }

        // open files
        Scanner in = new Scanner(new File(args[0]));
        PrintWriter out = new PrintWriter(new FileWriter(args[1]));

        // read lines from in, extract and print tokens from each line
        while (in.hasNextLine()) {
            // trim leading and trailing spaces, then add one trailing space so
            // split works on blank lines
            String line = in.nextLine().trim() + " ";

            // split line around white space
            String[] token = line.split("\\s+");

            int n = token.length;

            for (int i = 0; i < n; i++) {
                // initialize returnString used by stringReverse() method here
                // before the method is invoked
                // can't do inside stringReverse() as it will not work properly
                returnString = "";

                // token[i] is a string
                String S = stringReverse(token[i], token[i].length());

                // write return string into out file
                out.println(S);
                System.out.println();
            }
        }
        // close files
        in.close();
        out.close();
    }

    // Reverse method - reverseArray1 Version
    // prints out the reversal of the leftmost n elements in s
    public static String stringReverse(String s, int n) {
        // if n==0 do nothing
        if (n > 0) {
            // converts string s to Array A
            String[] A = s.split("");

            // concatenates each reversed string char into returnString
            returnString = returnString.concat(A[n-1]);
            System.out.print(A[n-1]); // print nth element from left
            stringReverse(s, n - 1);
        }
        return returnString;
    }
}
