import java.util.Hashtable;

/**
 * Creates a Markov chain which can be used for fancy sentence creation stuff.
 * @author Nomi Ringach
 * @since 3.22.16
 * @version 1.0.0
 * @see MarkovNode
 */
public class MarkovGraph 
{
	private Hashtable<String, MarkovNode> nodeTable;
	private static final int DEFAULT_HASHTABLE_SIZE = 1000;
	
	public MarkovGraph(int size)
	{
		nodeTable = new Hashtable<String, MarkovNode>(size);
	}
	
	public MarkovGraph()
	{
		this(DEFAULT_HASHTABLE_SIZE);
	}
	
	public boolean addNode(String word)							
	{
		if (nodeTable.containsKey(word))					// Make sure that we don't have the node already
			return false;
		nodeTable.put(word, new MarkovNode(word));
		return true;
	}
	
	/**
	 * Precondition that the node given by w1 already exists.
	 * @param w1 The string of the first node
	 * @param w2 The string of the node that the arrow will be pointing towards
	 */
	public void addEdge(String w1, String w2)
	{
		nodeTable.get(w1).addEdge(w2);
	}
	
	/**
	 * Only to be used once the Markov Chain has been completed.
	 * @param base The base n characters which chose the node to go off from.
	 * @return The next n characters shifted over 1 for the new character.
	 */
	public String getNextLetter(String base)
	{
		return nodeTable.get(base).getRandomNext().getWord();
	}
	
	public String toString()
	{
		return nodeTable.toString();
	}
}
