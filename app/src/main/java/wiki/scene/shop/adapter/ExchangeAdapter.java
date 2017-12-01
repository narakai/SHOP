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
import wiki.scene.shop.entity.PrizeInfo;
import wiki.scene.shop.utils.PriceUtil;
import wiki.scene.shop.widgets.RatioImageView;

/**
 * 兑换
 * Created by scene on 2017/11/22.
 */

public class ExchangeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<PrizeInfo> list;
    private OnExchangeItemClickListener onExchangeItemClickListener;

    public ExchangeAdapter(Context context, List<PrizeInfo> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnExchangeItemClickListener(OnExchangeItemClickListener onExchangeItemClickListener) {
        this.onExchangeItemClickListener = onExchangeItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExchangeViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_exchange_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        try {
            ExchangeViewHolder viewHolder = (ExchangeViewHolder) holder;
            PrizeInfo info = list.get(position);
            viewHolder.status.setImageResource(info.isChecked() ? R.drawable.ic_address_choosed_s : R.drawable.ic_address_choosed_d);
            viewHolder.goodsName.setText(info.getProduct_name());
            viewHolder.money.setText(PriceUtil.getPrice(info.getCost()) + "夺宝币");
            viewHolder.num.setText(String.valueOf(info.getNumber()));
            if (viewHolder.goodsImage.getTag() == null || !viewHolder.goodsImage.getTag().toString().equals(info.getThumb())) {
                GlideImageLoader.create(viewHolder.goodsImage).loadImage(ShopApplication.configInfo.getFile_domain() + info.getThumb(), R.drawable.ic_default_goods_image);
                viewHolder.goodsImage.setTag(info.getThumb());
            }
            viewHolder.choosedNumber.setText(String.valueOf(info.getCheckedNumber()));
            viewHolder.numberLess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onExchangeItemClickListener != null) {
                        onExchangeItemClickListener.onClickNumberLess(position);
                    }
                }
            });
            viewHolder.numberAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onExchangeItemClickListener != null) {
                        onExchangeItemClickListener.onClickNumberAdd(position);
                    }
                }
            });

            viewHolder.status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onExchangeItemClickListener != null) {
                        onExchangeItemClickListener.onClickStatus(position);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ExchangeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.status)
        ImageView status;
        @BindView(R.id.goods_image)
        RatioImageView goodsImage;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.money)
        TextView money;
        @BindView(R.id.num)
        TextView num;
        @BindView(R.id.number_less)
        TextView numberLess;
        @BindView(R.id.number_add)
        TextView numberAdd;
        @BindView(R.id.choosed_number)
        TextView choosedNumber;

        ExchangeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnExchangeItemClickListener {
        void onClickNumberLess(int position);

        void onClickNumberAdd(int position);

        void onClickStatus(int position);
    }
}
