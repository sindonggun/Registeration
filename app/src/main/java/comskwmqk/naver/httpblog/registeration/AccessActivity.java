package comskwmqk.naver.httpblog.registeration;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        CircleImageView loginimageView = (CircleImageView) findViewById(R.id.loginimageView);
        loginimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AccessActivity.this,LoginActivity.class);
                AccessActivity.this.startActivity(intent);
            }
        });
        CircleImageView infoimageView = (CircleImageView) findViewById(R.id.infoimageView);
        infoimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccessActivity.this,Pop.class));
            }
        });
        CircleImageView serverimageView = (CircleImageView) findViewById(R.id.serverimageView);
        serverimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://time.navyism.com/?host=nsugang.kangwon.ac.kr"));
                startActivity(myintent);
            }
        });
        CircleImageView cafeimageView = (CircleImageView) findViewById(R.id.cafeimageView);
        cafeimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://cafe.daum.net/kangwonlike"));
                startActivity(myintent);
            }
        });
        CircleImageView buildingimageView = (CircleImageView) findViewById(R.id.buildingimageView);
        buildingimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask().execute();
            }
        });
    }
    //서버내용 가져오는 클래스
    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;
        //초기화메소드
        @Override
        protected void onPreExecute(){
            target="http://zlskqk.cafe24.com/Map.php";
        }
        //실행
        @Override
        protected String doInBackground(Void... params) {
            try{
                URL url=new URL(target);
                //url커미션
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

                String temp;
                //매열마다 담기기
                StringBuilder stringBuilder=new StringBuilder();
                while((temp=bufferedReader.readLine())!=null){
                    stringBuilder.append(temp+"\n");

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return  stringBuilder.toString().trim();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }
        public void onPostExecute(String result){
            Intent intent=new Intent(AccessActivity.this,MapActivity.class);
            intent.putExtra("mapList",result);
            AccessActivity.this.startActivity(intent);
        }
    }

}
