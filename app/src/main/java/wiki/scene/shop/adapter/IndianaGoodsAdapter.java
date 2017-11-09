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
import wiki.scene.shop.widgets.RatioImageView;

/**
 * Case By:夺宝的adapter
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/6/28 15:31
 */

public class IndianaGoodsAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public IndianaGoodsAdapter(Context context, List<String> list) {
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
        IndianaGoodsViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_indiana_goods_item, viewGroup, false);
            viewHolder = new IndianaGoodsViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (IndianaGoodsViewHolder) view.getTag();
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
