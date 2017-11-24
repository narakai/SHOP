package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.MineCollectionResultInfo;
import wiki.scene.shop.utils.NetTimeUtils;

/**
 * 我的收藏
 * Created by scene on 17-8-14.
 */

public class MineCollectAdapter extends RecyclerView.Adapter {
    private static final int TYPE_ONGOING = 0;
    private static final int TYPE_RENDING = 1;
    private static final int TYPE_RESULT = 2;
    private Context context;
    private List<MineCollectionResultInfo.MineCollectionInfo> list;

    public MineCollectAdapter(Context context, List<MineCollectionResultInfo.MineCollectionInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == TYPE_ONGOING) {
            return new ViewHolderType1(inflater.inflate(R.layout.fragment_mine_collect_item_type_1, parent, false));
        } else if (viewType == TYPE_RENDING) {
            return new ViewHolderType2(inflater.inflate(R.layout.fragment_mine_collect_item_type_2, parent, false));
        } else {
            return new ViewHolderType3(inflater.inflate(R.layout.fragment_mine_collect_item_type_3, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MineCollectionResultInfo.MineCollectionInfo info = list.get(position);
        if (holder instanceof ViewHolderType1) {
            ViewHolderType1 viewHolderType1 = (ViewHolderType1) holder;
            viewHolderType1.goodsName.setText(info.getTitle());
            viewHolderType1.goodsImage.loadImage(ShopApplication.configInfo.getFile_domain() + info.getThumb(), R.drawable.ic_default_goods_image);
            int precent = info.getCurrent_source() * 100 / info.getNeed_source();
            viewHolderType1.precent.setText(precent + "%");
            setGoodsTag(viewHolderType1.goodsTag, info.getType());
            viewHolderType1.ongoingProgressbar.setProgress(precent);
        } else if (holder instanceof ViewHolderType2) {
            ViewHolderType2 viewHolderType2 = (ViewHolderType2) holder;
            viewHolderType2.goodsName.setText(info.getTitle());
            viewHolderType2.goodsImage.loadImage(ShopApplication.configInfo.getFile_domain() + info.getThumb(), R.drawable.ic_default_goods_image);
            viewHolderType2.refreshTime(info.getOpen_time() * 1000 - NetTimeUtils.getWebsiteDatetime());
            setGoodsTag(viewHolderType2.goodsTag, info.getType());
        } else {
            ViewHolderType3 viewHolderType3 = (ViewHolderType3) holder;
            viewHolderType3.goodsName.setText(info.getTitle());
            viewHolderType3.goodsImage.loadImage(ShopApplication.configInfo.getFile_domain() + info.getThumb(), R.drawable.ic_default_goods_image);
            setGoodsTag(viewHolderType3.goodsTag, info.getType());
            viewHolderType3.luckCode.setText(info.getLucky_code());
        }
    }

    private void setGoodsTag(TextView tagView, int type) {
        switch (type) {
            case 1:
                tagView.setText(context.getString(R.string.second_open));
                tagView.setVisibility(View.VISIBLE);
                break;
            case 2:
                tagView.setVisibility(View.GONE);
                break;
            case 3:
                tagView.setVisibility(View.VISIBLE);
                tagView.setText(context.getString(R.string.price_10_area));
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getStatus() == 1) {
            //进行中
            return TYPE_ONGOING;
        } else if (list.get(position).getStatus() == 2) {
            //待揭晓
            return TYPE_RENDING;
        } else if (list.get(position).getStatus() == 3) {
            //揭晓中
            return TYPE_RESULT;
        } else {
            //已揭晓
            return TYPE_RESULT;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        int pos = holder.getAdapterPosition();
        MineCollectionResultInfo.MineCollectionInfo mineCollectionInfo = list.get(pos);
        if (holder instanceof ViewHolderType2) {
            ((ViewHolderType2) holder).refreshTime(mineCollectionInfo.getOpen_time() * 1000 - NetTimeUtils.getWebsiteDatetime());
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if (holder instanceof ViewHolderType2) {
            ((ViewHolderType2) holder).newestCountDownView.stop();
        }
    }

    class ViewHolderType1 extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_image)
        GlideImageView goodsImage;
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

        ViewHolderType1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolderType2 extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_image)
        GlideImageView goodsImage;
        @BindView(R.id.goods_tag)
        TextView goodsTag;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.newest_countDownView)
        CountdownView newestCountDownView;
        @BindView(R.id.goon_indiana)
        TextView goonIndiana;

        ViewHolderType2(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void refreshTime(long leftTime) {
            if (leftTime > 0) {
                newestCountDownView.start(leftTime);
            } else {
                newestCountDownView.stop();
                newestCountDownView.allShowZero();
            }
        }
    }

    static class ViewHolderType3 extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_image)
        GlideImageView goodsImage;
        @BindView(R.id.goods_tag)
        TextView goodsTag;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.winner)
        TextView winner;
        @BindView(R.id.luck_code)
        TextView luckCode;
        @BindView(R.id.goon_indiana)
        TextView goonIndiana;

        ViewHolderType3(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
