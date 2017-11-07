package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunfusheng.glideimageview.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.entity.MineOrderInfo;
import wiki.scene.shop.widgets.CustomFontTextView;

/**
 * Case By:夺宝记录
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/7/5 13:52
 */

public class IndianaRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<MineOrderInfo> list;
    private IndianaRecordItemButtonClickListener indianaRecordItemButtonClickListener;

    public IndianaRecordAdapter(Context context, List<MineOrderInfo> list) {
        this.context = context;
        this.list = list;
    }

    public void setIndianaRecordItemButtonClickListener(IndianaRecordItemButtonClickListener indianaRecordItemButtonClickListener) {
        this.indianaRecordItemButtonClickListener = indianaRecordItemButtonClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new IndianaRecordViewHolder(inflater.inflate(R.layout.fragement_indiana_record_item_ongoing, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MineOrderInfo info = list.get(position);
        IndianaRecordViewHolder viewHolder = (IndianaRecordViewHolder) holder;
        viewHolder.goodsName.setText(info.getTitle());
        GlideImageLoader.create(viewHolder.goodsImage).loadImage(ShopApplication.configInfo.getFile_domain() + info.getThumb(), R.drawable.ic_default_image);
        viewHolder.goonIndiana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (indianaRecordItemButtonClickListener != null) {
                    if (info.getOrder_status() == 1) {
                        indianaRecordItemButtonClickListener.onClickItemPay(position);
                    } else {
                        indianaRecordItemButtonClickListener.toGoodsDetail(position);
                    }
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


    public interface IndianaRecordItemButtonClickListener {
        void onClickItemPay(int position);

        void toGoodsDetail(int position);

        void seeAllCodes(int position);

        void goToShareOrder(int position);
    }

    static class IndianaRecordViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.goods_cycle_code)
        TextView goodsCycleCode;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.text_state)
        TextView textState;
        @BindView(R.id.buy_number)
        TextView buyNumber;
        @BindView(R.id.number_base)
        TextView numberBase;
        @BindView(R.id.downtimer)
        CustomFontTextView downtimer;
        @BindView(R.id.layout_downtimer)
        LinearLayout layoutDowntimer;
        @BindView(R.id.win_number)
        TextView winNumber;
        @BindView(R.id.win_state)
        TextView winState;
        @BindView(R.id.layout_win_info)
        LinearLayout layoutWinInfo;
        @BindView(R.id.state_image)
        ImageView stateImage;
        @BindView(R.id.goon_indiana)
        TextView goonIndiana;

        IndianaRecordViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
