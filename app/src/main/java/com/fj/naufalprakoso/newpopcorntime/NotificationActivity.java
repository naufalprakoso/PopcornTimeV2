package com.fj.naufalprakoso.newpopcorntime;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fj.naufalprakoso.newpopcorntime.preference.AlarmPreference;
import com.fj.naufalprakoso.newpopcorntime.service.AlarmReceiver;
import com.fj.naufalprakoso.newpopcorntime.service.UpcomingScheduler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.btn_alarm_time)
    Button btnAlarmTime;
    @BindView(R.id.tv_alarm_time)
    TextView tvAlarmTime;
    @BindView(R.id.btn_set_daily) Button btnSetDaily;
    @BindView(R.id.btn_cancel_daily) Button btnCancelDialy;
    @BindView(R.id.btn_set_upcoming) Button btnSetUpcomingSchedul;
    @BindView(R.id.btn_cancel_upcoming) Button btnCancelUpcomingSchedul;

    private Calendar calRepeatTimeTime;
    private AlarmReceiver alarmReceiver;
    private AlarmPreference alarmPreference;
    private UpcomingScheduler upcomingScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        btnAlarmTime.setOnClickListener(this);
        btnSetDaily.setOnClickListener(this);
        btnCancelDialy.setOnClickListener(this);
        btnSetUpcomingSchedul.setOnClickListener(this);
        btnCancelUpcomingSchedul.setOnClickListener(this);
        calRepeatTimeTime = Calendar.getInstance();
        alarmPreference = new AlarmPreference(this);
        alarmReceiver = new AlarmReceiver();

        upcomingScheduler = new UpcomingScheduler(this);

        if (!TextUtils.isEmpty(alarmPreference.getRepeatingTime())){
            setRepeatingText();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_alarm_time){
            final Calendar currentDate = Calendar.getInstance();

            new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    calRepeatTimeTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calRepeatTimeTime.set(Calendar.MINUTE, minute);

                    @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                    tvAlarmTime.setText(simpleDateFormat.format(calRepeatTimeTime.getTime()));
                }
            },currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), true).show();
        }else if (v.getId() == R.id.btn_set_daily){
            @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String setRepeatingTime = timeFormat.format(calRepeatTimeTime.getTime());

            alarmPreference.setRepeatingTime(setRepeatingTime);
            alarmReceiver.setAlarmDailyReminder(this, alarmPreference.getRepeatingTime());
        }else  if (v.getId() == R.id.btn_cancel_daily){
            alarmReceiver.cancelAlarm(this);
            alarmPreference.clear();
        }else if (v.getId() == R.id.btn_set_upcoming){
            upcomingScheduler.createPeriodicTask();
            Toast.makeText(this, "Notifikasi berhasil diaktifkan", Toast.LENGTH_LONG).show();
        }else if (v.getId() == R.id.btn_cancel_upcoming){
            upcomingScheduler.cancelPeriodicTask();
            Toast.makeText(this, "Notifikasi berhasil dibatalkan", Toast.LENGTH_LONG).show();
        }
    }

    private void setRepeatingText() {
        tvAlarmTime.setText(alarmPreference.getRepeatingTime());
    }
}
