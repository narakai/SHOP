package wiki.scene.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.ShopApplication;
import wiki.scene.shop.config.AppConfig;
import wiki.scene.shop.entity.PKInfo;
import wiki.scene.shop.entity.PkMineInfo;

/**
 * pk详情
 * Created by scene on 2017/11/22.
 */

public class PkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<PKInfo> list;
    private PkMineInfo mineInfo;

    public PkAdapter(Context context, List<PKInfo> list, PkMineInfo mineInfo) {
        this.context = context;
        this.list = list;
        this.mineInfo = mineInfo;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PkViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_order_pk_detail_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PkViewHolder viewHolder = (PkViewHolder) holder;
        PKInfo info = list.get(position);
        viewHolder.mineName.setText(mineInfo.getUsername());
        viewHolder.mineAvatar.loadCircleImage(ShopApplication.configInfo.getFile_domain() + mineInfo.getAvatar(), R.drawable.ic_default_avater);
        viewHolder.mineBuyType.setText(getBuyTypeString(mineInfo.getBuy_type()));

        viewHolder.otherName.setText(info.getNickname());
        viewHolder.otherAvatar.loadCircleImage(ShopApplication.configInfo.getFile_domain() + info.getAvatar(), R.drawable.ic_default_avater);
        viewHolder.otherBuyType.setText(getBuyTypeString(info.getTarget_buy_type()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class PkViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mine_buy_type)
        TextView mineBuyType;
        @BindView(R.id.mine_avatar)
        GlideImageView mineAvatar;
        @BindView(R.id.mine_name)
        TextView mineName;
        @BindView(R.id.other_buy_type)
        TextView otherBuyType;
        @BindView(R.id.other_avatar)
        GlideImageView otherAvatar;
        @BindView(R.id.other_name)
        TextView otherName;

        PkViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private String getBuyTypeString(int buyType) {
        String buyTypeStr = "";
        switch (buyType) {
            case AppConfig.BUY_TYPE_BIG:
                buyTypeStr = "大";
                break;
            case AppConfig.BUY_TYPE_SMALL:
                buyTypeStr = "小";
                break;
            case AppConfig.BUY_TYPE_SINGLE:
                buyTypeStr = "单";
                break;
            case AppConfig.BUY_TYPE_DOUBLE:
                buyTypeStr = "双";
                break;
            case AppConfig.BUY_TYPE_BIG_SINGLE:
                buyTypeStr = "大单";
                break;
            case AppConfig.BUY_TYPE_BIG_DOUBLE:
                buyTypeStr = "大双";
                break;
            case AppConfig.BUY_TYPE_SMALL_SINGLE:
                buyTypeStr = "小单";
                break;
            case AppConfig.BUY_TYPE_SMALL_DOUBLE:
                buyTypeStr = "小双";
                break;
            case AppConfig.BUY_TYPE_NUM_1:
                buyTypeStr = "1";
                break;
            case AppConfig.BUY_TYPE_NUM_2:
                buyTypeStr = "2";
                break;
            case AppConfig.BUY_TYPE_NUM_3:
                buyTypeStr = "3";
                break;
            case AppConfig.BUY_TYPE_NUM_4:
                buyTypeStr = "4";
                break;
            case AppConfig.BUY_TYPE_NUM_5:
                buyTypeStr = "5";
                break;
            case AppConfig.BUY_TYPE_NUM_6:
                buyTypeStr = "6";
                break;
            case AppConfig.BUY_TYPE_NUM_7:
                buyTypeStr = "7";
                break;
            case AppConfig.BUY_TYPE_NUM_8:
                buyTypeStr = "8";
                break;
            case AppConfig.BUY_TYPE_NUM_9:
                buyTypeStr = "9";
                break;
            case AppConfig.BUY_TYPE_NUM_0:
                buyTypeStr = "0";
                break;
        }

        return buyTypeStr;

    }
}
