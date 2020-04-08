package com.imed.medcare.ui.home.home.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.User;
import com.imed.medcare.model.UserPoll;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.ui.home.home.adapter.CharterAdapter;
import com.imed.medcare.utils.ProgressBarAnimation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

import static com.imed.medcare.utils.MedcareUtils.getCantDays;

public class AttachmentViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.progressbar_attachment_percent)
    ProgressBar progressbarAttachmentPercent;
    @BindView(R.id.tv_attachment_percent)
    TextView tvAttachmentPercent;
    @BindView(R.id.tv_time_left)
    TextView tvTimeLeft;
    @BindView(R.id.tv_attachment_title)
    TextView tvAttachmentTitle;
    @BindView(R.id.conent_name_dayleft)
    RelativeLayout contentNameDayleft;
    @BindView(R.id.tv_how_are_you_name)
    TextView tvHowAreYouName;
    @BindView(R.id.tv_body_attachment_poll)
    TextView tvBodyAttachmentPoll;
    @BindView(R.id.content_attachment_poll)
    LinearLayout contentAttachmentPoll;
    @BindView(R.id.content_attachment_poll_finished)
    LinearLayout contentAttachmentPollFinished;
    @BindView(R.id.btn_pollmenu)
    Button btnPollMenu;
    @BindView(R.id.guideline2)
    View guideline;

    private CharterAdapter.onItemListener listener;

    public AttachmentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CharterAdapter.onItemListener listener) {
        this.listener = listener;
        Realm realm = Realm.getDefaultInstance();
        GenericRepositoryRealm<User> user = new GenericRepositoryRealm<>(User.class);
        user.setRealm(realm);

        GenericRepositoryRealm<UserPoll> userPoll = new GenericRepositoryRealm<>(UserPoll.class);
        userPoll.setRealm(realm);
        if(user.getFirst().getName().split(" ").length <= 1) {
            tvHowAreYouName.setText(user.getFirst().getName());
        }else{
            tvHowAreYouName.setText(user.getFirst().getName().split(" ")[0]);

        }
        int attachmentStatus = user.getFirst().getAttachmentStatus();
        tvAttachmentPercent.setText(attachmentStatus + "%");
        if(userPoll.getFirst() != null && userPoll.getFirst().getStartAt()!=null) {
            tvTimeLeft.setText(getCantDays(userPoll.getFirst().getFinishAt()));
        }else {
            tvTimeLeft.setText("");
        }
        ProgressBarAnimation anim = new ProgressBarAnimation(progressbarAttachmentPercent, 0, attachmentStatus);

        if (attachmentStatus < 78) {
            progressbarAttachmentPercent.setProgressDrawable(App.getContext().getDrawable(R.drawable.circular_red));
            tvAttachmentTitle.setText("Tienes problemas que hay que resolver urgentemente");
        } else if (attachmentStatus < 92) {
            progressbarAttachmentPercent.setProgressDrawable(App.getContext().getDrawable(R.drawable.circular_yellow));
            tvAttachmentTitle.setText("¡No te dejes estar! El equipo médico tiene soluciones para ti");
        } else {
            progressbarAttachmentPercent.setProgressDrawable(App.getContext().getDrawable(R.drawable.circular_green));
            tvAttachmentTitle.setText("¡Estás muy bien, pero sigue cuidándote!");
        }

        if (!user.getFirst().getAvailablePoll()){
            tvBodyAttachmentPoll.setText("Pronto llegarán preguntas para ver cómo está tu Apego");
            btnPollMenu.setVisibility(View.GONE);
            tvTimeLeft.setVisibility(View.GONE);
            guideline.setVisibility(View.GONE);
        }else {
            tvBodyAttachmentPoll.setText("Contesta las siguientes preguntas para ver cómo está tu Apego");
            btnPollMenu.setVisibility(View.VISIBLE);
            tvTimeLeft.setVisibility(View.VISIBLE);
            guideline.setVisibility(View.VISIBLE);
        }

        anim.setDuration(1000);
        progressbarAttachmentPercent.startAnimation(anim);

        if (user.getFirst().getAttachmentStatus() == 100 && user.getFirst().isShowPollAttachmentMessage() ) {
            contentAttachmentPoll.setVisibility(View.GONE);
            contentAttachmentPollFinished.setVisibility(View.VISIBLE);
            realm.beginTransaction();
            user.getFirst().setShowPollAttachmentMessage(false);
            realm.commitTransaction();
            realm.close();
        } else {
            contentAttachmentPoll.setVisibility(View.VISIBLE);
            contentAttachmentPollFinished.setVisibility(View.GONE);
        }

    }


    @OnClick(R.id.btn_pollmenu)
    void onViewClickedAnswer() {

        listener.btnAttachmentAnswer();
    }

    @OnClick(R.id.btn_pollmenu_history)
    void onViewClickedHistory() {


        listener.btnAttachmentHistoy();
    }
}
