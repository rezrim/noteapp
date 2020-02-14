package com.example.noteapp2;

public class KaryawanModel {
    private int K_No;
    private String K_Id;
    private String K_Nama;
    private String K_Email;

    public KaryawanModel(int K_No, String K_Id, String K_Nama, String K_Email) {
        this.K_No = K_No;
        this.K_Id = K_Id;
        this.K_Nama = K_Nama;
        this.K_Email = K_Email;
    }

    public KaryawanModel(int K_No, String K_Id, String K_Nama) {
        this.K_No = K_No;
        this.K_Id = K_Id;
        this.K_Nama = K_Nama;
    }

    public KaryawanModel(String K_Id, String K_Nama, String K_Email) {
        this.K_Id = K_Id;
        this.K_Nama = K_Nama;
        this.K_Email = K_Email;
    }

    public int getK_No() {
        return K_No;
    }

    public void setK_No(int K_No) {
        this.K_No = K_No;
    }

    public String getK_Id() {
        return K_Id;
    }

    public void setK_Id(String K_Id) {
        this.K_Id = K_Id;
    }
    public String getK_Nama() {
        return K_Nama;
    }

    public void setK_Nama(String K_Nama) {
        this.K_Nama = K_Nama;
    }
    public String getK_Email() {
        return K_Email;
    }

    public void setK_Email(String K_Email) {
        this.K_Email = K_Email;
    }
}
