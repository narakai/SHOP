package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;

/**
 * Case By:我的红包
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/6/30 13:36
 */

public class MineRedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> list;

    public MineRedAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MineRedViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_mine_red_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MineRedViewHolder viewHolder = (MineRedViewHolder) holder;
        viewHolder.redName.setText(list.get(position));
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

        MineRedViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
