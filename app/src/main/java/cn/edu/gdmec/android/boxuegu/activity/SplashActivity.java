package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import cn.edu.gdmec.android.boxuegu.MainActivity;
import cn.edu.gdmec.android.boxuegu.R;

/**
 * Created by GJ on 2018/3/20.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init() {
        TextView tv_version = (TextView)findViewById(R.id.tv_version);
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            tv_version.setText("v" + info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            tv_version.setText("v");
        }

        //利用timer让此界面延迟三秒后跳转，timer有一个线程，这个线程不断执行task
        Timer timer = new Timer();
        //timertask实现runnable借口，TimeTask类表示在一个指定时间内执行的task
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        };
        timer.schedule(task,3000);//设置这个task在延迟三秒后自动执行
    }

}
