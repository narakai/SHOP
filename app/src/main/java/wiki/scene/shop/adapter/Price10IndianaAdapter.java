package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sunfusheng.glideimageview.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.Price10IndianaResultInfo;
import wiki.scene.shop.widgets.RatioImageView;

/**
 * 10元 秒开夺宝
 * Created by scene on 17-8-15.
 */

public class Price10IndianaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Price10IndianaResultInfo.Price10IndianaInfo> list;
    private int type;

    public Price10IndianaAdapter(Context context, List<Price10IndianaResultInfo.Price10IndianaInfo> list, int type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Price10IndianaViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_price_10_indiana_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Price10IndianaResultInfo.Price10IndianaInfo info = list.get(position);
        Price10IndianaViewHolder viewHolder = (Price10IndianaViewHolder) holder;
        viewHolder.goodsName.setText(info.getTitle());
        int precent = info.getCurrent_source() * 100 / info.getNeed_source();
        viewHolder.precent.setText(precent + "%");
        viewHolder.ongoingProgressbar.setProgress(precent);
        GlideImageLoader.create(viewHolder.goodsImage).loadImage(ShopApplication.configInfo.getFile_domain() + info.getThumb(), R.drawable.ic_default_image);
        if (this.type == 1) {
            viewHolder.goodsTag.setText(context.getString(R.string.price_10_area));
        } else {
            viewHolder.goodsTag.setText(context.getString(R.string.second_open));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class Price10IndianaViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_image)
        RatioImageView goodsImage;
        @BindView(R.id.goods_tag)
        TextView goodsTag;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.precent)
        TextView precent;
        @BindView(R.id.ongoing_progressbar)
        ProgressBar ongoingProgressbar;
        @BindView(R.id.goon_indiana)
        TextView goonIndiana;

        Price10IndianaViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
