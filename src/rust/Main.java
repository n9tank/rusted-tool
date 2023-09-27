package rust;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {
 public static void main(String[] strArr) throws Exception {
  Scanner scanner = new Scanner(System.in);
  PrintStream printStream = System.out;
  printStream.println(tool.lod());
  while (true) {
   String[] split = scanner.nextLine().split(" ", 2);
   String str=null;
   int hashCode = split[0].hashCode();
   switch (hashCode) {
   /*case 0:
     str=String.valueOf(split[1].hashCode());
     break;*/
    case 3315:
     hashCode = 2;
     break;
    case 101916:
     hashCode = 4;
     break;
    case 107329:
     str = tool.lod();
     break;
    case 107868:
     hashCode = 0;
     break;
    case 120609:
     hashCode=3;
     break;
    case 114975:
     hashCode=1;
     break;
    default:
     str = "lod;map,tmx,gz,zip,fzp <path>";
     break;
   }
   if (str == null) {
    str = split.length == 2 ? tool.ws(split[1], hashCode) : "no path";
   }
   printStream.println(str);
  }
 }
}
