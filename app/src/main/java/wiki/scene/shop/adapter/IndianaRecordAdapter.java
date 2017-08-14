package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.MineOrderInfo;
import wiki.scene.shop.utils.DateUtil;

/**
 * Case By:夺宝记录
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/7/5 13:52
 */

public class IndianaRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ONGOING = 0;
    private static final int TYPE_RENDING = 1;
    private static final int TYPE_RESULT = 2;
    private Context context;
    private List<MineOrderInfo> list;
    private IndianaRecordItemButtonClickListener indianaRecordItemButtonClickListener;

    public IndianaRecordAdapter(Context context, List<MineOrderInfo> list) {
        this.context = context;
        this.list = list;
    }

    public void setIndianaRecordItemButtonClickListener(IndianaRecordItemButtonClickListener indianaRecordItemButtonClickListener) {
        this.indianaRecordItemButtonClickListener = indianaRecordItemButtonClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == TYPE_ONGOING) {
            return new OnGoingViewHolder(inflater.inflate(R.layout.fragement_indiana_record_item_ongoing, parent, false));
        } else if (viewType == TYPE_RENDING) {
            return new PendingViewHolder(inflater.inflate(R.layout.fragement_indiana_record_item_pending, parent, false));
        } else {
            return new IndianaResultViewHolder(inflater.inflate(R.layout.fragment_win_record_item, parent, false));
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MineOrderInfo info = list.get(position);
        if (holder instanceof OnGoingViewHolder) {
            OnGoingViewHolder onGoingViewHolder = (OnGoingViewHolder) holder;
            onGoingViewHolder.goodsName.setText(info.getTitle());
            Glide.with(context).load(ShopApplication.configInfo.getFile_domain() + info.getThumb()).fitCenter().into(onGoingViewHolder.goodsImage);
            if (info.getOrder_status() == 1) {
                //未支付的订单
                onGoingViewHolder.goonIndiana.setText(R.string.pay_now);
                onGoingViewHolder.seeCodes.setVisibility(View.GONE);
            } else {
                //已支付的订单
                onGoingViewHolder.goonIndiana.setText(R.string.goon_indiana);
                onGoingViewHolder.seeCodes.setVisibility(View.VISIBLE);
            }
            onGoingViewHolder.goonIndiana.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (indianaRecordItemButtonClickListener != null) {
                        if (info.getOrder_status() == 1) {
                            indianaRecordItemButtonClickListener.onClickItemPay(position);
                        } else {
                            indianaRecordItemButtonClickListener.toGoodsDetail(position);
                        }
                    }
                }
            });
            onGoingViewHolder.seeCodes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (indianaRecordItemButtonClickListener != null) {
                        indianaRecordItemButtonClickListener.seeAllCodes(position);
                    }
                }
            });
            onGoingViewHolder.personTimes.setText(String.valueOf(info.getNumber()));
            setGoodsTag(onGoingViewHolder.goodsTag, info.getType());
            onGoingViewHolder.ongoingProgressbar.setProgress(info.getCurrent_source() * 100 / info.getNeed_source());
        } else if (holder instanceof PendingViewHolder) {
            PendingViewHolder pendingViewHolder = (PendingViewHolder) holder;
            pendingViewHolder.goodsName.setText(info.getTitle());
            Glide.with(context).load(ShopApplication.configInfo.getFile_domain() + info.getThumb()).fitCenter().into(pendingViewHolder.goodsImage);
            pendingViewHolder.personTimes.setText(String.valueOf(info.getNumber()));
            pendingViewHolder.refreshTime(info.getOpen_time() * 1000 - System.currentTimeMillis());
            setGoodsTag(pendingViewHolder.goodsTag, info.getType());
        } else {
            IndianaResultViewHolder indianaResultViewHolder = (IndianaResultViewHolder) holder;
            indianaResultViewHolder.goodsName.setText(info.getTitle());
            Glide.with(context).load(ShopApplication.configInfo.getFile_domain() + info.getThumb()).fitCenter().into(indianaResultViewHolder.goodsImage);
            setGoodsTag(indianaResultViewHolder.goodsTag, info.getType());
            indianaResultViewHolder.joinTimes.setText(String.format(context.getString(R.string.xx_fen), info.getWinner_codes().size()));
            indianaResultViewHolder.announcedTime.setText(DateUtil.timeStampToStr(info.getOpen_time()));
            indianaResultViewHolder.personTimes.setText(String.valueOf(info.getNumber()));
            indianaResultViewHolder.winnerName.setText(info.getWinner_nickname());
            indianaResultViewHolder.luckCode.setText(info.getLucky_code());
            if (info.getLucky_user_id().equals(info.getUser_id())) {
                indianaResultViewHolder.goToShareOrder.setVisibility(View.VISIBLE);
            } else {
                indianaResultViewHolder.goToShareOrder.setVisibility(View.GONE);
            }
            indianaResultViewHolder.goToShareOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(indianaRecordItemButtonClickListener!=null){
                        indianaRecordItemButtonClickListener.goToShareOrder(position);
                    }
                }
            });
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
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getCycle_status() == 1) {
            //进行中
            return TYPE_ONGOING;
        } else if (list.get(position).getCycle_status() == 2) {
            //待揭晓
            return TYPE_RENDING;
        } else if (list.get(position).getCycle_status() == 3) {
            //揭晓中
            return TYPE_RESULT;
        } else {
            //已揭晓
            return TYPE_RESULT;
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        int pos = holder.getAdapterPosition();
        MineOrderInfo mineOrderInfo = list.get(pos);
        if (holder instanceof PendingViewHolder) {
            ((PendingViewHolder) holder).refreshTime(mineOrderInfo.getOpen_time() * 1000 - System.currentTimeMillis());
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if (holder instanceof PendingViewHolder) {
            ((PendingViewHolder) holder).newestCountDownView.stop();
        }
    }

    /**
     * Case By: 进行中
     * Author：scene on 2017/7/5 13:54
     */
    class OnGoingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.goods_tag)
        TextView goodsTag;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.person_times)
        TextView personTimes;
        @BindView(R.id.ongoing_progressbar)
        ProgressBar ongoingProgressbar;
        @BindView(R.id.goon_indiana)
        TextView goonIndiana;
        @BindView(R.id.see_codes)
        TextView seeCodes;

        OnGoingViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    class PendingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.goods_tag)
        TextView goodsTag;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.person_times)
        TextView personTimes;
        @BindView(R.id.newest_countDownView)
        CountdownView newestCountDownView;

        PendingViewHolder(View view) {
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

    class IndianaResultViewHolder extends RecyclerView.ViewHolder {
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
        @BindView(R.id.go_to_share_order)
        TextView goToShareOrder;

        IndianaResultViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface IndianaRecordItemButtonClickListener {
        void onClickItemPay(int position);

        void toGoodsDetail(int position);

        void seeAllCodes(int position);

        void goToShareOrder(int position);
    }
}
