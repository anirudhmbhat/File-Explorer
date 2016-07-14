package com.example.ani.calcfraggridview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ani on 27/1/16.
 */
public class ViewHolder {
    ImageView myImage;
    TextView title;
    TextView description;

    ViewHolder(View v){
        myImage= (ImageView) v.findViewById(R.id.singlerowimageView);
        title= (TextView) v.findViewById(R.id.singlerowtextView1);
        description= (TextView) v.findViewById(R.id.singlerowtextView2);
    }
}
