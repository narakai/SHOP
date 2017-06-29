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
import wiki.scene.shop.widgets.RatioImageView;

/**
 * Case By:晒单
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/6/29 13:11
 */

public class ShareAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> list;

    public ShareAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShareViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_share_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ShareViewHolder viewHolder = (ShareViewHolder) holder;
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    static class ShareViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.user_avater)
        RatioImageView userAvater;
        @BindView(R.id.user_level)
        TextView userLevel;
        @BindView(R.id.username)
        TextView username;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.goods_image)
        RatioImageView goodsImage;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.goods_time)
        TextView goodsTime;
        @BindView(R.id.image_zan)
        ImageView imageZan;
        @BindView(R.id.zan_number)
        TextView zanNumber;
        @BindView(R.id.try_luck)
        TextView tryLuck;

        ShareViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
