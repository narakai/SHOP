package wiki.scene.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import wiki.scene.loadmore.utils.PtrLocalDisplay;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.CartInfo;

/**
 * Case By:购物车商品
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/6/29 14:49
 */

public class CarGoodsAdapter extends BaseAdapter {

    private Context context;
    private List<CartInfo> list;
    private LayoutInflater inflater;

    private OnCarItemClickListener onCarItemClickListener;

    public CarGoodsAdapter(Context context, List<CartInfo> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public OnCarItemClickListener getOnCarItemClickListener() {
        return onCarItemClickListener;
    }

    public void setOnCarItemClickListener(OnCarItemClickListener onCarItemClickListener) {
        this.onCarItemClickListener = onCarItemClickListener;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CarGoodsViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_car_goods_item, parent, false);
            viewHolder = new CarGoodsViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CarGoodsViewHolder) convertView.getTag();
        }
        CartInfo info = list.get(position);
        viewHolder.status.setImageResource(info.isChecked() ? R.drawable.ic_address_choosed_s : R.drawable.ic_address_choosed_d);
        Glide.with(context).load(ShopApplication.configInfo.getFile_domain() + info.getThumb()).bitmapTransform(new RoundedCornersTransformation(context, PtrLocalDisplay.dp2px(10), 0)).into(viewHolder.goodsImage);
        viewHolder.goodsName.setText(info.getTitle());
        viewHolder.progressBar.setProgress(info.getCurrent_source() * 100 / info.getNeed_source());
        viewHolder.goodsTime.setText(String.format(context.getString(R.string.xx_times), info.getCycle_id()));
        viewHolder.totalNeedCount.setText(String.format(context.getString(R.string.need_xx_person_count), info.getNeed_source()));
        viewHolder.surplusPersonTimes.setText(String.valueOf((info.getNeed_source() - info.getCurrent_source())));
        viewHolder.number.setText(String.valueOf(info.getNumber()));
        viewHolder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCarItemClickListener != null) {
                    onCarItemClickListener.onItemClickStatus(position);
                }
            }
        });
        viewHolder.numberAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCarItemClickListener != null) {
                    onCarItemClickListener.onItemClickAdd(position);
                }
            }
        });
        viewHolder.numberLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCarItemClickListener != null) {
                    onCarItemClickListener.onItemClickLess(position);
                }
            }
        });
        viewHolder.goodsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCarItemClickListener != null) {
                    onCarItemClickListener.onItemClickGoodsImage(position);
                }
            }
        });
        return convertView;
    }

    static class CarGoodsViewHolder {
        @BindView(R.id.status)
        ImageView status;
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.progressBar)
        ProgressBar progressBar;
        @BindView(R.id.goods_time)
        TextView goodsTime;
        @BindView(R.id.total_need_count)
        TextView totalNeedCount;
        @BindView(R.id.surplus_person_times)
        TextView surplusPersonTimes;
        @BindView(R.id.number_less)
        TextView numberLess;
        @BindView(R.id.number_add)
        TextView numberAdd;
        @BindView(R.id.number)
        TextView number;

        CarGoodsViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnCarItemClickListener {
        void onItemClickStatus(int position);

        void onItemClickAdd(int position);

        void onItemClickLess(int position);

        void onItemClickGoodsImage(int position);
    }

}
