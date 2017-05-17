package cn.studyjams.s2.sj0131.snail.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.studyjams.s2.sj0131.snail.R;
import cn.studyjams.s2.sj0131.snail.entity.Android;
import cn.studyjams.s2.sj0131.snail.utils.AppUtils;

/**
 * Created by panda.guo on 2017/5/17.
 */

public class AndroidAdapter extends BaseQuickAdapter<Android, BaseViewHolder> {

    public AndroidAdapter(@Nullable List<Android> data) {
        super(R.layout.item_android, data);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int positions) {
        super.onBindViewHolder(holder, positions);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Android item) {
        ImageView imageView = (ImageView) helper.getView(R.id.imageView);
        TextView tvTitle = (TextView) helper.getView(R.id.tvTitle);
        TextView tvDesc = (TextView) helper.getView(R.id.tvDesc);
        List<String> images = item.getImages();
        if (!AppUtils.isEmpty(images) && !TextUtils.isEmpty(images.get(0))) {
            Glide.with(mContext)
                    .load(images.get(0))
                    .asBitmap()
                    .centerCrop()
                    .into(imageView);
        } else {
            imageView.setImageResource(R.drawable.icon_guide_default);
        }
        tvTitle.setText(TextUtils.isEmpty(item.getWho()) ? "佚名" : item.getWho());
        tvDesc.setText(item.getDesc());
    }

}
