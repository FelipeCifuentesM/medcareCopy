package com.imed.medcare.ui.home.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imed.medcare.R;
import com.imed.medcare.model.User;
import com.imed.medcare.model.UserMeasurement;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.ui.history_list.HistoryListActivity;
import com.imed.medcare.ui.home.home.adapter.CharterAdapter;
import com.imed.medcare.ui.user_poll_menu.UserPollMenu;
import com.imed.medcare.utils.MedcareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmList;


public class HomeFragment extends Fragment {


    @BindView(R.id.rv_chart)
    RecyclerView rvChart;

    private Unbinder unbinder;
    CharterAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setData();
    }

    private RealmList<UserMeasurement> getUserMeasurements() {

        GenericRepositoryRealm<User> userGenericRepositoryRealm = new GenericRepositoryRealm<>(User.class);
        userGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());

        RealmList<UserMeasurement> userMeasurementRealmList = new RealmList<>();
        userMeasurementRealmList.add(new UserMeasurement(0));
        if(!userGenericRepositoryRealm.getFirst().isMessageReaded()) {
            userMeasurementRealmList.add(new UserMeasurement(1));
        }
        if(userGenericRepositoryRealm.getFirst().getUserMeasurementRealmList().size()>0) {
            userMeasurementRealmList.add(new UserMeasurement(2));
        }
        userMeasurementRealmList.addAll(userGenericRepositoryRealm.getFirst().getUserMeasurementRealmList());

        return userMeasurementRealmList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    void setData(){

        final RealmList<UserMeasurement> userMeasurementRealmList = getUserMeasurements();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        for(UserMeasurement userMeasurement : userMeasurementRealmList) {
            if (userMeasurement == null) {
               Log.i("userMeasurement","null");
            } else{
                if(userMeasurement.getName() == null){
                    Log.i("userMeasurementName","null");
                }else {
                    Log.i("userMeasurementName", userMeasurement.getName());
                }
            }
        }

        adapter = new CharterAdapter(userMeasurementRealmList, R.layout.item_chart, R.layout.item_attachment_home, R.layout.item_message_home, R.layout.item_title_charter, new CharterAdapter.onItemListener() {
            @Override
            public void btnMessage(int position) {
                userMeasurementRealmList.remove(position);
                adapter.notifyItemRemoved(position);
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.where(User.class).findFirst().setMessageReaded(true);
                realm.where(User.class).findFirst().setMessageDate(MedcareUtils.getDateToday());
                realm.commitTransaction();
                realm.close();
            }

            @Override
            public void btnAttachmentAnswer() {
                Intent intent = new Intent(getActivity(), UserPollMenu.class);
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                startActivity(intent);
            }

            @Override
            public void btnAttachmentHistoy() {

                Intent intent = new Intent(getActivity(), HistoryListActivity.class);
                intent.putExtra("isFromAttachment",true);
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                startActivity(intent);
            }
        });
        rvChart.setLayoutManager(llm);
        rvChart.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }
}
