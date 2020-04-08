package com.imed.medcare.ui.home.pillbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.imed.medcare.R;
import com.imed.medcare.model.Pillbox;
import com.imed.medcare.model.PillboxHeader;
import com.imed.medcare.ui.home.pillbox.adapter.PillboxAdapter;
import com.imed.medcare.ui.home.pillbox.bottom_sheet_dialog.MyBottomSheetDialogFragment;
import com.imed.medcare.ui.home.pillbox.dialog_fragment.TimePickerDialogFragment;
import com.imed.medcare.ui.manage_prescription.ManagePrescriptionActivity;
import com.imed.medcare.utils.MedcareUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import io.realm.Realm;
import io.realm.RealmList;

import static com.imed.medcare.utils.MedcareUtils.YearMonthDayString;

/**
 * Created by Ramiro on 08-05-2018.
 */

public class PillboxFragment extends Fragment implements PillboxContract.View {
    @BindView(R.id.main_constraint)
    ConstraintLayout mainConstraint;
    @BindView(R.id.actual_date)
    TextView actualDate;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.calendarView)
    HorizontalCalendarView calendarView;
    @BindView(R.id.content_progressbar_home_pillbox)
    RelativeLayout contentProgressbar;
    Date updatedDate;
    private HorizontalCalendar horizontalCalendar;
    private Integer actualPosition;
    private PillboxContract.Presenter pillboxPresenter;
    private Unbinder unbinder;
    private int itemsTimeMovement;
    private PillboxAdapter pillboxAdapter = null;
    private BottomSheetDialogFragment bsdFragment;
    private final int REQUEST_CODE = 201;
    ArrayList<PillboxHeader> pillboxHeaders = new ArrayList<>();
    private boolean isFirstTime = true;
    private View rootView;

    public static PillboxFragment newInstance() {

        Bundle args = new Bundle();
        PillboxFragment fragment = new PillboxFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home_pillbox, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        pillboxPresenter = new PillboxPresenter(this);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.itemsTimeMovement = 0;
        Calendar calendar = Calendar.getInstance();
        updatedDate = calendar.getTime();
        pillboxPresenter.getMedicinesByDay(calendar);
        pillboxPresenter.updateDate(calendar);


    }

    @Override
    public void showMedicines(ArrayList<PillboxHeader> pillboxHeaders) {

        this.pillboxHeaders = pillboxHeaders;
        if (pillboxAdapter == null) {
            LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            pillboxAdapter = new PillboxAdapter(pillboxHeaders, this, updatedDate, getActivity());
            recyclerView.setLayoutManager(llm);
            recyclerView.setAdapter(pillboxAdapter);
            recyclerView.invalidate();
            ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

            recyclerView.setHasFixedSize(true);
            showElements();
        } else {
            recyclerView.removeAllViews();
            pillboxAdapter = new PillboxAdapter(pillboxHeaders, this, updatedDate, getActivity());
            recyclerView.setAdapter(pillboxAdapter);
            ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
            recyclerView.setHasFixedSize(true);
            showRecycleView();
        }
    }

    @Override
    public void showTimePickerDialog(int idPrescription, String date, String time, String dose, String responseDate, String responseDose, Integer taken) {
        if (getActivity() != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("idPrescription", idPrescription);
            bundle.putString("time", time);
            bundle.putString("date", date);
            bundle.putString("responseDate", responseDate);
            bundle.putInt("taken", taken);
            bundle.putString("dose", dose);
            bundle.putString("responseDose", responseDose);

            FragmentManager fm = getActivity().getSupportFragmentManager();
            TimePickerDialogFragment timePickerDialogFragment = TimePickerDialogFragment.newInstance(this);
            timePickerDialogFragment.setArguments(bundle);
            timePickerDialogFragment.show(fm, "FragmentPickerDialog");
        }
    }

    @Override
    public void showUpdatedDate(String dateUpdated) {
        actualDate.setText(dateUpdated);
    }

    @Override
    public void setPrescriptionAttachment(int idPrescription, String date, String time, String dose, String responseDate, String responseTime, String responseDose, Integer reason, Integer taken) {
        pillboxPresenter.setPrescriptionAttachment(idPrescription, date, time, dose, responseDate, responseTime, responseDose, reason, taken);
    }

    @Override
    public void showManagePrescription() {
        try {
            Intent intent = new Intent(getActivity(), ManagePrescriptionActivity.class);
            intent.putExtra("date", YearMonthDayString(updatedDate));
            getActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.stand);
            startActivityForResult(intent, REQUEST_CODE);
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                manageLoader();
                final String result = data.getStringExtra(ManagePrescriptionActivity.EXTRA_DATA);
                showResult(result);
            }
        }
    }

    @Override
    public void manageLoader() {
        if (contentProgressbar.getVisibility() == View.VISIBLE)
            contentProgressbar.setVisibility(View.GONE);
        else
            contentProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResult(String message) {

        Snackbar snackbar = Snackbar.make(mainConstraint, message, Snackbar.LENGTH_LONG);
        if (message.contains("Éxito") || message.contains("exitosamente")) {
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.green_blue));
        }
        snackbar.show();
        Calendar cal = Calendar.getInstance();
        cal.setTime(updatedDate);
        this.itemsTimeMovement = 0;
        pillboxPresenter.getMedicinesByDay(cal);
        manageLoader();
    }

    private void showElements() {

        Animation animationTop;
        animationTop = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_top_calendar);
        animationTop.setDuration(itemsTimeMovement);
        appBarLayout.startAnimation(animationTop);
        appBarLayout.setVisibility(View.VISIBLE);

        showRecycleView();
        this.itemsTimeMovement = 650;

        initCalendar();
    }

    private void initCalendar() {

        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        horizontalCalendar = new HorizontalCalendar.Builder(rootView, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(7)
                .configure()
                .formatTopText("EEE")
                .showTopText(true)
                .showBottomText(false)
                .end()
                .defaultSelectedDate(Calendar.getInstance())
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                Log.e("Calendar", String.valueOf(date.getTime()));
                onDateSelectedInCalendar(date);
                if (actualPosition != null) {
                }

                actualPosition = position;

            }

            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView,
                                         int dx, int dy) {
            }

            @Override
            public boolean onDateLongClicked(Calendar date, int position) {
                return true;
            }
        });

    }

    private void onDateSelectedInCalendar(Calendar date) {
        pillboxPresenter.updateDate(date);
        updatedDate = date.getTime();
        pillboxPresenter.getMedicinesByDay(date);
    }
    private void showRecycleView() {

        LayoutAnimationController animationBottom = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_fall_down);
        recyclerView.setLayoutAnimation(animationBottom);
        assert recyclerView.getAdapter() != null;
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
        Activity activity = getActivity();
        if (activity != null && activity.getIntent() != null && activity.getIntent().getStringExtra("time") != null && isFirstTime) {
            String time = getActivity().getIntent().getStringExtra("time");
            setOnclickPillbox(time);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showBottomSheet(int bottomSheetType, Pillbox pillbox) {

        String currentDateAndHour = MedcareUtils.CurrentDateAndHour();
        String currentDate = currentDateAndHour.split(" ")[0];
        String currentTime = currentDateAndHour.split(" ")[1];
        Bundle bundle = new Bundle();
        bundle.putInt("idPrescription", pillbox.getIdPrescription());
        bundle.putString("time", pillbox.getTime());
        bundle.putInt("idSchedule", pillbox.getIdSchuedule());
        bundle.putString("date", YearMonthDayString(updatedDate));
        bundle.putString("responseDate", currentDate);
        bundle.putString("responseTime", currentTime);
        bsdFragment =
                MyBottomSheetDialogFragment.newInstance(bottomSheetType, this);

        bsdFragment.setArguments(bundle);
        bsdFragment.show(
                getActivity().getSupportFragmentManager(), "BSS");

    }

    @Override
    public void closeBottomSheet() {
        if (bsdFragment != null && bsdFragment.isVisible()) {
            bsdFragment.dismiss();

        }
    }


    public void setOnclickPillbox(final String time) {
        isFirstTime = false;
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.delete(Pillbox.class);
        realm.commitTransaction();
        RealmList<Pillbox> pillboxRealmList = new RealmList<>();
        updatedDate = Calendar.getInstance().getTime();
        for (int i = 0; pillboxHeaders.size() > i; i++) {
            for (int j = 0; pillboxHeaders.get(i).getPillboxArrayList().size() > j; j++) {
                if (time.equalsIgnoreCase(pillboxHeaders.get(i).getPillboxArrayList().get(j).getTime()) && pillboxHeaders.get(i).getPillboxArrayList().get(j).getTaken() == null) {
                    pillboxRealmList.add(pillboxHeaders.get(i).getPillboxArrayList().get(j));
                }
            }
        }
        Pillbox.saveToRealm(pillboxRealmList, realm);
        realm.close();
        if (pillboxRealmList.size() > 0) {
            showManagePrescription();
        }
    }
}





/*
https://github.com/Mulham-Raee/Horizontal-Calendar
https://stackoverflow.com/questions/46693319/how-to-make-a-calendar-view-with-horizontal-scrolling-week-view
 */