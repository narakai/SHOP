package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.entity.CreateOrderInfo;
import wiki.scene.shop.utils.DateUtil;
import wiki.scene.shop.utils.PriceUtil;

/**
 * Case By:我的红包
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/6/30 13:36
 */

public class MineRedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<CreateOrderInfo.CouponsBean> list;
    private OnMineRedItemClickListener onMineRedItemClickListener;

    public MineRedAdapter(Context context, ArrayList<CreateOrderInfo.CouponsBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnMineRedItemClickListener(OnMineRedItemClickListener onMineRedItemClickListener) {
        this.onMineRedItemClickListener = onMineRedItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MineRedViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_mine_red_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MineRedViewHolder viewHolder = (MineRedViewHolder) holder;
        CreateOrderInfo.CouponsBean bean = list.get(position);
        viewHolder.redName.setText(bean.getTitle());
        viewHolder.needPrice.setText(PriceUtil.getPrice(bean.getMini_money()));
        viewHolder.cost.setText(PriceUtil.getPrice(bean.getCost()));
        if (bean.getExpired_time() == 0) {
            viewHolder.expiryDate.setText(String.format(context.getString(R.string.expiry_date), "永久"));
        } else {
            viewHolder.expiryDate.setText(String.format(context.getString(R.string.expiry_date), DateUtil.formatDate(bean.getExpired_time())));
        }
        viewHolder.immddiatelyIndiana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMineRedItemClickListener != null) {
                    onMineRedItemClickListener.onMineRedItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    static class MineRedViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.red_name)
        TextView redName;
        @BindView(R.id.need_price)
        TextView needPrice;
        @BindView(R.id.expiry_date)
        TextView expiryDate;
        @BindView(R.id.immddiately_indiana)
        TextView immddiatelyIndiana;
        @BindView(R.id.cost)
        TextView cost;

        MineRedViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnMineRedItemClickListener {
        void onMineRedItemClick(int position);
    }
}
