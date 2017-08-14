package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ta.utdid2.android.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.WinRecordResultInfo;
import wiki.scene.shop.utils.DateUtil;

/**
 * Case By:中奖记录
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/7/5 12:04
 */

public class WinRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<WinRecordResultInfo.WinRecordInfo> list;
    private OnWinRecordItemButtonClickListener onWinRecordItemButtonClickListener;

    public WinRecordAdapter(Context context, List<WinRecordResultInfo.WinRecordInfo> list) {
        this.context = context;
        this.list = list;
    }

    public void setWinRecordItemButtonClickListener(OnWinRecordItemButtonClickListener onWinRecordItemButtonClickListener) {
        this.onWinRecordItemButtonClickListener = onWinRecordItemButtonClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WinRecordViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_win_record_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        WinRecordViewHolder viewHolder = (WinRecordViewHolder) holder;
        WinRecordResultInfo.WinRecordInfo info = list.get(position);
        viewHolder.goodsName.setText(info.getTitle());
        viewHolder.winnerName.setText(StringUtils.isEmpty(ShopApplication.userInfo.getNickname()) ? ShopApplication.userInfo.getMobile() : ShopApplication.userInfo.getNickname());
        viewHolder.personTimes.setText(String.valueOf(info.getNumber()));
        viewHolder.joinTimes.setText(String.format(context.getString(R.string.xx_fen), info.getNumber()));
        viewHolder.luckCode.setText(info.getLucky_code());
        viewHolder.announcedTime.setText(DateUtil.timeStampToStr(info.getOpen_time()));
        Glide.with(context).load(ShopApplication.configInfo.getFile_domain() + info.getThumb()).fitCenter().into(viewHolder.goodsImage);
        setGoodsTag(viewHolder.goodsTag, info.getType());
        viewHolder.goToShareOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onWinRecordItemButtonClickListener != null) {
                    onWinRecordItemButtonClickListener.onClickShareOrder(position);
                }
            }
        });
    }

    private void setGoodsTag(TextView tagView, int type) {
        switch (type) {
            case 1:
                tagView.setText(context.getString(R.string.second_open));
                tagView.setVisibility(View.VISIBLE);
                break;
            case 2:
                tagView.setVisibility(View.GONE);
                break;
            case 3:
                tagView.setVisibility(View.VISIBLE);
                tagView.setText(context.getString(R.string.price_10_area));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    static class WinRecordViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.goods_tag)
        TextView goodsTag;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.person_times)
        TextView personTimes;
        @BindView(R.id.winner_name)
        TextView winnerName;
        @BindView(R.id.join_times)
        TextView joinTimes;
        @BindView(R.id.luck_code)
        TextView luckCode;
        @BindView(R.id.announced_time)
        TextView announcedTime;
        @BindView(R.id.go_to_share_order)
        TextView goToShareOrder;

        WinRecordViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnWinRecordItemButtonClickListener {
        void onClickShareOrder(int position);
    }
}
