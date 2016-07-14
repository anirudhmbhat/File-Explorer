package com.example.ani.calcfraggridview;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ani on 15/1/16.
 */
public class frag extends Fragment {

    String path, backup;
    ListView listview;
    static File[] a;
    List<String> b;
    File f;
    TextView linear_text_view;
    MyCustomAdapter localAdapter;
    ArrayList<String> localitemsselected=new ArrayList<>();
    ArrayList<Integer> localitemsindex=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag, container, false);
        path = this.getArguments().getString("path");
//        Log.e("Ani", "Path = " + path);
        backup = path;
//        Log.e("Ani", "backup = " + backup);
        listview = (ListView) v.findViewById(R.id.frag_list_view);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        linear_text_view = (TextView) v.findViewById(R.id.linear_text_view);

        setlistview();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.home = false;
                String temppath = path + "/" + a[position].getName();
//                Log.e("Clicked ",temppath + "Path is " +path);
                if (a[position].isDirectory()) {
                    if(localitemsselected.size()>0){
                        localItemClickHandler(position);
                        localAdapter.notifyDataSetChanged();
                    }
                    else {
                        backup = path;
                        if (path.equals("/"))
                            path = path + a[position].getName();
                        else
                            path = temppath;
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("path", path);
//                    Log.e("listener","path is " + path);
                        frag frag2 = new frag();
                        frag2.setArguments(bundle2);
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.main_frame_layout, frag2);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                } else
                    localItemClickHandler(position);
                localAdapter.notifyDataSetChanged();
            }

        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                localItemClickHandler(position);
                localAdapter.notifyDataSetChanged();

                return true;
            }
        });

        return v;
    }

    public void setLocalAdapter(MyCustomAdapter setcustomadapter){
        localAdapter=setcustomadapter;
    }


    public void setlistview() {

        LocalList task=new LocalList();
        task.set(this, path, listview, linear_text_view);
        task.execute();

    }

    public static void seta(File[] b){
        a=b;
    }

    public static String getStringFileSize(double bytes){
        double kilobytes = bytes/1024;
        double megabytes = kilobytes/1024;
        double gigabytes = megabytes/1024;
        String rval = Double.toString(bytes)+" Bytes";
        if(kilobytes<1){}
        else if(megabytes<1){
            DecimalFormat numberFormat = new DecimalFormat("#.00");
            rval = numberFormat.format(kilobytes).toString()+" KB";
        }
        else if(gigabytes<1){
            DecimalFormat numberFormat = new DecimalFormat("#.00");
            rval = numberFormat.format(megabytes).toString()+" MB";
        }
        else {
            DecimalFormat numberFormat = new DecimalFormat("#.00");
            rval = numberFormat.format(gigabytes).toString()+" GB";
        }
        return rval;
    }

    public void localItemClickHandler(int position){
        if(localitemsselected.contains(a[position].getName())){
            localitemsselected.remove(a[position].getName());
            localitemsindex.remove(localitemsindex.indexOf(position));
            localAdapter.setselecteditems(localitemsindex);
        }
        else{
            localitemsselected.add(a[position].getName());
            localitemsindex.add(position);
            localAdapter.setselecteditems(localitemsindex);
          //  localAdapter.notifyDataSetChanged();
        }
    }
}
