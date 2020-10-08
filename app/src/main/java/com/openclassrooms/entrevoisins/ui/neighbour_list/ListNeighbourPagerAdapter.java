package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {


    //variables
    public static final String TAG = ListNeighbourPagerAdapter.class.getSimpleName();

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        //Switch between diff Fragement
        switch (position) {
            case 0 :
                return NeighbourFragment.newInstance();
            case 1 :
                return FavFragment.newInstance();
            default: {
                Log.e(TAG, "No fragment selected");
                return null;
            }
        }
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }
}