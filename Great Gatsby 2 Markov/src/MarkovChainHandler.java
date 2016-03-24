import java.io.*;
import java.util.Hashtable;

public class MarkovChainHandler
{
	private BufferedReader read;
	private MarkovChain mChain;
	
	private Hashtable<String, Integer> wordAppearances;
	private static final int DEFAULT_HASHTABLE_SIZE = 270585;
	/**
	 * Creates and initializes a Markov chain with the specified order based off of the given file.
	 * @param fileName The name of the file that the Markov chain will be based off of
	 * @param order	The order of the Markov chain
	 * @throws FileNotFoundException If the file with the name fileName is not found
	 * @throws IOException If there's some other type of exception
	 */
	public MarkovChainHandler(String fileName, int order) throws FileNotFoundException, IOException 
	{
		read = new BufferedReader(new FileReader(fileName));
		mChain = new MarkovChain();
		wordAppearances = new Hashtable<String, Integer>(DEFAULT_HASHTABLE_SIZE);
		
		String previous = firstNChars(order);
		
		while(true)
		{
			int letter = read.read();
			if (letter == -1)									//There's no more to read
				break;
			String current = previous.substring(1) + (char)letter;
			
		}
		
		read.close();
	}

	private String firstNChars(int n) throws IOException
	{
		String first = "";
		for (int i = 0; i < n; i++)
			first += read.read();
		return first;
	}
}
