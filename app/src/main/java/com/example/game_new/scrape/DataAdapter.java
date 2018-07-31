package com.example.game_new.scrape;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Admin on 17-05-2018.
 */

public class DataAdapter extends ArrayAdapter<data> {
    public DataAdapter(Context context, List<data> data) {
        super(context, 0,data);
    }
    @Override
    public View getView(int position,View convertView, ViewGroup parent)
    {
        View listview=convertView;
        if(listview==null)
        {
            listview= LayoutInflater.from(getContext()).inflate(R.layout.data_layout,parent,false);
        }
        data current=getItem(position);
        TextView title=(TextView) listview.findViewById(R.id.title);
        TextView value=(TextView) listview.findViewById(R.id.value);
        title.setText(current.getTitle());
        value.setText(current.getValue());
        return listview;
    }
}
