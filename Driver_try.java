import java.util.Scanner;

public class Driver_try {

    public static void main(String args[]) {
        int numTestCases;
        Scanner sc = new Scanner(System.in);
        numTestCases = sc.nextInt();
        while (numTestCases-- > 0) {
            int size;
            size = sc.nextInt();
            A2DynamicMem obj = new A2DynamicMem(size, 2);
            int numCommands = sc.nextInt();
            while (numCommands-- > 0) {
                String command;
                command = sc.next();
                int argument;
                int result = -5;
                switch (command) {
                    case "Allocate":
                        argument = sc.nextInt();
                        result = obj.Allocate(argument);
                        break;
                    case "Free":
                        argument = sc.nextInt();
                        result = obj.Free(argument);
                        break;
                    case "Defragment":
                        obj.Defragment();
                        result = -10;
                        break;
                    default:
                        break;
                }
                System.out.print("AllocBlk => ");
                print(obj.allocBlk);
                System.out.print("FreeBlk => ");
                print(obj.freeBlk);

            }

        }
    }

    public static void print(Dictionary tree) {
        Dictionary first = tree.getFirst();
        while (first != null) {
            System.out.print("(" + first.address + " " + first.size + " " + first.key + ") => ");
            first = first.getNext();
        }
        System.out.print("\n");
    }
}
