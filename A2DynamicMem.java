// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 

    public void Defragment() 
    {
        Dictionary tree;
        if(this.type==2)
        {
            tree = new BSTree();
        }
        else
        {
            tree = new AVLTree();
        }

        Dictionary count;
        for(count=this.freeBlk.getFirst();count!=null;count=count.getNext())
        {
            tree.Insert(count.address,count.size,count.address);
        }

        int add,s;
        Dictionary temp;
        count = tree.getFirst();
        while(count!=null)
        {
            if(count.getNext()!=null)
            {
                if(count.getNext().key==count.key+count.size)
                {
                    if(this.type==2)
                    {
                        temp = new BSTree(count.address,count.size,count.size);
                        this.freeBlk.Delete(temp);
                        temp = new BSTree(count.getNext().address,count.getNext().size,count.getNext().size);
                        this.freeBlk.Delete(temp);
                    }
                    else
                    {
                        temp = new AVLTree(count.address,count.size,count.size);
                        this.freeBlk.Delete(temp);
                        temp = new AVLTree(count.getNext().address,count.getNext().size,count.getNext().size);
                        this.freeBlk.Delete(temp);
                    }
                    add = count.address;
                    s =  count.size + count.getNext().size;
                    this.freeBlk.Insert(add,s,s);
                    temp = count.getNext();
                    tree.Delete(temp);
                    tree.Delete(count);
                    count = tree.Insert(add,s,add);
                }
                else
                {
                    count = count.getNext();
                }
            }
            else
            {
                break;
            }
        }
        
        count = tree.getFirst();
        while(count!=null)
        {
            temp = count.getNext();
            tree.Delete(count);
            count = temp;
        }
        return;
    }
 
}