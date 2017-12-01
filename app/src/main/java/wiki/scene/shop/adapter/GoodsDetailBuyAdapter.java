package wiki.scene.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import wiki.scene.shop.entity.NewestWinInfo;

/**
 * 商品详情开奖记录
 * Created by scene on 2017/11/13.
 */

public class GoodsDetailBuyAdapter extends BaseAdapter {
    private Context context;
    private List<NewestWinInfo> list;
    private LayoutInflater inflater;
    private OnClickGoodsDetailBuyItemListener onClickGoodsDetailBuyItemListener;

    public GoodsDetailBuyAdapter(Context context, List<NewestWinInfo> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void setOnClickGoodsDetailBuyItemListener(OnClickGoodsDetailBuyItemListener onClickGoodsDetailBuyItemListener) {
        this.onClickGoodsDetailBuyItemListener = onClickGoodsDetailBuyItemListener;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i % list.size());
    }

    @Override
    public long getItemId(int i) {
        return i % list.size();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        GoodsDetailBuyViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_goods_detail_buy_item, viewGroup, false);
            viewHolder = new GoodsDetailBuyViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (GoodsDetailBuyViewHolder) view.getTag();
        }
        NewestWinInfo info = list.get(i % list.size());
        viewHolder.username.setText(info.getNickname());
        viewHolder.number.setText(String.valueOf(info.getNumber()));
        viewHolder.time.setText(TimeUtils.getFriendlyTimeSpanByNow(info.getCreate_time() * 1000));
        GlideImageLoader.create(viewHolder.userAvater).loadCircleImage(ShopApplication.configInfo.getFile_domain() + info.getAvatar(), R.drawable.ic_default_avater);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    static class GoodsDetailBuyViewHolder {
        @BindView(R.id.user_avater)
        ImageView userAvater;
        @BindView(R.id.username)
        TextView username;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.item_view)
        LinearLayout itemView;

        GoodsDetailBuyViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnClickGoodsDetailBuyItemListener {
        void onClickItem(int position);
    }
}
