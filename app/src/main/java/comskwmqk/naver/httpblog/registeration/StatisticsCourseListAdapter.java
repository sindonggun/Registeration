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
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-08-09.
 */

public class StatisticsCourseListAdapter extends BaseAdapter {

    private Context context;
    private List<Course> courseList;
    private Fragment parent;
    private String userID=MainActivity.userID;

    public StatisticsCourseListAdapter(Context context, List<Course> courseList, Fragment parent) {
        this.context = context;
        this.courseList = courseList;
        this.parent=parent;

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
        View v=View.inflate(context,R.layout.statistics,null);
        TextView courseGrade=(TextView)v.findViewById(R.id.courseGrade);
        TextView courseTitle=(TextView)v.findViewById(R.id.courseTitle);
        TextView courseDivide=(TextView)v.findViewById(R.id.courseDivide);
        TextView coursePersonnel=(TextView)v.findViewById(R.id.coursePersonnel);
        TextView courseRate=(TextView)v.findViewById(R.id.courseRate);

        if(courseList.get(position).getCourseGrade().equals("제한없음")||courseList.get(position).getCourseGrade().equals("")){
            courseGrade.setText("모든 학년");
        }
        else{
            courseGrade.setText(courseList.get(position).getCourseGrade()+"학년");
        }
        courseTitle.setText(courseList.get(position).getCourseTitle());
        courseDivide.setText(courseList.get(position).getCourseDivide()+"분반");
      //제한인원이0명이라면
        if(courseList.get(position).getCoursePersonnel()==0){
            coursePersonnel.setText("인원제한없음");
            courseRate.setText("");
        }
        else{
            coursePersonnel.setText("신청인원:"+courseList.get(position).getCourseRival()+"/"+courseList.get(position).getCoursePersonnel());
            int rate=((int)(((double)courseList.get(position).getCourseRival()*100/courseList.get(position).getCoursePersonnel())*0.5));
            courseRate.setText("경쟁률:"+rate+"%");
            //경쟁률이낮으면
            if(rate<20){
                courseRate.setTextColor(parent.getResources().getColor(R.color.colorSafe));
            }
            else if(rate<=50){
                courseRate.setTextColor(parent.getResources().getColor(R.color.colorPrimary));
            }
            else if(rate<=100){
                courseRate.setTextColor(parent.getResources().getColor(R.color.colorDanger));
            }
            else if(rate<=150){
                courseRate.setTextColor(parent.getResources().getColor(R.color.colorWarning));
            }
            else {
                courseRate.setTextColor(parent.getResources().getColor(R.color.colorWarning));
            }
        }
        v.setTag(courseList.get(position).getCourseID());



        Button deleteButton=(Button)v.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                                    AlertDialog dialog = builder.setMessage("강의가 삭제되었습니다.")
                                            .setPositiveButton("확인", null)
                                            .create();
                                    dialog.show();
                                    StatisticsFragment.totalCredit-=courseList.get(position).getCourseCredit();
                                    StatisticsFragment.credit.setText(StatisticsFragment.totalCredit+"학점");
                                    courseList.remove(position);

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                                    AlertDialog dialog = builder.setMessage("강의 삭제에 실패하였습니다.")
                                            .setNegativeButton("다시시도", null)
                                            .create();
                                    dialog.show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    //+""문자열변환
                    DeleteRequest deleteRequest = new DeleteRequest(userID, courseList.get(position).getCourseID() + "", responseListener);
                    RequestQueue queue = Volley.newRequestQueue(parent.getContext());
                    queue.add(deleteRequest);
                }

        });
        return v;
    }

}
