package wiki.scene.shop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import wiki.scene.shop.ui.share.ShareHotestFragment;
import wiki.scene.shop.ui.share.ShareNewestFragment;

/**
 * Case By:夺宝记录
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/6/29 11:53
 */

public class IndiaRecordPagerFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTab;
    private List<Fragment> fragmentList;

    public IndiaRecordPagerFragmentAdapter(FragmentManager fm, String[] mTab, List<Fragment> fragmentList) {
        super(fm);
        this.mTab = mTab;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mTab.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTab[position];
    }
}
