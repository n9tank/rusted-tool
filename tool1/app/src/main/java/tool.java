
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.zip.GZIPInputStream;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;

public class tool {
 final static ByteBuffer b;
 final static ByteBuffer eo;
 final static File hex;
 final static File map;
 static ByteBuffer ma;
 static byte bs[];
 static byte xm[];
 static byte med[];
 static byte mst[];
 static int sq;
 static boolean lsm;
 static{
  b = ByteBuffer.allocateDirect(8211);
  //8192+4*4(utf-32)-1+4(int)
  b.limit(8192);
  bs = new byte[8192];
  eo = ByteBuffer.allocateDirect(1);
  File ru=new File("sdcard/rustedWarfare");
  hex = new File(ru, "hex");
  map = new File(ru, "maps");
 }
 public static void fmk() {
  hex.mkdirs();
  map.mkdirs();
 }
 public static String lod() {
  File lod=new File(hex, ".ini");
  Properties p=new Properties();
  if (lod.exists()) {
   try {
	FileReader fr=new FileReader(lod);
	BufferedReader br=new BufferedReader(fr);
	try {
	 p.load(br);
	} catch (Exception e) {}
    br.close();
   } catch (Exception e) {
   }
  }
  String key=null;
  String value=null;
  try {
   key = "lessRAM";
   value = p.getProperty(key);
   boolean ism=true;
   if (value != null) {
    ism = value.equals('T');
    lsm = ism;
   }
   key = "searchSize";
   value = p.getProperty(key);
   if (value != null) {
    sq = Integer.valueOf(value);
   }else{
    sq = ism ?200: 8192;
   }
   key = "encode";
   value = p.getProperty(key);
   if (value == null) {
    value="utf-8";
   }
  byte m[]="<map".getBytes(value);
  byte st[]=mst;
  if (!m.equals(st)) {
   mst = m;
   med = ">pam".getBytes(value);
   xm = "<?xm".getBytes(value);
  }
 }catch(Exception e){
  String err=e.toString();
  StringBuilder str=new StringBuilder(key.length() + value.length() + err.length() + 2);
  str.append(key);
  str.append('=');
  str.append(value);
  str.append('\n');
  str.append(err);
  return str.toString();
 }
 return null;
 }
 public static String ws(String pt, int b) {
  String ru;
  rn: {
   File r=new File(pt);
   if (r.length() == 0l) {
    return "文件异常";
   }
   if (b <= 2) {
    int ls=pt.lastIndexOf('/');
    int sl=pt.length();
    int l2=sl - 7;
	String ed;
    File rn;
	if (b == 2) {
	 ed = ".gz";
     rn = hex;
	} else {
	 ed = ".tmx";
     rn = map;
	}
	if (pt.charAt(l2) == '.') {
	 sl = l2;
	}
	StringBuilder name=new StringBuilder(sl - ls + ed.length());
	name.append(pt, ls, sl);
	name.append(ed);
	pt = name.toString();
    rn = new File(rn, pt);
    if (rn.exists()) {
     return "文件已存在";
    }
	try {
	 FileInputStream f=new FileInputStream(r);
	 try {
	  ru = whex(f, b, rn);
	  break rn;
	 } catch (Exception e) {
	  f.close();
	 }
	} catch (Exception e) {
	}
	ru = "失败";
	break rn;
   } else if (b == 3) {
	ru = tool.wzip(r);
	break rn;
   }
   ru = tool.fzp(r);
  }
  System.gc();
  return ru;
 }
 public static String whex(FileInputStream f, int sta, File rn)throws Exception {
  ByteBuffer br=b;
  FileChannel fc=f.getChannel();
  byte by[]=bs;
  int n,l=bs.length,ls=l;
  int k=sq;
  boolean ism=lsm;
  if (!ism) {
   n = 8192;
  } else {
   n = k;
  }
  int p=f.read(by, 0, n);
  n = p - 1;
  if (n < k) {
   k = n;
  }
  int i=0;
  tag: {
   do{
	if (by[i++] == 0x1f && by[i++] == -117) {
	 break tag;
	}
   }while(i < k);
   f.close();
   return "文件异常";
  }
  i -= 2;
  p -= i;
  InputStream in=null;
  if (!ism || sta == 2 || sta == 0) {
   i -= 4;
   n = (by[i++] & 0xff) << 24;
   n |= (by[i++] & 0xff) << 16;
   n |= (by[i++] & 0xff) << 8;
   n |= by[i++] & 0xff;
   if (sta != 2) {
	if (!ism) {
	 if (sta != 0) {
	  n -= 4;
	 }
	 while (l < n) {
	  l <<= 1;
	 }
	 if (l != ls) {
	  byte[] bo=new byte[l];
	  System.arraycopy(by, i, bo, 0, p);
	  by = bo;
	  bs = bo;
	  i = 0;
	 } else if (l - i < n) {
	  System.arraycopy(by, i, by, 0, p);
	  i = 0;
	 }
	 l = n - p;
	 if (l > 0)f.read(by, p + i, l);
	 f.close();
	 n += i - 4;
	} else if (sta == 0) {
	 n += i - 4;
	 fc.position(n);
	 f.read(by, 0, 4);
	 n = 0;
	}
	if (sta == 0) {
	 l = (by[n] & 0xff);
	 l |= (by[++n] & 0xff) << 8;
	 l |= (by[++n] & 0xff) << 16;
	 l |= (by[++n] & 0xff) << 24;
	 n -= 3;
	}
	if (!ism)in = new ByteArrayInputStream(by, i, n);
   }
  }
  if (sta == 2) {
   FileOutputStream ou=new FileOutputStream(rn);
   FileChannel c=ou.getChannel();
   String si;
   try {
	setLen(c, n);
	if (p < n) {
	 n -= p;
	 ou.write(by,i, p);
	 fc.transferTo(p,n,c);
	} else {
	 ou.write(by,i, n);
	}
	si = "完成";
   } catch (Exception e) {
	si = "失败";
   }
   ou.close();
   fc.close();
   return si;
  }
  if (ism) {
   n = 0;
   fc.position(i);
   in = new BufferedInputStream(f);
  }
  ReadableByteChannel g=Channels.newChannel(new GZIPInputStream(in));
  String si;
  if (sta == 1) {
   int st;
   byte s2[]=xm;
   byte b2=s2[0];
   k=s2.length-1;
   int e=0,len=0,v;
   wh:
   while (true) {
	i = g.read(br)+e;
	st = e;
    e=0;
	do{
	 if (br.get(st++) == b2) {
	  if (e == k) {
	   v= --st-k;
       len=br.getInt(v-4);
       break wh;
       }
	  b2=s2[++e];
	 } else if (e != 0) {
	  e = 0;
	  b2=s2[e];
	 }
	}while(st < i);
	if (i != 8192) {
	 br.position(0);
     br.limit(8192);
	 g.close();
	 return "未找到";
	}
    e+=4;
    n=i-e;
    br.clear();
    br.position(0);
    ByteBuffer bo=br.asReadOnlyBuffer();
    bo.position(n);
    bo.limit(i);
    br.limit(8192+e);
    br.put(bo);
	br.position(e);
   }
   FileChannel c=new FileOutputStream(rn).getChannel();
   try {
	setLen(c,len);
    br.position(v);
    int wl=i-v;
    c.write(br);
    len-=wl;
	if(len>0)c.transferFrom(g,wl,len);
	si = "完成";
   } catch (Exception e3) {
    e3.printStackTrace();
	si = "失败";
   }
   fc.close();
   c.close();
   g.close();
   br.position(0);
   br.limit(8192);
  } else {
   byte st[]=mst;
   int pl=0;
   byte b2=st[pl];
   n = st.length-1;
   try {
	tag: {
	 wh:
	 do{
	  k = g.read(br);
	  br.position(0);
	  l -= k;
	  i = 0;
	  br.position(i);
	  do{
	   if (br.get(i)== b2) {
		if (pl == n) {
		 break wh;
         }
		b2 = st[++pl];	
	   } else if (pl != 0) {
		pl = 0;
		b2 = st[pl];
	   }
	  }while(++i < k);
	  if (k != 8192) {
	   si = "未找到";
	   break tag;
	  }
	 }while(true);
	 ByteBuffer ou=ma;
	 p = k + 4 - ++i;
	 l += p;
	 if (ou == null || ou.capacity() < l) {
	  n = 8192;
	  while (n < l) {
	   n <<= 1;
	  }
	  ou = ByteBuffer.allocateDirect(n);
	 }
	 ou.put(st);
	 br.position(i);
	 br.limit(k);
	 ou.put(br);
	 br.position(0);
	 br.limit(8192);
	 g.read(ou);
	 ou.position(0);
	 st = med;
	 pl = 0;
	 b2 = st[pl];
     n = st.length-1;
	 do{
	  if (ou.get(--l)== b2) {
	   if (pl == n) {
		break;
	   }
	   b2 = st[++pl];
	  } else if (pl != 0) {
	   pl = 0;
	   b2 = st[pl];
	  }
	 }while(l > 0);
	 FileChannel out=new FileOutputStream(rn).getChannel();
	 try {
	  setLen(out, l);
	  ou.position(0);
	  ou.limit(l + ++n);
	  out.write(ou);
	  si = "完成";
	 } catch (Exception e) {
	  si = "失败";
	 }
	 out.close();
	 ou.clear();
	}
   } catch (Exception e2) {
	si = "失败";
   }
   g.close();
  }
  return si;
 }
 public static void setLen(FileChannel c, int i) throws Exception {
  ByteBuffer ei=eo;
  c.write(ei, i - 1);
  ei.position(0);
 }
 public static String fzp(File f) {
  try {
   RandomAccessFile rf=new RandomAccessFile(f, "rw");
   FileChannel c=rf.getChannel();
   int fsize=(int)c.size();
   String ru;
   ByteBuffer rw=c.map(FileChannel.MapMode.READ_WRITE, 0, fsize);
   try {
    int i=2;
    do{
     int size,j,k;
     i += 16;
     size = rint(rw, i);
     i += 8;
     j = rshort(rw, i);
     i += 2;
     k = rshort(rw, i);
     i += j + 1;
     byte c2=rw.get(i);
     if (size != 0 && (c2 == '/' || c2 == '\\')) {
      rw.put(i, (byte)'+');
     }
     i += size + k + 1;
     if (rw.get(i) == (byte)2) {
      i += 4;
     } else i += 2;
    }while(rw.get(i) != (byte)1);
    i += 22;
    do{
     int size=rint(rw, i);
     i += 4;
     int j=rshort(rw, i);
     i += 2;
     int k=rshort(rw, i);
     i += 15 + j;
     byte c2=rw.get(i);
     if (size != 0 && (c2 == '/' || c2 == '\\')) {
      rw.put(i, (byte)'+');
     }
     i += k + 25;
    }while(i < fsize);
    ru = "完成";
   } catch (Exception e) {
	ru = "失败";
   }
   rf.close();
   return ru;
  } catch (Exception e) {
  }
  return "失败";
 }
 public static int rshort(ByteBuffer b, int i) {
  int s=b.get(i) & 0xff;
  return s |= (b.get(++i) & 0xff) << 8;
 }
 public static int rint(ByteBuffer b, int i) {
  int s=b.get(i) & 0xff;
  s |= (b.get(++i) & 0xff) << 8;
  s |= (b.get(++i) & 0xff) << 16;
  return s |= (b.get(++i) & 0xff) << 24;
 }
 public static String wzip(File f) {
  try {
   ZipFile z=new ZipFile(f);
   List<FileHeader> fhs=z.getFileHeaders();
   int i=fhs.size();
   int k=16;
   while (k < i) {
	k >>= 1;
   }
   HashMap hp=new HashMap(k);
   while (--i >= 0) {
	FileHeader fh=fhs.get(i);
	String na=fh.getFileName();
	int le=na.length() - 1;
	char c=na.charAt(le);
	if (fh.getCompressedSize() != 0l || fh.getUncompressedSize() != 0l) {
     fh.setDirectory(false);
	 if (c == '/') {
	  c = '+';
	 } else if (c == '\\') {
	  c = '-';
	 } else continue;
	 char cr[]=na.toCharArray();
	 cr[le] = c;
	 String rs=new String(cr);
	 hp.put(na, rs);
	}
	z.renameFiles(hp);
	return "完成";
   }
  } catch (Exception e) {
  }
  return "失败";
 }
}
