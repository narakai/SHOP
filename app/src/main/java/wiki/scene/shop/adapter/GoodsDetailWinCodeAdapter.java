package wiki.scene.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.entity.WinCodeInfo;

/**
 * 商品详情开奖记录
 * Created by scene on 2017/11/13.
 */

public class GoodsDetailWinCodeAdapter extends BaseAdapter {
    private Context context;
    private List<WinCodeInfo> list;
    private LayoutInflater inflater;

    public GoodsDetailWinCodeAdapter(Context context, List<WinCodeInfo> list) {
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
        GoodsDetailWinCodeViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_goods_detail_win_code_item, viewGroup, false);
            viewHolder = new GoodsDetailWinCodeViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (GoodsDetailWinCodeViewHolder) view.getTag();
        }
        WinCodeInfo info = list.get(i);
        viewHolder.lastCycleCode.setText(info.getCycle_code());
        viewHolder.lastOpenResult.setText("(" + info.getResult_text() + ")");
        viewHolder.lastWinCode.setText(info.getResult());
        return view;
    }

    static class GoodsDetailWinCodeViewHolder {
        @BindView(R.id.last_cycle_code)
        TextView lastCycleCode;
        @BindView(R.id.last_win_code)
        TextView lastWinCode;
        @BindView(R.id.last_open_result)
        TextView lastOpenResult;

        GoodsDetailWinCodeViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
