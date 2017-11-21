package wiki.scene.shop.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.sunfusheng.glideimageview.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.MineOrderInfo;
import wiki.scene.shop.utils.DateFormatUtils;
import wiki.scene.shop.utils.PriceUtil;
import wiki.scene.shop.widgets.RatioImageView;

/**
 * Case By:夺宝记录
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/7/5 13:52
 */

public class IndianaRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<MineOrderInfo> list;
    private IndianaRecordItemButtonClickListener indianaRecordItemButtonClickListener;
    //用于退出 Activity,避免 Countdown，造成资源浪费。
    private SparseArray<CountDownTimer> countDownCounters;

    public IndianaRecordAdapter(Context context, List<MineOrderInfo> list) {
        this.context = context;
        this.list = list;
        this.countDownCounters = new SparseArray<>();
    }

    /**
     * 清空当前 CountTimeDown 资源
     */
    public void cancelAllTimers() {
        if (countDownCounters == null) {
            return;
        }
        for (int i = 0, length = countDownCounters.size(); i < length; i++) {
            CountDownTimer cdt = countDownCounters.get(countDownCounters.keyAt(i));
            if (cdt != null) {
                cdt.cancel();
            }
        }
    }

    public void setIndianaRecordItemButtonClickListener(IndianaRecordItemButtonClickListener indianaRecordItemButtonClickListener) {
        this.indianaRecordItemButtonClickListener = indianaRecordItemButtonClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new IndianaRecordViewHolder(inflater.inflate(R.layout.fragement_indiana_record_item_ongoing, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MineOrderInfo info = list.get(position);
        final IndianaRecordViewHolder viewHolder = (IndianaRecordViewHolder) holder;
        //倒计时
        CountDownTimer countDownTimer = countDownCounters.get(viewHolder.countdownView.hashCode());
        if (countDownTimer != null) {
            //将复用的倒计时清除
            countDownTimer.cancel();
        }
        long timer = info.getOpen_time() * 1000 - System.currentTimeMillis();
        if (timer > 0) {
            countDownTimer = new CountDownTimer(timer, 1000) {
                public void onTick(long millisUntilFinished) {
                    viewHolder.countdownView.setText(DateFormatUtils.getHoursByNow(info.getOpen_time()));
                }

                public void onFinish() {
                    viewHolder.textGoodsStatus.setText("正在开奖");
                    viewHolder.textGoodsStatus.setTextColor(ContextCompat.getColor(context, R.color.text_color_title));
                    viewHolder.countdownView.setVisibility(View.GONE);
                    viewHolder.textGoodsStatus.setVisibility(View.VISIBLE);
                }
            }.start();
            //将此 countDownTimer 放入list.
            countDownCounters.put(viewHolder.countdownView.hashCode(), countDownTimer);
        } else {
            viewHolder.textGoodsStatus.setText("正在开奖");
            viewHolder.textGoodsStatus.setTextColor(ContextCompat.getColor(context, R.color.text_color_title));
            viewHolder.countdownView.setVisibility(View.GONE);
            viewHolder.textGoodsStatus.setVisibility(View.VISIBLE);
        }

        if (info.getStatus() == 2) {
            //已支付未开奖
            if (info.getOpen_time() * 1000 > System.currentTimeMillis()) {
                //需要倒计时
                viewHolder.imageGoodsStatus.setImageResource(R.drawable.ic_goods_state_wait);
                viewHolder.imageGoodsStatus.setVisibility(View.VISIBLE);
                viewHolder.stateImage.setVisibility(View.GONE);
                viewHolder.winCode.setText("待揭晓...");
                viewHolder.countdownView.setVisibility(View.VISIBLE);
                viewHolder.textGoodsStatus.setVisibility(View.GONE);
            } else {
                //不需要倒计时
                viewHolder.textGoodsStatus.setText("正在开奖");
                viewHolder.textGoodsStatus.setTextColor(ContextCompat.getColor(context, R.color.text_color_title));
                viewHolder.imageGoodsStatus.setVisibility(View.GONE);
                viewHolder.stateImage.setVisibility(View.GONE);
                viewHolder.winCode.setText("待揭晓...");
                viewHolder.countdownView.setVisibility(View.GONE);
                viewHolder.textGoodsStatus.setVisibility(View.VISIBLE);
            }
        } else if (info.getStatus() == 3) {
            //中奖
            viewHolder.imageGoodsStatus.setImageResource(R.drawable.ic_goods_state_win);
            viewHolder.textGoodsStatus.setText("恭喜获胜");
            viewHolder.textGoodsStatus.setTextColor(ContextCompat.getColor(context, R.color.color_theme));
            viewHolder.imageGoodsStatus.setVisibility(View.VISIBLE);
            viewHolder.stateImage.setVisibility(View.VISIBLE);
            viewHolder.winCode.setText(info.getSsc_result());
            viewHolder.countdownView.setVisibility(View.GONE);
            viewHolder.textGoodsStatus.setVisibility(View.VISIBLE);
        } else {
            //未中奖
            viewHolder.imageGoodsStatus.setImageResource(R.drawable.ic_goods_state_fail);
            viewHolder.textGoodsStatus.setText("再接再厉");
            viewHolder.textGoodsStatus.setTextColor(ContextCompat.getColor(context, R.color.text_color_title));
            viewHolder.imageGoodsStatus.setVisibility(View.VISIBLE);
            viewHolder.stateImage.setVisibility(View.GONE);
            viewHolder.winCode.setText(info.getSsc_result());
            viewHolder.countdownView.setVisibility(View.GONE);
            viewHolder.textGoodsStatus.setVisibility(View.VISIBLE);
        }
        viewHolder.goodsCycleCode.setText("第" + info.getCycle_code() + "期");
        viewHolder.buyTime.setText(TimeUtils.millis2String(info.getCreate_time() * 1000));
        viewHolder.goodsName.setText(info.getName());
        GlideImageLoader.create(viewHolder.goodsImage).loadImage(ShopApplication.configInfo.getFile_domain() + info.getThumb(), R.drawable.ic_default_goods_image);
        viewHolder.buyMoney.setText("￥" + PriceUtil.getPrice(info.getCost()));
        viewHolder.cycleCode.setText(info.getCycle_code());
        String playWayStr = "";
        switch (info.getBuy_type()) {
            case AppConfig.BUY_TYPE_BIG:
                playWayStr = "[尾号：大]";
                break;
            case AppConfig.BUY_TYPE_SMALL:
                playWayStr = "[尾号：小]";
                break;
            case AppConfig.BUY_TYPE_SINGLE:
                playWayStr = "[尾号：单数]";
                break;
            case AppConfig.BUY_TYPE_DOUBLE:
                playWayStr = "[尾号：双数]";
                break;
            case AppConfig.BUY_TYPE_BIG_SINGLE:
                playWayStr = "[后2位：大单]";
                break;
            case AppConfig.BUY_TYPE_BIG_DOUBLE:
                playWayStr = "[后2位：大双]";
                break;
            case AppConfig.BUY_TYPE_SMALL_SINGLE:
                playWayStr = "[后2位：小单]";
                break;
            case AppConfig.BUY_TYPE_SMALL_DOUBLE:
                playWayStr = "[后2位：小双]";
                break;
            case AppConfig.BUY_TYPE_NUM_1:
                playWayStr = "[尾号：1]";
                break;
            case AppConfig.BUY_TYPE_NUM_2:
                playWayStr = "[尾号：2]";
                break;
            case AppConfig.BUY_TYPE_NUM_3:
                playWayStr = "[尾号：3]";
                break;
            case AppConfig.BUY_TYPE_NUM_4:
                playWayStr = "[尾号：4]";
                break;
            case AppConfig.BUY_TYPE_NUM_5:
                playWayStr = "[尾号：5]";
                break;
            case AppConfig.BUY_TYPE_NUM_6:
                playWayStr = "[尾号：6]";
                break;
            case AppConfig.BUY_TYPE_NUM_7:
                playWayStr = "[尾号：7]";
                break;
            case AppConfig.BUY_TYPE_NUM_8:
                playWayStr = "[尾号：8]";
                break;
            case AppConfig.BUY_TYPE_NUM_9:
                playWayStr = "[尾号：9]";
                break;
            case AppConfig.BUY_TYPE_NUM_0:
                playWayStr = "[尾号：0]";
                break;
        }
        playWayStr = playWayStr + "*" + info.getNumber();
        viewHolder.playWay.setText(playWayStr);
        viewHolder.goonIndiana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indianaRecordItemButtonClickListener != null) {
                    indianaRecordItemButtonClickListener.onClickGoonIndiana();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


    public interface IndianaRecordItemButtonClickListener {
        void onClickGoonIndiana();
    }


    static class IndianaRecordViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_cycle_code)
        TextView goodsCycleCode;
        @BindView(R.id.buy_time)
        TextView buyTime;
        @BindView(R.id.goods_image)
        RatioImageView goodsImage;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.play_way)
        TextView playWay;
        @BindView(R.id.buy_money)
        TextView buyMoney;
        @BindView(R.id.cycle_code)
        TextView cycleCode;
        @BindView(R.id.win_code)
        TextView winCode;
        @BindView(R.id.layout1)
        LinearLayout layout1;
        @BindView(R.id.state_image)
        ImageView stateImage;
        @BindView(R.id.image_goods_status)
        ImageView imageGoodsStatus;
        @BindView(R.id.text_goods_status)
        TextView textGoodsStatus;
        @BindView(R.id.countdownView)
        TextView countdownView;
        @BindView(R.id.goon_indiana)
        TextView goonIndiana;

        IndianaRecordViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
