package com.example.studentinformation;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StudentDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "student_database";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_STUDENTS = "students";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_STUDENT_ID = "student_id";
    public static final String COLUMN_IMAGE = "image_resource";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PROGRAM = "program";
    private static final String CREATE_TABLE_STUDENTS =
            "CREATE TABLE " + TABLE_STUDENTS + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_STUDENT_ID + " TEXT,"
                    + COLUMN_IMAGE + " INTEGER,"
                    + COLUMN_EMAIL + " TEXT,"
                    + COLUMN_PHONE + " TEXT,"
                    + COLUMN_ADDRESS + " TEXT,"
                    + COLUMN_PROGRAM + " TEXT"
                    + ")";

    public StudentDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        db.execSQL(CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
    }

    public long addStudent(String name, String studentId, int imageResource,
                           String email, String phone, String address, String program) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_STUDENT_ID, studentId);
        values.put(COLUMN_IMAGE, imageResource);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_PROGRAM, program);

        long id = db.insert(TABLE_STUDENTS, null, values);
        db.close();

        return id;
    }
    public Cursor getAllStudents() {
        String selectQuery = "SELECT * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(selectQuery, null);
    }

    public Cursor getStudentById(String studentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_STUDENTS +
                " WHERE " + COLUMN_STUDENT_ID + " = ?";
        return db.rawQuery(selectQuery, new String[] {studentId});
    }

    public Cursor getStudentByPosition(int position) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_STUDENTS +
                " LIMIT 1 OFFSET " + position;
        return db.rawQuery(selectQuery, null);
    }
}
