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
import wiki.scene.shop.entity.WinCodeInfo;

/**
 * 开奖走势
 * Created by scene on 2017/11/14.
 */

public class TrendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<WinCodeInfo> list;

    public TrendAdapter(Context context, List<WinCodeInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TrendViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_trend_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        WinCodeInfo info = list.get(position);
        TrendViewHolder viewHolder = (TrendViewHolder) holder;
        viewHolder.qishu.setText(info.getCycle_code());
        char[] numbers = info.getResult().toCharArray();
        viewHolder.number1.setText(numbers[0]+"");
        viewHolder.number2.setText(numbers[1]+"");
        viewHolder.number3.setText(numbers[2]+"");
        viewHolder.number4.setText(numbers[3]+"");
        viewHolder.number5.setText(numbers[4]+"");
        String result1 = numbers[4] > 4 ? "大" : "小";
        String result2 = numbers[4] % 2 == 0 ? "双" : "单";
        String result3 = numbers[3] > 4 ? "大" : "小";
        viewHolder.result1.setText(result1);
        viewHolder.result2.setText(result2);
        viewHolder.result3.setText(result3 + result2);
        viewHolder.result4.setText(numbers[4]+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class TrendViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.qishu)
        TextView qishu;
        @BindView(R.id.number_1)
        TextView number1;
        @BindView(R.id.number_2)
        TextView number2;
        @BindView(R.id.number_3)
        TextView number3;
        @BindView(R.id.number_4)
        TextView number4;
        @BindView(R.id.number_5)
        TextView number5;
        @BindView(R.id.result_1)
        TextView result1;
        @BindView(R.id.result_2)
        TextView result2;
        @BindView(R.id.result_3)
        TextView result3;
        @BindView(R.id.result_4)
        TextView result4;

        TrendViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
