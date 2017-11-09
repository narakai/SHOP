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

/**
 * 首页最新参与
 * Created by scene on 2017/11/9.
 */

public class IndianaCanyuAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater mInflater;

    public IndianaCanyuAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i % list.size());
    }

    @Override
    public long getItemId(int i) {
        return i % list.size();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        IndianaCanyuViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.fragment_indiana_canyu_item, viewGroup, false);
            viewHolder = new IndianaCanyuViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (IndianaCanyuViewHolder) convertView.getTag();
        }
        viewHolder.username.setText(list.get(i%list.size()));
        return convertView;
    }

    static class IndianaCanyuViewHolder {
        @BindView(R.id.user_avater)
        GlideImageView userAvater;
        @BindView(R.id.username)
        TextView username;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.goods_name)
        TextView goodsName;

        IndianaCanyuViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
