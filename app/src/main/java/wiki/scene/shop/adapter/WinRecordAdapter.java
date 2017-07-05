package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;

/**
 * Case By:中奖记录
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/7/5 12:04
 */

public class WinRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> list;

    public WinRecordAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WinRecordViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_win_record_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        WinRecordViewHolder viewHolder= (WinRecordViewHolder) holder;
        viewHolder.goodsName.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    static class WinRecordViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.goods_tag)
        TextView goodsTag;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.person_times)
        TextView personTimes;
        @BindView(R.id.winner_name)
        TextView winnerName;
        @BindView(R.id.join_times)
        TextView joinTimes;
        @BindView(R.id.luck_code)
        TextView luckCode;
        @BindView(R.id.announced_time)
        TextView announcedTime;

        WinRecordViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
