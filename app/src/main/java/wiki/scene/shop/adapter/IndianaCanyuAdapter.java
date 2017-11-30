package wiki.scene.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.sunfusheng.glideimageview.GlideImageLoader;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.NewestWinInfo;

/**
 * 首页最新参与
 * Created by scene on 2017/11/9.
 */

public class IndianaCanyuAdapter extends BaseAdapter {
    private Context context;
    private List<NewestWinInfo> list;
    private LayoutInflater mInflater;

    private OnClickItemViewListener onClickItemViewListener;

    public IndianaCanyuAdapter(Context context, List<NewestWinInfo> list) {
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

    public void setOnClickItemViewListener(OnClickItemViewListener onClickItemViewListener) {
        this.onClickItemViewListener = onClickItemViewListener;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        IndianaCanyuViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.fragment_indiana_canyu_item, viewGroup, false);
            viewHolder = new IndianaCanyuViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (IndianaCanyuViewHolder) convertView.getTag();
        }
        NewestWinInfo info = list.get(i % list.size());
        viewHolder.username.setText(info.getNickname());
        viewHolder.goodsName.setText(info.getProduct_name());
        viewHolder.time.setText(TimeUtils.getFriendlyTimeSpanByNow(info.getCreate_time() * 1000));
        viewHolder.number.setText(String.valueOf(info.getNumber()));
        viewHolder.status.setText("参与");
        GlideImageLoader.create(viewHolder.userAvater).loadCircleImage(ShopApplication.configInfo.getFile_domain() + info.getAvatar(), R.drawable.ic_default_avater);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (onClickItemViewListener != null) {
                        onClickItemViewListener.onClickItem(i % list.size());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return convertView;
    }

    static class IndianaCanyuViewHolder {
        @BindView(R.id.user_avater)
        ImageView userAvater;
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
        @BindView(R.id.item_view)
        LinearLayout itemView;

        IndianaCanyuViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnClickItemViewListener {
        void onClickItem(int realPosition);
    }
}
