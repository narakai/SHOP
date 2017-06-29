package wiki.scene.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;

/**
 * Case By:购物车商品
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/6/29 14:49
 */

public class CarGoodsAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public CarGoodsAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        CarGoodsViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_car_goods_item, parent, false);
            viewHolder = new CarGoodsViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CarGoodsViewHolder) convertView.getTag();
        }
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

        CarGoodsViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
