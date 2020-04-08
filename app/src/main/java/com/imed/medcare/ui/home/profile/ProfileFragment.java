package com.imed.medcare.ui.home.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imed.medcare.R;
import com.imed.medcare.model.User;
import com.imed.medcare.ui.home.HomeContract;
import com.imed.medcare.utils.MedcareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ramiro on 08-05-2018.
 */


public class ProfileFragment extends Fragment implements ProfileContract.View {
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_card_image)
    CircleImageView userImage;
    private Unbinder unbinder;

    private HomeContract.View activityListener;
    private ProfileContract.Presenter profilePresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_profile, container, false);
        unbinder = ButterKnife.bind(this, view);

        activityListener = (HomeContract.View) getActivity();
        profilePresenter = new ProfilePresenter(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setData();
    }

    private void setData() {

        User user = profilePresenter.getProfile();
        userName.setText(user.getName() + " " + user.getLastName());
        MedcareUtils.glideImage(userImage, user.getAvatar(), R.drawable.dummy_avatar, getActivity());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.button_profile,R.id.button_personal,R.id.button_lifestyle,R.id.button_medics})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_profile:
                activityListener.showProfile();
                break;
            case R.id.button_personal:
                activityListener.showPersonalProfile();
                break;
            case R.id.button_lifestyle:

                activityListener.showPersonalHabit();
                break;
            case R.id.button_medics:

                activityListener.showPersonalMedic();
                break;
        }
    }

}
