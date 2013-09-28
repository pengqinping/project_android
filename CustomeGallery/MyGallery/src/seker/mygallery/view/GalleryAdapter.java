package seker.mygallery.view;

import java.util.List;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class GalleryAdapter extends BaseAdapter
{
    private Context m_context;
    private List<ResolveInfo> m_infos;
    private PackageManager m_packageMgr;
    
    public GalleryAdapter(Context context, List<ResolveInfo> infos, PackageManager packageMgr)
    {
        m_context = context;
        m_infos = infos;
        m_packageMgr = packageMgr;
    }
    
    @Override
    public int getCount()
    {
        return m_infos.size();
    }

    @Override
    public Object getItem(int position)
    {
        return m_infos.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (null != convertView)
        {
            return convertView;
        }
        
        ResolveInfo info = m_infos.get(position);
        
        GalleryChild child = new GalleryChild(m_context);
        ImageView imageView = new ImageView(m_context);
        imageView.setScaleType(ScaleType.CENTER_INSIDE);
        imageView.setBackgroundDrawable(info.loadIcon(m_packageMgr));
        child.setContentView(imageView);
        
        child.setLabel((String) info.loadLabel(m_packageMgr));
        
        child.setId(position);
        
        return child;
    }

}
