package wiki.scene.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;

/**
 * Case By:商品详情-->参与记录
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/7/5 11:14
 */

public class GoodsDetailJoinRecordAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public GoodsDetailJoinRecordAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JoinRecordViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_goods_detail_join_record_item, parent, false);
            viewHolder = new JoinRecordViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (JoinRecordViewHolder) convertView.getTag();
        }

        viewHolder.userInfo.setText(String.format(context.getString(R.string.user_info), list.get(position), "重庆南岸区", "192.168.1.1"));
        return convertView;
    }

    static class JoinRecordViewHolder {
        @BindView(R.id.user_avater)
        ImageView userAvater;
        @BindView(R.id.user_info)
        TextView userInfo;
        @BindView(R.id.join_times)
        TextView joinTimes;
        @BindView(R.id.join_time)
        TextView joinTime;

        JoinRecordViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
