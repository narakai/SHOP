package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ta.utdid2.android.utils.StringUtils;
import com.w4lle.library.NineGridlayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.ShareListResultInfo;
import wiki.scene.shop.utils.DateUtil;
import wiki.scene.shop.widgets.RatioImageView;

/**
 * Case By:晒单
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/6/29 13:11
 */

public class ShareAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ShareListResultInfo.ShareListInfo> list;
    private boolean isMine;
    private OnClickShareOrderItemListener onClickShareOrderItemListener;

    public ShareAdapter(Context context, List<ShareListResultInfo.ShareListInfo> list, boolean isMine) {
        this.context = context;
        this.list = list;
        this.isMine = isMine;
    }

    public void setOnClickShareOrderItemListener(OnClickShareOrderItemListener onClickShareOrderItemListener) {
        this.onClickShareOrderItemListener = onClickShareOrderItemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShareViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_share_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ShareViewHolder viewHolder = (ShareViewHolder) holder;
        final ShareListResultInfo.ShareListInfo info = list.get(position);
        String mobile = info.getMobile();
        if (!TextUtils.isEmpty(mobile)) {
            mobile = mobile.replace(mobile.substring(3, 7), "****");
        }
        if (isMine) {
            Glide.with(context).load(ShopApplication.userInfo.getAvatar()).bitmapTransform(new CropCircleTransformation(context)).into(viewHolder.userAvater);
            viewHolder.userLevel.setText(String.valueOf(ShopApplication.userInfo.getLevel()));
            viewHolder.username.setText(StringUtils.isEmpty(ShopApplication.userInfo.getNickname()) ? ShopApplication.userInfo.getMobile() : ShopApplication.userInfo.getNickname());
        } else {
            Glide.with(context).load(ShopApplication.configInfo.getFile_domain() + info.getAvatar()).bitmapTransform(new CropCircleTransformation(context)).into(viewHolder.userAvater);
            viewHolder.userLevel.setText(String.valueOf(info.getLevel()));
            viewHolder.username.setText(StringUtils.isEmpty(info.getNickname()) ? mobile : info.getNickname());
        }
        viewHolder.content.setText(info.getContent());
        viewHolder.goodsName.setText(info.getTitle());
        viewHolder.goodsTime.setText(String.format(context.getString(R.string.share_goods_times), info.getCycle_code()));
        viewHolder.time.setText(DateUtil.convertTimeToFormat(info.getCreate_time()));
        viewHolder.zanNumber.setText(String.valueOf(info.getLike_number()));
        if (info.getLike() == 0) {
            viewHolder.imageZan.setImageResource(R.drawable.ic_zan_d);
        } else {
            viewHolder.imageZan.setImageResource(R.drawable.ic_zan_s);
        }
        ShareImagesAdapter shareImagesAdapter = new ShareImagesAdapter(context, info.getImages());
        viewHolder.shareImages.setAdapter(shareImagesAdapter);
        viewHolder.imageZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getLike() == 0) {
                    if (onClickShareOrderItemListener != null) {
                        onClickShareOrderItemListener.onClickItemZan(position);
                    }
                }
            }
        });
        viewHolder.tryLuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickShareOrderItemListener != null) {
                    onClickShareOrderItemListener.onClikcItemTryLuck(position);
                }
            }
        });
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
        @BindView(R.id.share_images)
        NineGridlayout shareImages;
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

    public interface OnClickShareOrderItemListener {
        void onClickItemZan(int position);

        void onClikcItemTryLuck(int position);
    }
}
