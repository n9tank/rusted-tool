
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
public class mlod{
 public static boolean isloder(String s){
  return s.endsWith(".ini")||s.endsWith(".template");
 }
 public static void lodF(File [] f,int l,HashMap m) throws Exception{
  int i=f.length;
  while(--i>=0){
   File o=f[i];
   File fj[];
   if(o.isFile()||(fj=o.listFiles()).length==0){
    String pt=o.getPath();
    if(isloder(pt)){
    m.put(pt.substring(l),conf.lod(new FileReader(o)));
    }
   }else{
    lodF(fj,l,m);
   }
  }
 }
 public static HashMap<String,HashMap> lod(File f){
  HashMap mini=new HashMap();
  int i=0;
  try{
  if (f.isDirectory()) {
   lodF(f.listFiles(),f.getPath().length()+1,mini);
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
    HashMap.Entry me=(HashMap.Entry)list.next();
    HashMap m=(HashMap)me.getValue();
    Object o=m.get("core");
    if(o!=null) {
     HashMap v=(HashMap)o;
     Object k=v.get("copyFrom");
     if(k!=null){
      String key=(String)me.getKey();
      //String pt=key;
      i = key.lastIndexOf("/") + 1;
      key = key.substring(0, i);
      String j=(String)k;
      if(conf.text(j)){
       j=j.substring(3,j.length()-3);
      }
      String li[]=j.split(",");
      HashMap buff=new HashMap(m.size());
      StringBuilder buf=new StringBuilder();
      do{
      buf.append(key,0,i);
      buf.append("all-units.template");
      Object ac=mini.get(buf.toString());
      if(ac==null){
       i=key.lastIndexOf('/',i)+1;
      }else{
        HashMap at=(HashMap)ac;
        conf.put(buff, at);
        break;
      }
      if(i==0)break;
      }while(true);
      i=li.length;
      while(--i>=0){
       String s=li[i].trim();
       if(!s.startsWith("ROOT:")){
        s=key.concat(s);
       }else{
        s=s.substring(5);
       }
       conf.put(buff,(HashMap)mini.get(s));
       }
       conf.put(buff,m);
       me.setValue(buff);
       /*if(pt.endsWith(".ini")){

       }*/
      } 
   }
  }
  return mini;
 }
}
