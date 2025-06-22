package com.example.k22411csampleproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.adapters.TelephonyInforAdapter;
import com.tuyetanh.model.TelephonieInfor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TelephonieActivity extends AppCompatActivity {

    ListView lvTelephonie;
    TelephonyInforAdapter adapter;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private static final int PERMISSION_REQUEST_CALL_PHONE = 101;
    private String previousPhoneNumber = null;
    private List<TelephonieInfor> allContacts = new ArrayList<>();
    private static final List<String> VIETTEL_PREFIXES = Arrays.asList("032", "033", "034", "035", "036", "037", "038", "039", "096", "097", "098", "086");
    private static final List<String> MOBIFONE_PREFIXES = Arrays.asList("090", "093", "070", "076", "077", "078", "079", "089");
    private static final List<String> VINAPHONE_PREFIXES = Arrays.asList("091", "094", "081", "082", "083", "084", "085", "088");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telephonie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        addEvents();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            getAllContacts();
        }
    }

    private void addViews() {
        lvTelephonie = findViewById(R.id.lvTelephonieInfor);
        adapter = new TelephonyInforAdapter(this, R.layout.item_telephony_infor);
        lvTelephonie.setAdapter(adapter);
    }

    private void addEvents() {
        lvTelephonie.setOnItemClickListener((adapterView, view, i, l) -> {
            TelephonieInfor ti = adapter.getItem(i);
            if (ti != null) {
                makeAPhoneCall(ti);
            }
        });
    }

    private void getAllContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        adapter.clear();
        allContacts.clear();

        while (cursor != null && cursor.moveToNext()) {
            int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String name = cursor.getString(nameIndex);
            String phone = cursor.getString(phoneIndex);

            // Chuẩn hóa số điện thoại
            phone = normalizePhoneNumber(phone);

            TelephonieInfor ti = new TelephonieInfor(name, phone);
            adapter.add(ti);
            allContacts.add(ti);
        }

        if (cursor != null) {
            cursor.close();
        }
    }

    private String normalizePhoneNumber(String phone) {
        // Loại bỏ ký tự không phải số và chuẩn hóa định dạng
        phone = phone.replaceAll("[^0-9+]", "");
        if (phone.startsWith("+84")) {
            phone = "0" + phone.substring(3);
        } else if (phone.startsWith("84") && !phone.startsWith("0")) {
            phone = "0" + phone.substring(2);
        }
        return phone;
    }

    private void filterContactsByNetwork(String network) {
        adapter.clear();
        for (TelephonieInfor ti : allContacts) {
            String phone = ti.getPhone();
            if (phone != null && phone.length() >= 10) {
                String prefix = phone.substring(0, 3);
                if ("viettel".equalsIgnoreCase(network) && VIETTEL_PREFIXES.contains(prefix)) {
                    adapter.add(ti);
                } else if ("mobifone".equalsIgnoreCase(network) && MOBIFONE_PREFIXES.contains(prefix)) {
                    adapter.add(ti);
                } else if ("other".equalsIgnoreCase(network) &&
                        !VIETTEL_PREFIXES.contains(prefix) &&
                        !MOBIFONE_PREFIXES.contains(prefix) &&
                        !VINAPHONE_PREFIXES.contains(prefix)) {
                    adapter.add(ti);
                } else if ("all".equalsIgnoreCase(network)) {
                    adapter.add(ti);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.telephony_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_filter_viettel) {
            filterContactsByNetwork("viettel");
            Toast.makeText(this, "Hiển thị khách hàng dùng Viettel", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_filter_mobifone) {
            filterContactsByNetwork("mobifone");
            Toast.makeText(this, "Hiển thị khách hàng dùng MobiFone", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_filter_other) {
            filterContactsByNetwork("other");
            Toast.makeText(this, "Hiển thị khách hàng dùng nhà mạng khác", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_filter_all) {
            filterContactsByNetwork("all");
            Toast.makeText(this, "Hiển thị tất cả khách hàng", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void makeAPhoneCall(TelephonieInfor ti) {
        String phoneNumber = ti.getPhone();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            previousPhoneNumber = phoneNumber;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    PERMISSION_REQUEST_CALL_PHONE);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        }
    }

    public void directCall(TelephonieInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }

    public void dialupCall(TelephonieInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getAllContacts();
            } else {
                Toast.makeText(this, "Không có quyền truy cập danh bạ", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PERMISSION_REQUEST_CALL_PHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (previousPhoneNumber != null) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + previousPhoneNumber));
                    startActivity(intent);
                }
            } else {
                Toast.makeText(this, "Bạn cần cấp quyền gọi điện để tiếp tục", Toast.LENGTH_SHORT).show();
            }
        }
    }
}