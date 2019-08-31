import java.util.LinkedList;
import java.lang.Math;

public class RabinKarp {
	private String mainText;
	private int prime;
	private int patternHash;
	private int patternLen;
	private int lastMainHash;
	
	RabinKarp(String txt)
	{
		mainText = txt;
		prime = 3;
		patternHash = -1;
		patternLen = 0;
		lastMainHash = 0;
	}
	
	public void setText(String text)
	{
		mainText = text;
	}
	
	public String getText()
	{
		return mainText;
	}
	
	// middle hash
	private int hash(int index, int val)
	{
		int lastHead = (int)mainText.charAt(index-1);
		int newHead = (int)mainText.charAt(index+(patternLen-1));
		
		return ((val-lastHead)/prime) + (newHead * (int)Math.pow(prime, patternLen-1));
	}
	
	// full hash
	private int hash(String str)
	{
		int sum = 0;
		if(str == null)
			str = mainText;

		for (int i=0; i<patternLen; i++)
			sum += ( (int)str.charAt(i) * Math.pow(prime, i) );
		
		return sum;
	}
	
	public LinkedList<Integer> search(String pattern, boolean once)
	{
		LinkedList<Integer> l = new LinkedList<Integer>();
		patternLen = pattern.length();
		patternHash = hash(pattern);
		
		for(int i=0; i<mainText.length(); i++)
		{
			if(match(i, pattern))
			{
				l.add(i);
				if ( once )
					return l;
				else
					i += pattern.length()-1;
			}
		}
		return l;
	}
	
	private boolean match(int index, String pattern)
	{
		if ( index+pattern.length() > mainText.length())
			return false;
		
		if (index == 0)
			lastMainHash = hash(null);
		else
			lastMainHash = hash(index, lastMainHash);

		if(lastMainHash == patternHash)
		{
			for(int i = index, j=0; i<index+pattern.length(); i++)
			{
				if (mainText.charAt(i) !=  pattern.charAt(j))
					return false;
				j++;
			}
			return true;
		}
		else
			return false;
	}
}
