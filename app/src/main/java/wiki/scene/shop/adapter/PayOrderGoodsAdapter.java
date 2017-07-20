package wiki.scene.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.entity.CreateOrderInfo;

/**
 * 支付订单界面的商品列表
 * Created by scene on 17-7-20.
 */

public class PayOrderGoodsAdapter extends BaseAdapter {
    private List<CreateOrderInfo.CyclesBean> list;
    private LayoutInflater inflater;

    public PayOrderGoodsAdapter(Context context, List<CreateOrderInfo.CyclesBean> list) {
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
        PayOrderGoodsViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_car_pay_order_goods_item, parent, false);
            viewHolder = new PayOrderGoodsViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (PayOrderGoodsViewHolder) convertView.getTag();
        }
        CreateOrderInfo.CyclesBean info = list.get(position);
        viewHolder.goodsName.setText(info.getTitle());
        viewHolder.goodsCycleCode.setText(info.getCycle_code());
        viewHolder.goodsNumber.setText(String.valueOf(info.getNumber()));
        return convertView;
    }

     static class PayOrderGoodsViewHolder {
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.goods_number)
        TextView goodsNumber;
        @BindView(R.id.goods_cycle_code)
        TextView goodsCycleCode;

        PayOrderGoodsViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
