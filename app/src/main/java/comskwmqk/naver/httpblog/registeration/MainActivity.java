package comskwmqk.naver.httpblog.registeration;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;
    public static String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //세로고정
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        userID=getIntent().getStringExtra("userID");

        final Button courseButton=(Button)findViewById(R.id.courseButton);
        final Button statisticsButton=(Button)findViewById(R.id.statisticsButton);
        final Button scheduleButton=(Button)findViewById(R.id.scheduleButton);
        final LinearLayout notice=(LinearLayout)findViewById(R.id.notice);
        //공지사항
        noticeListView=(ListView)findViewById(R.id.noticeListView);
        noticeList=new ArrayList<Notice>();
        //동작하나를 넣어줘야 backtask실행하여 서버에있는 데이터가 받아짐
        noticeList.add(new Notice("최초공지사항","관리","2017-08-09"));
        adapter=new NoticeListAdapter(getApplicationContext(),noticeList);
        noticeListView.setAdapter(adapter);

        //새로운화면으로 바꾸기
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                //CourseFragment 호출
        fragmentTransaction.replace(R.id.timeFragment,new StopwatchFragment());
        fragmentTransaction.commit();



        courseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //공지사항 보이지 않도록
                notice.setVisibility(View.GONE);
                courseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                statisticsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                //새로운화면으로 바꾸기
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                //CourseFragment 호출
                fragmentTransaction.replace(R.id.Fragment,new CourseFragment());
                fragmentTransaction.commit();
            }
        });

        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                courseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                statisticsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                //새로운화면으로 바꾸기
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                //시간표호출
                fragmentTransaction.replace(R.id.Fragment,new StatisticsFragment());
                fragmentTransaction.commit();
            }
        });

        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                courseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                statisticsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                //새로운화면으로 바꾸기
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Fragment,new ScheduleFragment());
                fragmentTransaction.commit();
            }
        });

        new BackgroundTask().execute();
    }
    class BackgroundTask extends AsyncTask<Void,Void,String>{
        String target;
        @Override
        protected void onPreExecute(){
            target="http://zlskqk.cafe24.com/NoticeList.php";
        }
        @Override
        protected String doInBackground(Void... params) {
           try{
               URL url=new URL(target);
               HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
               InputStream inputStream=httpURLConnection.getInputStream();
               BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
               String temp;
               StringBuilder stringBuilder=new StringBuilder();
               while ((temp=bufferedReader.readLine())!=null){
                   stringBuilder.append(temp+"\n");
               }
               bufferedReader.close();
               inputStream.close();
               httpURLConnection.disconnect();
               return stringBuilder.toString().trim();
           }catch (Exception e){
               e.printStackTrace();
           }
            return null;
        }
        @Override
        public void onProgressUpdate(Void...values){
            super.onProgressUpdate();
        }
        @Override
        public void onPostExecute(String result){
            try{
                JSONObject jsonObject=new JSONObject(result);
                JSONArray jsonArray=jsonObject.getJSONArray("response");
                int count=0;
                String noticeContent,noticeName,noticeDate;
                //서버저장 리스트 불러오기
                while(count<jsonArray.length()){
                    JSONObject object=jsonArray.getJSONObject(count);
                    noticeContent=object.getString("noticeContent");
                    noticeName=object.getString("noticeName");
                    noticeDate=object.getString("noticeDate");
                    Notice notice=new Notice(noticeContent,noticeName,noticeDate);
                    noticeList.add(notice);
                    count++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private long lastTimeBackPressd;
    //한번버튼을누르고1.5초안에 또누르면
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(System.currentTimeMillis()-lastTimeBackPressd<1500){
            finish();
            return;
        }
        Toast.makeText(this,"'뒤로' 버튼을 한번 더 눌러 종료합니다",Toast.LENGTH_SHORT);
        lastTimeBackPressd=System.currentTimeMillis();
    }
}
