// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    
    private static int Height(AVLTree node)
    {
        if(node==null)
        {
            return -1;
        }
        return node.height;
    }
    private static int max(int a,int b)
    {
        if(a>b)
        {
            return a;
        }
        return b;
    }
    private static void update_ht(AVLTree z,AVLTree y,AVLTree x)
    {
        z.height = max(Height(z.left),Height(z.right))+1;
        y.height = max(Height(y.left),Height(y.right))+1;
        x.height = max(Height(x.left),Height(x.right))+1;
    }

    private static void Rotate(AVLTree z)
    {
        AVLTree y,x,par;

        if(Height(z.left)>Height(z.right))
        {y = z.left;}
        else
        {y = z.right;}

        if(Height(y.left)>Height(y.right) || (Height(y.left)==Height(y.right) && z.left==y))
        {x = y.left;}
        else
        {x = y.right;}

        par = z.parent;
        if(z.left==y && y.left==x)
        {
            z.left = y.right;
            if(y.right!=null)
            {y.right.parent = z;}            
            y.right = z;
            z.parent = y;
            y.parent = par;

            if(par.left==z)
            {par.left=y;}
            else
            {par.right=y;}
            update_ht(z,x,y);
            return ;      
        }
        if(z.right==y && y.right==x)
        {
            z.right = y.left;
            if(y.left!=null)
            {y.left.parent = z;}            
            y.left = z;
            z.parent = y;
            y.parent = par;

            if(par.left==z)
            {par.left = y;}
            else
            {par.right = y;}
            update_ht(z,x,y);
            return ;
        }
        if(z.left==y && y.right==x)
        {
            y.right = x.left;
            if(x.left!=null)
            {x.left.parent = y;}
            x.left = y;
            y.parent = x;
            x.parent = par;
            z.parent = x;
            z.left = x.right;
            if(x.right!=null)
            {x.right.parent=z;}
            x.right = z;

            if(par.left==z)
            {par.left=x;}
            else
            {par.right=x;}
            update_ht(z,y,x);
            return ;
        }
        
        y.left = x.right;
        if(x.right!=null)
        {x.right.parent=y;}
        x.right = y;
        y.parent = x;
        x.parent = par;
        z.parent = x;
        z.right = x.left;
        if(x.left!=null)
        {x.left.parent=z;}
        x.left = z;

        if(par.left==z)
        {par.left = x;}
        else
        {par.right = x;}

        update_ht(z,y,x);
        return ;
    }

    public AVLTree Insert(int address, int size, int key) 
    { 
        AVLTree temp = this;
        while(temp.parent!=null)
        {
            temp = temp.parent;
        }
        
        if(temp.right==null)
        {
            temp.right = new AVLTree(address,size,key);
            temp.right.parent = temp;
            temp = temp.right;
            temp.left = null;
            temp.right = null;
            temp.height = 0;
            return temp;
        }

        temp = temp.right;
        while((temp.right!=null && (temp.key<key ||(temp.key==key && temp.address<address))) || (temp.left!=null && (temp.key>key ||(temp.key==key && temp.address>address))))
        {
            if(temp.key<key ||(temp.key==key && temp.address<address))
            {
                temp = temp.right;
            }
            else
            {
                temp = temp.left;
            }
        }

        AVLTree store;
        if(temp.key<key || (temp.key==key && temp.address<address))
        {
            temp.right = new AVLTree(address, size, key);
            temp.right.parent = temp;
            temp = temp.right;
            temp.right = null;
            temp.left = null;
            temp.height = 0;
            store = temp;
        }
        else
        {
            temp.left = new AVLTree(address, size, key);
            temp.left.parent = temp;
            temp = temp.left;
            temp.height = 0;
            temp.right = null;
            temp.left = null;
            store = temp;
        }

        int ht = Height(temp.right) - Height(temp.left);
        while(ht<2 && ht>-2 && temp!=null && temp.parent!=null)
        {
            temp.height = max(Height(temp.right),Height(temp.left)) + 1;
            temp = temp.parent;
            if(temp.parent==null)
            {
                return store;
            }
            ht = Height(temp.right) - Height(temp.left);
        }
             
        Rotate(temp);
        return store;

    }

    private void my_delete()
    {
        AVLTree temp = this;
        AVLTree par = temp.parent;
        if(par==null)
        {
            return ;
        }
        if(temp.left==null && temp.right==null)
        {
            if(par.right==temp)
            {
                par.right = null;
                temp.parent = null;
                return ;
            }
            else
            {
                par.left = null;
                temp.parent  = null;
                return ;
            }
        }
        if(temp.left==null && temp.right!=null)
        {
            if(par.right==temp)
            {
                par.right = temp.right;
                temp.right.parent = par;
                temp.parent = null;
                temp.right=null;
                return ;
            }
            else
            {
                par.left = temp.right;
                temp.right.parent = par;
                temp.parent = null;
                temp.right = null;
                return ;
            }
        }

        if(temp.left!=null && temp.right==null)
        {
            if(par.right==temp)
            {
                par.right = temp.left;
                temp.left.parent = par;
                temp.parent = null;
                temp.left = null;
            }
            else
            {
                par.left = temp.left;
                temp.left.parent = par;
                temp.parent = null;
                temp.left = null;
            }
            return ;
        }
        return ;
    }

    public boolean Delete(Dictionary d)
    {
        if(d==null)
        {
            return false;
        }
        AVLTree temp = this;
        while(temp.parent!=null)
        {
            temp = temp.parent;
        }

        if(temp.right==null)
        {
            return false;
        }
        temp = temp.right;

        while(temp!=null && (temp.key!=d.key || temp.address!=d.address || temp.size!=d.size))
        {
            if(temp.key<d.key || (temp.key==d.key && temp.address<d.address))
            {
                if(temp.right==null)
                {
                    return false;
                }
                temp = temp.right;
            }
            else
            {
                if(temp.left==null)
                {
                    return false;
                }
                temp = temp.left;
            }
        }

        AVLTree succ,fin;
        if(temp.right==null || temp.left==null)
        {
            succ = temp.parent;
            temp.my_delete();
        }

        else
        {
            succ = temp.right;
            int testi = 0;
            while(succ.left!=null)
            {
                testi = 1;
                succ = succ.left;
            }
            if(testi==0)
            {
                succ.parent = temp.parent;
                succ.left = temp.left;
                temp.left.parent = succ;

                if(temp.parent.right==temp) 
                {temp.parent.right = succ;}
                else
                {temp.parent.left = succ;}
            
                temp.parent = null;
                temp.left = null;
                temp.right = null;
            }
            else
            {
                fin = succ.parent;
                succ.parent.left = succ.right;
                if(succ.right!=null)
                {
                    fin = succ.right;
                    succ.right.parent = succ.parent;
                }

                succ.parent = temp.parent;
                succ.left = temp.left;
                succ.right = temp.right;
                temp.left.parent = succ;
                temp.right.parent = succ;

                if(temp.parent.right==temp)
                {temp.parent.right = succ;}
                else
                {temp.parent.left = succ;}

                temp.right = null;
                temp.parent = null;
                temp.left = null;
                succ = fin;
            }    
        }  

        temp = succ;
        int ht;
        while(temp!=null && temp.parent!=null)
        {
            ht = Height(temp.right) - Height(temp.left);
            if(ht>1 || ht<-1)
            {
                Rotate(temp);
            }
            temp.height = max(Height(temp.right),Height(temp.left)) + 1;
            temp = temp.parent;

            if(temp.parent==null)
            {return true;}
    
        }
        
        return true;
    }
        
    public BSTree Find(int key, boolean exact)
    { 
        AVLTree temp = this;
        while(temp.parent!=null)
        {
            temp = temp.parent;
        }
        if(temp.right==null)
        {
            return null;
        }

        temp = temp.right;
        AVLTree count;
        if(exact)
        {
            count = temp;
            temp = null;
            while(count!=null)
            {
                while(count!=null && count.key!=key)
                {
                    if(count.key<key)
                    {count = count.right;}

                    else
                    {count = count.left;}

                    if(count==null)
                    {return temp;}
                }
                temp = count;
                while(count.left!=null && count.left.key==key)
                {
                    count = count.left;
                    temp = count;
                }
                if(count.left==null)
                {return temp;}

                count = count.left;
            }
            return temp;
        }

        count = temp;
        temp = null;
        while(count!=null)
        {
            while(count!=null && count.key<key)
            {
                count = count.right;
                if(count==null)
                {
                    return temp;
                }
            }
            temp = count;
            while(count.left!=null && count.left.key>=key)
            {
                count = count.left;
                temp = count;
            }
            if(count.left==null)
            {return temp;}

            count = count.left;
        }
        return temp;        
    }

    public AVLTree getFirst()
    { 
        AVLTree temp = this;
        while(temp.parent!=null)
        {
            temp  = temp.parent;
        }
        if(temp.right==null)
        {
            return null;
        }
        temp = temp.right;
        while(temp.left!=null)
        {
            temp = temp.left;
        }
        return temp;
    }

    public AVLTree getNext()
    { 
        AVLTree temp = this;
        if(temp.parent==null)
        {
            return null;
        }
        if(temp.right!=null)
        {
            temp = temp.right;
            while(temp.left!=null)
            {
                temp = temp.left;
            }
            return temp;
        }

        AVLTree par = temp.parent;
        while(par!=null)
        {
            if(par.left==temp)
            {
                return par;
            }
            temp = temp.parent;
            par = par.parent;
        }
        return null;
    }

    public boolean AVLproperty(int minimum,int maximum)
    {
        if(this.key>maximum || this.key<minimum || ((this.left==this) || (this.right==this) || (this.parent==this)))
        {
            return false;
        }

        if(this.left==null && this.right==null)
        {
            return (this.height==0);
        }

        if(this.left!=null && this.right==null)
        {
            if(this.left.key>this.key || this.left.parent!=this || (this.left.key==this.key && this.left.address>this.address))
            {
                return false;
            }
            return (this.left.left==null) && (this.left.right==null)&&(this.height==1)&&(this.left.AVLproperty(minimum,this.key));
        }
        if(this.left==null && this.right!=null)
        {
            if(this.right.key<this.key || this.right.parent!=this || (this.right.key==this.key && this.right.address<this.address))
            {
                return false;
            }
            return (this.right.left==null) && (this.right.right==null) && (this.height==1) && (this.right.AVLproperty(this.key,maximum));
        }
        Boolean b = (this.left.key<this.key) || (this.left.key==this.key && this.left.address<this.address);
        b = b && ((this.right.key>this.key) || (this.right.key==this.key && this.right.address>this.address));
        int maxi = this.left.height - this.right.height;
        return (b && (maxi<2) && (maxi>-2) && (this.height == max(this.left.height,this.right.height)+1) && (this.left.parent==this) && (this.right.parent==this) && (this.left.AVLproperty(minimum,this.key) && this.right.AVLproperty(this.key,maximum)));
    }
    public boolean sanity()
    { 
        AVLTree temp = this;
        while(temp.parent!=null)
        {
            if((temp.parent.right!=temp && temp.parent.left!=temp) || temp.parent==this)
            {
                return false;
            }
            temp = temp.parent;
        }
        if(temp.left!=null || temp.key!=-1 || temp.address!=-1 || temp.size!=-1)
        {
            return false;
        }
        temp = temp.right;
        if(temp==null)
        {
            return true;
        }
        return temp.AVLproperty(Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
}


