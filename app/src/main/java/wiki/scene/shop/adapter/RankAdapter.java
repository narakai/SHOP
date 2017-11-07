package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunfusheng.glideimageview.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.RankInfo;

/**
 * 排行榜
 * Created by scene on 17-8-16.
 */

public class RankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<RankInfo> list;

    public RankAdapter(Context context, List<RankInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RankViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_rank_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RankViewHolder viewHolder = (RankViewHolder) holder;
        RankInfo info = list.get(position);
        viewHolder.number.setText(String.valueOf(position + 4));
        GlideImageLoader.create(viewHolder.avater).loadCircleImage(ShopApplication.configInfo.getFile_domain() + info.getAvatar(), R.drawable.ic_default_avater);
        viewHolder.nickname.setText(info.getNickname());
        viewHolder.winTime.setText(String.valueOf(info.getWin_times()));
        viewHolder.winPrice.setText(info.getTotal_cost());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    static class RankViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.avater)
        ImageView avater;
        @BindView(R.id.nickname)
        TextView nickname;
        @BindView(R.id.win_time)
        TextView winTime;
        @BindView(R.id.win_price)
        TextView winPrice;

        RankViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
