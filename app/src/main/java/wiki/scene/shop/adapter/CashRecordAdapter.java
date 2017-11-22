package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.entity.CashRecordInfo;
import wiki.scene.shop.utils.PriceUtil;

/**
 * 兑换记录
 * Created by scene on 2017/11/22.
 */

public class CashRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context context;
    private List<CashRecordInfo> list;

    public CashRecordAdapter(Context context, List<CashRecordInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CashRecordViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_cash_record_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CashRecordViewHolder viewHolder = (CashRecordViewHolder) holder;
        CashRecordInfo info = list.get(position);
        viewHolder.time.setText(TimeUtils.millis2String(info.getDone_time() * 1000,new SimpleDateFormat("yyyy.MM.dd")));
        viewHolder.money.setText(PriceUtil.getPrice(info.getMoney()));
        viewHolder.coin.setText(PriceUtil.getPrice(info.getSpend()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CashRecordViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.coin)
        TextView coin;
        @BindView(R.id.money)
        TextView money;
        @BindView(R.id.time)
        TextView time;

        CashRecordViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
