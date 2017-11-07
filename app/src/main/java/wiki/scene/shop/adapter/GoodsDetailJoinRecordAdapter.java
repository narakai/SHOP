package wiki.scene.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.entity.GoodsDetailInfo;
import wiki.scene.shop.utils.DateUtil;

/**
 * Case By:商品详情-->参与记录
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/7/5 11:14
 */

public class GoodsDetailJoinRecordAdapter extends BaseAdapter {
    private Context context;
    private List<GoodsDetailInfo.LogInfo> list;
    private LayoutInflater inflater;

    public GoodsDetailJoinRecordAdapter(Context context, List<GoodsDetailInfo.LogInfo> list) {
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
        GoodsDetailInfo.LogInfo info = list.get(position);
        viewHolder.userInfo.setText(String.format(context.getString(R.string.user_info), info.getNickname(), info.getArea(), info.getIp()));
        viewHolder.joinTimes.setText(String.format(context.getString(R.string.join_xx_times), info.getNumber()));
        viewHolder.joinTime.setText(DateUtil.timeStampToStr(info.getCreate_time()));
        viewHolder.userAvater.loadCircleImage(info.getAvatar(), R.drawable.ic_default_avater);
        return convertView;
    }

    static class JoinRecordViewHolder {
        @BindView(R.id.user_avater)
        GlideImageView userAvater;
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
