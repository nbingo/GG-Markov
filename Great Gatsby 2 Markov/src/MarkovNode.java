import java.util.ArrayList;
public class MarkovNode 
{
	private String word;
	private ArrayList<MarkovNode> linkedNodes;
	private ArrayList<Integer> nodeNums;
	
	public MarkovNode(String w)
	{
		word = w;
		linkedNodes = new ArrayList<MarkovNode>();
		nodeNums = new ArrayList<Integer>();
	}
	
	public void addEdge(String word)
	{
		int i = indexOfString(word);
		if (i != -1)									//The edge is already contained, so we just add the number of times it appeared.
			nodeNums.set(i, nodeNums.get(i)+1);
		else											//The edge is not contained, so we have to create
		{
			linkedNodes.add(new MarkovNode(word));
			nodeNums.add(1);
		}
	}
	
	public MarkovNode getRandomNext()
	{
		int sum = sumList(nodeNums);
		double[] probs = new double[nodeNums.size()];
		
		for (int i = 0; i < nodeNums.size(); i++)
			probs[i] = nodeNums.get(i)/(sum+.0);
		
		double rand = Math.random();
		
		MarkovNode current = linkedNodes.get(0);
		double probSum = probs[0];
		for (int i = 0; i < linkedNodes.size() - 1; i++)
		{
			if (rand > probSum)								//Not on the right one yet
			{
				probSum += probs[i+1];
				current = linkedNodes.get(i+1);
			}
			else if (rand <= probSum)						//We made it!
				break;
		}
		return current;
	}
	
	public String getWord()
	{
		return word;
	}
	
	public int sumList(ArrayList<Integer> nums)
	{
		int sum = 0;
		for (int i : nums)
			sum += i;
		return sum;
	}
	
	public String toString()
	{
		return word;
	}
	
	private int indexOfString (String word)
	{
		int index = -1;
		for (int i = 0; i < linkedNodes.size(); i++)
			if (linkedNodes.get(i).getWord().equals(word))
			{
				index = i;
				break;
			}
		return index;
	}
}
