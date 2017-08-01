package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.MineOrderInfo;

/**
 * Case By:夺宝记录
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/7/5 13:52
 */

public class IndianaRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ONGOING=0;
    private static final int TYPE_RENDING=1;
    private static final int TYPE_RESULT=2;
    private Context context;
    private List<MineOrderInfo> list;

    public IndianaRecordAdapter(Context context, List<MineOrderInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        if(viewType==TYPE_ONGOING){
            return new OnGoingViewHolder(inflater.inflate(R.layout.fragement_indiana_record_item_ongoing,parent,false));
        }else if(viewType==TYPE_RENDING){
            return new PendingViewHolder(inflater.inflate(R.layout.fragement_indiana_record_item_pending,parent,false));
        }else{
            return new IndianaResultViewHolder(inflater.inflate(R.layout.fragment_win_record_item,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MineOrderInfo info=list.get(position);
        if(holder instanceof OnGoingViewHolder){
            OnGoingViewHolder onGoingViewHolder= (OnGoingViewHolder) holder;
            onGoingViewHolder.goodsName.setText(info.getTitle());
            Glide.with(context).load(ShopApplication.configInfo.getFile_domain()+info.getThumb()).fitCenter().into(onGoingViewHolder.goodsImage);
        }else if(holder instanceof PendingViewHolder){
            PendingViewHolder pendingViewHolder= (PendingViewHolder) holder;
            pendingViewHolder.goodsName.setText(info.getTitle());
            Glide.with(context).load(ShopApplication.configInfo.getFile_domain()+info.getThumb()).fitCenter().into(pendingViewHolder.goodsImage);
            pendingViewHolder.personTimes.setText(String.valueOf(info.getMy_buy_number()));
        }else{
            IndianaResultViewHolder indianaResultViewHolder= (IndianaResultViewHolder) holder;
            indianaResultViewHolder.goodsName.setText(info.getTitle());
            Glide.with(context).load(ShopApplication.configInfo.getFile_domain()+info.getThumb()).fitCenter().into(indianaResultViewHolder.goodsImage);
        }

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position).getStatus()==0){
            return TYPE_ONGOING;
        }else if(list.get(position).getStatus()==1){
            return TYPE_RENDING;
        }else {
            return TYPE_RESULT;
        }
    }

    /**
     * Case By: 进行中
     * Author：scene on 2017/7/5 13:54
     */
    static class OnGoingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.goods_tag)
        TextView goodsTag;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.person_times)
        TextView personTimes;
        @BindView(R.id.ongoing_progressbar)
        ProgressBar ongoingProgressbar;
        @BindView(R.id.goon_indiana)
        TextView goonIndiana;

        OnGoingViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    static class PendingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.goods_tag)
        TextView goodsTag;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.person_times)
        TextView personTimes;
        @BindView(R.id.newest_countDownView)
        CountdownView newestCountDownView;

        PendingViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class IndianaResultViewHolder extends RecyclerView.ViewHolder {
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

        IndianaResultViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
