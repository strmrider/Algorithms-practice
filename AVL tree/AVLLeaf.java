
public class AVLLeaf extends Leaf{

	int height;
	AVLLeaf(Wrapper element) {
		super(element);
		height = 1;
	}
	public int getHeight(AVLLeaf leaf)
	{
		if (leaf == null)
			return 0;
		
		return leaf.height;
	}
	
	public int getBalance()
	{
		if(left == null && right == null)
			return 0;
		else
			return getHeight((AVLLeaf)left) - getHeight((AVLLeaf)right);
	}
	
	public void updateHeight()
	{
		
		int l = getHeight((AVLLeaf)left);
		int r = getHeight((AVLLeaf)right);
		
		int max = 0;
		if( l > r)
			max = l;
		else
			max = r;
		height  = 1 + max;
	}
}
