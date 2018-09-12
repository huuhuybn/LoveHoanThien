package com.example.dev.lovehoanthien.KhoiTaoDuLieu;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dev.lovehoanthien.DataBase.DataBase;
import com.example.dev.lovehoanthien.Gif;
import com.example.dev.lovehoanthien.R;
import com.example.dev.lovehoanthien.Service.ExampleService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class KhoiTao_Activity extends AppCompatActivity {
    private TextView txtLove;
    private EditText txtNgaysinh1, txtNgaysinh2, ngayhientai;
    private ImageView loaOn;
    private int vitri = 1;
    private MediaPlayer player;
    private Animation anim4, anim9;
    private TextView txLove;
    private Typeface typeface, typeface1;
    private EditText tenban, tennguoiay, ngayyeunhau;
    private Button khoitao;
    private CheckBox dongy;
    private Cursor cursorData, cursorData1;
    private DataBase dataBase;
    Calendar calendarOne, calendarTwo;
    SimpleDateFormat simpleDateFormat;
    private FloatingActionButton floatbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoi_tao_);
        Anhxa();
        Click();
        calendarTwo=Calendar.getInstance();
        ngayhientai.setText(simpleDateFormat.format(calendarTwo.getTime())+"(Ngày hiện tại)");

        final AlertDialog.Builder builder = new AlertDialog.Builder(KhoiTao_Activity.this);
        builder.setTitle("Lời nhắn");
        builder.setIcon(R.drawable.canhbao);
        builder.setMessage("Mong bạn hài lòng với những gì tôi tạo ra.Cảm ơn!");
        builder.setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.setCancelable(true);
            }
        });

        builder.show();

        khoitao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1=tenban.getText().toString();
                String t2=tennguoiay.getText().toString();
                String date=ngayyeunhau.getText().toString();


                if ( t1.equals("")||t2.equals("")|| date.equals("")||!dongy.isChecked() ){
                    Toast.makeText(KhoiTao_Activity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();

                }else {
                    int ngayyeu=(int)(calendarOne.getTimeInMillis()/(1000*60*60*24));
                    int ngay= (int) ((calendarTwo.getTimeInMillis()-calendarOne.getTimeInMillis())/(1000*60*60*24));
                    dataBase.insertSoNgayYeu(ngay);
                    dataBase.insertTen(t1,t2);
                    dataBase.insertNgayYeu(ngayyeu);
                    Thread thread=new Thread(){
                        @Override
                        public void run() {
                            try {
                                sleep(150);
                            } catch (Exception e) {
                                Log.e("Tình",e+"");
                            }finally {
                                startActivity(new Intent(KhoiTao_Activity.this,Gif.class));
                                overridePendingTransition(R.anim.divao,R.anim.dira);

                                finish();
                            }
                        }
                    };
                    thread.start();
                    String input =t1+"❤"+t2+"_"+ngay+"ngày rồi đó"+"-Luôn hạnh phúc nhé!";
                    Intent serviceIntent = new Intent(KhoiTao_Activity.this, ExampleService.class);
                    serviceIntent.putExtra("inputExtra", input);
                    ContextCompat.startForegroundService(KhoiTao_Activity.this, serviceIntent);
                }
            }
        });
    }


    void Anhxa() {
        txtLove = findViewById(R.id.txLove);
        ngayhientai = findViewById(R.id.ngayhientai);
        floatbutton = findViewById(R.id.floatbutton);
        dataBase=new DataBase(this);
        tenban=(EditText)findViewById(R.id.txTenBan);
        tennguoiay=(EditText)findViewById(R.id.txNguouiAy);
        ngayyeunhau=(EditText)findViewById(R.id.ngayyeunhau);
        khoitao=(Button)findViewById(R.id.khoitao);
        dongy=(CheckBox)findViewById(R.id.dongy);
        simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");

        Typeface font1 = Typeface.createFromAsset(getAssets(), "font_holidays.ttf");
        txtLove.setTypeface(font1);
        ngayhientai.setEnabled(false);
        ngayyeunhau.setEnabled(false);

        floatbutton.setImageResource(R.drawable.delete);


    }
    void floatButton(){

                ngayyeunhau.setText("");
                tenban.setText("");
                tennguoiay.setText("");
                dongy.setChecked(false);


    }



    void Click() {
        floatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(KhoiTao_Activity.this);
                builder.setTitle("Xác nhận");
                builder.setIcon(R.drawable.canhbao);
                builder.setMessage("Bạn có muốn xóa hết dữ liệu vừa nhập không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                       if (tenban.getText().toString().equals("")&&tennguoiay.getText().toString().equals("")&&!dongy.isChecked()){
                           Toast.makeText(KhoiTao_Activity.this, "Không có gì để xóa", Toast.LENGTH_SHORT).show();
                       }else {
                           floatButton();
                           Toast.makeText(KhoiTao_Activity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                       }

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
        });
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(KhoiTao_Activity.this);
        builder.setTitle("Thoát");
        builder.setIcon(R.drawable.canhbao);
        builder.setMessage("Bạn có muốn thoát không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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




    public void ChonNgayYeu(View view) {
        chonngay1();
    }





    private void chonngay1(){
        calendarOne=Calendar.getInstance();
        int ngay=calendarOne.get(Calendar.DATE);
        int thang=calendarOne.get(Calendar.MONTH);
        int nam=calendarOne.get(Calendar.YEAR);
        DatePickerDialog pickerDialog =new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendarOne.set(i,i1,i2);
                        ngayyeunhau.setText(simpleDateFormat.format(calendarOne.getTime()));
                    }
                },nam,thang,ngay);
        pickerDialog.show();

    }

    private void chonngaysinh1(){
        calendarOne=Calendar.getInstance();
        int ngay=calendarOne.get(Calendar.DATE);
        int thang=calendarOne.get(Calendar.MONTH);
        int nam=calendarOne.get(Calendar.YEAR);
        DatePickerDialog pickerDialog =new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendarOne.set(i,i1,i2);
                        txtNgaysinh1.setText(simpleDateFormat.format(calendarOne.getTime()));
                    }
                },nam,thang,ngay);
        pickerDialog.show();

    }

    private void chonngaysinh2(){
        calendarOne=Calendar.getInstance();
        int ngay=calendarOne.get(Calendar.DATE);
        int thang=calendarOne.get(Calendar.MONTH);
        int nam=calendarOne.get(Calendar.YEAR);
        DatePickerDialog pickerDialog =new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendarOne.set(i,i1,i2);
                        txtNgaysinh2.setText(simpleDateFormat.format(calendarOne.getTime()));
                    }
                },nam,thang,ngay);
        pickerDialog.show();

    }
    private void chonngay2(){
        calendarTwo=Calendar.getInstance();
        int ngay=calendarTwo.get(Calendar.DATE);
        int thang=calendarTwo.get(Calendar.MONTH);
        int nam=calendarTwo.get(Calendar.YEAR);
        DatePickerDialog  pickerDialog =new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        ngayhientai.setText(simpleDateFormat.format(calendarTwo.getTime()));

                    }
                },nam,thang,ngay);

        pickerDialog.show();

    }

}
