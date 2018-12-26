package com.sheygam.masa_2018_g1_26_12_18;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("MY_TAG", "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Configuration conf = getResources().getConfiguration();

        if(conf.orientation == Configuration.ORIENTATION_PORTRAIT) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.root_container, new ListFragment(),"LIST_FRAG")
                    .commit();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e("MY_TAG", "onSaveInstanceState: ");
        if(!isFinishing()){
            getSupportFragmentManager().popBackStack();
            ListFragment fragment = (ListFragment) getSupportFragmentManager()
                    .findFragmentByTag("LIST_FRAG");
            Log.d("MY_TAG", "onSaveInstanceState: " + fragment);
            if(fragment!=null){
                getSupportFragmentManager()
                        .beginTransaction()
                        .remove(fragment)
                        .commit();
            }

//            int count = getSupportFragmentManager().getBackStackEntryCount();
//            for(int i = 0; i < count; i++){
//                getSupportFragmentManager().popBackStack();
//            }

        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        Log.e("MY_TAG", "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e("MY_TAG", "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e("MY_TAG", "onDestroy: ");

        super.onDestroy();
    }
}
