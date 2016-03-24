import java.io.*;
import java.util.Scanner;

public class GatsbyTester {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		
		Scanner k = new Scanner (System.in);
		
		System.out.println("File to read from? (include extension)");
		String inputName = k.nextLine();
		
		System.out.println("Markov chain order?");
		int order = k.nextInt();
		
		MarkovChainHandler chain = new MarkovChainHandler(inputName, order);
		
		System.out.println("File to output to?");
		
		String outputName = k.nextLine();
		
		System.out.println("Number of characters to write?");
		int writingLength = k.nextInt();
		
		k.close();
	}

}
