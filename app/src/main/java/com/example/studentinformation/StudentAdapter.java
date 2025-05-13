package com.example.studentinformation;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StudentAdapter extends BaseAdapter {
        private Context context;
        private StudentDatabaseHelper dbHelper;
        private Cursor cursor;
        private LayoutInflater inflater;

        public StudentAdapter(Context context, StudentDatabaseHelper dbHelper) {
            this.context = context;
            this.dbHelper = dbHelper;
            this.cursor = dbHelper.getAllStudents();
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return cursor.getCount();
        }

        @Override
        public Object getItem(int position) {
            if (cursor.moveToPosition(position)) {
                return getStudentFromCursor(cursor);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            if (cursor.moveToPosition(position)) {
                return cursor.getLong(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_ID));
            }
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.studentview, parent, false);
            }

            TextView nameTextView = convertView.findViewById(R.id.detail_student_name);
            TextView idTextView = convertView.findViewById(R.id.detail_student_email);
            ImageView imageView = convertView.findViewById(R.id.studentimg);

            if (cursor.moveToPosition(position)) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_NAME));
                String studentId = cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_STUDENT_ID));
                int imageResource = cursor.getInt(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_IMAGE));

                nameTextView.setText(name);
                idTextView.setText(studentId);
                imageView.setImageResource(imageResource);
            }

            return convertView;
        }

        private Student getStudentFromCursor(Cursor cursor) {
            return new Student(
                    cursor.getInt(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_STUDENT_ID)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_IMAGE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_PHONE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_ADDRESS)),
                    cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_PROGRAM))
            );
        }

        public void refreshData() {
            if (cursor != null) {
                cursor.close();
            }
            cursor = dbHelper.getAllStudents();
            notifyDataSetChanged();
        }

        public void closeCursor() {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }
