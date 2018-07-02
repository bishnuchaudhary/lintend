package app.lintend.khel;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText username, phonenumber;
    Button loginbtn,logoutbtn;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences=getSharedPreferences("userinfo",0);


        username = findViewById(R.id.username);
        phonenumber = findViewById(R.id.phonenumber);
        loginbtn = findViewById(R.id.loginbtn);
        logoutbtn=findViewById(R.id.logoutbtn);




        String sessionidvalue = sharedPreferences.getString("sid", null);

        Log.i("session id from shared:",""+sessionidvalue);

        /*if (sessionidvalue!=null) {
            Intent intent = new Intent(LoginActivity.this, JoinGame.class);
            startActivity(intent);
            finish();
        }
*/

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernamevalue=username.getText().toString();
                String phonenumbervalue=phonenumber.getText().toString();


                RequestQueue queue=Volley.newRequestQueue(LoginActivity.this);
                String url="http://192.168.1.98:3000/login/"+usernamevalue+","+phonenumbervalue+","+null;



                StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.i("response",""+String.valueOf(response));

                                String val = null;

                                JSONObject res = null;
                                try {
                                    res = new JSONObject(String.valueOf(response));

                                    val = res.optString("sid");

                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("sid", val);
                                    editor.apply();

                                    Toast.makeText(LoginActivity.this, ""+res, Toast.LENGTH_SHORT).show();

                                  /*  Intent intent = new Intent(LoginActivity.this,JoinGame.class);
                                    startActivity(intent);*/



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }



                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(stringRequest);
            }
        });




        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sid=sharedPreferences.getString("sid",null);




                RequestQueue queue=Volley.newRequestQueue(LoginActivity.this);
                String url="http://192.168.1.98:3000/session/"+sid;



                StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.i("response",""+String.valueOf(response));

                                String val = null;

                                JSONObject res = null;
                                try {
                                    res = new JSONObject(String.valueOf(response));

                                  //  val = res.optString("sid");


                                    Toast.makeText(LoginActivity.this, ""+res, Toast.LENGTH_SHORT).show();



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }



                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(stringRequest);
            }
        });






/*
        final String defaulturl = "http://192.168.:3000/login/";
        sharedPreferences = getSharedPreferences("userinfo", 0);
        */





      /*  final String sessionidvalue = sharedPreferences.getString("sid", null);
        // Log.i("session id from shared:",""+sessionidvalue);
        if (sessionidvalue != null) {
            Intent intent = new Intent(LoginActivity.this, JoinGame.class);
            startActivity(intent);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String usernamevalue = username.getText().toString();
                final String phonenumbervalue = phonenumber.getText().toString();


                if(usernamevalue.isEmpty()){
                    Toast.makeText(LoginActivity.this, "empty username", Toast.LENGTH_SHORT).show();
                }
                else if(phonenumbervalue.isEmpty()){

                    Toast.makeText(LoginActivity.this, "empty phonenumber", Toast.LENGTH_SHORT).show();
                }else{
                    request(defaulturl,usernamevalue,phonenumbervalue,sessionidvalue);
                }







            }
        });


    }

public void request(String defaulturl,String usernamevalue,String phonenumbervalue,String sessionidvalue){
    final RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
    String url = defaulturl + usernamevalue + "," + phonenumbervalue + "," + sessionidvalue;
// Request a string response from the provided URL.
    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
                @Override

                public void onResponse(String response) {

                    // Display the first 500 characters of the response string.
                    String val = null;

                    JSONObject res = null;
                    try {
                        res = new JSONObject(String.valueOf(response));

                        val = res.optString("sid");
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("sid", val);
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this, JoinGame.class);
                        startActivity(intent);
                        finish();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // textView.setText("status msg = "+val);
                    //textView.setText("gamedescription"+res.optString("description"));
                    Log.i("response", "" + response);
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
        }
    });
    queue.add(stringRequest);

*/
}
}

