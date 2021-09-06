import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Driver_alt {
    public static void main(String args[]) throws IOException{
        long startTime = System.nanoTime();
        File myObj = new File("./A2_test_11.in");
        FileWriter fw=new FileWriter("./res.out");
        Scanner sc = new Scanner(myObj);
        int numTestCases;
        numTestCases = sc.nextInt();
        int fr = 0;
        int all = 0;
        while (numTestCases-- > 0) 
        {
            int size;
            size = sc.nextInt();
            A2DynamicMem obj = new A2DynamicMem(size,3);
            int numCommands = sc.nextInt();
            while (numCommands-- > 0) 
            {
                String command;
                command = sc.next();
                int argument;
                argument = sc.nextInt();
                int result = -5;
                switch (command) {
                    case "Allocate":
                        result = obj.Allocate(argument);
                        break;
                    case "Free":
                        result = obj.Free(argument);
                        break;
                    case "Defragment":
                        obj.Defragment();
                        result = -5;
                        break;
                    default:
                        break;
                }
                if(obj.freeBlk.sanity()==false)
                {
                    fr++;
                }
                if(obj.allocBlk.sanity()==false)
                {
                    all++;
                }
                String str = String.valueOf(result);
                fw.write(str);
                fw.write("\n");
            }
        }
        System.out.println(fr+ " "+ all);
        long stopTime = System.nanoTime();
        System.out.println((stopTime - startTime)/1000000000.0);        
        fw.close();
        sc.close();
    }
}