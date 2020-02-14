package com.example.noteapp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "NotesAppDB3";
    private static final String TABLE_NOTES = "tb_notes";
    private static final String TABLE_KARYAWAN = "tb_karyawan";

    private static final String KEY_NOTE_ID = "id";
    private static final String KEY_NOTE_TITLE = "title";
    private static final String KEY_NOTE_CONTENT = "content";
    private static final String KEY_NOTE_DATE = "date";

    private static final String KEY_KYR_NO = "number";
    private static final String KEY_KYR_ID = "idk";
    private static final String KEY_KYR_NAMA = "nama";
    private static final String KEY_KYR_EMAIL = "email";

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NOTES + " (" +
                KEY_NOTE_ID + " INTEGER PRIMARY KEY, " +
                KEY_NOTE_TITLE + " TEXT, " +
                KEY_NOTE_CONTENT + " TEXT, " +
                KEY_NOTE_DATE + " TEXT" + ")";
        db.execSQL(query);

        String queryK = "CREATE TABLE " + TABLE_KARYAWAN + "(" +
                KEY_KYR_NO + " INTEGER PRIMARY KEY, " +
                KEY_KYR_ID + " TEXT, " +
                KEY_KYR_NAMA + " TEXT, " +
                KEY_KYR_EMAIL + " TEXT" + ")";
        db.execSQL(queryK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KARYAWAN);
        onCreate(db);
    }

    public int addNote(NoteModel noteModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NOTE_TITLE, noteModel.getNoteTitle());
        values.put(KEY_NOTE_CONTENT, noteModel.getNoteContent());
        values.put(KEY_NOTE_DATE, noteModel.getNoteDate());

        long ID = db.insert(TABLE_NOTES, null, values);
        db.close();

        return (int) ID;
    }
    public int addKaryawan(KaryawanModel karyawanModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_KYR_ID, karyawanModel.getK_Id());
        values.put(KEY_KYR_NAMA, karyawanModel.getK_Nama());
        values.put(KEY_KYR_EMAIL, karyawanModel.getK_Email());

        long ID = db.insert(TABLE_KARYAWAN, null, values);
        db.close();

        return (int) ID;
    }
    //
    public List<NoteModel> getNotes() {
        List<NoteModel> noteList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NOTES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String desc = cursor.getString(2);
                String date = cursor.getString(3);

                NoteModel noteModel = new NoteModel(id, title, desc, date);

                noteList.add(noteModel);
            } while (cursor.moveToNext());
        }
        return noteList;
    }
    public List<KaryawanModel> getKaryawan() {
        List<KaryawanModel> karyawanList = new ArrayList<>();
        String queryK = "SELECT * FROM " + TABLE_KARYAWAN;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor1 = db.rawQuery(queryK, null);

        if (cursor1.moveToFirst()) {
            do {
                int no = cursor1.getInt(0);
                String id = cursor1.getString(1);
                String nama = cursor1.getString(2);
                String email = cursor1.getString(3);

                KaryawanModel karyawanModel = new KaryawanModel(no,id,nama,email);

                karyawanList.add(karyawanModel);
            } while (cursor1.moveToNext());
        }

        return karyawanList;
    }
    //
    public NoteModel getNote(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NOTES, new String[] {
                KEY_NOTE_ID,
                KEY_NOTE_TITLE,
                KEY_NOTE_CONTENT,
                KEY_NOTE_DATE
        }, KEY_NOTE_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        int noteID = cursor.getInt(0);
        String title = cursor.getString(1);
        String desc = cursor.getString(2);
        String date = cursor.getString(3);

        NoteModel noteModel = new NoteModel(noteID, title, desc, date);

        return noteModel;
    }
    public KaryawanModel getKaryawan(int no) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_KARYAWAN, new String[] {
                KEY_KYR_NO,
                KEY_KYR_ID,
                KEY_KYR_NAMA,
                KEY_KYR_EMAIL
        }, KEY_KYR_NO + "=?", new String[]{String.valueOf(no)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        int karyawanNO = cursor.getInt(0);
        String id = cursor.getString(1);
        String nama = cursor.getString(2);
        String email = cursor.getString(3);

        KaryawanModel karyawanModel = new KaryawanModel(karyawanNO, id, nama, email);

        return karyawanModel;
    }
    //
    public int updateNote(NoteModel noteModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NOTE_TITLE, noteModel.getNoteTitle());
        values.put(KEY_NOTE_CONTENT, noteModel.getNoteContent());

        return db.update(TABLE_NOTES, values, KEY_NOTE_ID + "=?",
                new String[]{String.valueOf(noteModel.getNoteID())});
    }

    public int deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int ID = db.delete(TABLE_NOTES, KEY_NOTE_ID + "=?",
                new String[]{String.valueOf(id)});
        db.close();

        return ID;
    }
    //
    public int updateKaryawan(KaryawanModel karyawanModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_KYR_ID, karyawanModel.getK_Id());
        values.put(KEY_KYR_NAMA, karyawanModel.getK_Nama());
        values.put(KEY_KYR_EMAIL, karyawanModel.getK_Email());

        return db.update(TABLE_KARYAWAN, values, KEY_KYR_NO + "=?",
                new String[]{String.valueOf(karyawanModel.getK_No())});
    }
    public int deleteKaryawan(int no) {
        SQLiteDatabase db = this.getWritableDatabase();
        int NO = db.delete(TABLE_KARYAWAN, KEY_KYR_NO + "=?",
                new String[]{String.valueOf(no)});
        db.close();

        return NO;
    }
}
