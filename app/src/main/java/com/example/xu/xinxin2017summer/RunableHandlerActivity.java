package com.example.xu.xinxin2017summer;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RunableHandlerActivity extends BaseActivity {
    @BindView(R.id.activity_Runable_Handler_et)
    EditText editText;

    @BindView(R.id.activity_Runable_Handler_bt)
    Button button;
    
    private View.OnClickListener startListener;
    private View.OnClickListener stopListener;
    private View.OnClickListener resetListener;

    Handler handler = new Handler();
    private int time = 0;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;
            if(time >= 0){
                editText.setText(String.valueOf(time));
                //public final boolean postDelayed(Runnable r, long delayMilles)
//                handler.post(runnable);
                handler.postDelayed(runnable,1000);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runable_handler);
        ButterKnife.bind(this);
        startListener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                button.setText("Stop");
                editText.setEnabled(false);
                button.setOnClickListener(stopListener);
                time = Integer.valueOf(editText.getText().toString());
                handler.postDelayed(runnable, 1000);
            }
        };

        stopListener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                button.setText("reset");
                button.setOnClickListener(resetListener);
                handler.removeCallbacks(runnable);
            }

        };

        resetListener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                button.setText("Start");
                editText.setEnabled(true);
                button.setOnClickListener(startListener);
            }

        };
        button.setOnClickListener(startListener);

    }
}
