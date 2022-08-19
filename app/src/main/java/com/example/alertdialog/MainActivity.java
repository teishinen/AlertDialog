 package com.example.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 public class MainActivity extends AppCompatActivity {
     String[]str = new String[]{"梧桐树", "旺仔", "苦樱桃", "盛夏",
             "蝉鸣", "附中", "给某某", "哥"};                       //设置对话框中的items
     String str1 = "旺仔";
     boolean[]flag = new boolean[]{true, false, false, false,
             false, false, false, false};    //初始化的时候哪些项目有被选择
     String content;              //用于存放一些需要输出的内容
     //设置SimpleAdapter
     ListView listView;
     String[] title = new String[]{"dog", "kiss", "home", "date",
             "eating", "memory", "skating", "traveling"};
     int[] images = new int[]{R.drawable.img01, R.drawable.img02, R.drawable.img03, R.drawable.img04,
             R.drawable.img05, R.drawable.img06, R.drawable.img07, R.drawable.img08};
     SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //设置简单对话框
    public void dialog1(View view){                       //void后面的名称与button的onClick名称一致
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("某某");                         //设置对话框title
        builder.setMessage("我的骨骼说，我还是爱你。");      //设置对话框内容
        //设置对话框的按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "您单击了【确定】，收获了江添的么么哒", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "您单击了【取消】，收获了盛望的呜呜呜", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
    //设置简单列表对话框
    public void dialog2(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("元素");
        //builder.setMessage("内容");//有item的时候，Message就可以不要了
        //设置items的单击事件
        builder.setItems(str, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(MainActivity.this, "您选择的是：" + str[i], Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
    //设置单选列表对话框
    public void dialog3(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("标题");
        //这里的2是默认第二项被选中
        builder.setSingleChoiceItems(str, 2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                str1 = str[i];
            }
        });
        //加按钮，并显示选项内容
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, str1, Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
    //设置多选对话框
     public void dialog4(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("请选择令你印象最深的元素");
        //items选择项以及初始化选择项及单击事件
        builder.setMultiChoiceItems(str, flag, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                flag[i] = b;
            }
        });
        //设置确定按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                for (int j=0 ; j<flag.length ; j++){
                    if (flag[j] == true){
                        content += str[j] + ";";
                    }
                }
                Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
                content = "";
            }
        });
        builder.show();
     }
     //设置自定义对话框
     public void dialog5(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("自定义对话框");
        //设置适配器:simpleAdapter
         List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
         //通过循环将title和images数组内容放到List中
         for (int i=0 ; i<title.length ; i++){
             Map<String, Object> map = new HashMap<String, Object>();
             //name, picture为键值
             map.put("name", title[i]);
             map.put("picture", images[i]);
             list.add(map);
         }
         //适配器里的文字＋图片
         simpleAdapter = new SimpleAdapter(MainActivity.this, list,
                 R.layout.item, new String[]{"name", "picture"},
                 new int[]{R.id.textView, R.id.imageView});
         builder.setAdapter(simpleAdapter, new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                 Toast.makeText(MainActivity.this, title[i], Toast.LENGTH_SHORT).show();
             }
         });
         builder.show();
     }
}