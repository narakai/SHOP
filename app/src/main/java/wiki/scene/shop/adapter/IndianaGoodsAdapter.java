package wiki.scene.shop.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sunfusheng.glideimageview.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.IndexInfo;
import wiki.scene.shop.utils.DateFormatUtils;
import wiki.scene.shop.utils.PriceUtil;
import wiki.scene.shop.widgets.RatioImageView;

/**
 * Case By:夺宝的adapter
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/6/28 15:31
 */

public class IndianaGoodsAdapter extends BaseAdapter {
    private Context context;
    private List<IndexInfo.ProductsBean> list;
    private LayoutInflater inflater;
    //用于退出 Activity,避免 Countdown，造成资源浪费。
    private SparseArray<CountDownTimer> countDownCounters;

    public IndianaGoodsAdapter(Context context, List<IndexInfo.ProductsBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
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

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final IndianaGoodsViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_indiana_goods_item, viewGroup, false);
            viewHolder = new IndianaGoodsViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (IndianaGoodsViewHolder) view.getTag();
        }
        final IndexInfo.ProductsBean info = list.get(i);
        viewHolder.goodsName.setText(info.getName());
        GlideImageLoader.create(viewHolder.goodsImage).loadRoundCornerImage(ShopApplication.configInfo.getFile_domain()+info.getThumb(), R.drawable.ic_default_goods_image, PtrLocalDisplay.dp2px(3));
        viewHolder.goodsPrice.setText(PriceUtil.getPrice(info.getTen_price()) + "/" + PriceUtil.getPrice(info.getFour_price()) + "/" + PriceUtil.getPrice(info.getTwo_price()));
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
                    viewHolder.countdownView.setText("等待开奖");
                }
            }.start();
            //将此 countDownTimer 放入list.
            countDownCounters.put(viewHolder.countdownView.hashCode(), countDownTimer);
        } else {
            viewHolder.countdownView.setText("等待开奖");
        }

        return view;
    }

    static class IndianaGoodsViewHolder {
        @BindView(R.id.goods_image)
        RatioImageView goodsImage;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.goods_price)
        TextView goodsPrice;
        @BindView(R.id.countdownView)
        TextView countdownView;
        @BindView(R.id.indiana_now)
        TextView indianaNow;

        IndianaGoodsViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
