package com.example.huaishuo.gd1;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private EditText path;
    private ImageView imageView;
    private Button button;
    private Handler mHandler = new Handler(){

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    System.out.println("handle");
                    String pathtext = path.getText().toString();
                    //System.out.println(pathtext);
                    try{
                        Bitmap bitmap = ImageService.getImage(pathtext);
                        imageView.setImageBitmap(bitmap);
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),R.string.error,Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        };
    };
    private class MyTask extends TimerTask {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            mHandler.sendMessage(message);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy(new
                StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(
                new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        path = (EditText)this.findViewById(R.id.path);
        imageView = (ImageView)this.findViewById(R.id.imageView);
        button = (Button)this.findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new MyTask(), 1, 5000);

            }
        });
    }
}
