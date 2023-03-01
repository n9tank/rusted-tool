
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.Reader;
public class conf{
 public static void print(String s){
  System.out.println(s);
 }
 public static void write(BufferedWriter f,Map table){
  Iterator list=table.entrySet().iterator();
  try{
  while(list.hasNext()){
   Map.Entry o=(Map.Entry)list.next();
   f.write('[');
   f.write((String)o.getKey());
   f.write("]\n");
   Iterator m=((Map)o.getValue()).entrySet().iterator();
   while(m.hasNext()){
    Map.Entry j=(Map.Entry)m.next();
    f.write((String)j.getKey());
    f.write(':');
    f.write((String)j.getValue());
    f.write('\n');
   }
   }
   }catch(Exception e){
   }
  try {
   f.close();
   }catch(Exception e){}
 }
 public static void put(Map f,Map table) {
  Map mta=table;
  Iterator list=f.entrySet().iterator();
  while (list.hasNext()) {
   Map.Entry o=(Map.Entry)list.next();
   Object key=o.getKey();
   Object map=o.getValue();
   Object set=mta.get(key);
   if(set==null){
    mta.put(key,map);
   }else{
    Map hash=(Map)map;
    if(hash.get("@copyFrom_skipThisSection")!=true){
     Map setM=(Map)set;
     setM.putAll(hash);
    }
   }
  }
 }
 public static boolean text(String s){
  return s.startsWith("\"\"\"")||s.startsWith("\'\'\'");
 }
 public static Map lod(Reader re){
  BufferedReader br=new BufferedReader(re);
  Map table=new HashMap();
   Map list=null;
   String s,key="";
   boolean skip=false;
   lod:{
   try{
   while((s=br.readLine())!=null){
   s=s.trim();
   if(s.equals(""))continue;
   if(s.charAt(0)=='#')continue;
   int i=s.length()-1;
   if(s.charAt(0)=='['&&s.charAt(i)==']'){
    key=s.substring(1,i);
    skip=key.startsWith("comment");
    if(!skip){
    list=new HashMap();
    table.put(key,list);
    }
   }else if(!skip){
    String[] str=s.split(":", 2);
    if(str.length==1){
     str=s.split("=",2);
    }
    if(str.length!=1){
     if(list==null){
     print(s.concat(":no section"));
     continue;
     }
     s=str[1];
     if(s.startsWith("\"\"\"")||s.startsWith("\'\'\'")){
      StringBuilder buf=new StringBuilder();
      do{
      buf.append(s);
      if(s.endsWith("\"\"\"")||s.endsWith("\'\'\'"))break;
      buf.append('\n');
      s=br.readLine();
      if(s==null){
       buf.append("can't put");
       print(buf.toString());
       break lod;
      }else{
       s=s.trim();
      }
      }while(true);
      s=buf.toString();
     }
    list.put(str[0],s);
    }else{
     int size=s.length()+10+key.length();
     String err=new StringBuffer(size).append(s).append(":no value:").append(key).toString();
     print(err);
    }
    }
   }
   }catch(Exception e){
   }
   }
   try{
   br.close();
   }catch(Exception e){
   }
   return table;
 }
}
