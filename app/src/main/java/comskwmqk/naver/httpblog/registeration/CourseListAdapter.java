package comskwmqk.naver.httpblog.registeration;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-08-09.
 */

public class CourseListAdapter extends BaseAdapter {

    private Context context;
    private List<Course> courseList;
    private Fragment parent;
    private String userID=MainActivity.userID;
    private Schedule schedule= new Schedule();
    private List<Integer> courseIDList;//courseId 중복검사
    public static int totalCredit=0;
    public CourseListAdapter(Context context, List<Course> courseList,Fragment parent) {
        this.context = context;
        this.courseList = courseList;
        this.parent=parent;
        schedule=new Schedule();
        courseIDList=new ArrayList<Integer>();
        new BackgroundTask().execute();
        totalCredit=0;

    }



    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View View,ViewGroup veiwGroup) {
        View v=View.inflate(context,R.layout.course,null);
        TextView courseGrade=(TextView)v.findViewById(R.id.courseGrade);
        TextView courseTitle=(TextView)v.findViewById(R.id.courseTitle);
        TextView courseProfessor=(TextView)v.findViewById(R.id.courseProfessor);
        final TextView courseCredit=(TextView)v.findViewById(R.id.courseCredit);
        TextView courseDivide=(TextView)v.findViewById(R.id.courseDivide);
        TextView coursePersonnel=(TextView)v.findViewById(R.id.coursePersonnel);
        TextView courseTime=(TextView)v.findViewById(R.id.courseTime);
        //학년제한
        if(courseList.get(position).getCourseGrade().equals("제한없음")||courseList.get(position).getCourseGrade().equals("")){
            courseGrade.setText("모든 학년");
        }
        else{
            courseGrade.setText(courseList.get(position).getCourseGrade()+"학년");
        }
        courseTitle.setText(courseList.get(position).getCourseTitle());
        courseCredit.setText(courseList.get(position).getCourseCredit()+"학점");
        courseDivide.setText(courseList.get(position).getCourseDivide()+"분반");
      //제한인원이0명이라면
        if(courseList.get(position).getCoursePersonnel()==0){
         coursePersonnel.setText("인원제한없음");
        }
        else{
            coursePersonnel.setText("제한인원:"+courseList.get(position).getCoursePersonnel()+"명");

        }
        if(courseList.get(position).getCourseProfessor().equals("")){
            courseProfessor.setText("개인연구");
        }
        else{
            courseProfessor.setText(courseList.get(position).getCourseProfessor()+"님");
        }
        courseTime.setText(courseList.get(position).getCourseTime()+"");
        v.setTag(courseList.get(position).getCourseID());

        Button addButton=(Button)v.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validate = false;//강의추가 타당성 검사
                validate = schedule.validate(courseList.get(position).getCourseTime());

                //회원아이디
                if (!alreadyIn(courseIDList, courseList.get(position).getCourseID())) {//이미있는강의라면
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                    AlertDialog dialog = builder.setMessage("이미 추가한 강의입니다.")
                            .setPositiveButton("다시 시도", null)
                            .create();
                    dialog.show();
                }
                else if(totalCredit+courseList.get(position).getCourseCredit()>18){
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                    AlertDialog dialog = builder.setMessage("18학점을 초과할수없습니다.")
                            .setPositiveButton("다시 시도", null)
                            .create();
                    dialog.show();
                }
                else if (validate == false) {//시간표가 중복되었다면
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                    AlertDialog dialog = builder.setMessage("시간표가 중복되었습니다.")
                            .setPositiveButton("다시 시도", null)
                            .create();
                    dialog.show();
                } else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                                    AlertDialog dialog = builder.setMessage("강의가 추가되었습니다.")
                                            .setPositiveButton("확인", null)
                                            .create();
                                    dialog.show();
                                    courseIDList.add(courseList.get(position).getCourseID());
                                    schedule.addSchedule(courseList.get(position).getCourseTime());
                                    totalCredit+=courseList.get(position).getCourseCredit();
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                                    AlertDialog dialog = builder.setMessage("강의 추가에 실패하였습니다.")
                                            .setNegativeButton("확인", null)
                                            .create();
                                    dialog.show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    //+""문자열변환
                    AddRequest addRequest = new AddRequest(userID, courseList.get(position).getCourseID() + "", responseListener);
                    RequestQueue queue = Volley.newRequestQueue(parent.getContext());
                    queue.add(addRequest);
                }
            }
        });

        return v;
    }
    class BackgroundTask extends AsyncTask<Void,Void,String> {
        String target;
        @Override
        protected void onPreExecute(){
            try {
                target="http://zlskqk.cafe24.com/ScheduleList.php?userID="+ URLEncoder.encode(userID,"UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
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
                String courseProfessor;
                String courseTime;
                int courseID;
                totalCredit=0;
                while(count<jsonArray.length()){

                    JSONObject object=jsonArray.getJSONObject(count);
                    courseID=object.getInt("courseID");
                    courseTime=object.getString("courseTime");
                    courseProfessor=object.getString("courseProfessor");
                    totalCredit+=object.getInt("courseCredit");
                    courseIDList.add(courseID);
                    schedule.addSchedule(courseTime);
                    count++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public boolean alreadyIn(List<Integer>courseIDList,int item){
        for(int i=0;i<courseIDList.size();i++){
            if(courseIDList.get(i)==item){
                return false;
            }
        }
        return true;
    }
}
