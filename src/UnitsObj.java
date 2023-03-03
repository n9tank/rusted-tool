//-keep class
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
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
 
 public static Object valueof(String s,Class type) throws Exception{
  switch(type.hashCode()){
   case 24957402:
    return Integer.valueOf(s);
    //..
    default:
    return s;
  }
 }
 public static HashMap Filelds(String s){
  HashMap id;
  try {
   //.getClass() 或许更快
  Field[] ids=Class.forName(s).getFields();
  int i=16;
  int l=ids.length;
  while(i<l){
   i<<=1;
   }
   //i>>=1;
  id=new HashMap(i);
  while(--l>=0){
   Field v=ids[l];
   id.put(v.getName(),v);
  }
  }catch(Exception e){
   id=null;
  }
  return id;
 }
 public void set(String s,String v) throws Exception{
  Field i=mid.get(s);
  if(i==null){
   conf.print("can't find ".concat(s));
   return;
   }
   i.set(this,valueof(v,i.getType()));
 }
 public void put(String s,String k,Object m) throws Exception{
  Field i=mid.get(s);
  if(i!=null){
  ((HashMap)i.get(this)).put(k,m);
  }
 }
 //..
}
