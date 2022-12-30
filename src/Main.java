
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
 public static void main(String arg[]) {
  Scanner in=new Scanner(System.in);
  PrintStream out=System.out;
  out.println(tool.lod());
  do{
   String s=in.nextLine();
   String l[]=s.split(" ", 2);
   String key=l[0];
   String pr=null;
   int i=key.hashCode();
   switch (i) {
    case 107329:
     pr = tool.lod();
     break;
    case 107868:
     i=0;
     break;
    case 3570731:
     i=1;
     break;
    case 3315:
     i=2;
     break;
    case 120609:
     i=3;
     break;
    case 101916:
     i=4;
     break;
    default:
     pr ="lod;map,tmx,gz,zip,fzp <path>";
     break;
   }
   if(pr==null){
    if(l.length==2){
     pr=tool.ws(l[1],i);
    }else pr="no path";
   }
   out.println(pr);
  } while(true);
 }
}
