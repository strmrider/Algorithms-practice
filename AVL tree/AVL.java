public class AVL extends Tree {
	AVL(){}
	
	public int getBalance()
	{
		int left = height(root.left, 0, false);
		int right = height(root.right, 0, false);
		
		return left-right;
	}
	
	private int maxHeight(AVLLeaf leaf1, AVLLeaf leaf2)
	{
		return (getHeight(leaf1) > getHeight(leaf2)) ? 
				getHeight(leaf1) : getHeight(leaf2);
	}
	
	public int getHeight(AVLLeaf leaf)
	{
		if (leaf == null)
			return 0;
		
		return leaf.height;
	}
	
	public boolean balanced()
	{
		int balance = getBalance();
		if(balance >=-1 && balance <=1)
			return true;
		else
			return false;
	}
	
	private AVLLeaf rightRotate(AVLLeaf leaf)
	{
		AVLLeaf x = (AVLLeaf) leaf.left;
		AVLLeaf y = (AVLLeaf) x.right;
		
		x.right = leaf;
		leaf.left = y;
		
		leaf.height = maxHeight((AVLLeaf)leaf.left, (AVLLeaf)leaf.right);
		x.height = maxHeight((AVLLeaf)x.left, (AVLLeaf)x.right);
		
		return x;
	}
	
	private AVLLeaf leftRotate(AVLLeaf leaf)
	{
		AVLLeaf x = (AVLLeaf) leaf.right;
		AVLLeaf y = (AVLLeaf) x.left;
		
		x.left = leaf;
		leaf.right = y;
		
		leaf.height = maxHeight((AVLLeaf)leaf.left, (AVLLeaf)leaf.right);
		x.height = maxHeight((AVLLeaf)x.left, (AVLLeaf)x.right);
		
		return x;
	}
	
	public void insert(Wrapper elm)
	{
		root = insertIn(root, elm);
		size++;
	}
	
	private AVLLeaf insertIn(Leaf leaf, Wrapper elm)
	{
		if(leaf == null)
			return new AVLLeaf(elm);
		
		if(leaf.elm.cmp(elm) == 1)
		{
			leaf.left = insertIn(leaf.left, elm);
		}
		else if(leaf.elm.cmp(elm) == -1)
			leaf.right = insertIn(leaf.right, elm);
		else
			return (AVLLeaf)leaf;
	
		((AVLLeaf)leaf).updateHeight();

		int balance = ((AVLLeaf)leaf).getBalance();
		// left left
		if(balance > 1 && leaf.elm.cmp(elm) == -1)
			return rightRotate((AVLLeaf)leaf);
		// right right
		if(balance < -1 && leaf.elm.cmp(elm) == 1)
			return leftRotate((AVLLeaf)leaf);
		// left-right
		if(balance > 1 && leaf.elm.cmp(elm) == 1)
		{
			leaf.left = leftRotate((AVLLeaf)leaf.left);
			return rightRotate((AVLLeaf)leaf);
		}
		//right-left
		if(balance < -1 && leaf.elm.cmp(elm) == -1)
		{
			leaf.right = leftRotate((AVLLeaf)leaf.right);
			return leftRotate((AVLLeaf)leaf);
		}
		
		return (AVLLeaf)leaf;
		
	}
}
