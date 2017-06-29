package wiki.scene.shop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import wiki.scene.shop.fragment.share.ShareHotestFragment;
import wiki.scene.shop.fragment.share.ShareNewestFragment;

/**
 * Case By:晒单
 * package:wiki.scene.shop.adapter
 * Author：scene on 2017/6/29 11:53
 */

public class SharePagerFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTab = new String[]{"最新", "最热"};

    public SharePagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return ShareNewestFragment.newInstance();
        } else {
            return ShareHotestFragment.newInstance();
        }
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
