
import java.util.HashMap;
import java.lang.reflect.Field;

public class Main {
 public static void main(String arg[]) throws Exception{
  long i=System.nanoTime();
  System.out.println(String.class.hashCode());
  System.out.println(Integer.TYPE.hashCode());
  System.out.println(System.nanoTime() - i);
 }
}
