package com.imed.medcare.ui.all_documents;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.imed.medcare.R;
import com.imed.medcare.model.Document;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.ui.all_documents.adapter.AllDocumentsAdapter;
import com.imed.medcare.ui.show_document.ShowDocument;
import com.imed.medcare.utils.BaseActivity;
import com.imed.medcare.utils.BottomSheetDialog;
import com.imed.medcare.utils.Constants;
import com.imed.medcare.utils.ImagePicker;
import com.imed.medcare.utils.MedcareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;

import static com.imed.medcare.utils.Constants.ID_TREATMENT;

public class AllDocumentsActivity extends BaseActivity implements AllDocumentsContract.View {

    AllDocumentsAdapter adapter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content_all_document)
    CoordinatorLayout contentAllDocument;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.rv_document)
    RecyclerView rvDocument;
    @BindView(R.id.content_progressbar_all_documents)
    RelativeLayout contentProgressBar;
    Bitmap imageBitmap;
    private static final int PICK_IMAGE_ID = 234;
    @BindView(R.id.btn_add_document)
    FloatingActionButton btnAddDocument;
    private RealmList<Document> documentRealmList;
    AllDocumentsContract.Presenter presenter;
    int idTreatment;
    boolean mShowDialog = false;
    BottomSheetDialog addPhotoBottomDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_documents);
        ButterKnife.bind(this);

        idTreatment = getIntent().getIntExtra(ID_TREATMENT, 0);
        presenter = new AllDocumentsPresenter(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Typeface font = Typeface.createFromAsset(getAssets(), "font/poppins_regular.ttf");

        collapsingToolbar.setCollapsedTitleTypeface(font);
        collapsingToolbar.setExpandedTitleTypeface(font);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        rvDocument.setLayoutManager(linearLayoutManager);
        int idTreatment = getIntent().getIntExtra(Constants.ID_TREATMENT, 1);
        documentRealmList = getAllDocuments(idTreatment);
        adapter = new AllDocumentsAdapter(R.layout.item_document_all_document, documentRealmList, new AllDocumentsAdapter.onItemListener() {
            @Override
            public void showDocument(Document document) {
                Intent intent = new Intent(AllDocumentsActivity.this, ShowDocument.class);
                intent.putExtra(Constants.ID_DOCUMENT, document.getId());
                intent.putExtra(Constants.NAME_DOCUMENT, document.getName());
                startActivity(intent);
            }
        });

        rvDocument.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    RealmList<Document> getAllDocuments(int idTreatment) {
        GenericRepositoryRealm<Treatment> genericRepositoryRealmTreatment = new GenericRepositoryRealm<>(Treatment.class);
        genericRepositoryRealmTreatment.setRealm(Realm.getDefaultInstance());
        return genericRepositoryRealmTreatment.get(idTreatment, "id").getDocumentRealmList();
    }

    @OnClick(R.id.btn_add_document)
    public void onViewClicked() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != getPackageManager().PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        } else {
            Intent chooseImageIntent = ImagePicker.getPickImageIntent(this);
            startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICK_IMAGE_ID:

                if (resultCode == RESULT_OK) {

                    mShowDialog = true;
                    imageBitmap = ImagePicker.getImageFromResult(this, resultCode, data);

                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();

        // play with fragments here
        if (mShowDialog) {
            mShowDialog = false;
        // Show only if is necessary, otherwise FragmentManager will take care
            addPhotoBottomDialogFragment =
                    new BottomSheetDialog();

            addPhotoBottomDialogFragment.show(getSupportFragmentManager(),
                    "add_photo_dialog_fragment");
        }
    }


    @Override
    public void showError(String message) {
        //Snackbar.make(allDocumentContent, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void manageLoading() {
        if (contentProgressBar.getVisibility() == View.VISIBLE) {
            contentProgressBar.setVisibility(View.GONE);
        } else {
            contentProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void updateNewDocument(RealmList<Document> documentRealmListUpdated) {
        documentRealmList = new RealmList<>();
        documentRealmList.addAll(documentRealmListUpdated);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void closeBottomSheet() {
        MedcareUtils.hideKeyBoard(this, contentAllDocument);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnAddDocument.setVisibility(View.VISIBLE);
                addPhotoBottomDialogFragment.dismiss();
            }
        }, 500);
    }

    @Override
    public void setNewDocument(String name) {
        MedcareUtils.hideKeyBoard(this, contentAllDocument);
        presenter.setNewDocument(idTreatment, imageBitmap, name);
        addPhotoBottomDialogFragment.dismiss();
    }
}
