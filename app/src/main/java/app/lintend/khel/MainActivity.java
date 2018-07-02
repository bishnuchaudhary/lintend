package app.lintend.khel;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity{
    Button joinbtn;
    EditText pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pin=findViewById(R.id.pin);
        joinbtn=findViewById(R.id.joinbtn);


       joinbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String pinvalue=pin.getText().toString();
               RequestQueue queue=Volley.newRequestQueue(MainActivity.this);
               String url="http://192.168.1.98:3000/getgamedetailbypin/"+pinvalue;
               StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                       new Response.Listener<String>() {
                           @Override
                           public void onResponse(String response) {

                               Log.i("response",""+String.valueOf(response));

                               Toast.makeText(MainActivity.this, "game joined", Toast.LENGTH_SHORT).show();
                               Intent intent=new Intent(MainActivity.this,QuestionActivity.class);
                               startActivity(intent);

                           }
                       }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                   }
               });
               queue.add(stringRequest);
           }
       });


    }

}
