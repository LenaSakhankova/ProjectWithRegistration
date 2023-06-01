package com.iba.springsecurity.models;

public class Schedule {

    private int Position_id;


    private int Librarian_id;


    private String Work_days;

    private String timetable;

    private String department;

    private String Kabinet;
    public Schedule(){};
    public Schedule(int librarian_id, String work_days, String timetable, String department, String kabinet) {
        Librarian_id = librarian_id;
        Work_days = work_days;
        this.timetable = timetable;
        this.department = department;
        Kabinet = kabinet;
    }

    public int getPosition_id() {
        return Position_id;
    }

    public void setPosition_id(int position_id) {
        Position_id = position_id;
    }

    public int getLibrarian_id() {
        return Librarian_id;
    }

    public void setLibrarian_id(int librarian_id) {
        Librarian_id = librarian_id;
    }

    public String getWork_days() {
        return Work_days;
    }

    public void setWork_days(String work_days) {
        Work_days = work_days;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getKabinet() {
        return Kabinet;
    }

    public void setKabinet(String kabinet) {
        Kabinet = kabinet;
    }
}
