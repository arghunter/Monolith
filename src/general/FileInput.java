//Author: Armaan Gomes
//Date:
//Rev: 00
//Notes: Model of a scanner class, but for file input and with capabilities of file output because it is a PrintWriter
package general;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FileInput {
	public BufferedReader reader;
	public StringTokenizer tokens;

	public FileInput(InputStream in) {
		
		reader = new BufferedReader(new InputStreamReader(in));

	}

	public FileInput() {
		
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	public FileInput(String inputFileName) throws FileNotFoundException {
		
		reader = new BufferedReader(new FileReader(inputFileName));

	}

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
