package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunfusheng.glideimageview.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
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
        viewHolder.bankName.setText(info.getBank());
        viewHolder.bankAccount.setText(info.getAccount());
        viewHolder.realName.setText(info.getName());
        GlideImageLoader.create(viewHolder.logo).loadCircleImage(ShopApplication.configInfo.getFile_domain() + "", R.drawable.ic_default_avater);
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

        BankViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
