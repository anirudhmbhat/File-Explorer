package com.example.ani.calcfraggridview;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    public final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE=0;
//    public ViewPager pager;
//    public Mypageradapter padapter;
    frag frag1;
    boolean restored=false;
    public static boolean home=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar= (Toolbar) findViewById(R.id.appbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
          //      Log.e("dwaraka","Permission request initaited");
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            }
        }
        else
        {
            if(savedInstanceState!=null){
                android.app.Fragment temp = getFragmentManager().getFragment(savedInstanceState, "myFragment");
                frag1= (frag) temp;
            }
            else
            loadbundle();
        }
    }


    public void loadbundle(){

        Bundle bundle=new Bundle();
//        File temp=new File(System.getenv("SECONDARY_STORAGE"));

        bundle.putString("path", Environment.getExternalStorageDirectory().getAbsolutePath());
   //     bundle.putString("path", System.getenv("SECONDARY_STORAGE"));

   //     bundle.putString("path", "/");
   //     Log.e("path", Environment.getExternalStorageState());

        frag1 = new frag();
        frag1.setArguments(bundle);
   //     Log.e("Main", "Path= " + frag1.getArguments().getString("path"));
        FragmentTransaction t = getFragmentManager().beginTransaction();
        t.add(R.id.main_frame_layout,frag1);
        t.commit();
//        pager.setAdapter(padapter);

    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() == 0)
            android.os.Process.killProcess(android.os.Process.myPid());
        else
            getFragmentManager().popBackStack();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(getBaseContext(), "Work in progress for Settings :P", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2:
                Toast.makeText(getBaseContext(), "Work in progress for Help :P", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //////////
                    loadbundle();

                } else {
                    // permission denied
                }
                return;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getFragmentManager().putFragment(outState, "myFragment", frag1);
    }

    //    public class Mypageradapter extends FragmentStatePagerAdapter {
//        private int NUM_COUNT=2;
//
//
//        public Mypageradapter(android.support.v4.app.FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            switch (position){
//                case 0:
//                    Bundle bundle=new Bundle();
//
//                    bundle.putString("path", Environment.getExternalStorageDirectory().getAbsolutePath());
//                    frag1 = new frag();
//                    frag1.setArguments(bundle);
//                    return frag1;
//                case 1: return frag1.Newinstance();
//            }
//            return null;
//        }
//
//        @Override
//        public int getCount() {
//            return NUM_COUNT;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch(position)
//            {
//                case 0: return "Fragment A";
////                break;
//                case 1:return "Fragment B";
////                break;
//            }
//            return null;
//        }
//    }
}
