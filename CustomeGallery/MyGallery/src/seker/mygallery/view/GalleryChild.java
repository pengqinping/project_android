package seker.mygallery.view;

import seker.mygallery.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GalleryChild extends LinearLayout
{
    private LinearLayout m_gallerychild;
    
    private ImageView m_selectframe;
    private LinearLayout m_container;
    private TextView m_label;
    
    private LayoutInflater m_inflater;
    
    
    public GalleryChild(Context context)
    {
        super(context);
        
        m_inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        m_gallerychild = (LinearLayout)m_inflater.inflate(R.layout.gallerychild, null);
        
        m_selectframe = (ImageView)m_gallerychild.findViewById(R.id.selectedframe);
        m_container = (LinearLayout)m_gallerychild.findViewById(R.id.container);
        m_container.setPadding(15,15,15,15);
        
        m_label = (TextView)m_gallerychild.findViewById(R.id.label);
        
        setLayoutParams(new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, 
            ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void setContentView(View contentview)
    {
        setContentView(contentview, null);
    }
    
    public void setContentView(View contentview, ViewGroup.LayoutParams params)
    {
        m_container.addView(contentview);
        
        int width = 116;
        if (null != params)
        {
            width = params.width;
            m_container.getLayoutParams().width = params.width - 20;
        }
        
        addView(m_gallerychild, new ViewGroup.LayoutParams(width, 140));
    }
    
    
    @Override
    public void setSelected(boolean isSelected)
    {
        m_selectframe.setVisibility(isSelected ? VISIBLE : INVISIBLE);
    }
    
    public void setLabel(String strLabel)
    {
        if (null == strLabel || 0 == strLabel.length())
        {
            m_label.setVisibility(GONE);
        }
        else
        {
            m_label.setVisibility(VISIBLE);
            m_label.setText(strLabel);
        }
    }
    
    public void setLabel(String strLabel, int color)
    {
        setLabel(strLabel);
        m_label.setTextColor(color);
    }
}
