
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
public class mlod{
 public static boolean isloder(String s){
  return s.endsWith(".ini")||s.endsWith(".template");
 }
 public static void lodF(File [] f,int l,Map m) throws Exception{
  int i=f.length;
  Map mini=m;
  while(--i>0){
   File o=f[i];
   File fj[];
   if(o.isFile()||(fj=o.listFiles()).length==0){
    String pt=o.getPath();
    if(isloder(pt)){
    mini.put(pt.substring(l),conf.lod(new FileReader(o)));
    }
   }else{
    lodF(fj,l,m);
   }
  }
 }
 public static Map<String,Map> lod(File f){
  Map mini=new HashMap();
  int i=0;
  try{
  if (f.isDirectory()) {
   lodF(f.listFiles(),f.getPath().length()-1,mini);
  }else{
   ZipFile zip=new ZipFile(f);
   Enumeration<? extends ZipEntry> e=zip.entries();
   while(e.hasMoreElements()){
    ZipEntry z=(ZipEntry)e.nextElement();
    String name=z.getName();
    if(isloder(name)){
     mini.put(name,conf.lod(new InputStreamReader(zip.getInputStream(z))));
    }
   }
  }
  }catch(Exception e){
  }
  Iterator list=mini.entrySet().iterator();
   while(list.hasNext()){
    Map.Entry me=(Map.Entry)list.next();
    Map m=(Map)me.getValue();
    Object o=m.get("core");
    if(o!=null) {
     Map v=(Map)o;
     Object k=v.get("copyFrom");
     if(k!=null){
      String key=(String)me.getKey();
      i=key.lastIndexOf("/")+1;
      key=key.substring(0,i);
      String j=(String)k;
      if(conf.text(j)){
       j=j.substring(3,j.length()-4);
      }
      String li[]=j.split(",");
      int n=li.length;
      Map buff=new HashMap();
      Map at=null;
      StringBuilder buf=new StringBuilder();
      do{
      buf.append(key,0,i);
      buf.append("all-units.template");
      Object ac=mini.get(buf.toString());
      if(ac==null){
       i=key.lastIndexOf('/',i)+1;
      }else{
       at=(Map)ac;
       break;
      }
      if(i==0)break;
      }while(true);
      if(at!=null){
       conf.put(buff,at);
      }
      while(--n>0){
       String s=li[i].trim();
       if(!s.startsWith("ROOT:")){
        s=key.concat(s);
       }else{
        s=s.substring(5);
       }
       conf.put(buff,(Map)mini.get(s));
       }
       conf.put(m,buff);
      } 
   }
  }
  return mini;
 }
}
