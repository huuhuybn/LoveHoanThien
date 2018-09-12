package com.example.dev.lovehoanthien;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;

import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;


import com.example.dev.lovehoanthien.DataBase.BaseNgaySinhNhat;
import com.example.dev.lovehoanthien.DataBase.DataBase;
import com.example.dev.lovehoanthien.DataBase.DataBaseAnh;
import com.example.dev.lovehoanthien.DataBase.DataBaseColor;

import com.example.dev.lovehoanthien.PassWord.CreatPass;
import com.example.dev.lovehoanthien.Service.ExampleService;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import me.itangqi.waveloadingview.WaveLoadingView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private android.support.v7.widget.Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private TextView tieude, home, txtTen1, txtTen2, cung, cung1, hanhphuc, nam, thang, ngaya, gio, phut, giay, tuoi1, tuoi2;
    private FloatingActionButton fb1, gmail1, nhac;
    private boolean dung = false;
    private DataBase dataBase;
    private DataBaseColor baseColor;
    private BaseNgaySinhNhat baseNgaySinhNhat;
    private boolean sai = true;
    private int so = 0;
    SimpleDateFormat simpleDateFormat;
    Calendar calendarOne, calendar3, calendar4;


    private ArrayList<AnhNam> anhNams;
    private DataBaseAnh baseAnh;

    private ArrayList<AnhNu> anhNus;


    private int color = -1157546;

    private MediaPlayer player;

    private EditText ngay1, ngay2;


    private Uri uri, uri1;
    private int REQUEST_CODE_FOLDER = 123;
    private int REQUEST_CODE_FOLDER1 = 456;

    private CircularImageView anh_Nam, anh_Nu;


    private CheckBox checkBox;


    private WaveLoadingView waveLoadingView;

    private Calendar calendarTwo;
    private Cursor cursor, cursor1, cursor3, cursor4;
    private TextView ten1, ten2;


    private EditText t1, t2, ngayyeunhau, ngayhientai;

    private View root, roo1;
    private int currentBackgroundColor = 0xffffffff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        OnClick();
        AnhXaFont();
        herder();
        onClickAnh();
        CungHoangDao();
        onClick();
        Color();
        setText();
        updateNgayyeu();
        anhNams = new ArrayList<>();
        anhNus = new ArrayList<>();
        baseAnh = new DataBaseAnh(getApplicationContext(), "Anh.sqlite", null, 1);
        baseAnh.QueryData("CREATE TABLE IF NOT EXISTS  AnhNam(_id INTEGER PRIMARY KEY AUTOINCREMENT,Hinh BLOB)");
        Cursor AnhNam = baseAnh.getData("SELECT * FROM AnhNam");
        baseAnh.QueryData("CREATE TABLE IF NOT EXISTS  AnhNu(_id INTEGER PRIMARY KEY AUTOINCREMENT,Hinh BLOB)");
        Cursor AnhNu = baseAnh.getData("SELECT * FROM AnhNu");
        if (AnhNam.moveToNext()&&AnhNu.moveToNext()) {
            anhNams.add(new AnhNam(AnhNam.getBlob(1)));

            AddAnh(0);

            anhNus.add(new AnhNu(AnhNu.getBlob(1)));

            AddAnh1(0);
        }





        player.start();

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Cursor cursor1 = dataBase.getThongdiep();
        if (cursor1.moveToNext()) {
            int so1 = cursor1.getInt(1);

            tatthongdiep();
        } else {
            dataBase.insert(so);
            thongdiep();
        }


    }

    void AddAnh(int i) {
        AnhNam anhNam = anhNams.get(i);
        Bitmap bitmap = BitmapFactory.decodeByteArray(anhNam.getHinhnam(), 0, anhNam.getHinhnam().length);
        anh_Nam.setImageBitmap(bitmap);
    }

    void AddAnh1(int i) {
        AnhNu anhNu = anhNus.get(i);
        Bitmap bitmap = BitmapFactory.decodeByteArray(anhNu.getAnhNu(), 0, anhNu.getAnhNu().length);
        anh_Nu.setImageBitmap(bitmap);
    }

    void getLink() {

        try {
            Cursor AnhNam = baseAnh.getData("SELECT * FROM AnhNam");
            if (AnhNam.moveToNext()) {
                baseAnh.QueryData("DELETE FROM AnhNam");
                baseAnh.QueryData("CREATE TABLE IF NOT EXISTS  AnhNam(_id INTEGER PRIMARY KEY AUTOINCREMENT,Hinh BLOB)");
                baseAnh.INSERT_ANH(
                        Image_to_Byte(anh_Nam)
                );
            } else {
                baseAnh.INSERT_ANH(
                        Image_to_Byte(anh_Nam)
                );
            }

        } catch (Exception e) {

        }


    }

    void getLink1() {

        try {
            Cursor AnhNam = baseAnh.getData("SELECT * FROM AnhNu");
            if (AnhNam.moveToNext()) {
                baseAnh.QueryData("DELETE FROM AnhNu");
                baseAnh.QueryData("CREATE TABLE IF NOT EXISTS  AnhNu(_id INTEGER PRIMARY KEY AUTOINCREMENT,Hinh BLOB)");
                baseAnh.INSERT_ANH1(
                        Image_to_Byte(anh_Nu)
                );
            } else {
                baseAnh.INSERT_ANH1(
                        Image_to_Byte(anh_Nu)
                );
            }

        } catch (Exception e) {

        }


    }

    public byte[] Image_to_Byte(CircularImageView imageView) {


        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        byte[] byteArray = outputStream.toByteArray();
        return byteArray;
    }

    void imgFile() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_FOLDER);
    }

    void imgFile1() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_FOLDER1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            uri = data.getData();
            anh_Nam.setImageURI(uri);
            getLink();
        }

        if (requestCode == REQUEST_CODE_FOLDER1 && resultCode == RESULT_OK && data != null) {
            uri = data.getData();
            anh_Nu.setImageURI(uri);
            getLink1();
        }
    }


    void onClickAnh() {
        anh_Nam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgFile();
                Toast.makeText(MainActivity.this, "Nên dùng ảnh có dung lượng thấp", Toast.LENGTH_SHORT).show();
            }
        });

        anh_Nu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgFile1();
                Toast.makeText(MainActivity.this, "Nên dùng ảnh có dung lượng thấp", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận");
        builder.setIcon(R.drawable.canhbao);
        builder.setMessage("Bạn có muốn thoát không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Good Bye", Toast.LENGTH_SHORT).show();
                player.stop();
                finish();
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.setCancelable(true);
            }
        });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.lienhe:
                if (dung == false) {
                    hien();
                } else {
                    an();
                }

        }

        return true;
    }

    void OnClick() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawers();
                } else {
                    drawerLayout.openDrawer(navigationView);
                }
            }
        });

    }

    public void AnhXaFont() {
        Typeface font1 = Typeface.createFromAsset(getAssets(), "font_holidays.ttf");
        //tieude.setTypeface(font1);
        Typeface font2 = Typeface.createFromAsset(getAssets(), "font_moon_star.ttf");
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "font_dep.ttf");
        home.setTypeface(typeface1);

    }

    public void AnhXa() {
        toolbar = findViewById(R.id.toobbar);
        navigationView = findViewById(R.id.navigation);
        drawerLayout = findViewById(R.id.drawerLayout);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        home = findViewById(R.id.txtHome);
        fb1 = (FloatingActionButton) findViewById(R.id.fb);
        gmail1 = (FloatingActionButton) findViewById(R.id.gmail);
        nhac = (FloatingActionButton) findViewById(R.id.nhac);
        dataBase = new DataBase(this);
        baseColor = new DataBaseColor(this);
        baseNgaySinhNhat = new BaseNgaySinhNhat(this);
        waveLoadingView = (WaveLoadingView) findViewById(R.id.loaddingWave);
        waveLoadingView.setProgressValue(0);
        txtTen1 = findViewById(R.id.tenNam);
        txtTen2 = findViewById(R.id.tenNu);
        cung = findViewById(R.id.cung);
        cung1 = findViewById(R.id.cung1);
        hanhphuc = findViewById(R.id.hanhphuc);

        anh_Nam = findViewById(R.id.anh);
        anh_Nu = findViewById(R.id.anh1);

        tuoi1 = findViewById(R.id.tuoiNam);
        tuoi2 = findViewById(R.id.tuoiNu);


        root = findViewById(R.id.linear);
        roo1 = findViewById(R.id.linear1);

        player = MediaPlayer.create(MainActivity.this, R.raw.the_girl);

        nam = findViewById(R.id.nam);
        thang = findViewById(R.id.thang);
        ngaya = findViewById(R.id.ngay);
        gio = findViewById(R.id.gio);
        phut = findViewById(R.id.phut);
        giay = findViewById(R.id.giay);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dulieu:
                dialogChinhsua();
                break;
            case R.id.thoat:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Xác nhận");
                builder.setIcon(R.drawable.canhbao);
                builder.setMessage("Bạn có muốn thoát không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Good Bye", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.setCancelable(true);
                    }
                });
                builder.show();


                break;

            case R.id.thongdiep:
                Cursor cursor2 = dataBase.getThongdiep();
                if (cursor2.moveToNext()) {
                    int so1 = cursor2.getInt(1);
                    if (so1 == 1) {
                        batThongdiep();
                    } else {
                        tathongdiep();
                    }
                }
                break;

            case R.id.themngaysinh:

                Cursor cursor = baseNgaySinhNhat.getNam();
                Cursor cursor1 = baseNgaySinhNhat.getNu();

                if (cursor.moveToNext() && cursor1.moveToNext()) {
                    suaNgaySinhNhat();
                } else {
                    themNgaySinhNhat();
                }


                break;
            case R.id.color:
                dialog();
                break;

            case R.id.macdinh:
                MauMacDinh(color);
                break;
            case R.id.pass:
               Cursor cursor5=dataBase.getPassWord();
               if (cursor5.moveToNext()){
                   int a=cursor5.getInt(1);
                   if (a==0){
                       final AlertDialog.Builder builde1 = new AlertDialog.Builder(this);
                       builde1.setTitle("Xác nhận");
                       builde1.setIcon(R.drawable.canhbao);
                       builde1.setMessage("Bạn có tắt mật khẩu không?");
                       builde1.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               dataBase.updatetPassWord(1,1);
                               Toast.makeText(MainActivity.this, "Mật khẩu đã tắt", Toast.LENGTH_SHORT).show();
                           }
                       });

                       builde1.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               builde1.setCancelable(true);
                           }
                       });
                       builde1.show();
                   }else {
                       startActivity(new Intent(MainActivity.this, CreatPass.class));
                       overridePendingTransition(R.anim.divao,R.anim.dira);
                       finish();
                   }
               }else {
                   startActivity(new Intent(MainActivity.this, CreatPass.class));
                   overridePendingTransition(R.anim.divao,R.anim.dira);
                   finish();
                   }
                break;


        }
        drawerLayout.closeDrawer(Gravity.START);
        return true;
    }

    @SuppressLint("ResourceAsColor")
    void herder() {

        View view = navigationView.getHeaderView(0);
        TextView textView = (TextView) view.findViewById(R.id.txtTieude);
         ten1 = (TextView) view.findViewById(R.id.txtTen1);
         ten2 = (TextView) view.findViewById(R.id.txtTen2);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "love_girl.ttf");
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "font_dep.ttf");
        textView.setTypeface(typeface);
        ten1.setTypeface(typeface);
        ten2.setTypeface(typeface);
        txtTen1.setTypeface(typeface);
        txtTen2.setTypeface(typeface);
        cung.setTypeface(typeface);
        cung1.setTypeface(typeface);
        cursor = dataBase.getTen();
        cursor1 = dataBase.getSoNgayYeu();
        home.setTypeface(typeface1);
        hanhphuc.setTypeface(typeface);
        nam.setTypeface(typeface);
        thang.setTypeface(typeface);
        ngaya.setTypeface(typeface);
        gio.setTypeface(typeface);
        phut.setTypeface(typeface);
        giay.setTypeface(typeface);
        if (cursor.moveToNext()) {
            String tenban = cursor.getString(1);
            String tennguoiay = cursor.getString(2);
            ten1.setText(tenban);
            ten2.setText(tennguoiay);
        }


    }

    void onClick() {
        nhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.isPlaying()) {
                    nhac.setImageResource(R.drawable.tatloa);
                    player.pause();


                } else {
                    nhac.setImageResource(R.drawable.batloa);
                    player.start();


                }
            }
        });
    }

    void hien() {
        fb1.show();
        gmail1.show();
        nhac.show();
        dung = true;
    }

    void an() {
        fb1.hide();
        gmail1.hide();
        nhac.hide();
        dung = false;
    }

    void tatthongdiep() {
        Cursor cursor = dataBase.getThongdiep();
        if (cursor.moveToNext()) {
            int so1 = cursor.getInt(1);
            if (so1 == 0) {
                thongdiep();
            }


        }

    }

    void thongdiep() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Lời nhắn");
        builder.setIcon(R.drawable.icon_menu);
        builder.setMessage("Mong bạn hài lòng với những gì tôi tạo ra.Cảm ơn!");
        builder.setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.setCancelable(true);
            }
        });

        builder.setNegativeButton("Tắt", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int tat = 1;
                dataBase.updatethongdiep(tat, 1);
                builder.setCancelable(true);
            }
        });

        builder.show();
    }

    void batThongdiep() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Lời nhắn");
        builder.setIcon(R.drawable.canhbao);
        builder.setMessage("Lời nhắn từ Developer đã tắt bạn có muốn bật không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dataBase.updatethongdiep(so, 1);
                builder.setCancelable(true);
                thongdiep();
                Toast.makeText(MainActivity.this, "Lời nhắn đã bật", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.setCancelable(true);
            }
        });

        builder.show();
    }

    void tathongdiep() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Lời nhắn");
        builder.setIcon(R.drawable.canhbao);
        builder.setMessage("Bạn có muốn tắt lời nhắn của DevAndroid");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int tat = 1;
                dataBase.updatethongdiep(tat, 1);
                Toast.makeText(MainActivity.this, "Lời nhắn đã tắt", Toast.LENGTH_SHORT).show();
                builder.setCancelable(true);
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.setCancelable(true);
            }
        });

        builder.show();
    }

    public void updateNgayyeu() {
        cursor4 = dataBase.getNgayyeu();
        calendarTwo = Calendar.getInstance();
        int a1 = (int) (calendarTwo.getTimeInMillis() / (1000 * 60 * 60 * 24));
        if (cursor4.moveToNext()) {
            int a2 = cursor4.getInt(1);
            int a = a1 - a2;
            dataBase.updateSoNgayYeu(a, 1);

        }
        setText();

    }

    void setText() {
        cursor = dataBase.getTen();
        cursor1 = dataBase.getSoNgayYeu();

        if (cursor.moveToNext()) {
            String tenban = cursor.getString(1);
            String tennguoiay = cursor.getString(2);
            txtTen1.setText(tenban);
            txtTen2.setText(tennguoiay);
        }
        if (cursor1.moveToNext()) {
            int ngay1 = cursor1.getInt(1);
            if (ngay1 < 350) {
                waveLoadingView.setProgressValue(25);
                waveLoadingView.setBottomTitle("");
                waveLoadingView.setCenterTitle(String.valueOf(ngay1 + "\t" + "ngày"));
                waveLoadingView.setTopTitle("");
            } else if (ngay1 < 600) {
                waveLoadingView.setProgressValue(55);
                waveLoadingView.setBottomTitle("");
                waveLoadingView.setCenterTitle(String.valueOf(ngay1 + "\t" + "ngày"));
                waveLoadingView.setTopTitle("");
            } else if (ngay1 < 900) {
                waveLoadingView.setProgressValue(85);
                waveLoadingView.setBottomTitle("");
                waveLoadingView.setCenterTitle(String.valueOf(ngay1 + "\t" + "ngày"));
                waveLoadingView.setTopTitle("");
            } else if (ngay1 == 2000) {
                waveLoadingView.setProgressValue(100);
                waveLoadingView.setBottomTitle("");
                waveLoadingView.setCenterTitle(String.valueOf(ngay1 + "\t" + "ngày"));
                waveLoadingView.setTopTitle("");
            }
            String tenban = txtTen1.getText().toString();
            String tennguoiay = txtTen2.getText().toString();
            String input =tenban+"❤"+tennguoiay+"_"+ngay1+"ngày rồi đó"+"-Luôn hạnh phúc nhé!";
            Intent serviceIntent = new Intent(MainActivity.this, ExampleService.class);
            serviceIntent.putExtra("inputExtra", input);
            ContextCompat.startForegroundService(MainActivity.this, serviceIntent);

            double so = ngay1;
            double so1 = 365;

            double nam1 = so / so1;
            int nguyen = (int) nam1;
            nam.setText(nguyen + "năm:");
            double sodu = so - (nguyen * so1);

            double thang1 = sodu / 30;
            int thangnguyen = (int) thang1;
            thang.setText(thangnguyen + "Tháng:");

            double sodungay = sodu - (thangnguyen * 30);
            int sodungay1 = (int) sodungay;
            ngaya.setText(sodungay1 + "Ngày:");


            final Date date = new Date();
            String strDateFormat24 = "HH";
            String strDateFormat25 = "mm";
            String strDateFormat26 = "ss";
            SimpleDateFormat sdf = null;
            SimpleDateFormat sdf1 = null;
            SimpleDateFormat sdf2 = null;
            sdf = new SimpleDateFormat(strDateFormat24);
            sdf1 = new SimpleDateFormat(strDateFormat25);
            sdf2 = new SimpleDateFormat(strDateFormat26);
            gio.setText(sdf.format(date) + "Giờ:");
            phut.setText(sdf1.format(date) + "Phút:");
            giay.setText(sdf2.format(date) + "Giây");


//            if (ngay1/365>=1 && ngay1/365<2 ){
//                nam.setText(1+"-năm");
//                double ngaydu=(double) ngay1%365;
//
//                }
//
//            if (ngay1/365>=2 && ngay1/365<3 ){
//                nam.setText(2+"-năm");
//            }
//
//            if (ngay1/365>=3 && ngay1/365<4 ){
//                nam.setText(3+"-năm");
//            }
//
//            if (ngay1/365<1){
//                nam.setText(0+"-năm");
//            }


        }


    }


    void dialog() {
        changeBackgroundColor(currentBackgroundColor);
        ColorPickerDialogBuilder
                .with(this)
                .setTitle("Chọn màu")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {

                    }
                })
                .setPositiveButton("Oki", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, Integer[] integers) {

                        txtTen1.setTextColor(i);
                        txtTen2.setTextColor(i);
                        cung.setTextColor(i);
                        cung1.setTextColor(i);
                        hanhphuc.setTextColor(i);
                        nam.setTextColor(i);
                        thang.setTextColor(i);
                        ngaya.setTextColor(i);
                        gio.setTextColor(i);
                        phut.setTextColor(i);
                        giay.setTextColor(i);
                        tuoi1.setTextColor(i);
                        tuoi2.setTextColor(i);
                        ten1.setTextColor(i);
                        ten2.setTextColor(i);
                        Cursor cursor = baseColor.getMau();
                        if (cursor.moveToNext()) {
                            baseColor.updateMau(i, 1);
                        } else {
                            baseColor.insertMau(i);

                        }

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).showColorEdit(true)
                .setColorEditTextColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_blue_bright))
                .build()
                .show();
    }

    private void changeBackgroundColor(int selectedColor) {
        currentBackgroundColor = selectedColor;
        root.setBackgroundColor(selectedColor);
    }

    void Color() {
        Cursor cursor = baseColor.getMau();
        if (cursor.moveToNext()) {
            int i = cursor.getInt(1);

            txtTen1.setTextColor(i);
            txtTen2.setTextColor(i);
            cung.setTextColor(i);
            cung1.setTextColor(i);
            hanhphuc.setTextColor(i);
            nam.setTextColor(i);
            thang.setTextColor(i);
            ngaya.setTextColor(i);
            gio.setTextColor(i);
            phut.setTextColor(i);
            giay.setTextColor(i);
            tuoi1.setTextColor(i);
            tuoi2.setTextColor(i);
            ten1.setTextColor(i);
            ten2.setTextColor(i);
        }
    }

    @SuppressLint("ResourceAsColor")
    void MauMacDinh(int i) {


        txtTen1.setTextColor(i);
        txtTen2.setTextColor(i);
        cung.setTextColor(i);
        cung1.setTextColor(i);
        hanhphuc.setTextColor(i);
        nam.setTextColor(i);
        thang.setTextColor(i);
        ngaya.setTextColor(i);
        gio.setTextColor(i);
        phut.setTextColor(i);
        giay.setTextColor(i);
        tuoi1.setTextColor(i);
        tuoi2.setTextColor(i);
        ten1.setTextColor(i);
        ten2.setTextColor(i);
        baseColor.updateMau(i, 1);

    }


    void dialogChinhsua() {
        final Dialog ChinhSua = new Dialog(this);
        ChinhSua.setContentView(R.layout.activity_chinhsua);
        Cursor cursor = dataBase.getTen();
        Cursor cursor2 = dataBase.getNgayyeu();
        t1 = ChinhSua.findViewById(R.id.txTenBan);
        t2 = ChinhSua.findViewById(R.id.txNguouiAy);
        TextView txLove = ChinhSua.findViewById(R.id.txLove);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "love_girl.ttf");
        txLove.setTypeface(typeface);
        ngayyeunhau = ChinhSua.findViewById(R.id.ngayyeunhau);
        Button button = ChinhSua.findViewById(R.id.chonngay);
        ngayhientai = ChinhSua.findViewById(R.id.ngayhientai);
        checkBox = ChinhSua.findViewById(R.id.dongy);
        checkBox.setChecked(true);
        ngayyeunhau.setEnabled(false);
        ngayhientai.setEnabled(false);
        calendarTwo = Calendar.getInstance();
        ngayhientai.setText(simpleDateFormat.format(calendarTwo.getTime()) + "(Ngày hiện tại)");


        Button Thaydoi = ChinhSua.findViewById(R.id.thaydoi);
        Thaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t3 = t1.getText().toString();
                String t4 = t2.getText().toString();
                String date = ngayyeunhau.getText().toString();
                if (t3.equals("") || t4.equals("") || date.equals("") || !checkBox.isChecked()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();

                } else {
                    int ngayyeu = (int) (calendarOne.getTimeInMillis() / (1000 * 60 * 60 * 24));
                    int ngay = (int) ((calendarTwo.getTimeInMillis() - calendarOne.getTimeInMillis()) / (1000 * 60 * 60 * 24));
                    dataBase.updateSoNgayYeu(ngay, 1);
                    dataBase.updateTen(t3, t4, 1);
                    dataBase.updateNgayYeu(ngayyeu, 1);
                    txtTen1.setText(t3);
                    txtTen2.setText(t4);

                    if (ngay < 350) {
                        waveLoadingView.setProgressValue(25);
                        waveLoadingView.setBottomTitle("");
                        waveLoadingView.setCenterTitle(String.valueOf(ngay + "\t" + "ngày"));
                        waveLoadingView.setTopTitle("");
                    } else if (ngay < 600) {
                        waveLoadingView.setProgressValue(55);
                        waveLoadingView.setBottomTitle("");
                        waveLoadingView.setCenterTitle(String.valueOf(ngay + "\t" + "ngày"));
                        waveLoadingView.setTopTitle("");
                    } else if (ngay < 900) {
                        waveLoadingView.setProgressValue(85);
                        waveLoadingView.setBottomTitle("");
                        waveLoadingView.setCenterTitle(String.valueOf(ngay + "\t" + "ngày"));
                        waveLoadingView.setTopTitle("");
                    } else if (ngay == 2000) {
                        waveLoadingView.setProgressValue(100);
                        waveLoadingView.setBottomTitle("");
                        waveLoadingView.setCenterTitle(String.valueOf(ngay + "\t" + "ngày"));
                        waveLoadingView.setTopTitle("");
                    }

                    String input =t3+"❤"+t4+"_"+ngay+"ngày rồi đó"+"-Luôn hạnh phúc nhé!";
                    Intent serviceIntent = new Intent(MainActivity.this, ExampleService.class);
                    serviceIntent.putExtra("inputExtra", input);
                    ContextCompat.startForegroundService(MainActivity.this, serviceIntent);

                    double so = ngay;
                    double so1 = 365;

                    double nam1 = so / so1;
                    int nguyen = (int) nam1;
                    nam.setText(nguyen + "năm:");
                    double sodu = so - (nguyen * so1);

                    double thang1 = sodu / 30;
                    int thangnguyen = (int) thang1;
                    thang.setText(thangnguyen + "Tháng:");

                    double sodungay = sodu - (thangnguyen * 30);
                    int sodungay1 = (int) sodungay;
                    ngaya.setText(sodungay1 + "Ngày:");


                    final Date date1 = new Date();
                    String strDateFormat24 = "HH";
                    String strDateFormat25 = "mm";
                    String strDateFormat26 = "ss";
                    SimpleDateFormat sdf = null;
                    SimpleDateFormat sdf1 = null;
                    SimpleDateFormat sdf2 = null;
                    sdf = new SimpleDateFormat(strDateFormat24);
                    sdf1 = new SimpleDateFormat(strDateFormat25);
                    sdf2 = new SimpleDateFormat(strDateFormat26);
                    gio.setText(sdf.format(date1) + "Giờ:");
                    phut.setText(sdf1.format(date1) + "Phút:");
                    giay.setText(sdf2.format(date1) + "Giây");
                    Toast.makeText(MainActivity.this, "Thay đổi thành công", Toast.LENGTH_SHORT).show();

                    ChinhSua.dismiss();
                }
            }
        });

        Button huy = ChinhSua.findViewById(R.id.huy);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChinhSua.cancel();
                ChinhSua.dismiss();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarOne = Calendar.getInstance();
                int ngay = calendarOne.get(Calendar.DATE);
                int thang = calendarOne.get(Calendar.MONTH);
                int nam = calendarOne.get(Calendar.YEAR);
                DatePickerDialog pickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                calendarOne.set(i, i1, i2);
                                ngayyeunhau.setText(simpleDateFormat.format(calendarOne.getTime()));
                            }
                        }, nam, thang, ngay);
                pickerDialog.show();
            }
        });

        if (cursor.moveToNext() && cursor2.moveToNext()) {
            String a = cursor.getString(1);
            String b = cursor.getString(2);
            int c = cursor4.getInt(1);
            t1.setText(a);
            t2.setText(b);
        }

        ChinhSua.show();


    }

    private void chonngay2() {
        calendarTwo = Calendar.getInstance();
        int ngay = calendarTwo.get(Calendar.DATE);
        int thang = calendarTwo.get(Calendar.MONTH);
        int nam = calendarTwo.get(Calendar.YEAR);
        DatePickerDialog pickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        ngayhientai.setText(simpleDateFormat.format(calendarTwo.getTime()));

                    }
                }, nam, thang, ngay);

        pickerDialog.show();

    }


    void themNgaySinhNhat() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_ngaysinh);
        dialog.getActionBar();
        dialog.setCancelable(false);
        dialog.setTitle("Thêm");
        Button chonngay1 = dialog.findViewById(R.id.chonngay1);
        Button chonngay2 = dialog.findViewById(R.id.chonngay2);
        Button luu = dialog.findViewById(R.id.luu);
        Button huy = dialog.findViewById(R.id.huybo);
        ngay1 = dialog.findViewById(R.id.ngaysinh1);
        ngay2 = dialog.findViewById(R.id.ngaysinh2);
        ngay1.setEnabled(false);
        ngay2.setEnabled(false);

        chonngay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sinhnhat1();
            }
        });

        chonngay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sinhnhat2();
            }
        });

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dialog.cancel();
            }
        });


        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ngay1.getText().toString().equals("") && ngay2.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Chưa có gì để thêm", Toast.LENGTH_SHORT).show();
                } else {

                    CungHoangDao();
                    dialog.dismiss();
                }

            }

        });

        dialog.show();


    }


    private void sinhnhat1() {
        calendar3 = Calendar.getInstance();
        int ngay = calendar3.get(Calendar.DATE);
        int thang = calendar3.get(Calendar.MONTH);
        int nam = calendar3.get(Calendar.YEAR);
        DatePickerDialog pickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar3.set(i, i1, i2);
                        Cursor a = baseNgaySinhNhat.getNam();
                        if (!a.moveToNext()) {
                            baseNgaySinhNhat.insertNgaySinhNhatNam(i2, i1 + 1, i);
                        } else {
                            baseNgaySinhNhat.updateNgaySinhNhatNam(i2, i1 + 1, i, 1);
                        }
                        ngay1.setText(simpleDateFormat.format(calendar3.getTime()));

                    }
                }, nam, thang, ngay);

        pickerDialog.show();

    }

    private void sinhnhat2() {
        calendar4 = Calendar.getInstance();
        int ngay = calendar4.get(Calendar.DATE);
        int thang = calendar4.get(Calendar.MONTH);
        int nam = calendar4.get(Calendar.YEAR);
        DatePickerDialog pickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar4.set(i, i1, i2);
                        Cursor a = baseNgaySinhNhat.getNu();
                        if (!a.moveToNext()) {
                            baseNgaySinhNhat.insertNgaySinhNhatNu(i2, i1 + 1, i);
                        } else {
                            baseNgaySinhNhat.updateNgaySinhNhatNu(i2, i1 + 1, i, 1);
                        }
                        ngay2.setText(simpleDateFormat.format(calendar4.getTime()));

                    }
                }, nam, thang, ngay);

        pickerDialog.show();

    }

    void suaNgaySinhNhat() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_chinhsua_sinhnhat);
        dialog.getActionBar();
        dialog.setCancelable(false);
        dialog.setTitle("Sửa");
        Button chonngay1 = dialog.findViewById(R.id.chonngay1);
        Button chonngay2 = dialog.findViewById(R.id.chonngay2);
        Button luu = dialog.findViewById(R.id.luu);
        Button huy = dialog.findViewById(R.id.huybo);
        ngay1 = dialog.findViewById(R.id.ngaysinh1);
        ngay2 = dialog.findViewById(R.id.ngaysinh2);
        ngay1.setEnabled(false);
        ngay2.setEnabled(false);

        Cursor b = baseNgaySinhNhat.getNam();
        Cursor c = baseNgaySinhNhat.getNu();
        if (b.moveToNext() && c.moveToNext()) {
            int ngay = b.getInt(1);
            int thang = b.getInt(2);
            int nam = b.getInt(3);
            int ngay3 = c.getInt(1);
            int thang3 = c.getInt(2);
            int nam3 = c.getInt(3);
            ngay1.setText(ngay + "-" + thang + "-" + nam + "(Bạn Nam)");
            ngay2.setText(ngay3 + "-" + thang3 + "-" + nam3 + "(Bạn Nữ)");

            chonngay1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sinhnhat1();
                }
            });

            chonngay2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sinhnhat2();
                }
            });

            huy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    dialog.cancel();
                }
            });


            luu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ngay1.getText().toString().equals("") && ngay2.getText().toString().equals("")) {
                        Toast.makeText(MainActivity.this, "Chưa có gì để thêm", Toast.LENGTH_SHORT).show();
                    } else {
                        CungHoangDao();
                        dialog.dismiss();
                    }
                }
            });

            dialog.show();


        }


    }

    void CungHoangDao() {
        calendarOne = Calendar.getInstance();
        int namhientai = calendarOne.get(Calendar.YEAR);
        Cursor b = baseNgaySinhNhat.getNam();
        Cursor c = baseNgaySinhNhat.getNu();
        if (b.moveToNext() && c.moveToNext()) {
            int ngay = b.getInt(1);
            int thang = b.getInt(2);
            int nam = b.getInt(3);
            int ngay3 = c.getInt(1);
            int thang3 = c.getInt(2);
            int nam3 = c.getInt(3);
            int tuoiNam = namhientai - nam;
            int tuoiNu = namhientai - nam3;
            tuoi1.setText(tuoiNam + "");
            tuoi2.setText(tuoiNu + "");
            if (ngay >= 21 && ngay <= 31 && thang == 3) {
                cung.setText("Bạch Dương");
            }

            if (ngay >= 1 && ngay <= 20 && thang == 4) {
                cung.setText("Bạch Dương");
            }

            if (ngay >= 21 && ngay <= 31 && thang == 4) {
                cung.setText("Kim Ngưu");
            }

            if (ngay >= 1 && ngay <= 20 && thang == 5) {
                cung.setText("Kim Ngưu");
            }

            if (ngay >= 21 && ngay <= 31 && thang == 5) {
                cung.setText("Song Tử");
            }

            if (ngay >= 1 && ngay <= 21 && thang == 6) {
                cung.setText("Song Tử");
            }

            if (ngay >= 22 && ngay <= 31 && thang == 6) {
                cung.setText("Cự Giải");
            }

            if (ngay >= 1 && ngay <= 22 && thang == 7) {
                cung.setText("Cự Giải");
            }

            if (ngay >= 23 && ngay <= 31 && thang == 7) {
                cung.setText("Sư Tử");
            }

            if (ngay >= 1 && ngay <= 22 && thang == 8) {
                cung.setText("Sư Tử");
            }

            if (ngay >= 23 && ngay <= 31 && thang == 8) {
                cung.setText("Xử Nữ");
            }

            if (ngay >= 1 && ngay <= 22 && thang == 9) {
                cung.setText("Xử Nữ");
            }

            if (ngay >= 23 && ngay <= 31 && thang == 9) {
                cung.setText("Thiên Bình");
            }

            if (ngay >= 1 && ngay <= 23 && thang == 10) {
                cung.setText("Thiên Bình");
            }

            if (ngay >= 24 && ngay <= 31 && thang == 10) {
                cung.setText("Bọ Cạp");
            }

            if (ngay >= 1 && ngay <= 22 && thang == 11) {
                cung.setText("Bọ Cạp");
            }

            if (ngay >= 23 && ngay <= 31 && thang == 11) {
                cung.setText("Nhân Mã");
            }

            if (ngay >= 1 && ngay <= 21 && thang == 12) {
                cung.setText("Nhân Mã");
            }
            if (ngay >= 22 && ngay <= 31 && thang == 12) {
                cung.setText("Ma Kết");
            }

            if (ngay >= 1 && ngay <= 19 && thang == 1) {
                cung.setText("Ma Kết");
            }

            if (ngay >= 20 && ngay <= 31 && thang == 1) {
                cung.setText("Bảo Bình");
            }

            if (ngay >= 1 && ngay <= 18 && thang == 2) {
                cung.setText("Bảo Bình");
            }

            if (ngay >= 19 && ngay <= 31 && thang == 2) {
                cung.setText("Song Ngư");
            }

            if (ngay >= 1 && ngay <= 20 && thang == 3) {
                cung.setText("Song Ngư");
            }


            if (ngay3 >= 21 && ngay3 <= 31 && thang3 == 3) {
                cung1.setText("Bạch Dương");
            }

            if (ngay3 >= 1 && ngay3 <= 20 && thang3 == 4) {
                cung1.setText("Bạch Dương");
            }

            if (ngay3 >= 21 && ngay3 <= 31 && thang3 == 4) {
                cung1.setText("Kim Ngưu");
            }

            if (ngay3 >= 1 && ngay3 <= 20 && thang3 == 5) {
                cung1.setText("Kim Ngưu");
            }

            if (ngay3 >= 21 && ngay3 <= 31 && thang3 == 5) {
                cung1.setText("Song Tử");
            }

            if (ngay3 >= 1 && ngay3 <= 21 && thang3 == 6) {
                cung1.setText("Song Tử");
            }

            if (ngay3 >= 22 && ngay3 <= 31 && thang3 == 6) {
                cung1.setText("Cự Giải");
            }

            if (ngay3 >= 1 && ngay3 <= 22 && thang3 == 7) {
                cung1.setText("Cự Giải");
            }

            if (ngay3 >= 23 && ngay3 <= 31 && thang3 == 7) {
                cung1.setText("Sư Tử");
            }

            if (ngay3 >= 1 && ngay3 <= 22 && thang3 == 8) {
                cung1.setText("Sư Tử");
            }

            if (ngay3 >= 23 && ngay3 <= 31 && thang3 == 8) {
                cung1.setText("Xử Nữ");
            }

            if (ngay3 >= 1 && ngay3 <= 22 && thang3 == 9) {
                cung1.setText("Xử Nữ");
            }

            if (ngay3 >= 23 && ngay3 <= 31 && thang3 == 9) {
                cung1.setText("Thiên Bình");
            }

            if (ngay3 >= 1 && ngay3 <= 23 && thang3 == 10) {
                cung1.setText("Thiên Bình");
            }

            if (ngay3 >= 24 && ngay3 <= 31 && thang3 == 10) {
                cung1.setText("Bọ Cạp");
            }

            if (ngay3 >= 1 && ngay3 <= 22 && thang3 == 11) {
                cung1.setText("Bọ Cạp");
            }

            if (ngay3 >= 23 && ngay3 <= 31 && thang3 == 11) {
                cung1.setText("Nhân Mã");
            }

            if (ngay3 >= 1 && ngay3 <= 21 && thang3 == 12) {
                cung1.setText("Nhân Mã");
            }
            if (ngay3 >= 22 && ngay3 <= 31 && thang3 == 12) {
                cung1.setText("Ma Kết");
            }

            if (ngay3 >= 1 && ngay3 <= 19 && thang3 == 1) {
                cung1.setText("Ma Kết");
            }

            if (ngay3 >= 20 && ngay3 <= 31 && thang3 == 1) {
                cung1.setText("Bảo Bình");
            }

            if (ngay3 >= 1 && ngay3 <= 18 && thang3 == 2) {
                cung1.setText("Bảo Bình");
            }

            if (ngay3 >= 19 && ngay3 <= 31 && thang3 == 2) {
                cung1.setText("Song Ngư");
            }

            if (ngay3 >= 1 && ngay3 <= 20 && thang3 == 3) {
                cung1.setText("Song Ngư");
            }

        }

    }



}

