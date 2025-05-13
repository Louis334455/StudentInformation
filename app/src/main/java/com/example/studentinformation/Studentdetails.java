package com.example.studentinformation;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Studentdetails extends AppCompatActivity {

    private StudentDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentdetails);

        dbHelper = new StudentDatabaseHelper(this);
        Intent intent = getIntent();
        String studentId = intent.getStringExtra("student_id");
        ImageView imageView = findViewById(R.id.detail_student_image);
        TextView nameTextView = findViewById(R.id.detail_student_name);
        TextView idTextView = findViewById(R.id.detail_student_id);
        TextView emailTextView = findViewById(R.id.detail_student_email);
        TextView phoneTextView = findViewById(R.id.detail_student_phone);
        TextView addressTextView = findViewById(R.id.detail_student_address);
        TextView programTextView = findViewById(R.id.detail_student_program);
        Cursor cursor = dbHelper.getStudentById(studentId);

        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_NAME));
            String id = cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_STUDENT_ID));
            int imageResource = cursor.getInt(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_IMAGE));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_EMAIL));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_PHONE));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_ADDRESS));
            String program = cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_PROGRAM));
            imageView.setImageResource(imageResource);
            nameTextView.setText(name);
            idTextView.setText(id);
            emailTextView.setText(email);
            phoneTextView.setText(phone);
            addressTextView.setText(address);
            programTextView.setText(program);
        }

        cursor.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}
