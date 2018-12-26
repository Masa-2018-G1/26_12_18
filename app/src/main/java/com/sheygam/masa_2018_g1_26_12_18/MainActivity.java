package com.sheygam.masa_2018_g1_26_12_18;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button changeTextBtn, clickBtn;
    private TextView myTxt;
    private EditText inputData;
    private MyTask myTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MY_TAG", "onCreate: " + savedInstanceState);
        Log.d("MY_TAG", "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeTextBtn = findViewById(R.id.changeTextBtn);
        clickBtn = findViewById(R.id.click_btn);
        myTxt = findViewById(R.id.myTxt);
        inputData = findViewById(R.id.inputData);
        clickBtn.setOnClickListener(this);
        changeTextBtn.setOnClickListener(this);
        if(savedInstanceState!=null){
            myTxt.setText(savedInstanceState.getString("TEXT",""));
        }

        myTask = (MyTask) getLastCustomNonConfigurationInstance();
        if(myTask!= null){
            myTask.attach(this);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("MY_TAG", "onSaveInstanceState: ");
        outState.putString("TEXT",myTxt.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        Log.d("MY_TAG", "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.changeTextBtn){
//            myTxt.setText("New text");
            myTask = new MyTask(this);
            myTask.execute();
        }else if(v.getId() == R.id.click_btn){
            startActivity(new Intent(this,SecondActivity.class));
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        if(myTask != null){
            return myTask;
        }
        return super.onRetainCustomNonConfigurationInstance();
    }

    class MyTask  extends AsyncTask<Void,Integer,Void>{

        private MainActivity activity;

        public MyTask(MainActivity activity) {
            this.activity = activity;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if(activity!= null) {
                activity.myTxt.setText(String.valueOf(values[0]));
            }

        }

        public void detach(){
            activity = null;
        }

        public void attach(MainActivity activity){
            this.activity = activity;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 100; i++) {
                Log.d("MY_TAG", "doInBackground: " + i);
                publishProgress(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
