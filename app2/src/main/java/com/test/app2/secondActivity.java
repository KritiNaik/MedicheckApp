package com.test.app2;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class secondActivity extends AppCompatActivity  {

    ImageView ivShowImage;
    ImageView ivMedals;
    ArrayList<String> Questions=new ArrayList<>();
    ArrayList<String> newQuestions= new ArrayList<>();
    HashMap<String, String> map= new HashMap<>();
    int index;
    Random random;
    String[] answers= new String[4];
    Button btn1, btn2, btn3, btn4;
    TextView tvPoints;
    int points=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ivShowImage= findViewById(R.id.ivShowImage);
        ivMedals=findViewById(R.id.ivMedals);
        btn1= findViewById(R.id.btn1);
        btn2= findViewById(R.id.btn2);
        btn3= findViewById(R.id.btn3);
        btn4= findViewById(R.id.btn4);
        tvPoints= findViewById(R.id.tvPoints);
        Questions.add("left");
        Questions.add("right");
        Questions.add("up");
        Questions.add("right");
        Questions.add("Up");
       index= 0;
        map.put(Questions.get(0),"https://1.bp.blogspot.com/-JV6Yz2Ji9H0/X7or7bD_CSI/AAAAAAAAA6I/A2xkybkkTo8Kd_4oGctMEBq8l03C9cBKwCLcBGAsYHQ/s113/E254FECD-4E1E-4668-8DA8-B4581241FCF1.jpeg");
        map.put(Questions.get(1),"https://1.bp.blogspot.com/-xSdBZzMsxoM/X7or6h6VaPI/AAAAAAAAA6E/fPexLFMe7sMzF2nrFgA3jsZKAsyD9kPhwCLcBGAsYHQ/s113/6137BDD7-0EBC-4E31-BC48-BE9C137673B4.jpeg");
        map.put(Questions.get(2),"https://1.bp.blogspot.com/-JV6Yz2Ji9H0/X7or7bD_CSI/AAAAAAAAA6I/A2xkybkkTo8Kd_4oGctMEBq8l03C9cBKwCLcBGAsYHQ/w22-h23/E254FECD-4E1E-4668-8DA8-B4581241FCF1.jpeg");
        map.put(Questions.get(3),"https://1.bp.blogspot.com/-xSdBZzMsxoM/X7or6h6VaPI/AAAAAAAAA6E/fPexLFMe7sMzF2nrFgA3jsZKAsyD9kPhwCLcBGAsYHQ/w21-h20/6137BDD7-0EBC-4E31-BC48-BE9C137673B4.jpeg");
        map.put(Questions.get(4),"https://1.bp.blogspot.com/-R5RJGOkI2Lo/X7or6abYMQI/AAAAAAAAA6A/A6wPWsiMAXI1XHXtTPmtddDt72c_B6fSgCLcBGAsYHQ/w29-h28/73AEDD95-8D63-4C15-8F09-2B6B18B6A3E1.jpeg");
        random =new Random();
        generateQuestions(index);



    }

    private void generateQuestions(int index) {
        try {
            Bitmap bitmap = new ImageDownloader().execute(map.get(Questions.get(index))).get();
        ivShowImage.setImageBitmap(bitmap);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        newQuestions= (ArrayList<String>) Questions.clone();
        newQuestions.remove(index);
        Collections.shuffle(newQuestions);
        int correctAnswerPosition= random.nextInt(4);
        for (int i=0;i<4;i++){
            if(i==correctAnswerPosition)
                answers[i]=Questions.get(index);
            else
                answers[i]=newQuestions.get(i);
        }
        btn1.setText(answers[0]);
        btn2.setText(answers[1]);
        btn3.setText(answers[2]);
        btn4.setText(answers[3]);
        newQuestions.clear();

    }



    public void answerSelected(View view) {
        String answer =((Button)view).getText().toString();
        if(answer.equals(Questions.get(index))){
            points++;
            tvPoints.setText(points + "/5");

        }
        index++;
        if(index>Questions.size()-1){
            ivShowImage.setVisibility(View.GONE);
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
            if(points==5){
                ivMedals.setImageResource(R.drawable.res1);
                Log. d("myTag", "This is my message");
            }
            else if(points<5 && points>=3){
                ivMedals.setImageResource(R.drawable.res2);
                Log. d("myTag", "This is my message");
            }
            else if(points<3){
                Log. d("myTag", "This is my message");
                ivMedals.setImageResource(R.drawable.res3);
            }
        }
        else
            generateQuestions(index);

    }

    private class ImageDownloader extends AsyncTask<String,Void, Bitmap> {
        HttpURLConnection httpURLConnection;
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                httpURLConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream= new BufferedInputStream(httpURLConnection.getInputStream());
                Bitmap temp = BitmapFactory.decodeStream(inputStream);
                return temp;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally{
                httpURLConnection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            if(bitmap != null){
                ivShowImage.setImageBitmap(bitmap);
                Toast.makeText(getApplicationContext(), "Download Successful!", Toast.LENGTH_SHORT).show();

            }
            else
                Toast.makeText(getApplicationContext(), "Download Failed!", Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);
        }
    }
}