package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;

/**
 * Case By:收货地址
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/6/30 10:42
 */

public class MineReceiverAddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> list;

    public MineReceiverAddressAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MineReceiverAddressViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_mine_receiver_address_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MineReceiverAddressViewHolder viewHolder = (MineReceiverAddressViewHolder) holder;
        viewHolder.receiverName.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    static class MineReceiverAddressViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.receiver_name)
        TextView receiverName;
        @BindView(R.id.receiver_phone)
        TextView receiverPhone;
        @BindView(R.id.receiver_address)
        TextView receiverAddress;
        @BindView(R.id.line)
        View line;
        @BindView(R.id.default_status)
        ImageView defaultStatus;
        @BindView(R.id.edit)
        TextView edit;
        @BindView(R.id.delete)
        TextView delete;

        MineReceiverAddressViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
