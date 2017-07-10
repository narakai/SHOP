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
import wiki.scene.shop.entity.AddressInfo;

/**
 * Case By:收货地址
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/6/30 10:42
 */

public class MineReceiverAddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<AddressInfo> list;
    private OnReceiverAddressListener receiverAddressListener;

    public MineReceiverAddressAdapter(Context context, List<AddressInfo> list) {
        this.context = context;
        this.list = list;
    }

    public void setReceiverAddressListener(OnReceiverAddressListener receiverAddressListener) {
        this.receiverAddressListener = receiverAddressListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MineReceiverAddressViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_mine_receiver_address_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MineReceiverAddressViewHolder viewHolder = (MineReceiverAddressViewHolder) holder;
        AddressInfo info = list.get(position);
        viewHolder.receiverName.setText(info.getName());
        viewHolder.receiverPhone.setText(info.getMobile());
        viewHolder.receiverAddress.setText(info.getAddress());
        viewHolder.defaultStatus.setText(info.getIs_default() == 0 ? R.string.set_default : R.string.default_address);
        viewHolder.defaultStatus.setCompoundDrawablesWithIntrinsicBounds(context.getResources().getDrawable(info.getIs_default() == 0
                ? R.drawable.ic_address_choosed_d : R.drawable.ic_address_choosed_s), null, null, null);
        viewHolder.defaultStatus.setTextColor(context.getResources().getColor(info.getIs_default() == 0 ? R.color.text_color_content : R.color.address_default_color));
        if (receiverAddressListener != null) {
            viewHolder.defaultStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    receiverAddressListener.onItemClickSetDefaultAddress(position);
                }
            });
            viewHolder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    receiverAddressListener.onItemClickEdit(position);
                }
            });
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    receiverAddressListener.onItemClickDelete(position);
                }
            });
        }
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
        TextView defaultStatus;
        @BindView(R.id.edit)
        TextView edit;
        @BindView(R.id.delete)
        TextView delete;

        MineReceiverAddressViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnReceiverAddressListener {
        void onItemClickSetDefaultAddress(int position);

        void onItemClickEdit(int position);

        void onItemClickDelete(int position);
    }
}
