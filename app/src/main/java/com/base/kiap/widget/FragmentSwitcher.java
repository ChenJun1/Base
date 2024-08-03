package com.base.kiap.widget;


import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentSwitcher {
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tags = new ArrayList<>();
    private FragmentManager fragmentManager;
    private int containerResId;
    private int currentIndex = -1;

    public int getCurrentIndex() {
        return currentIndex;
    }

    public FragmentSwitcher(FragmentManager fragmentManager, int containerResId){
        this.fragmentManager = fragmentManager;
        this.containerResId = containerResId;
    }

    public void addFragment(Fragment f, String tag){
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            fragment = f;
        }
        fragments.add(fragment);
        tags.add(tag);
    }

    public Fragment getFragment(int index){
        return index < fragments.size() ? fragments.get(index) : null;
    }

    public void switchToFragment(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(tags.get(index));
        if (fragment == null) {
            fragment = fragments.get(index);
            fragmentTransaction.add(containerResId, fragment, tags.get(index));
            //fragmentTransaction.hide(fragment);
        }
        if (!fragment.isAdded()) {
            fragmentTransaction.attach(fragment);
        }
        if (index != currentIndex) {
            for (int i = 0; i < fragments.size(); i++) {
                if (i != index) {
                    Fragment f = fragmentManager.findFragmentByTag(tags.get(i));
                    if (f != null) {
                        fragmentTransaction.hide(fragments.get(i));
                    }
                }
            }
        }

        fragmentTransaction.show(fragment);

        currentIndex = index;
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }
}
