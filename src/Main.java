
import java.util.HashMap;
import java.lang.reflect.Field;

public class Main {
public static String s;
 public static void main(String arg[]) throws Exception{
  long i=System.nanoTime();
  HashMap<String,Field> F=UnitsObj.Filelds("Main");
  Field f=F.get("s");
  System.out.println(f.getType()==String.class);
  System.out.println(System.nanoTime() - i);
 }
}
