package com.example.ani.calcfraggridview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ani on 27/1/16.
 */
public class MyCustomAdapter extends ArrayAdapter<String> {
    private Activity context;
    private ArrayList<String> filename;
    private ArrayList<String> filesize;
    private ArrayList<Integer> iconid;
    private ArrayList<Integer> selecteditem= new ArrayList<>();

    MyCustomAdapter(Activity c,ArrayList<String> string,ArrayList<String> filesize,ArrayList<Integer> imageid){
        super(c,R.layout.single_row,string);
        this.filename=string;
        this.filesize=filesize;
        this.iconid=imageid;
        this.context=c;
    }

    public void setselecteditems(ArrayList<Integer> selecteditem){
        this.selecteditem=selecteditem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        ViewHolder holder=null;
        if(row==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.single_row,parent,false);
            holder=new ViewHolder(row);
            row.setTag(holder);
        }
        else{
            holder= (ViewHolder) row.getTag();
        }

        holder.myImage.setImageResource(iconid.get(position));
        holder.title.setText(filename.get(position));
        holder.description.setText(filesize.get(position));

//        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View row=inflater.inflate(R.layout.single_row,parent,false);
//
//        ImageView myImage= (ImageView) row.findViewById(R.id.singlerowimageView);
//        TextView title= (TextView) row.findViewById(R.id.singlerowtextView1);
//        TextView description= (TextView) row.findViewById(R.id.singlerowtextView2);
//
//        myImage.setImageResource(iconid.get(position));
//        title.setText(filename.get(position));
//        description.setText(filesize.get(position));

        if(selecteditem.contains(position)){
            row.setBackgroundColor(Color.parseColor("#515ab1"));
        }
        else
            row.setBackgroundColor(Color.parseColor("#262a53"));


        return row;
    }
}
