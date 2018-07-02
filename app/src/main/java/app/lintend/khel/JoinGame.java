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

public class JoinGame extends AppCompatActivity {

    EditText pin;
    Button joinbtn,btnlogout;
SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);

      pin=findViewById(R.id.pin);

      joinbtn=findViewById(R.id.joinbtn);
      btnlogout=findViewById(R.id.btnlogout);


        sharedPreferences = getSharedPreferences("userinfo",0);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String sessionidvalue = sharedPreferences.getString("sid", null);
                RequestQueue queue = Volley.newRequestQueue(JoinGame.this);
                // Request a string response from the provided URL.
                String url = "http://192.168.1.98:3000/session/"+sessionidvalue;
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override

                            public void onResponse(String response) {

                                // Display the first 500 characters of the response string.
                                String val = null;

                                JSONObject res = null;
                                try {
                                    res = new JSONObject(String.valueOf(response));

                                    val = res.optString("msg");
                                    Log.i("session ststus", "" + val);


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
                        Toast.makeText(JoinGame.this, "error", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(stringRequest);


            }
        });



        ///////////////////////////////////////////



/*

      joinbtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String pinvalue=pin.getText().toString().trim();
              if(pinvalue.isEmpty()){

                  Toast.makeText(JoinGame.this, "pin is not entered", Toast.LENGTH_SHORT).show();
              }
              else{
                 Intent intent= new Intent(JoinGame.this,QuestionActivity.class);
                 startActivity(intent);
              }
          }
      });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.logout) {

            String sessionidvalue = sharedPreferences.getString("sid", null);
            RequestQueue queue = Volley.newRequestQueue(JoinGame.this);
            // Request a string response from the provided URL.
            String url = "http://192.168.1.98:3000/session/" + sessionidvalue;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override

                        public void onResponse(String response) {

                            // Display the first 500 characters of the response string.
                            String val = null;

                            JSONObject res = null;
                            try {
                                res = new JSONObject(String.valueOf(response));

                                val = res.optString("msg");
                                Log.i("session ststus", "" + val);


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
                    Toast.makeText(JoinGame.this, "error", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(stringRequest);


           */
/* SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("sid", null);
            editor.apply();
*//*


            Intent intent = new Intent(JoinGame.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }





        return super.onOptionsItemSelected(item);
*/

        //this is first commit
    }
}
