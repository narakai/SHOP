package wiki.scene.shop.fragment.newest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.shop.R;
import wiki.scene.shop.base.BaseMainFragment;

/**
 * Case By:最新揭晓
 * package:wiki.scene.shop.fragment.indiana
 * Author：scene on 2017/6/26 14:13
 */
public class NewestFragment extends BaseMainFragment {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    public static NewestFragment newInstance() {
        NewestFragment fragment = new NewestFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newest, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        toolbarTitle.setText(R.string.bottom_tab_newest);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
