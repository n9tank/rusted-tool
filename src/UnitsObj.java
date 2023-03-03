//-keep class
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
public class UnitsObj {
 static HashMap<String,Field> mid;
 static HashMap mtype;
 //反射写法
 public String name;
 public int price;
 public int maxHp;
 //..
 public HashMap action;
 static{
 mid=Filelds("UnitsObj");
 HashMap type=new HashMap();
 Class str=String.class;
 try{
 type.put(Integer.TYPE,Integer.class.getMethod("valueOf",str));
 //..
 }catch(Exception e){
 }
 mtype=type;
 }
 public UnitsObj(){
  action=new HashMap();
 }
 public static Object valueof(String s,Class type) throws Exception{
  Object c=mtype.get(type);
  if(c==null)return null;
 return ((Method)c).invoke(null,s);
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
   Class c=i.getType();
  /*
   if(c==Integer.class){
   set=Integer.valueOf(v);
   }*/
  if(c!=String.class){
  i.set(this,valueof(v,c));
  }else{
  i.set(this,v);
  }
 }
 public void put(String s,String k,Object m) throws Exception{
  Field i=mid.get(s);
  if(i!=null){
  ((HashMap)i.get(this)).put(k,m);
  }
 }
 //..
}
