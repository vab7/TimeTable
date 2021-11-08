package com.example.timetable.adapter;

import android.app.Activity;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.example.timetable.R;
import com.example.timetable.model.Lesson;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class LessonAdapter extends SectionedRecyclerViewAdapter<LessonAdapter.ViewHolder> {

    Activity activity;

    ArrayList<String> sectionList;
    HashMap<String, ArrayList<Lesson>> itemList;

    public LessonAdapter(
            Activity activity,
            ArrayList<String> sectionList,
            HashMap<String, ArrayList<Lesson>> itemList
    ) {
        this.activity = activity;
        this.sectionList = sectionList;
        this.itemList = itemList;
    }

    @Override
    public int getSectionCount() {
        return sectionList.size();
    }

    @Override
    public int getItemCount(int section) {
        return itemList.get(sectionList.get(section)).size();
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.date.setText(sectionList.get(i));
    }

    @Override
    public int getItemViewType(int section, int relativePosition, int absolutePosition) {
        return section;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i, int i1, int i2) {
        Lesson sItem = itemList.get(sectionList.get(i)).get(i1);
        viewHolder.firstTime.setText(sItem.getFirstTime());
        viewHolder.secondTime.setText(sItem.getSecondTime());
        viewHolder.lesson.setText(sItem.getLesson());
        viewHolder.teacher.setText(sItem.getTeacher());
        viewHolder.door.setText(sItem.getDoor());

//        viewHolder.lesson.setText(String.valueOf(i));
//        viewHolder.teacher.setText(String.valueOf(i1));
//        viewHolder.door.setText(String.valueOf(i2));

        if (sectionList.get(i).contains("Сегодня")) {
            viewHolder.bg.setBackgroundColor(ContextCompat.getColor(activity, R.color.blue_light));
            viewHolder.number.setVisibility(View.VISIBLE);

            String[] time = sItem.getFirstTime().split(":");

            Calendar timeCalendar = Calendar.getInstance();
            timeCalendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
            timeCalendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
            timeCalendar.set(Calendar.SECOND, 0);
            timeCalendar.set(Calendar.MILLISECOND, 0);
            long firstTime = timeCalendar.getTimeInMillis();

            Calendar currentCalendar = Calendar.getInstance();
            long currentTime = currentCalendar.getTimeInMillis();

            new CountDownTimer(firstTime - currentTime, 1) {

                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onTick(long millisUntilFinished) {
                    int color = ContextCompat.getColor(activity, R.color.blue_light);

                    Duration duration = Duration.ofMillis(millisUntilFinished);
                    long hours = duration.toHours();
                    duration = duration.minusHours(hours);
                    long minutes = duration.toMinutes();
                    duration = duration.minusMinutes(minutes);
                    long seconds = duration.getSeconds();

                    String timeThrough = "Начало через ";

                    if (hours == 0 && minutes == 0) {
                        timeThrough = seconds + " сек.";
                    } else {
                        if (!(hours == 0)) {
                            timeThrough += hours + " ч. ";
                        }
                        if (!(minutes == 0)) {
                            timeThrough += minutes + " мин. ";
                        }
                    }

                    viewHolder.bg.setBackgroundColor(color);
                    viewHolder.number.setTextColor(color);

                    viewHolder.number.setText(timeThrough);
                }

                @Override
                public void onFinish() {
                    
                    Calendar timeLesson = Calendar.getInstance();
                    Calendar timeNow = Calendar.getInstance();

                    String[] time = sItem.getFirstTime().split(":");

                    timeLesson.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
                    timeLesson.set(Calendar.MINUTE, Integer.parseInt(time[1]));
                    timeLesson.set(Calendar.SECOND, 0);
                    timeLesson.set(Calendar.MILLISECOND, 0);

                    long millis = 5400000 + 300000 - timeNow.getTimeInMillis() + timeLesson.getTimeInMillis();
                    
                    new CountDownTimer(millis, 1) {

                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onTick(long millisUntilFinished) {
                            int color = ContextCompat.getColor(activity, R.color.green);

                            viewHolder.bg.setBackgroundColor(color);
                            viewHolder.number.setTextColor(color);

                            Duration duration = Duration.ofMillis(millisUntilFinished);
                            long hours = duration.toHours();
                            duration = duration.minusHours(hours);
                            long minutes = duration.toMinutes();
                            duration = duration.minusMinutes(minutes);
                            long seconds = duration.getSeconds();

                            String timeThrough = "Конец через ";

                            if (hours == 0 && minutes == 0) {
                                timeThrough = seconds + " секунд";
                            } else {
                                if (!(hours == 0)) {
                                    timeThrough += hours + " ч. ";
                                }
                                if (!(minutes == 0)) {
                                    timeThrough += minutes + " мин. ";
                                }
                            }

                            viewHolder.number.setText(timeThrough);
                        }

                        @Override
                        public void onFinish() {
                            int color = ContextCompat.getColor(activity, R.color.grey);

                            viewHolder.bg.setBackgroundColor(color);
                            viewHolder.number.setTextColor(color);

                            viewHolder.number.setText("Пара закончилась!");

                        }

                    }.start();

                }
            }.start();
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout;

        if (viewType == VIEW_TYPE_HEADER) {
            layout = R.layout.time_table_date_item;
        } else {
            layout = R.layout.time_table_lesson_item;
        }

        View view = LayoutInflater.from(
                parent.getContext()).inflate(
                        layout,
                        parent,
                false
        );

        return new ViewHolder(view);
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {

        TextView date, firstTime, secondTime, lesson, teacher, door, number;
        ConstraintLayout bg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);

            firstTime = itemView.findViewById(R.id.first_time);
            secondTime = itemView.findViewById(R.id.second_time);
            lesson = itemView.findViewById(R.id.lesson);
            teacher = itemView.findViewById(R.id.teacher);
            door = itemView.findViewById(R.id.door);
            bg = itemView.findViewById(R.id.bg);
            number = itemView.findViewById(R.id.number);

        }
    }

}