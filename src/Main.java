


import java.io.File;

public class Main {
 public static void main(String arg[]) throws Exception{
  long i=System.nanoTime();
  mlod.lod(new File("/sdcard/rustedWarfare/units"));
  System.out.println(System.nanoTime()-i);
 }
}
