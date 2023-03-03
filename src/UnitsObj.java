//-keep class
import java.util.HashMap;
import java.lang.reflect.Field;
public class UnitsObj {
 static HashMap<String,Field> mid;
 //反射写法
 public String name;
 public int price;
 public int maxHp;
 //..
 public HashMap action;
 static{
 mid=Filelds("UnitsObj");
 }
 public UnitsObj(){
  action=new HashMap();
 }
 public static HashMap Filelds(String s){
  HashMap id=new HashMap();
  try {
   //.getClass() 或许更快
  Field[] ids=Class.forName(s).getFields();
  int i=ids.length;
  while(--i>=0){
   Field v=ids[i];
   id.put(v.getName(),v);
  }
  }catch(Exception e){}
  return id;
 }
 public void set(String s,Object v) throws Exception{
  Field i=mid.get(s);
  if(i!=null)i.set(this,v);
 }
 public void put(String s,String k,Object m) throws Exception{
  Field i=mid.get(s);
  if(i!=null){
  ((HashMap)i.get(this)).put(k,m);
  }
 }
 //..
}
