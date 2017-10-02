package comskwmqk.naver.httpblog.registeration;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseFragment newInstance(String param1, String param2) {
        CourseFragment fragment = new CourseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    //course목록
    private ArrayAdapter yearAdapter;
    private Spinner yearSpinner;
    private ArrayAdapter termAdapter;
    private Spinner termSpinner;
    private ArrayAdapter areaAdapter;
    private Spinner areaSpinner;
    private ArrayAdapter majorAdapter;
    private Spinner majorSpinner;
    private ArrayAdapter majorgroupAdapter;
    private Spinner majorgroupSpinner;

    //학부인지 대학원인지
    private String courseUniversity="";

    private ListView courseListView;
    private CourseListAdapter adapter;
    private List<Course> courseList;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final RadioGroup courseUniGroup=(RadioGroup)getView().findViewById(R.id.courseUniGroup);
        yearSpinner=(Spinner)getView().findViewById(R.id.yearSpinner);
        termSpinner=(Spinner)getView().findViewById(R.id.termSpinner);
        areaSpinner=(Spinner)getView().findViewById(R.id.areaSpinner);
        majorSpinner=(Spinner)getView().findViewById(R.id.majorSpinner);
        majorgroupSpinner=(Spinner)getView().findViewById(R.id.majorSpinnergroup);

        courseUniGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton courseButton=(RadioButton)getView().findViewById(checkedId);
                courseUniversity=courseButton.getText().toString();

                yearAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.year,android.R.layout.simple_spinner_dropdown_item);
                yearSpinner.setAdapter(yearAdapter);
                //미리선택
                yearSpinner.setSelection(0);
                termAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.term,android.R.layout.simple_spinner_dropdown_item);
                termSpinner.setAdapter(termAdapter);
                termSpinner.setSelection(2);

                if(courseUniversity.equals("학부")){
                   areaAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.universityArea,android.R.layout.simple_spinner_dropdown_item);
                  areaSpinner.setAdapter(areaAdapter);

                }
                else if(courseUniversity.equals("대학원")){
                     areaAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.graduateArea,android.R.layout.simple_spinner_dropdown_item);
                     areaSpinner.setAdapter(areaAdapter);
                     majorAdapter= ArrayAdapter.createFromResource(getActivity(),R.array.graduateMajor,android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);
                 }
            }
        });
        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(areaSpinner.getSelectedItem().equals("교양및기타")){
                    majorAdapter= ArrayAdapter.createFromResource(getActivity(),R.array.universityRefinementMajor,android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);
                    majorgroupSpinner.setVisibility(View.GONE);
                }
                if(areaSpinner.getSelectedItem().equals("전공")){
                    majorgroupAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.majorgroup,android.R.layout.simple_spinner_dropdown_item);
                    majorgroupSpinner.setAdapter(majorgroupAdapter);

                    majorgroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            switch (position) {
                                case 0:
                                    majorAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.Management, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case 1:
                                    majorAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.CollegeofEngineering, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case 2:
                                    majorAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.CollegeofAgricultureandLifeSciences, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case 3:
                                    majorAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.AnimalLifeScienceCollege, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case 4:
                                    majorAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.CollegeofCultureandArts, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case 5:
                                    majorAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.CollegeofLaw, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case 6:
                                    majorAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.CollegeofEducation, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case 7:
                                    majorAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.CollegeofSocialScience, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case 8:
                                    majorAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.CollegeofForestEnvironmentalScience, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case 9:
                                    majorAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.CollegeofVeterinaryScience, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case 10:
                                    majorAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.CollegeofPharmacy, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case 11:
                                    majorAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.MedicalSchool, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case 12:
                                    majorAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.UniversityofLifeSciences, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case 13:
                                    majorAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.CollegeofHumanities, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case 14:
                                    majorAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.CollegeofNaturalScience, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case 15:
                                    majorAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.ITuniversity, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case 16:
                                    majorAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.DepartmentofSportsScience, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                            }
                            majorSpinner.setAdapter(majorAdapter);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                            // sometimes you need nothing here
                        }
                    });

                    majorgroupSpinner.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        courseListView=(ListView)getView().findViewById(R.id.courseListView);
        courseList=new ArrayList<Course>();
        adapter=new CourseListAdapter(getContext().getApplicationContext(),courseList,this);
        courseListView.setAdapter(adapter);
        //검색버튼 task 활성화
        Button searchButon=(Button)getView().findViewById(R.id.searchButton);
        searchButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask().execute();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course, container, false);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    class BackgroundTask extends AsyncTask<Void,Void,String>{
        String target;
        @Override
        protected void onPreExecute(){
            try{
                if(termSpinner.getSelectedItem().toString().equals("1학기")&&areaSpinner.getSelectedItem().toString().equals("교양및기타")){
                   //교양및기타 1학기 2학기 통합
                    termSpinner.setSelection(2);
                }
                target="http://zlskqk.cafe24.com/CourseList.php?courseUniversity="+URLEncoder.encode(courseUniversity,"UTF-8")+
                        "&courseYear="+URLEncoder.encode(yearSpinner.getSelectedItem().toString().substring(0,4),"UTF-8")+"&courseTerm="+URLEncoder.encode(termSpinner.getSelectedItem().toString(),"UTF-8")+
                        "&courseArea="+URLEncoder.encode(areaSpinner.getSelectedItem().toString(),"UTF-8")+"&courseTitle="+URLEncoder.encode(majorSpinner.getSelectedItem().toString(),"UTF-8");
            }catch (Exception e){
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
              courseList.clear();
                JSONObject jsonObject=new JSONObject(result);
                JSONArray jsonArray=jsonObject.getJSONArray("response");
                int count=0;
                int courseID;//강의 고유번호
                String courseUniversity;//학부 혹은 대학원
                int courseYear;//해당년도
                String courseTerm;//해당학기
                String courseArea;//강의소속
                String courseMajor;//해당학과
                String courseGrade;//해당학년
                String courseTitle;//강의 제목
                double courseCredit;//강의 학점
                int courseDivide;//강의 분반
                int coursePersonnel;//강의 제한 인원
                String courseProfessor;//강의 교수
                String courseTime;//강의 시간대
                String courseRoom;//강의실
                while(count<jsonArray.length()){
                    JSONObject object=jsonArray.getJSONObject(count);
                    courseID=object.getInt("courseID");
                    courseUniversity=object.getString("courseUniversity");
                    courseYear=object.getInt("courseYear");
                    courseTerm=object.getString("courseTerm");
                    courseArea=object.getString("courseArea");
                    courseMajor=object.getString("courseMajor");
                    courseGrade=object.getString("courseGrade");
                    courseTitle=object.getString("courseTitle");
                    courseCredit=object.getInt("courseCredit");
                    //5학점이면 0.5로 바꾸기
                    if(courseCredit==5)
                    {
                        courseCredit=0.5;
                    }
                    courseDivide=object.getInt("courseDivide");
                    coursePersonnel=object.getInt("coursePersonnel");
                    courseProfessor=object.getString("courseProfessor");
                    courseTime=object.getString("courseTime");
                    courseRoom=object.getString("courseRoom");
                    //학기가 같지 않으면 break;
                    if(!termSpinner.getSelectedItem().toString().equals(courseTerm)){
                    break;
                    }
                    Course course=new Course(courseID,courseUniversity,courseYear,courseTerm,courseArea,courseMajor,courseGrade,courseTitle,courseCredit,courseDivide,coursePersonnel,courseProfessor,courseTime,courseRoom);
                    courseList.add(course);
                    count++;
                }
                if(count==0){
                    AlertDialog dialog;
                    AlertDialog.Builder builder=new AlertDialog.Builder(CourseFragment.this.getActivity());
                    dialog=builder.setMessage("조회된 강의가 없습니다\n날짜를 확인하세요.")
                            .setPositiveButton("확인",null)
                            .create();
                    dialog.show();
                }
                //adapter 변경
                adapter.notifyDataSetChanged();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
