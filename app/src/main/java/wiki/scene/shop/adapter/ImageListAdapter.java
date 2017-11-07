package wiki.scene.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;

/**
 * 晒单选择图片列表
 * Created by scene on 17-8-14.
 */

public class ImageListAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;
    private OnClickImageListListener onClickImageListListener;

    public ImageListAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void setOnClickImageListListener(OnClickImageListListener onClickImageListListener) {
        this.onClickImageListListener = onClickImageListListener;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_image_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (list.get(position).equals("add")) {
            viewHolder.image.loadLocalImage(R.drawable.ic_add_image, R.drawable.ic_add_image);
            viewHolder.delete.setVisibility(View.GONE);
        } else {
            viewHolder.image.loadLocalImage(list.get(position), R.drawable.ic_add_image);
            viewHolder.delete.setVisibility(View.VISIBLE);
        }
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickImageListListener != null) {
                    onClickImageListListener.onClickImageListDelete(position);
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.image)
        GlideImageView image;
        @BindView(R.id.delete)
        ImageView delete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnClickImageListListener {
        void onClickImageListDelete(int position);
    }
}
