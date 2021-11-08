package com.example.timetable.model;

public class Lesson {
    private String firstTime, secondTime, lesson, teacher, door;

    public Lesson(String firstTime, String secondTime, String lesson, String teacher, String door) {
        this.firstTime = firstTime;
        this.secondTime = secondTime;
        this.lesson = lesson;
        this.teacher = teacher;
        this.door = door;
    }

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    public String getSecondTime() {
        return secondTime;
    }

    public void setSecondTime(String secondTime) {
        this.secondTime = secondTime;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }
}
