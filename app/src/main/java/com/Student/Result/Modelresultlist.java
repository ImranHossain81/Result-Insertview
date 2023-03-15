package com.Student.Result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Modelresultlist {


    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("student_id")
    @Expose
    private String student_id;

    @SerializedName("section")
    @Expose
    private String section;

    @SerializedName("result")
    @Expose
    private String result;

    @SerializedName("deptment")
    @Expose
    private String deptment;

    @SerializedName("semester")
    @Expose
    private String semester;

    @SerializedName("id")
    @Expose
    private String id;




    public void setName(String name) {
        this.name = name;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setDeptment(String deptment) {
        this.deptment = deptment;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getSection() {
        return section;
    }

    public String getResult() {
        return result;
    }

    public String getDeptment() {
        return deptment;
    }

    public String getSemester() {
        return semester;
    }

    public String getId() {
        return id;
    }
}
