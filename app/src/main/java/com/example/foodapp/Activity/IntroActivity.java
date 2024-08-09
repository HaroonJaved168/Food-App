package com.example.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.example.foodapp.R;
import com.example.foodapp.databinding.ActivityIntroBinding;

import java.util.Timer;
import java.util.TimerTask;

public class IntroActivity extends BaseActivity {
    ActivityIntroBinding binding;
    ProgressBar pb;
    int count = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_intro);
        setContentView(binding.getRoot());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {



                Intent intent = new Intent(IntroActivity.this, FragmentMain.class);
                startActivity(intent);
//                finish();

            }
        }, 5000);

        pb = (ProgressBar) findViewById(R.id.progressBar);
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {

                count++;
                pb.setProgress(count);

                if(count==100){
                    t.cancel();
                }
            }
        };

        t.schedule(tt,0 , 100);





        setVariable();
        getWindow().setStatusBarColor(Color.parseColor("#FFE4B5"));
    }

    private void setVariable() {

        binding.loginBtn.setOnClickListener(v -> {

            if(mAuth.getCurrentUser()!=null){
                startActivity(new Intent(IntroActivity.this , MainActivity.class));
            }
            else {

                startActivity(new Intent(IntroActivity.this , LoginActivity.class));
            }

        });
        binding.singnupBtn.setOnClickListener(v -> startActivity(new Intent(IntroActivity.this , SignUpActivity.class)));
    }


}