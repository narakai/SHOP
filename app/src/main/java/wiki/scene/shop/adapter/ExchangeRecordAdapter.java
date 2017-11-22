package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.sunfusheng.glideimageview.GlideImageLoader;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.ExchangeRecordInfo;
import wiki.scene.shop.utils.PriceUtil;
import wiki.scene.shop.widgets.RatioImageView;

/**
 * 兑换记录
 * Created by scene on 2017/11/22.
 */

public class ExchangeRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ExchangeRecordInfo> list;

    public ExchangeRecordAdapter(Context context, List<ExchangeRecordInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExchangeRecordViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_exchange_record_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ExchangeRecordViewHolder viewHolder = (ExchangeRecordViewHolder) holder;
        ExchangeRecordInfo info = list.get(position);
        viewHolder.goodsName.setText(info.getProdct_name());
        viewHolder.tiem.setText(TimeUtils.millis2String(info.getCreate_time() * 1000, new SimpleDateFormat("yyyy-MM-dd")));
        viewHolder.price.setText(PriceUtil.getPrice(info.getPrice()) + "夺宝币");
        viewHolder.number.setText(String.valueOf(info.getNumber()));
        viewHolder.totalPrice.setText(PriceUtil.getPrice(info.getCost()) + "夺宝币");
        GlideImageLoader.create(viewHolder.goodsImage).loadImage(ShopApplication.configInfo.getFile_domain() + info.getThumb(), R.drawable.ic_default_goods_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ExchangeRecordViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_image)
        RatioImageView goodsImage;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.tiem)
        TextView tiem;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.total_price)
        TextView totalPrice;

        ExchangeRecordViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
