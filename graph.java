import java.util.*;

public class Graph {
	int size;
	LinkedList<Integer> list[];
	
	Graph(int size)
	{
		this.size = size;
		list = new LinkedList[this.size];
		for(int i=0; i<this.size; i++)
			list[i] = new LinkedList<Integer>();
	}
	
	void add(int originVertix, int targetVertix) throws Exception
	{
		if (originVertix >= size || originVertix < 0)
			throw new Exception("Origin vertex is out of bounds.");
		else if(targetVertix >= size || targetVertix < 0)
			throw new Exception("Target vertex is out of bounds.");
		else
			list[originVertix].add(targetVertix);
	}
	
	private boolean traverseColor(int vertex, int[] visited, int colors, int adjColor)
	{
		if(visited[vertex] > -1)
		{
			if(visited[vertex] == adjColor)
				return false;
			else
				return true;
		}
		Iterator itr;
		int n;
		boolean errors = false;
		for(int i=0; i<colors; i++)
		{
			if(i == adjColor)
				continue;
			errors = false;
			visited[vertex] = i;
			itr = list[vertex].iterator();
			while(itr.hasNext())
			{
				n = (int) itr.next();
				boolean res = traverseColor(n, visited, colors, i);
				if(!res)
				{
					errors = true;
					break;
				}
			}
			if (!errors)
				return true;
		}
		visited[vertex] = -1;
		return false;
	}
	
	
	boolean colorGraph(int colors) throws Exception
	{
		if (size == 0)
			throw new Exception("Graph has no vertexes");
		else if (colors == 0)
			return false;
		
		int visited[] = new int[size];
		for(int i=0; i<size; i++)
			visited[i] = -1;

		return traverseColor(0, visited, colors, -1);
	}
}
