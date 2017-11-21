package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.BankInfo;

/**
 * 银行卡列表
 * Created by scene on 2017/11/21.
 */

public class BankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<BankInfo> list;

    public BankAdapter(Context context, List<BankInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BankViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_bank_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BankInfo info = list.get(position);
        BankViewHolder viewHolder = (BankViewHolder) holder;
        if (info.getType() == AppConfig.BANK_TYPE_BANK_CARD) {
            viewHolder.bankName.setText(info.getBank());
            viewHolder.itemLayout.setBackgroundResource(R.drawable.shape_bankcard_bg);
            viewHolder.logo.setImageResource(R.drawable.ic_unionpay);
        } else {
            viewHolder.bankName.setText("支付宝");
            viewHolder.itemLayout.setBackgroundResource(R.drawable.shape_alipay_bg);
            viewHolder.logo.setImageResource(R.drawable.ic_alipay_logo);
        }
        String accountStr = info.getAccount();
        if (accountStr.length() > 8) {
            accountStr = accountStr.replace(accountStr.substring(4, accountStr.length() - 4), "****");
            viewHolder.bankAccount.setText(accountStr);
        } else {
            viewHolder.bankAccount.setText(accountStr);
        }
        viewHolder.realName.setText(info.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class BankViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.logo)
        ImageView logo;
        @BindView(R.id.bank_name)
        TextView bankName;
        @BindView(R.id.real_name)
        TextView realName;
        @BindView(R.id.bank_account)
        TextView bankAccount;
        @BindView(R.id.itemLayout)
        RelativeLayout itemLayout;

        BankViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
