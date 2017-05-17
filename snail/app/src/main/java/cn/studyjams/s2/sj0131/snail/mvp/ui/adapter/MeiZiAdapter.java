package cn.studyjams.s2.sj0131.snail.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.studyjams.s2.sj0131.snail.R;
import cn.studyjams.s2.sj0131.snail.entity.MeiZi;

/**
 * Created by panda.guo on 2017/5/17.
 */

public class MeiZiAdapter extends BaseQuickAdapter<MeiZi, BaseViewHolder> {

    public MeiZiAdapter(@Nullable List<MeiZi> data) {
        super(R.layout.item_meizi, data);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int positions) {
        super.onBindViewHolder(holder, positions);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MeiZi item) {
        final ImageView imageView = (ImageView) helper.getView(R.id.imageView);
        Glide.with(mContext)
                .load(item.getUrl())
                .crossFade()
                .centerCrop()
                .override(800, 800)
                .into(imageView);
    }

}
