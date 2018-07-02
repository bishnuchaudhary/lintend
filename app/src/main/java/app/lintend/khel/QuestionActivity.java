package app.lintend.khel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class QuestionActivity extends AppCompatActivity  implements View.OnClickListener{


    Button btnA,btnB,btnC,btnD;
  int correct=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        btnA=findViewById(R.id.btnA);
        btnB=findViewById(R.id.btnB);
        btnC=findViewById(R.id.btnC);
        btnD=findViewById(R.id.btnD);
        
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);

    }


    public void volleyfunction(int correct){
        RequestQueue queue= Volley.newRequestQueue(QuestionActivity.this);
        String url="http://192.168.1.98:3000/correct/56458679,"+correct;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("response",""+String.valueOf(response));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(QuestionActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        });
        queue.add(stringRequest);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnA:
                 correct=1;
                 volleyfunction(correct);

                break;

            case R.id.btnB:
                 correct=2;
                 volleyfunction(correct);
                break;

            case R.id.btnC:
                correct=3;
                volleyfunction(correct);
                break;

            case R.id.btnD:
                correct=4;
                volleyfunction(correct);
                break;
        }
    }
}
