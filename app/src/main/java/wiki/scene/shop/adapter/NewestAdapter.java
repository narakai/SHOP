package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunfusheng.glideimageview.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.NewestResultInfo;
import wiki.scene.shop.utils.DateUtil;
import wiki.scene.shop.widgets.RatioImageView;

/**
 * Case By:最新揭晓
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/6/29 15:27
 */

public class NewestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<NewestResultInfo.NewestInfo> list;

    public NewestAdapter(Context context, List<NewestResultInfo.NewestInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewestViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_newest_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final NewestViewHolder viewHolder = (NewestViewHolder) holder;
        NewestResultInfo.NewestInfo info = list.get(position);
        viewHolder.goodsName.setText(list.get(position).getTitle());
        if (info.getStatus() == 2) {
            viewHolder.layoutHasTime.setVisibility(View.VISIBLE);
            viewHolder.layoutNoTime.setVisibility(View.GONE);
            viewHolder.isAnnouncing.setVisibility(View.GONE);
            viewHolder.refreshTime(info.getOpen_time() * 1000 - System.currentTimeMillis());
        } else if (info.getStatus() == 3) {
            viewHolder.layoutHasTime.setVisibility(View.GONE);
            viewHolder.layoutNoTime.setVisibility(View.GONE);
            viewHolder.isAnnouncing.setVisibility(View.VISIBLE);
        } else {
            viewHolder.layoutHasTime.setVisibility(View.GONE);
            viewHolder.layoutNoTime.setVisibility(View.VISIBLE);
            viewHolder.isAnnouncing.setVisibility(View.GONE);
            if (info.getWinner() != null) {
                viewHolder.username.setText(info.getWinner().getNickname());
                //viewHolder.personTimes.setText(info.get);
                viewHolder.luckCode.setText(info.getLucky_code());
                viewHolder.announcedTime.setText(DateUtil.timeStampToStr(info.getOpen_time()));
            }
        }
        GlideImageLoader.create(viewHolder.goodsImage).loadImage(ShopApplication.configInfo.getFile_domain() + info.getThumb(), R.drawable.ic_default_goods_image);
        viewHolder.goodsTime.setText(String.format(context.getString(R.string.times_code), info.getCycle_code()));
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        int pos = holder.getAdapterPosition();
        NewestResultInfo.NewestInfo info = list.get(pos);
        if (holder instanceof NewestViewHolder) {
            ((NewestViewHolder) holder).refreshTime(info.getOpen_time() * 1000 - System.currentTimeMillis());
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if (holder instanceof NewestViewHolder) {
            ((NewestViewHolder) holder).newestCountDownView.stop();
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class NewestViewHolder extends RecyclerView.ViewHolder {
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
        @BindView(R.id.is_announcing)
        TextView isAnnouncing;

        NewestViewHolder(View view) {
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
}
