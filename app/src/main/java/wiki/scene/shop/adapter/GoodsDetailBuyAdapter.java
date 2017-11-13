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

/**
 * 商品详情开奖记录
 * Created by scene on 2017/11/13.
 */

public class GoodsDetailBuyAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public GoodsDetailBuyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
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
        GoodsDetailBuyViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_goods_detail_buy_item, viewGroup, false);
            viewHolder = new GoodsDetailBuyViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (GoodsDetailBuyViewHolder) view.getTag();
        }
        return view;
    }

    static class GoodsDetailBuyViewHolder {
        @BindView(R.id.username)
        TextView username;
        @BindView(R.id.number)
        TextView number;

        GoodsDetailBuyViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
