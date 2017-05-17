package cn.studyjams.s2.sj0131.snail.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by panda.guo on 2017/5/5.
 */
public class DividerDecoration extends RecyclerView.ItemDecoration {

    private int mSapce;

    public DividerDecoration(int sapce) {
        mSapce = sapce;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSapce;
        outRect.right = mSapce;
        outRect.bottom = mSapce;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = mSapce;
        }
    }
}
