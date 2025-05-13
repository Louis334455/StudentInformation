package com.example.studentinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private StudentAdapter adapter;
    private StudentDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new StudentDatabaseHelper(this);
        addSampleData();
        listView = findViewById(R.id.studentlistview);
        adapter = new StudentAdapter(this, dbHelper);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private long lastClickTime = 0;
            private int lastClickPosition = -1;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long now = System.currentTimeMillis();

                if (position == lastClickPosition && (now - lastClickTime < 400)) {
                    Student student = (Student) adapter.getItem(position);
                    Intent intent = new Intent(MainActivity.this, Studentdetails.class);
                    intent.putExtra("student_id", student.getStudentId());
                    startActivity(intent);
                }
                lastClickTime = now;
                lastClickPosition = position;
            }
        });

    }

    private void addSampleData() {
        if (dbHelper.getAllStudents().getCount() == 0) {
            dbHelper.addStudent("Nguyễn Ngọc Hưng", "21200098", R.drawable.student1,
                    "21200098@student.hcmus.edu.vn", "0901234567",
                    "Bến Tre", "Máy tính hệ thống nhúng");

            dbHelper.addStudent("Trần Thị B", "21200002", R.drawable.student1,
                    "21200002@student.hcmus.edu.vn", "0912345678",
                    "TP.HCM", "Viễn thông");

            dbHelper.addStudent("Lê Văn C", "21200003", R.drawable.student1,
                    "21200003@student.hcmus.edu.vn", "0923456789",
                    "Đà Nẵng", "Máy tính hệ thống nhúng");

            dbHelper.addStudent("Phạm Thị D", "21200004", R.drawable.student1,
                    "21200004@student.hcmus.edu.vn", "0934567890",
                    "Hải Phòng", "Điện tử");

            dbHelper.addStudent("Hoàng Văn E", "21200005", R.drawable.student1,
                    "21200005@student.hcmus.edu.vn", "0945678901",
                    "Cần Thơ", "Viễn thông");

            dbHelper.addStudent("Đỗ Thị F", "21200006", R.drawable.student1,
                    "21200006@student.hcmus.edu.vn", "0956789012",
                    "Nghệ An", "Máy tính hệ thống nhúng");

            dbHelper.addStudent("Bùi Văn G", "21200007", R.drawable.student1,
                    "21200007@student.hcmus.edu.vn", "0967890123",
                    "Lâm Đồng", "Điện tử");

            dbHelper.addStudent("Ngô Thị H", "21200008", R.drawable.student1,
                    "21200008@student.hcmus.edu.vn", "0978901234",
                    "Quảng Ninh", "Viễn thông");

            dbHelper.addStudent("Vũ Văn I", "21200009", R.drawable.student1,
                    "21200009@student.hcmus.edu.vn", "0989012345",
                    "Bình Dương", "Máy tính hệ thống nhúng");

            dbHelper.addStudent("Phan Thị K", "21200010", R.drawable.student1,
                    "21200010@student.hcmus.edu.vn", "0990123456",
                    "Thanh Hóa", "Điện tử");

            dbHelper.addStudent("Trịnh Văn L", "21200011", R.drawable.student1,
                    "21200011@student.hcmus.edu.vn", "0901122334",
                    "Thừa Thiên Huế", "Viễn thông");

            dbHelper.addStudent("Dương Thị M", "21200012", R.drawable.student1,
                    "21200012@student.hcmus.edu.vn", "0912233445",
                    "Kiên Giang", "Máy tính hệ thống nhúng");

            dbHelper.addStudent("Lý Văn N", "21200013", R.drawable.student1,
                    "21200013@student.hcmus.edu.vn", "0923344556",
                    "Bình Định", "Điện tử");

            dbHelper.addStudent("Đinh Thị O", "21200014", R.drawable.student1,
                    "21200014@student.hcmus.edu.vn", "0934455667",
                    "Vĩnh Long", "Viễn thông");

            dbHelper.addStudent("Trương Văn P", "21200015", R.drawable.student1,
                    "21200015@student.hcmus.edu.vn", "0945566778",
                    "Tây Ninh", "Máy tính hệ thống nhúng");

            dbHelper.addStudent("Tạ Thị Q", "21200016", R.drawable.student1,
                    "21200016@student.hcmus.edu.vn", "0956677889",
                    "Bạc Liêu", "Điện tử");

            dbHelper.addStudent("Hồ Văn R", "21200017", R.drawable.student1,
                    "21200017@student.hcmus.edu.vn", "0967788990",
                    "An Giang", "Viễn thông");

            dbHelper.addStudent("Lâm Thị S", "21200018", R.drawable.student1,
                    "21200018@student.hcmus.edu.vn", "0978899001",
                    "Phú Yên", "Máy tính hệ thống nhúng");

            dbHelper.addStudent("Cao Văn T", "21200019", R.drawable.student1,
                    "21200019@student.hcmus.edu.vn", "0989900112",
                    "Quảng Nam", "Điện tử");

            dbHelper.addStudent("Tống Thị U", "21200020", R.drawable.student1,
                    "21200020@student.hcmus.edu.vn", "0991001223",
                    "Sóc Trăng", "Viễn thông");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.closeCursor();
        dbHelper.close();
    }
}