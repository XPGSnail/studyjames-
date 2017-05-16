package cn.studyjams.s2.sj0131.snail.mvp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.studyjams.s2.sj0131.common.CommonApplication;
import cn.studyjams.s2.sj0131.snail.R;
import cn.studyjams.s2.sj0131.snail.di.component.DaggerMainComponent;
import cn.studyjams.s2.sj0131.snail.di.component.MainComponent;
import cn.studyjams.s2.sj0131.snail.di.module.ServiceModule;

public class MainActivity extends AppCompatActivity {

    public MainComponent getMainComponent() {
        return mMainComponent;
    }

    private MainComponent mMainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setComponent();

    }

    private void setComponent() {
        mMainComponent = DaggerMainComponent.builder()
                .appComponent(CommonApplication.getInstance().getmAppComponent())
                .serviceModule(new ServiceModule())
                .build();
    }
}
