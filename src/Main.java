


import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class Main {
 public static void main(String arg[]) throws Exception{
  long i=System.nanoTime();
  Map m=mlod.lod(new File("/sdcard/rustedWarfare/units/a.zip"));
  System.out.println(m);
  System.out.println(System.nanoTime() - i);
 }
}
