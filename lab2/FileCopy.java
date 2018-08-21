// FileCopy.java
// Illustrates file IO

import java.io.*;
import java.util.Scanner;

class FileCopy{

	public static void main(String[] args) throws IOException{

		// check number of command line arguments is at least 2
		if(args.length < 2){
			System.out.println("Usage: FileCopy <input file> <output file>");
			System.exit(1);
		}

		// open files
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));

		// read lines from in, write lines to out
		while( in.hasNextLine() ){
			String line = in.nextLine();
			out.println( line );
		}

		// close files
		in.close();
		out.close();

	}

}
