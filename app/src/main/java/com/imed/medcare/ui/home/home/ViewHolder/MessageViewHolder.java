package com.imed.medcare.ui.home.home.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.imed.medcare.R;
import com.imed.medcare.model.User;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.ui.home.home.adapter.CharterAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_body_message)
    TextView tvBodyMessage;
    private CharterAdapter.onItemListener listener;
    private int position;

    public MessageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CharterAdapter.onItemListener listener, int position) {
        this.listener = listener;
        this.position = position;
        GenericRepositoryRealm<User> userGenericRepositoryRealm = new GenericRepositoryRealm<>(User.class);
        userGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());
        tvBodyMessage.setText(userGenericRepositoryRealm.getFirst().getDealyMessage());
    }

    @OnClick(R.id.btn_done)
    public void onViewClickedUnderstand() {
        listener.btnMessage(position);
    }
}
