package seker.mygallery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seker.mygallery.R;
import seker.mygallery.view.GalleryAdapter;
import seker.mygallery.view.MyAdapterView;
import seker.mygallery.view.MyGallery;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity implements  Runnable
{
    public static final String NAME = "name";
    public static final String NUMBER = "number";
    public static final String IMAGE = "image";
    
    SimpleAdapter m_simpleadapter;
    GalleryAdapter m_galleryadapter;
    
    private List<Map<String, Object>> m_list;
    
    private static final int FINISH_GET_APPS = 1024;
    List<ResolveInfo> m_infos;
    PackageManager m_packageMgr;
    
    MyGallery g1, g2;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        m_packageMgr = getPackageManager();
        initMyGallery1();
        
        Thread thread = new Thread(this);
        thread.start();
    }
    
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what)
            {
            case FINISH_GET_APPS:
                initMyGallery2();
                break;
            }
        }
    };
    
    @Override
    public void run()
    {
        
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        m_infos = m_packageMgr.queryIntentActivities(mainIntent, 0);
        Collections.sort(m_infos, new ResolveInfo.DisplayNameComparator(m_packageMgr));
        
        Message message = new Message();
        message.what = FINISH_GET_APPS;
        handler.sendMessage(message);
    }
    
    private void initMyGallery1()
    {
        initData();
        m_simpleadapter = new SimpleAdapter(this, m_list, R.layout.gallerychild_box
                , new String[] {NAME, NUMBER, IMAGE}
                , new int[] {R.id.name, R.id.box_num, R.id.AppBtnImageView});
        
        g1 = (MyGallery) findViewById(R.id.myGallery1);
        g1.setAdapter(m_simpleadapter);
        g1.setSelection(0);
        g1.setOnItemClickListener(itemclick_listener1);
    }
    
    private void initMyGallery2()
    {
        m_galleryadapter = new GalleryAdapter(this, m_infos, m_packageMgr);
        g2 = (MyGallery) findViewById(R.id.myGallery2);
        g2.setAdapter(m_galleryadapter);
        g2.setSelection(0);
        g2.setOnItemClickListener(itemclick_listener2);
    }
    
    MyGallery.OnItemClickListener itemclick_listener1 = new MyGallery.OnItemClickListener()
    {
        private int testIndex = 0;
        @Override
        public void onItemClick(MyAdapterView<?> parent, View view, int position, long id) 
        {
            if (0 == position) 
            {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(NAME, "add" + testIndex);
                map.put(NUMBER, testIndex ++);
                map.put(IMAGE, R.drawable.img00);
                m_list.add(1, map);

                m_simpleadapter.notifyDataSetChanged();
            }
        }
    };
    
    MyGallery.OnItemClickListener itemclick_listener2 = new MyGallery.OnItemClickListener()
    {
        @Override
        public void onItemClick(MyAdapterView<?> parent, View view, int position, long id) 
        {
            ResolveInfo info = m_infos.get(position);
            String packageName = info.activityInfo.applicationInfo.packageName;
            String className = info.activityInfo.name;
            Log.d("StartActivity", "packageName = " + packageName + "\tclassName = " + className);
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(new ComponentName(packageName, className));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            
            startActivity(intent);
        }
    };
    
    private void initData()
    {
        m_list = new ArrayList<Map<String, Object>>();
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(NAME, "img1");
        map.put(NUMBER, 1);
        map.put(IMAGE, R.drawable.img01);
        m_list.add(map);
        
        map = new HashMap<String, Object>();
        map.put(NAME, "img2");
        map.put(NUMBER, 2);
        map.put(IMAGE, R.drawable.img02);
        m_list.add(map);
        
        map = new HashMap<String, Object>();
        map.put(NAME, "img3");
        map.put(NUMBER, 3);
        map.put(IMAGE, R.drawable.img03);
        m_list.add(map);
        
        map = new HashMap<String, Object>();
        map.put(NAME, "img4");
        map.put(NUMBER, 4);
        map.put(IMAGE, R.drawable.img04);
        m_list.add(map);
        
        map = new HashMap<String, Object>();
        map.put(NAME, "img5");
        map.put(NUMBER, 5);
        map.put(IMAGE, R.drawable.img05);
        m_list.add(map);
        
        map = new HashMap<String, Object>();
        map.put(NAME, "img6");
        map.put(NUMBER, 6);
        map.put(IMAGE, R.drawable.img06);
        m_list.add(map);
        
        map = new HashMap<String, Object>();
        map.put(NAME, "img7");
        map.put(NUMBER, 7);
        map.put(IMAGE, R.drawable.img07);
        m_list.add(map);
        
        map = new HashMap<String, Object>();
        map.put(NAME, "img8");
        map.put(NUMBER, 8);
        map.put(IMAGE, R.drawable.img08);
        m_list.add(map);
    }
}
