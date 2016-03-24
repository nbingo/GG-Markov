import java.io.*;
import java.util.Hashtable;

public class MarkovChainHandler
{
	private BufferedReader read;
	private MarkovChain mChain;
	
	private Hashtable<String, Integer> wordAppearances;
	private static final int DEFAULT_HASHTABLE_SIZE = 270_585;
	
	private String base;
	
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
		base = previous;
		addAppearance(previous);
		mChain.addNode(previous);
		
		while(read.ready())
		{
			String current = previous.substring(1) + (char)read.read();			// Shift the string over 1
			mChain.addEdge(previous, current);									// Add the edge from the old string to the new one
			addAppearance(current);												// Add an appearance of that ward to the table
			base = chooseBetweenTwo(base, current);							    // Set the base to the most common of the base and the new word
			previous = current;												    // The current word is now the previous word
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
	
	private void addAppearance(String word)
	{
		if (wordAppearances.containsKey(word))
		{
			int i = wordAppearances.get(word);
			wordAppearances.replace(word, i+1);
		}
		else
			wordAppearances.put(word, 1);
	}
	
	private String chooseBetweenTwo(String s1, String s2)
	{
		if (wordAppearances.get(s1) > wordAppearances.get(s2))
			return s1;
		else if (wordAppearances.get(s1) > wordAppearances.get(s2))
			return s2;
		else																//Choose randomly between the two words since they appear the same number of times.
			if (Math.random() < 0.5)
				return s1;
			else
				return s2;
	}
}
