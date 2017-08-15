package wiki.scene.shop.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.w4lle.library.NineGridAdapter;

import java.util.List;

import wiki.scene.shop.ShopApplication;

/**
 * 9宫格的图片
 * Created by scene on 17-8-15.
 */

public class ShareImagesAdapter extends NineGridAdapter {
    private Context context;
    private List<String> list;

    public ShareImagesAdapter(Context context, List<String> list) {
        super(context, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return (list == null) ? 0 : list.size();
    }

    @Override
    public String getUrl(int position) {
        return getItem(position) == null ? null : list.get(position);
    }

    @Override
    public Object getItem(int position) {
        return (list == null) ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view) {
        ImageView iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Glide.with(context).load(ShopApplication.configInfo.getFile_domain() + getUrl(position)).into(iv);
        return iv;
    }
}
