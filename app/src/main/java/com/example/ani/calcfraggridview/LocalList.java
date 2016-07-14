package com.example.ani.calcfraggridview;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ani on 27/1/16.
 */
public class LocalList extends AsyncTask {
    String path,backupb;
    String[] d;
    File f;
    File[] f1;
    int[] image={R.drawable.folder,R.drawable.file};
    frag frag2;
    ListView listview;
    TextView textview;
    boolean result=true;

    public void set(frag temp,String temppath,ListView list,TextView text){
        frag2=temp;
        listview=list;
        textview=text;
        path=temppath;
        f=new File(path);
  //      Log.e("Ani","Path = "+path);
        if (f.list() == null) {
            Toast.makeText(frag2.getActivity(), "Could not change Directory!", Toast.LENGTH_SHORT).show();
            result=false;
            //  path = backupb;
        }
        return;
    }


    @Override
    protected Object doInBackground(Object[] params) {

        if(result){

           // Log.e("Inside", "Background Local list");

            f1 = f.listFiles();
            Arrays.sort(f1);

            frag.seta(f1);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {

        textview.setText(path);
        if(result) {
            ArrayList<String> filename = new ArrayList<>();
            ArrayList<Integer> iconid = new ArrayList<>();
            ArrayList<String> filesize = new ArrayList<>();

            for (File i : f1) {
                filename.add(i.getName());
                if (i.isDirectory()) {
                    iconid.add(image[0]);
                    filesize.add("Folder");
                } else {
                    iconid.add(image[1]);
                    filesize.add(frag2.getStringFileSize(i.length()));
//                Log.e("Ani","i.length returns "+i.length());
                }
            }

            MyCustomAdapter customAdapter = new MyCustomAdapter(frag2.getActivity(), filename, filesize, iconid);
            listview.setAdapter(customAdapter);
            frag2.setLocalAdapter(customAdapter);
        }

        super.onPostExecute(o);
    }
}
