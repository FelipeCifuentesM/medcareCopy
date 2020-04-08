package com.imed.medcare.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.imed.medcare.ui.home.home.HomeFragment;
import com.imed.medcare.ui.home.pillbox.PillboxFragment;
import com.imed.medcare.ui.home.profile.ProfileFragment;
import com.imed.medcare.ui.home.treatmentList.TreatmentListFragment;

import java.util.HashMap;

import static com.imed.medcare.utils.Constants.FRAGMENT_PILLBOX;
import static com.imed.medcare.utils.Constants.FRAGMENT_PROFILE;
import static com.imed.medcare.utils.Constants.FRAGMENT_TREATMENT;

/**
 * Created by Ramiro on 08-05-2018.
 */

public class HomePagerAdapter extends FragmentPagerAdapter{
    private HashMap<String,Fragment> fragmentList = new HashMap<>();

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                HomeFragment homeFragment = HomeFragment.newInstance();
                fragmentList.put(FRAGMENT_PILLBOX,homeFragment);
                return homeFragment;
            case 1:
                PillboxFragment pillboxFragment = PillboxFragment.newInstance();
                fragmentList.put(FRAGMENT_PILLBOX,pillboxFragment);
                return pillboxFragment;
            case 2:
                TreatmentListFragment treatmentFragment = new TreatmentListFragment();
                fragmentList.put(FRAGMENT_TREATMENT,treatmentFragment);
                return treatmentFragment;

            case 3:
                ProfileFragment profileFragment = new ProfileFragment();
                fragmentList.put(FRAGMENT_PROFILE,profileFragment);
                return profileFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
