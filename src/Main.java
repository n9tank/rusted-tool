


import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class Main {
 public static void main(String arg[]) throws Exception{
  long i=System.nanoTime();

  System.out.println(UnitsObj.valueof("123",Integer.TYPE));
  System.out.println(System.nanoTime() - i);
 }
}
