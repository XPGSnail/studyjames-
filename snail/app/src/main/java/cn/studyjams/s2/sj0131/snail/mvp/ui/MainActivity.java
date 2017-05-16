package cn.studyjams.s2.sj0131.snail.mvp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.studyjams.s2.sj0131.common.CommonApplication;
import cn.studyjams.s2.sj0131.snail.R;
import cn.studyjams.s2.sj0131.snail.di.component.DaggerMainComponent;
import cn.studyjams.s2.sj0131.snail.di.component.MainComponent;
import cn.studyjams.s2.sj0131.snail.di.module.ServiceModule;

import static cn.studyjams.s2.sj0131.snail.R.id.navigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(navigationView)
    NavigationView mNavigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    private FragmentManager mFragmentManager;
    private AndroidFragment mAndroidFragment;
    private MeiZiFragment mMeiZiFragment;

    public MainComponent getMainComponent() {
        return mMainComponent;
    }

    private MainComponent mMainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setComponent();
        mFragmentManager = getSupportFragmentManager();
        initFragment();
        initView();
        initListener();
        addFragment(mAndroidFragment);
    }

    private void initFragment() {
        if (mAndroidFragment == null) {
            mAndroidFragment = new AndroidFragment();
        }
        if (mMeiZiFragment == null) {
            mMeiZiFragment = new MeiZiFragment();
        }
    }

    private void initListener() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setCheckedItem(R.id.item_android);
    }

    private void initView() {
        setSupportActionBar(mToolbar);
    }

    private void setComponent() {
        mMainComponent = DaggerMainComponent.builder()
                .appComponent(CommonApplication.getInstance().getmAppComponent())
                .serviceModule(new ServiceModule())
                .build();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_android:
                Snackbar.make(mFab, "android 新闻查看", Snackbar.LENGTH_SHORT)
                        .setAction("1", null).show();
                addFragment(mAndroidFragment);
                hideFragment(mMeiZiFragment);
                break;
            case R.id.item_meizi:
                Snackbar.make(mFab, "meizi 福利？", Snackbar.LENGTH_SHORT)
                        .setAction("2", null).show();
                addFragment(mMeiZiFragment);
                hideFragment(mAndroidFragment);
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            ft.add(R.id.fl_container, fragment).commit();
        } else {
            ft.show(fragment).commit();
        }
    }

    private void hideFragment(Fragment fragment) {
        if (fragment.isAdded()) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.hide(fragment).commit();
        }
    }

}
