package com.imed.medcare.ui.show_document;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.imed.medcare.R;
import com.imed.medcare.model.Document;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.utils.Constants;
import com.imed.medcare.utils.MedcareUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class ShowDocument extends AppCompatActivity {

    @BindView(R.id.textView20)
    TextView textView20;
    @BindView(R.id.imageView9)
    ImageView imageView9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_document);
        ButterKnife.bind(this);

        GenericRepositoryRealm<Document> documentGenericRepositoryRealm = new GenericRepositoryRealm<>(Document.class);
        documentGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());
        int idDocument = getIntent().getIntExtra(Constants.ID_DOCUMENT,1);
        Document document = documentGenericRepositoryRealm.get(idDocument,"id");
        textView20.setText(document.getName());
        MedcareUtils.glideImageShowDocument(imageView9, document.getPicture(), R.drawable.ic_file_placeholder,ShowDocument.this);

    }

    @OnClick(R.id.imageButton)
    public void onViewClicked() {
        finish();
    }
}
