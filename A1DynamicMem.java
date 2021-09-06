// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize)
    {
        if(blockSize<=0)
        {return -1;}
        
        Dictionary mem;
        mem = this.freeBlk.Find(blockSize,false);
        if(mem==null)
        {return -1;}

        int mem_size = mem.size;
        int mem_address = mem.address;
        this.allocBlk.Insert(mem_address,blockSize,mem_address);
        this.freeBlk.Delete(mem);
        if(mem.size>blockSize)
        {
            this.freeBlk.Insert(mem_address+blockSize,mem_size-blockSize,mem_size-blockSize);
        }
        return mem_address;        
    } 
    
    public int Free(int startAddr)
    {
        Dictionary mem;
        mem = this.allocBlk.Find(startAddr,true);
        if(mem==null)
        {return -1;}

        this.freeBlk.Insert(mem.address,mem.size,mem.size);
        this.allocBlk.Delete(mem);
        return 0;
    }
}