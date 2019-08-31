public class KMP {
	private String mainText;
	
	KMP(String txt)
	{
		mainText = txt;
	}
	
	public String getText()
	{
		return mainText;
	}
	
	int search(String pattern)
	{
		boolean jumped = false;
		int indexArr[] = buildArr(pattern);
		for(int i=0, j=0; i<mainText.length();)
		{
			if(mainText.charAt(i) == pattern.charAt(j))
			{
				if (j == pattern.length()-1)
					return i-pattern.length()+1;
				i++;
				j++;
			}
			else
			{
				if(i==0 || j==0)
				{
					//j=0;
					i++;
					jumped = false;
				}
				else if (jumped)
				{
					i++;
					jumped = false;
				}
				else
				{
					if(j!=0)
						j = indexArr[j-1];
					jumped = true;
				}
			}
		}
		return -1;
	}
	
	private int[] buildArr(String pattern)
	{
		int indexArr[] = new int[pattern.length()];
		
		indexArr[0] = 0;
		for(int i=0, j=1; j<pattern.length();)
		{
			if(pattern.charAt(i) == pattern.charAt(j))
			{
				indexArr[j] = 1+i;
				i++;
				j++;
			}
			else
			{
				if(i==0)
				{
					indexArr[j] = 0;
					j++;
				}
				else
					i = indexArr[i-1];
			}
		}
		return indexArr;
	
