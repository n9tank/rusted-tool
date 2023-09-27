package rust.tool1;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import rust.tool;
public class Main extends Activity { 
TextView t;
Intent Intent;
String pe[];
String mpt="";
static Toast m;
static Context c;
public static void ms(String s){
Toast m2=m;
if(m2!=null)m2.cancel();
m2=Toast.makeText(c,s,0);
m=m2;
m2.show();
}
public void finish(){
moveTaskToBack(true);
}
protected void onCreate(Bundle savedInstanceState){
super.onCreate(savedInstanceState);
c=getApplicationContext();
String s;
setContentView(R.layout.activity_main);
t=findViewById(R.id.t);
Intent i=getIntent();
if(i!=null)st(i);
int sdk=Build.VERSION.SDK_INT;
if(sdk>=23){
s="android.permission.WRITE_EXTERNAL_STORAGE";
if(checkSelfPermission(s)!=PackageManager.PERMISSION_GRANTED){
String per[]=new String[]{s};
requestPermissions(per,0);
pe=per;
s=null;
}else s=tool.lod();
}else s=tool.lod();
if(s!=null)ms(s);
}
public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults){
super.onRequestPermissionsResult(requestCode,permissions,grantResults);
if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
String s=tool.lod();
if(s!=null){
ms(s);
}
pe=null;
}else if(shouldShowRequestPermissionRationale("android.permission.WRITE_EXTERNAL_STORAGE")){
requestPermissions(pe,0);
}else{
pe=null;
Intent intent=new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
intent.setData(Uri.parse("package:rust.tool1"));
intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
Intent=intent;
startActivityForResult(intent,0);
}
}
protected void onNewIntent(Intent intent){
super.onNewIntent(intent);
st(intent);
}
public void st(Intent intent){
Uri o=intent.getData();
if(o==null){
o=intent.getParcelableExtra(Intent.EXTRA_STREAM);
}
if(o!=null){
String f=o.getPath();
t.setText(f);
mpt=f;
}
}
public void onActivityResult(int requestCode, int resultCode, Intent data){
super.onActivityResult(requestCode,resultCode,data);
if(checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE")==PackageManager.PERMISSION_GRANTED){
Intent=null;
}else startActivityForResult(Intent,0);
}
public void sw(View v){
int b=v.getId()-0x7f010000;
ms(tool.ws(mpt,b));
}
public void ld(View v){
String s=tool.lod();
if(s==null)s="加载完成";
ms(s);
}
}
