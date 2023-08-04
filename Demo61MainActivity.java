package com.hnq40.myapplication.demo6;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hnq40.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Demo61MainActivity extends AppCompatActivity {
    Button btn1,btn2;
    TextView tvKQ;
    Context context=this;
    Button btnInsert,btnUpdate,btnDelete;
    EditText txt1,txt2,txt3,txt4;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo61_main);
        btn1=findViewById(R.id.demo61Btn1);
        btn2=findViewById(R.id.demo61Btn2);
        tvKQ=findViewById(R.id.demo61TvKQ);
        btnDelete=findViewById(R.id.demo61BtnDelete);
        btnUpdate=findViewById(R.id.demo61BtnUpdate);
        btnInsert=findViewById(R.id.demo61BtnInsert);
        txt1=findViewById(R.id.demo71Txt1);
        txt2=findViewById(R.id.demo71Txt2);
        txt3=findViewById(R.id.demo71Txt3);
        txt4=findViewById(R.id.demo71Txt4);
        btn1.setOnClickListener((view)->{
            //getStringByVolley();
            getStringAPI();
        });
        btn2.setOnClickListener((view)->{
            getJSON_ObjectsOfArray();
        });
        ///-----
        btnInsert.setOnClickListener((view)->{
            insertVolley();
        });
        btnUpdate.setOnClickListener((view)->{
            updateVolley();
        });
        btnDelete.setOnClickListener((view)->{
            deleteVolley();
        });
    }
    private void getStringAPI() {
        RequestQueue queue=Volley.newRequestQueue(context);
        String url="https://batdongsanabc.000webhostapp.com/mob403lab7/get_all_product.php";
        JsonObjectRequest request=new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray products=response.getJSONArray("products");
                    strKQ="";
                    for(int i=0;i<products.length();i++)
                    {
                        JSONObject product=products.getJSONObject(i);
                        String pid=product.getString("pid");
                        String name=product.getString("name");
                        String price=product.getString("price");
                        String description=product.getString("description");
                        strKQ += "pid: "+pid+"\n\n";
                        strKQ += "name: "+name+"\n\n";
                        strKQ += "price: "+price+"\n\n";
                        strKQ += "description: "+description+"\n\n";

                    }
                    tvKQ.setText(strKQ);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        });
        queue.add(request);

    }

    private void deleteVolley() {
        //b0-Chuan bi du lieu
        String pid=txt1.getText().toString();
        //b1-Tao queue
        RequestQueue queue=Volley.newRequestQueue(context);
        //B2- url
        String url="https://batdongsanabc.000webhostapp.com/mob403lab7/delete_product.php";
        //b3- request (xac dinh loai request)
        //StringRequest(method,url,thanhcong,thatbai){thamso}
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //lay ve ket qua
                        tvKQ.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> mydata=new HashMap<>();
                mydata.put("pid",pid);
                return mydata;
            }
        };
        //b4.truyen tham so (neu co)
        //b5- thuc thi request
        queue.add(request);
    }

    private void updateVolley() {
        //b0-Chuan bi du lieu
        //b1-Tao queue
        RequestQueue queue=Volley.newRequestQueue(context);
        //B2- url
        String url="https://batdongsanabc.000webhostapp.com/mob403lab7/update_product.php";
        //b3- request (xac dinh loai request)
        //StringRequest(method,url,thanhcong,thatbao){thamSo}
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvKQ.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        }){
            //b4.truyen tham so (neu co)
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> mydata=new HashMap<>();
                mydata.put("pid",txt1.getText().toString());
                mydata.put("name",txt2.getText().toString());
                mydata.put("price",txt3.getText().toString());
                mydata.put("description",txt4.getText().toString());
                return mydata;
            }
        };
        //b5- thuc thi request
        queue.add(request);

    }

    private void insertVolley() {
        //b0-Chuan bi du lieu
        //b1-Tao queue
        RequestQueue queue=Volley.newRequestQueue(context);
        //B2- url
        String url="https://batdongsanabc.000webhostapp.com/mob403lab7/create_product.php";
        //b3- request (xac dinh loai request)
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvKQ.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        }){
            //b4.truyen tham so (neu co)
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> mydata=new HashMap<>();
                mydata.put("name",txt2.getText().toString());
                mydata.put("price",txt3.getText().toString());
                mydata.put("description",txt4.getText().toString());
                return mydata;
            }
        };
        //b5- thuc thi request
        queue.add(request);
    }

    String strKQ="";
    private void getJSON_ObjectsOfArray() {
        //0.Tao hang doi
        RequestQueue queue=Volley.newRequestQueue(context);
        //1.url
        String url="https://batdongsanabc.000webhostapp.com/mob403lab6/array_json_new.json";
        //2. Tao request -> (xac dinh Loai request)
        //Truong hop nay la ArrayRequest (vi day la mang cua cac doi tuong)
        //JsonArrayRequest(url,thanhCong,thatBai)
        JsonArrayRequest request=new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //ket qua tra ve MANG cua cac DOITUONG
                        //-> can dung vong For de doc het cac doi tuong
                        strKQ="";
                        for(int i=0;i<response.length();i++)
                        {
                            try {
                                JSONObject person=response.getJSONObject(i);//lay doi tuong thu i
                                String id=person.getString("id");
                                String name=person.getString("name");
                                String email=person.getString("email");
                                JSONObject phone=person.getJSONObject("phone");
                                String mobile=phone.getString("mobile");
                                String home=phone.getString("home");
                                //Noi chuoi
                                strKQ += "id: "+id+"\n\n";
                                strKQ += "name: "+name+"\n\n";
                                strKQ += "email: "+email+"\n\n";
                                strKQ += "mobile: "+mobile+"\n\n";
                                strKQ += "home: "+home+"\n\n";
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        //dua ket qua len man hinh
                        tvKQ.setText(strKQ);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tvKQ.setText(error.getMessage());//in ra loi
                    }
                });
        //3.Truyen tham so (neu co)
        //4.Xu ly request
        queue.add(request);

    }

    //doc chuoi tu trang google.com
    private void getStringByVolley() {
        //B0. Tao hang doi
        RequestQueue queue= Volley.newRequestQueue(context);
        //b1- Url
        String url="https://www.google.com/";
        //b2.Tao request
        //StringRequest(phuongThuc,url,thanhCong,thatbai)
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //dua ket qua len man hinh
                        tvKQ.setText("Ket qua: "+response.substring(0,1000));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());//in ra loi
            }
        });
        //b3-Xu ly request
        queue.add(stringRequest);

    }
}