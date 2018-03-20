package com.fj.naufalprakoso.newpopcorntime.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.fj.naufalprakoso.newpopcorntime.R;
import com.fj.naufalprakoso.newpopcorntime.activity.MovieItemActivity;
import com.fj.naufalprakoso.newpopcorntime.entity.MovieModel;
import com.fj.naufalprakoso.newpopcorntime.entity.NewMovieModel;
import com.fj.naufalprakoso.newpopcorntime.entity.ResponseNewMovieModel;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlarmReceiver extends BroadcastReceiver {

    public static final String TYPE_REPEATING = "RepeatingAlarm";
    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_TYPE = "type";
    private final int NOTIF_ID_REPEATING = 101;
    private final static String api_key = "5bc5c506bf3ad45dd420e17ebb8486cf";
    private List<NewMovieModel> movieModels;
    final MovieModel mMovie = new MovieModel();
    Intent pindah;

    public AlarmReceiver(){
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        getMovie(context);
    }

    public void getMovie(final Context context){
        ApiInterface apiInterface = ApiService.getClient().create(ApiInterface.class);
        Call<ResponseNewMovieModel> call1 = apiInterface.getUpcomingMovie(api_key);

        call1.enqueue(new Callback<ResponseNewMovieModel>() {
            @Override
            public void onResponse(Call<ResponseNewMovieModel> call, Response<ResponseNewMovieModel> response) {
                movieModels = response.body().getResults();

                mMovie.setTitle(movieModels.get(0).getTitle());
                mMovie.setOverview(movieModels.get(0).getOverview());
                mMovie.setPopulariry(movieModels.get(0).getPopularity());
                mMovie.setRelease_date(movieModels.get(0).getRelease_date());
                mMovie.setBanner(movieModels.get(0).getBanner());
                mMovie.setPoster(movieModels.get(0).getPoster());
                Log.d("movieModels", "title :"+mMovie.getTitle());
                showNotification(context);
            }

            @Override
            public void onFailure(Call<ResponseNewMovieModel> call, Throwable t) {

            }
        });
    }

    private void showNotification(Context context) {
        pindah = new Intent(context, MovieItemActivity.class);
        pindah.putExtra(MovieItemActivity.EXTRA_MOVIE, (Parcelable) mMovie);
        pindah.setAction(Long.toString(System.currentTimeMillis()));
        Log.d("getmovie", "titlenya : "+mMovie.getTitle());
        PendingIntent pendingIntent1 = PendingIntent.getActivity(context, 0, pindah, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notification  = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_movie_filter_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_movie_filter_black_24dp))
                .setContentTitle("Daily Reminder Catalogue film" + mMovie.getTitle())
                .setContentText("Ada Banyak film seru dan baru loh")
                .setContentIntent(pendingIntent1)
                .setSound(alarmSound)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(NOTIF_ID_REPEATING, notification.build());
    }

    public void setAlarmDailyReminder(Context context, String time){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        String timeArray[] = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);

        int requestCode = NOTIF_ID_REPEATING;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Toast.makeText(context, "Notifikasi diaktifkan "+ calendar.getTimeInMillis(), Toast.LENGTH_LONG).show();
    }

    public void cancelAlarm(Context context){
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        int requestCode = NOTIF_ID_REPEATING;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(context, "Daily Reminder dibatalkan", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context, "alarm tidak ada", Toast.LENGTH_LONG).show();
        }
    }
}
