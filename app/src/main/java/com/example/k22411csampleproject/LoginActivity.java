package com.example.k22411csampleproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tuyetanh.connectors.SQLiteConnector;
import com.tuyetanh.model.Employee;
import com.tuyetanh.connectors.EmployeeConnector;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername;
    EditText edtPassword;
    CheckBox chkSaveLogin;
    String DATABASE_NAME = "SalesDatabase.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database = null;
    BroadcastReceiver networkReceiver = null;
    Button btnLogin;

    TextView txtNetworkType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        addViews();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        processCopy();
        setupBroadcastReceiver();
        checkNetworkStatus(); // Kiểm tra trạng thái mạng khi khởi động
    }

    private void setupBroadcastReceiver() {
        networkReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                checkNetworkStatus(); // Cập nhật trạng thái mạng khi có thay đổi
            }
        };
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, filter);
    }

    private void checkNetworkStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            btnLogin.setVisibility(View.VISIBLE);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // Trạng thái 1: Kết nối Wi-Fi
                txtNetworkType.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                txtNetworkType.setText("Biết rồi nha. Phát hiện bạn truy cập vào bằng Wi-Fi nha");
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                // Trạng thái 2: Kết nối 3G/4G
                txtNetworkType.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                txtNetworkType.setText("Bạn truy cập vào bằng mạng di động");
            }
        } else {
            // Trạng thái 3: Không có kết nối
            btnLogin.setVisibility(View.INVISIBLE);
            txtNetworkType.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            txtNetworkType.setText("Bạn không có kết nối internet");
        }
    }

    private void addViews() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        chkSaveLogin = findViewById(R.id.chkSaveLoginInfor);
        btnLogin = findViewById(R.id.btnLogin);
        txtNetworkType = findViewById(R.id.txt_network_type);
    }

    public void do_login(View view) {
        String usr = edtUsername.getText().toString();
        String pwd = edtPassword.getText().toString();
        EmployeeConnector ec = new EmployeeConnector();
        Employee emp = ec.login(new SQLiteConnector(this).openDatabase(), usr, pwd);

        if (emp != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Login failed - Please check your account again!", Toast.LENGTH_LONG).show();
        }
    }

    public void do_exit(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        Resources res = getResources();
        builder.setTitle(res.getText(R.string.confirm_exit_title));
        builder.setMessage(res.getText(R.string.confirm_exit_message));
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setNegativeButton(res.getText(R.string.confirm_exit_no), (dialogInterface, i) -> dialogInterface.cancel());
        builder.setPositiveButton(res.getText(R.string.confirm_exit_yes), (dialogInterface, i) -> finish());

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void saveLoginInformation() {
        SharedPreferences preferences = getSharedPreferences("LOGIN_INFORMATION", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String usr = edtUsername.getText().toString();
        String pwd = edtPassword.getText().toString();
        boolean isSave = chkSaveLogin.isChecked();
        editor.putString("USERNAME", usr);
        editor.putString("PASSWORD", pwd);
        editor.putBoolean("SAVED", isSave);
        editor.commit();
    }

    public void restoreLoginInformation() {
        SharedPreferences preferences = getSharedPreferences("LOGIN_INFORMATION", MODE_PRIVATE);
        String usr = preferences.getString("USERNAME", "");
        String pwd = preferences.getString("PASSWORD", "");
        boolean isSave = preferences.getBoolean("SAVED", true);
        if (isSave) {
            edtUsername.setText(usr);
            edtPassword.setText(pwd);
            chkSaveLogin.setChecked(isSave);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveLoginInformation();
        if (networkReceiver != null) {
            unregisterReceiver(networkReceiver);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreLoginInformation();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, filter);
        checkNetworkStatus(); // Kiểm tra lại trạng thái mạng khi resume
    }

    private void processCopy() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying success from Assets folder", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    public void CopyDataBaseFromAsset() {
        try {
            InputStream myInput = getAssets().open(DATABASE_NAME);
            String outFileName = getDatabasePath();
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists()) f.mkdir();
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}