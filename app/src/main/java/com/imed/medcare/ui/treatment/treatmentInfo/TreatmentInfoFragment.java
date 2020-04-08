package com.imed.medcare.ui.treatment.treatmentInfo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.imed.medcare.R;
import com.imed.medcare.model.Document;
import com.imed.medcare.model.Medicine;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.model.TreatmentPoll;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.ui.treatment.TreatmentContract;
import com.imed.medcare.ui.treatment.treatmentInfo.adapter.DocumentAdapter;
import com.imed.medcare.ui.treatment.treatmentInfo.adapter.MedicineAdapter;
import com.imed.medcare.utils.Constants;
import com.imed.medcare.utils.ImagePicker;
import com.imed.medcare.utils.MedcareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import io.realm.RealmList;

import static android.app.Activity.RESULT_OK;
import static com.imed.medcare.network.RestClient.BASE_URL;
import static com.imed.medcare.utils.MedcareUtils.getCantDays;
import static com.imed.medcare.utils.MedcareUtils.glideImage;

/**
 * Created by Ramiro on 24-05-2018.
 */

public class TreatmentInfoFragment extends Fragment implements TreatmentInfoContract.View {

    @BindView(R.id.treatment_see_more)
    Button treatmentSeeMore;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout ctLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.medicine_list)
    RecyclerView medicineList;
    @BindView(R.id.recicler_view_documents)
    RecyclerView recyclerView;
    BottomSheetBehavior bottomSheetBehavior;
    @BindView(R.id.item_add_name_document)
    ConstraintLayout itemAddNameDocument;
    @BindView(R.id.input_name_document)
    TextInputEditText inputNameDocument;
    @BindView(R.id.button2)
    Button btnDone;
    @BindView(R.id.button3)
    Button btnCancel;
    @BindView(R.id.bg_item_add_name_document)
    View bgItemAddNameDocument;
    @BindView(R.id.tv_name_poll)
    TextView tvNamePoll;
    @BindView(R.id.tv_time_left)
    TextView tvTimeLeft;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.progressbar_documents)
    ProgressBar progressBarDocument;
    @BindView(R.id.dr_avatar)
    CircleImageView drAvatar;
    @BindView(R.id.button_see_documents)
    TextView buttonSeeDocuments;
    @BindView(R.id.complete_button)
    Button btnCompleteButton;
    @BindView(R.id.all_complete_button)
    Button allCompleteButton;
    @BindView(R.id.btn_waiting_polls)
    Button btnWaitingPolls;
    @BindView(R.id.edit_button)
    Button editButton;
    RealmList<Document> documentRealmList;
    TreatmentInfoContract.Presenter presenter;
    Bitmap imageBitmap;
    private long lastClickTime = 0;
    private static final int PICK_IMAGE_ID = 234;
    private TreatmentContract.View activityListener;
    private MedicineAdapter adapter;
    private DocumentAdapter documentAdapter;

    private Unbinder unbinder;
    private int treatmentPollId;
    private int treatmentId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_treatment_info, container, false);
        unbinder = ButterKnife.bind(this, view);


        activityListener = (TreatmentContract.View) getActivity();
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "font/poppins_regular.ttf");

        Bundle args = getArguments();
        if (args != null) {
            int dissableButton = args.getInt(Constants.ID_POLLTREATMENT,-1);
            if(dissableButton == -1){
                btnCompleteButton.setVisibility(View.GONE);
                btnWaitingPolls.setVisibility(View.VISIBLE);
            } else {
                btnCompleteButton.setVisibility(View.VISIBLE);
                btnWaitingPolls.setVisibility(View.GONE);
            }
        }




        presenter = new TreatmentInfoPresenter(this);
        treatmentId = getArguments().getInt(Constants.ID_TREATMENT);
        treatmentPollId = getArguments().getInt(Constants.ID_POLLTREATMENT);
        documentRealmList = new RealmList<>();
        ctLayout.setCollapsedTitleTypeface(font);
        ctLayout.setExpandedTitleTypeface(font);
        bottomSheetBehavior = BottomSheetBehavior.from(itemAddNameDocument);

        inputNameDocument.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty())
                    btnDone.setEnabled(true);
                else
                    btnDone.setEnabled(false);
            }
        });

        presenter.getDocuments(treatmentId);
        presenter.getMedicines(treatmentId);
        presenter.getTreatmentPoll(treatmentPollId);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GenericRepositoryRealm<Treatment> genericRepositoryRealmTreatment = new GenericRepositoryRealm<>(Treatment.class);
        genericRepositoryRealmTreatment.setRealm(Realm.getDefaultInstance());
        Treatment treatment = genericRepositoryRealmTreatment.get(treatmentId, "id");
        ctLayout.setExpandedTitleColor(getResources().getColor(R.color.secondary_text_color));
        ctLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.secondary_text_color));
        ctLayout.setTitle(treatment.getTreatmentName());

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }


                if (scrollRange + verticalOffset == 0) {
                    treatmentSeeMore.setVisibility(View.GONE);
                } else{
                    treatmentSeeMore.setVisibility(View.VISIBLE);
                }
            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void addDocument() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != getActivity().getPackageManager().PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        } else {
            Intent chooseImageIntent = ImagePicker.getPickImageIntent(getActivity());
            startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);
        }
    }

    @Override
    public void setDocuments(RealmList<Document> documentRealmList) {
        if (this.documentRealmList.size() < documentRealmList.size() && recyclerView != null) {
            this.documentRealmList = documentRealmList;
            if (progressBarDocument != null) {
                progressBarDocument.setVisibility(View.GONE);
            }
            if(documentRealmList.size() >1){
                buttonSeeDocuments.setVisibility(View.VISIBLE);
            }else {
                buttonSeeDocuments.setVisibility(View.GONE);
            }
            LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            documentAdapter = new DocumentAdapter(documentRealmList, this, new DocumentAdapter.onItemListener() {
                @Override
                public void onDeleteDocument(Document document, int position) {
                    documentAdapter.removeAt(position);
                }

                @Override
                public void onShowDocument(Document document) {
                    activityListener.showDocument(document);
                }
            });

            recyclerView.setLayoutManager(llm);
            recyclerView.setAdapter(documentAdapter);
            recyclerView.setNestedScrollingEnabled(false);
            documentAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setMedicines(RealmList<Medicine> medicineRealmList, Treatment treatment) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        medicineList.setLayoutManager(linearLayoutManager);
        adapter = new MedicineAdapter(medicineRealmList, R.layout.item_treatment_medicine);
        medicineList.setAdapter(adapter);
        glideImage(drAvatar, BASE_URL+"professional/avatar/"+treatment.getProfessional().getAvatar(), R.drawable.dummy_avatar, getActivity());
    }

    @Override
    public void setTreatmentPoll(TreatmentPoll treatmentPoll) {
        if (treatmentPoll != null) {
            tvTimeLeft.setText(getCantDays(treatmentPoll.getFinishAt()));
        } else {
            tvTimeLeft.setText("");
        }
        tvNamePoll.setText("Cuestionario médico");
    }

    @Override
    public void showError(String message) {
        if (progressBarDocument != null)
            progressBarDocument.setVisibility(View.GONE);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICK_IMAGE_ID:

                if (resultCode == RESULT_OK) {
                    inputNameDocument.setText("");
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    imageBitmap = ImagePicker.getImageFromResult(getActivity(), resultCode, data);
                    bgItemAddNameDocument.setVisibility(View.VISIBLE);
                    treatmentSeeMore.setVisibility(View.GONE);
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (progressBar != null) {
            String progress = presenter.getAmountAnswered(treatmentId);
            if(!progress.isEmpty()) {
                Integer maxValue = Integer.valueOf(progress.split("/")[1]);
                Integer currentValue = Integer.valueOf(progress.split("/")[0]);

                progressBar.setMax(maxValue);
                progressBar.setProgress(currentValue);
                if(currentValue == maxValue){
                    progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.my_rounded_shape_green));
                    allCompleteButton.setVisibility(View.VISIBLE);
                    editButton.setVisibility(View.VISIBLE);
                    btnCompleteButton.setVisibility(View.GONE);
                }else {
                    btnCompleteButton.setVisibility(View.VISIBLE);
                    allCompleteButton.setVisibility(View.GONE);
                    editButton.setVisibility(View.GONE);
                }
            }else {
                progressBar.setVisibility(View.GONE);
            }
        }

        if (adapter != null) {
            presenter.getDocuments(treatmentId);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1001:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent chooseImageIntent = ImagePicker.getPickImageIntent(getActivity());
                    startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);
                } else {
                    Log.e("error", "pick a image");
                }
                break;
        }
    }

    @OnClick(R.id.button_see_historical)
    void onSeeHistorical() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        activityListener.showHistorical();
    }

    @OnClick(R.id.nav_back)
    void onNavBackClicked() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        activityListener.backPressed();
    }

    @OnClick(R.id.treatment_see_more)
    void treatmentSeeMore() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        activityListener.showDetail();
    }

    @OnClick(R.id.complete_button)
    void onComplete() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        activityListener.showPollTreatment();
    }

    @OnClick(R.id.edit_button)
    void onEdit() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        activityListener.showPollTreatment();
    }

    @OnClick(R.id.button3)
    void onCancel() {

        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        MedcareUtils.hideKeyBoard(getActivity(), inputNameDocument);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                bgItemAddNameDocument.setVisibility(View.GONE);
                treatmentSeeMore.setVisibility(View.GONE);
            }
        }, 500);
    }

    @OnClick(R.id.button2)
    void onDone() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        MedcareUtils.hideKeyBoard(getActivity(), inputNameDocument);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                bgItemAddNameDocument.setVisibility(View.GONE);
                treatmentSeeMore.setVisibility(View.GONE);
            }
        }, 500);
        presenter.setNewDocument(treatmentId, imageBitmap, inputNameDocument.getText().toString());
    }

    @OnClick(R.id.button_see_documents)
    public void onViewClicked() {

        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        activityListener.seeAllDocuments(treatmentId);
    }

    @OnClick(R.id.bg_item_add_name_document)
    public void onViewClickedBottomSheet() {

        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        inputNameDocument.setText("");
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bgItemAddNameDocument.setVisibility(View.GONE);
        treatmentSeeMore.setVisibility(View.VISIBLE);
    }
}
