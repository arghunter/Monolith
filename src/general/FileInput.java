//Author: Armaan Gomes 
//Date:5/8/22
//Rev: 01
//Notes: Model of a scanner class, but for file input and with capabilities of file output because it is a PrintWriter
package general;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FileInput {
	//Fields
	public BufferedReader reader;
	public StringTokenizer tokens;


    //Constructor
	public FileInput(String inputFileName) throws FileNotFoundException {
		
		reader = new BufferedReader(new FileReader(inputFileName));

	}
	//Returns the next string in the file
	public String next() {
		while (tokens == null || !tokens.hasMoreTokens()) {
			try {
				tokens = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				System.out.println("BIGPROBLEM");
				throw new RuntimeException(e);
			}
		}
		return tokens.nextToken();
	}


}
 