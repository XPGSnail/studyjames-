package cn.studyjams.s2.sj0131.snail.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.studyjams.s2.sj0131.snail.R;
import cn.studyjams.s2.sj0131.snail.entity.MeiZi;
import cn.studyjams.s2.sj0131.snail.utils.ImageLoader;
import cn.studyjams.s2.sj0131.snail.widget.ScaleImageView;

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
        final ScaleImageView imageView = (ScaleImageView) helper.getView(R.id.imageView);
        imageView.setOriginalSize(item.getWidth(),item.getHeight());
        ImageLoader.loadImage(item.getUrl(),imageView,mContext);
    }


}
