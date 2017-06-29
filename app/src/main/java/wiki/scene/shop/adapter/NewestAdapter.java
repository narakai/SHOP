package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;
import wiki.scene.shop.R;
import wiki.scene.shop.widgets.RatioImageView;

/**
 * Case By:最新揭晓
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/6/29 15:27
 */

public class NewestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> list;

    public NewestAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewestViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_newest_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewestViewHolder viewHolder = (NewestViewHolder) holder;
        viewHolder.goodsName.setText(list.get(position));
        if (position == 0) {
            viewHolder.layoutHasTime.setVisibility(View.VISIBLE);
            viewHolder.layoutNoTime.setVisibility(View.GONE);
        } else {
            viewHolder.layoutHasTime.setVisibility(View.GONE);
            viewHolder.layoutNoTime.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    static class NewestViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_image)
        RatioImageView goodsImage;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.goods_time)
        TextView goodsTime;
        @BindView(R.id.newest_countDownView)
        CountdownView newestCountDownView;
        @BindView(R.id.layout_has_time)
        LinearLayout layoutHasTime;
        @BindView(R.id.username)
        TextView username;
        @BindView(R.id.person_times)
        TextView personTimes;
        @BindView(R.id.luck_code)
        TextView luckCode;
        @BindView(R.id.announced_time)
        TextView announcedTime;
        @BindView(R.id.layout_no_time)
        LinearLayout layoutNoTime;

        NewestViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
