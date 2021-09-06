// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        if(this==null)
        {return null;}

        A1List new_A1List = new A1List(address,size,key);

        if(this.next==null)
        {
            this.next = new_A1List;
            new_A1List.prev = this;
            new_A1List.next = null;
            return new_A1List;
        }

        new_A1List.next = this.next;
        this.next = new_A1List;
        new_A1List.next.prev = new_A1List;
        new_A1List.prev = this;
        return new_A1List;
    }

    public boolean Delete(Dictionary d) 
    {
        if(this==null)
        {return false;}

        if(this.key==d.key && this.size==d.size && this.address==d.address)
        {
            this.prev.next = this.next;
            this.next.prev = this.prev;
            this.next = null;
            this.prev = null;
            return true;
        }

        A1List temp = this.next;
        if(temp==null)
        {return false;}

        while(temp!=null && temp.next!=null)
        {
            if(temp.key==d.key && temp.size==d.size && temp.address==d.address)
            {
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                temp.next = null;
                temp.prev = null;
                return true;
            }
            temp = temp.next;
        }

        temp = this.prev;
        if(temp==null)
        {
            return false;
        }
        while(temp!=null && temp.prev!=null)
        {
            if(temp.key==d.key && temp.size==d.size && temp.address==d.address)
            {
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                temp.next = null;
                temp.prev = null;
                return true;
            }
            temp = temp.prev;
        }

        return false;
    }

    public A1List Find(int k, boolean exact)
    { 
        if(this==null)
        {return null;}

        A1List temp=this;
        while(temp.prev!=null)
        {
            temp = temp.prev;
        }
        temp = temp.next;
        if(temp.next==null)
        {
            return null;
        }
        
        while(temp!=null && temp.next!=null)
        {
            if((exact && temp.key==k)|| (!exact && temp.key>=k))
            {
                return temp;
            }
            temp = temp.next;
        }
       
        return null;
    }

    public A1List getFirst()
    {
        if(this==null)
        {return null;}

        A1List temp = this;
        while(temp.prev!=null)
        {
            temp = temp.prev;
        }
        temp = temp.next;
        if(temp==null || temp.next==null)
        {
            return null;
        }
        return temp;
    }
    
    public A1List getNext() 
    {
        if(this==null)
        {return null;}

        if(this.next==null)
        {
            return null;
        }
        if(this.prev==null && this.next.next==null)
        {
            return null;
        }
        
        if(this.next.next!=null)
        {
            return this.next;
        }
        return null;
    }

    public boolean sanity()
    {
        A1List temp = this.prev;

        while(temp!=null && temp.prev!=null && temp!=this)
        {   if(temp.prev.next!=temp)
            {
                return false;
            }
            temp = temp.prev; 
        }

        if(temp==this)
        { return false; }

        if(temp==null)
        { temp = this;}

        A1List head = temp;
        if(temp.next.prev!=temp || temp.key!=-1 || temp.address!=-1 || temp.size!=-1)
        { return false;}

        while(temp!=null && temp.next!=null)
        {
            if(temp.next.prev!=temp || temp.next==temp || temp.prev==temp || temp.next==temp.prev)
            { return false;}

            temp = temp.next;
            if(temp==head)
            { return false;}
        }

        if(temp.prev.next!=temp || temp.prev==temp || temp.key!=-1 || temp.address!=-1 || temp.size!=-1)
        {return false;}

        A1List slow,fast;
        slow = head;
        fast = head;

        while(slow!=null && fast!=null && fast.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast)
            { return false; }
        }
        return true;
    }       
}


