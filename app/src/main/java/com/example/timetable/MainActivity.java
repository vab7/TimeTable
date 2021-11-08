package com.example.timetable;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.timetable.adapter.LessonAdapter;
import com.example.timetable.model.Lesson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    RecyclerView timetableRecycler;

    LessonAdapter lessonAdapter;

    ArrayList<String> sectionList = new ArrayList<>();
    HashMap<String, ArrayList<Lesson>> listHashMap = new HashMap<>();

    Calendar calendar = Calendar.getInstance();

//    int date = calendar.get(Calendar.WEEK_OF_YEAR)-1;

    ArrayList<ArrayList<Lesson>> arrayListLessons = new ArrayList<>();

    int i = 2;
    int j = 0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        timetableRecycler = findViewById(R.id.time_table_recycler);

        setTitle("Расписание (С-33)");

//        sectionList.add(whatIsToday(2));
//        sectionList.add(whatIsToday(3));
//        sectionList.add(whatIsToday(4));
//        sectionList.add(whatIsToday(5));
//        sectionList.add(whatIsToday(6));
//        sectionList.add(whatIsToday(7));
//
//        getMon();
//        getTue();
//        getWed();
//        getThu();
//        getFri();
//        getSat();
//
//        listHashMap.put(sectionList.get(0), arrayListLessons.get(0));
//        listHashMap.put(sectionList.get(1), arrayListLessons.get(1));
//        listHashMap.put(sectionList.get(2), arrayListLessons.get(2));
//        listHashMap.put(sectionList.get(3), arrayListLessons.get(3));
//        listHashMap.put(sectionList.get(4), arrayListLessons.get(4));
//        listHashMap.put(sectionList.get(5), arrayListLessons.get(5));

        while (sectionList.size() < 6) {

            if (calendar.get(Calendar.DAY_OF_WEEK) == i) {

                sectionList.add(whatIsToday(i));

                if (i == 2) {
                    getMon();
                }
                if (i == 3) {
                    getTue();
                }
                if (i == 4) {
                    getWed();
                }
                if (i == 5) {
                    getThu();
                }
                if (i == 6) {
                    getFri();
                }
                if (i == 7){
                    getSat();
                }

                listHashMap.put(sectionList.get(j), arrayListLessons.get(j));

                j++;

                calendar.add(Calendar.DATE, 1);

                i = 1;

            }

            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//                date++;
                calendar.add(Calendar.DATE, 1);
                i = 1;
            }

            i++;
        }

        lessonAdapter = new LessonAdapter(this, sectionList, listHashMap);
        lessonAdapter.shouldShowHeadersForEmptySections(false);
        timetableRecycler.setAdapter(lessonAdapter);
    }

    private void getMon() {
        ArrayList<Lesson> lessonArrayListMon = new ArrayList<>();
        lessonArrayListMon.add(new Lesson(
                "10:15",
                "11:50",
                "Архитектура",
                "Сухарева",
                "Кабинет 304-1"
        ));
        lessonArrayListMon.add(new Lesson(
                "12:20",
                "13:55",
                "Чухлебова",
                "Чухлебова",
                "Кабинет 308-1"
        ));
        if (calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 1) {
            lessonArrayListMon.add(new Lesson(
                    "14:05",
                    "15:40",
                    "Чухлебова",
                    "Чухлебова",
                    "Кабинет 308-1"
            ));
        }
        arrayListLessons.add(lessonArrayListMon);
    }

    private void getTue() {
        ArrayList<Lesson> lessonArrayListTue = new ArrayList<>();
        lessonArrayListTue.add(new Lesson(
                "8:30",
                "10:05",
                "Архитектура",
                "Сухарева",
                "Кабинет 304-1"
        ));
        lessonArrayListTue.add(new Lesson(
                "10:15",
                "11:50",
                "Английский язык",
                "Голубева",
                "Кабинет 61"
        ));
        lessonArrayListTue.add(new Lesson(
                "10:15",
                "11:50",
                "Английский язык",
                "Боева",
                "Кабинет 56"
        ));
        lessonArrayListTue.add(new Lesson(
                "12:20",
                "13:55",
                "Экология",
                "Сивопляс",
                "Кабинет"
        ));

        if (calendar.get(Calendar.WEEK_OF_YEAR) % 2 == 1) {
            lessonArrayListTue.add(new Lesson(
                    "14:05",
                    "15:40",
                    "Экология",
                    "Сивопляс",
                    "Кабинет"
            ));
        }
        arrayListLessons.add(lessonArrayListTue);
    }

    private void getWed() {
        ArrayList<Lesson> lessonArrayListWed = new ArrayList<>();
        lessonArrayListWed.add(new Lesson(
                "8:30",
                "10:05",
                "Ткач",
                "Ткач",
                "Кабинет 308-1"
        ));
        lessonArrayListWed.add(new Lesson(
                "10:15",
                "11:50",
                "Ткач",
                "Ткач",
                "Кабинет 308-1"
        ));
//        if (date % 2 == 0) {
//            lessonArrayListWed.remove(1);
//            lessonArrayListWed.add(new Lesson(
//                    "16:40",
//                    "18:10",
//                    "Английский язык",
//                    "Андрюшина А.Ю.",
//                    "Кабинет 301-1"
//            ));
//        }
        arrayListLessons.add(lessonArrayListWed);
    }

    private void getThu() {
        ArrayList<Lesson> lessonArrayListThu = new ArrayList<>();
        lessonArrayListThu.add(new Lesson(
                "8:30",
                "10:05",
                "Кутепова (2 подгруппа)",
                "Кутепова",
                "Кабинет 43"
        ));
        lessonArrayListThu.add(new Lesson(
                "10:15",
                "11:50",
                "Архитектура",
                "Сухарева",
                "Кабинет 401-1"
        ));
        lessonArrayListThu.add(new Lesson(
                "12:20",
                "13:55",
                "Шатохина (1 подгруппа)",
                "Шатохина",
                "Кабинет 44"
        ));
//        if (date % 2 == 0) {
//            lessonArrayListThu.remove(2);
//            lessonArrayListThu.add(new Lesson(
//                    "16:40",
//                    "18:10",
//                    "Английский язык",
//                    "Андрюшина А.Ю.",
//                    "301-1"
//            ));
//        }
        arrayListLessons.add(lessonArrayListThu);
    }

    private void getFri() {
        ArrayList<Lesson> lessonArrayListFri = new ArrayList<>();
        lessonArrayListFri.add(new Lesson(
                "8:30",
                "10:05",
                "Архитектура",
                "Сухарева",
                "Кабинет 302-1"
        ));
        lessonArrayListFri.add(new Lesson(
                "10:15",
                "11:50",
                "Слизанская",
                "Слизанская",
                "Кабинет 305-1"
        ));
        lessonArrayListFri.add(new Lesson(
                "12:20",
                "13:55",
                "Жук",
                "Жук",
                "Кабинет"
        ));
        lessonArrayListFri.add(new Lesson(
                "14:05",
                "15:40",
                "Физическая культура",
                "Учитель",
                "Кабинет"
        ));
//        if (date % 2 == 0) {
//            lessonArrayListFri.remove(2);
//            lessonArrayListFri.add(new Lesson(
//                    "16:40",
//                    "18:10",
//                    "Английский язык",
//                    "Андрюшина А.Ю.",
//                    "301-1"
//            ));
//        }
        arrayListLessons.add(lessonArrayListFri);
    }

    private void getSat() {
        ArrayList<Lesson> lessonArrayListSat = new ArrayList<>();
        lessonArrayListSat.add(new Lesson(
                "12:20",
                "13:55",
                "Курсач",
                "Сухарева/Ковтун",
                "Кабинет"
        ));
        lessonArrayListSat.add(new Lesson(
                "14:05",
                "15:40",
                "Курсач",
                "Сухарева/Ковтун",
                "Кабинет"
        ));
//        if (date % 2 == 0) {
//            lessonArrayListSat.remove(2);
//            lessonArrayListSat.add(new Lesson(
//                    "16:40",
//                    "18:10",
//                    "Английский язык",
//                    "Андрюшина А.Ю.",
//                    "301-1"
//            ));
//        }
        arrayListLessons.add(lessonArrayListSat);
    }

    private String whatIsToday(int date) {

        String dayOfWeek;
        Calendar today = Calendar.getInstance();

        if (date == today.get(Calendar.DAY_OF_WEEK)) {
            return "Сегодня";
        } else {
            today.add(Calendar.DATE, 1);
            if (date == today.get(Calendar.DAY_OF_WEEK)) {
                return "Завтра";
            } else {
                today.add(Calendar.DATE, 1);
                if (date == today.get(Calendar.DAY_OF_WEEK)) {
                    return "Послезавтра";
                } else {
                    switch (date) {
                        case 2:

                            for (int i=0; i < 6; i++) {
                                if (date == today.get(Calendar.DAY_OF_WEEK)) {
                                    break;
                                } else {
                                    today.add(Calendar.DATE, 1);
                                }
                            }

                            String dayMon = 9 < today.get(Calendar.DATE) ? "" + today.get(Calendar.DATE) : "0" + today.get(Calendar.DATE);
                            String monthMon = 9 < (today.get(Calendar.MONTH) + 1) ? "" + (today.get(Calendar.MONTH) + 1) : "0" + (today.get(Calendar.MONTH) + 1);
                            dayOfWeek = dayMon + "." + monthMon + " Понедельник";
                            break;
                        case 3:

                            for (int i=0; i < 6; i++) {
                                if (date == today.get(Calendar.DAY_OF_WEEK)) {
                                    break;
                                } else {
                                    today.add(Calendar.DATE, 1);
                                }
                            }

                            String dayTue = 9 < today.get(Calendar.DATE) ? "" + today.get(Calendar.DATE) : "0" + today.get(Calendar.DATE);
                            String monthTue = 9 < (today.get(Calendar.MONTH) + 1) ? "" + (today.get(Calendar.MONTH) + 1) : "0" + (today.get(Calendar.MONTH) + 1);
                            dayOfWeek = dayTue + "." + monthTue + " Вторник";
                            break;
                        case 4:

                            for (int i=0; i < 6; i++) {
                                if (date == today.get(Calendar.DAY_OF_WEEK)) {
                                    break;
                                } else {
                                    today.add(Calendar.DATE, 1);
                                }
                            }

                            String dayWed = 9 < today.get(Calendar.DATE) ? "" + today.get(Calendar.DATE) : "0" + today.get(Calendar.DATE);
                            String monthWed = 9 < (today.get(Calendar.MONTH) + 1) ? "" + (today.get(Calendar.MONTH) + 1) : "0" + (today.get(Calendar.MONTH) + 1);
                            dayOfWeek = dayWed + "." + monthWed + " Среда";
                            break;
                        case 5:

                            for (int i=0; i < 6; i++) {
                                if (date == today.get(Calendar.DAY_OF_WEEK)) {
                                    break;
                                } else {
                                    today.add(Calendar.DATE, 1);
                                }
                            }

                            String dayThu = 9 < today.get(Calendar.DATE) ? "" + today.get(Calendar.DATE) : "0" + today.get(Calendar.DATE);
                            String monthThu = 9 < (today.get(Calendar.MONTH) + 1) ? "" + (today.get(Calendar.MONTH) + 1) : "0" + (today.get(Calendar.MONTH) + 1);
                            dayOfWeek = dayThu + "." + monthThu + " Четверг";
                            break;
                        case 6:

                            for (int i=0; i < 6; i++) {
                                if (date == today.get(Calendar.DAY_OF_WEEK)) {
                                    break;
                                } else {
                                    today.add(Calendar.DATE, 1);
                                }
                            }

                            String dayFri = 9 < today.get(Calendar.DATE) ? "" + today.get(Calendar.DATE) : "0" + today.get(Calendar.DATE);
                            String monthFri = 9 < (today.get(Calendar.MONTH) + 1) ? "" + (today.get(Calendar.MONTH) + 1) : "0" + (today.get(Calendar.MONTH) + 1);
                            dayOfWeek = dayFri + "." + monthFri + " Пятница";
                            break;
                        case 7:

                            for (int i=0; i < 6; i++) {
                                if (date == today.get(Calendar.DAY_OF_WEEK)) {
                                    break;
                                } else {
                                    today.add(Calendar.DATE, 1);
                                }
                            }

                            String daySat = 9 < today.get(Calendar.DATE) ? "" + today.get(Calendar.DATE) : "0" + today.get(Calendar.DATE);
                            String monthSat = 9 < (today.get(Calendar.MONTH) + 1) ? "" + (today.get(Calendar.MONTH) + 1) : "0" + (today.get(Calendar.MONTH) + 1);
                            dayOfWeek = daySat + "." + monthSat + " Суббота";
                            break;
                        default:
                            dayOfWeek = "";
                    }
                }
            }
        }

        return dayOfWeek;

    }

}