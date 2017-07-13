package wiki.scene.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.ListGoodsInfo;
import wiki.scene.shop.widgets.RatioImageView;

/**
 * Case By:购物车猜你喜欢
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/6/29 14:30
 */

public class GuessLikeAdapter extends BaseAdapter {

    private Context context;
    private List<ListGoodsInfo> list;
    private LayoutInflater inflater;

    public GuessLikeAdapter(Context context, List<ListGoodsInfo> list) {
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
        GuessLikeViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_indiana_item, parent, false);
            viewHolder = new GuessLikeViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GuessLikeViewHolder) convertView.getTag();
        }
        ListGoodsInfo info=list.get(position);
        viewHolder.goodsName.setText(info.getTitle());
        Glide.with(context).load(ShopApplication.configInfo.getFile_domain() + info.getThumb()).into(viewHolder.goodsImage);
        int precent = info.getCurrent_source() * 100 / info.getNeed_source();
        viewHolder.progressText.setText(precent+"%");
        viewHolder.progressBar.setProgress(precent);
        return convertView;
    }

    static class GuessLikeViewHolder {
        @BindView(R.id.goods_image)
        RatioImageView goodsImage;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.progress_text)
        TextView progressText;
        @BindView(R.id.immddiately_indiana)
        TextView immddiatelyIndiana;
        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        GuessLikeViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
