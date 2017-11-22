package wiki.scene.shop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.entity.BankInfo;

/**
 * 提现的时候银行卡列表
 * Created by scene on 2017/11/22.
 */

public class CashBankAdapter extends BaseAdapter {
    private Context context;
    private List<BankInfo> list;
    private LayoutInflater inflater;

    public CashBankAdapter(Context context, List<BankInfo> list) {
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
        CashBankViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_cash_bank_item, viewGroup, false);
            holder = new CashBankViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (CashBankViewHolder) view.getTag();
        }
        BankInfo info = list.get(i);
        if (info.getType() == 1) {
            holder.cashWay.setText("提现到银行卡");
            String accountStr = info.getAccount();
            if (accountStr.length() > 5) {
                accountStr = info.getAccount().substring(info.getAccount().length() - 5);
            }
            holder.bankInfo.setText(info.getBank() + "(尾号" + accountStr + ")");
        } else {
            holder.cashWay.setText("提现到支付宝");
            String accountStr = info.getAccount();
            if (accountStr.length() > 8) {
                accountStr = accountStr.replace(accountStr.substring(4, accountStr.length() - 4), "****");
            }
            holder.bankInfo.setText(accountStr);
        }
        holder.serviceCharge.setText("提现收取手续费1%");
        if (info.isChecked()) {
            holder.serviceCharge.setTextColor(ContextCompat.getColor(context, R.color.white));
            holder.cashWay.setTextColor(ContextCompat.getColor(context, R.color.white));
            holder.itemView.setBackgroundColor(Color.parseColor("#424B81"));
        } else {
            holder.serviceCharge.setTextColor(ContextCompat.getColor(context, R.color.text_color_title));
            holder.cashWay.setTextColor(ContextCompat.getColor(context, R.color.text_color_title));
            holder.itemView.setBackgroundColor(Color.parseColor("#F0F0F0"));
        }

        return view;
    }

    static class CashBankViewHolder {
        @BindView(R.id.cash_way)
        TextView cashWay;
        @BindView(R.id.bank_info)
        TextView bankInfo;
        @BindView(R.id.service_charge)
        TextView serviceCharge;
        @BindView(R.id.icon)
        ImageView icon;
        @BindView(R.id.item_view)
        RelativeLayout itemView;

        CashBankViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
