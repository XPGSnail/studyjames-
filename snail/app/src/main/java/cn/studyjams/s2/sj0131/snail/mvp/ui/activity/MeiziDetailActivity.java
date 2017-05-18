package cn.studyjams.s2.sj0131.snail.mvp.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.studyjams.s2.sj0131.snail.R;
import cn.studyjams.s2.sj0131.snail.entity.MeiZi;

public class MeiziDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.photoView)
    PhotoView mPhotoView;
    public static final String TRANSIT_PIC = "picture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizi_detail);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        MeiZi meiZi = getIntent().getParcelableExtra("data");
        Glide.with(this).load(meiZi.getUrl()).asBitmap().centerCrop().into(mPhotoView);
    }

    private void initView() {
        ViewCompat.setTransitionName(mPhotoView, TRANSIT_PIC);
        mToolbar.setTitle("MeiZi");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
