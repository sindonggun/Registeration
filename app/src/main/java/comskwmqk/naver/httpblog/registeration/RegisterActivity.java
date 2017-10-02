package comskwmqk.naver.httpblog.registeration;

import android.content.pm.ActivityInfo;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private ArrayAdapter majorgroupadapter;
    private ArrayAdapter majoradapter;
    public  Spinner groupspinner;
    public  Spinner spinner;
    private String userGender;
    private AlertDialog dialog;
    private boolean validate=false;
    public static String groupsp;
    public static String majorsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_register);
        //스피너에 majorgroup연결
        groupspinner=(Spinner)findViewById(R.id.majorSpinnergroup);
        majorgroupadapter=ArrayAdapter.createFromResource(this,R.array.majorgroup, android.R.layout.simple_spinner_dropdown_item);
        groupspinner.setAdapter(majorgroupadapter);
        //스피너에 major연결
        spinner=(Spinner)findViewById(R.id.majorSpinner);
        //majoradapter=ArrayAdapter.createFromResource(this,R.array.major, android.R.layout.simple_spinner_dropdown_item);
        //groupspinner에따른 majoradapter변경
        groupspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        majoradapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.Management, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 1:
                        majoradapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.CollegeofEngineering, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 2:
                        majoradapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.CollegeofAgricultureandLifeSciences, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 3:
                        majoradapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.AnimalLifeScienceCollege, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 4:
                        majoradapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.CollegeofCultureandArts, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 5:
                        majoradapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.CollegeofLaw, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 6:
                        majoradapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.CollegeofEducation, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 7:
                        majoradapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.CollegeofSocialScience, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 8:
                        majoradapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.CollegeofForestEnvironmentalScience, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 9:
                        majoradapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.CollegeofVeterinaryScience, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 10:
                        majoradapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.CollegeofPharmacy, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 11:
                        majoradapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.MedicalSchool, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 12:
                        majoradapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.UniversityofLifeSciences, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 13:
                        majoradapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.CollegeofHumanities, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 14:
                        majoradapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.CollegeofNaturalScience, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 15:
                        majoradapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.ITuniversity, android.R.layout.simple_spinner_dropdown_item);
                        break;
                    case 16:
                        majoradapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.DepartmentofSportsScience, android.R.layout.simple_spinner_dropdown_item);
                        break;
                }
                spinner.setAdapter(majoradapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });


        final EditText idText=(EditText)findViewById(R.id.idText);
        final EditText passwordText=(EditText)findViewById(R.id.passwordText);
        final EditText emailText=(EditText)findViewById(R.id.emailText);

        RadioGroup genderGroup=(RadioGroup)findViewById(R.id.genderGroup);
        //성별선택확인
        int genderGroupID=genderGroup.getCheckedRadioButtonId();

        userGender=((RadioButton)findViewById(genderGroupID)).getText().toString();

        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                //성별 바뀌었을때
                RadioButton genderButton=(RadioButton)findViewById(checkedId);
                userGender=genderButton.getText().toString();
            }
        });
        //중복체크
        final Button validateButton=(Button)findViewById(R.id.validateButton);
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID=idText.getText().toString();
                //체크되있다면
                /*
                if(validate){
                    return;
                }
                */
                if(userID.equals("")){
                    AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                    dialog=builder.setMessage("아이디를 다시 입력하세요.")
                            .setPositiveButton("확인",null)
                            .create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener= new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            //FTP에서 success값 가지고옴
                            JSONObject jsonResponse=new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                                dialog=builder.setMessage("사용할수 있는 아이디입니다")
                                        .setPositiveButton("확인",null)
                                        .create();
                                dialog.show();

                                validate=true;
                              //  idText.setBackgroundColor(getResources().getColor(R.color.colorGray));
                                validateButton.setBackgroundColor(getResources().getColor(R.color.colorGray));
                            }
                            else {
                                AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                                dialog=builder.setMessage("사용할수 없는 아이디입니다")
                                        .setNegativeButton("확인",null)
                                        .create();
                                dialog.show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                //서버에 요청
                ValidateRequest validateRequest=new ValidateRequest(userID,responseListener);
                RequestQueue queue= Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateRequest);
            }
        });
        //회원가입
        Button registerButton=(Button)findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID=idText.getText().toString();
                String userPassword=passwordText.getText().toString();
                final String userMajor=spinner.getSelectedItem().toString();
                String userEmail=emailText.getText().toString();

                if(!validate){
                    AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                    dialog=builder.setMessage("먼저 중복체크를 먼저 해주세요")
                            .setPositiveButton("확인",null)
                            .create();
                    dialog.show();
                    return;
                }

                //빈곳 있는지 확인
                if(userID.equals("")||userPassword.equals("")||userMajor.equals("")||userEmail.equals("")||userGender.equals("")){
                    AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                    dialog=builder.setMessage("빈칸없이 입력해주세요")
                            .setPositiveButton("확인",null)
                            .create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener= new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse=new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                                dialog=builder.setMessage("회원가입에 성공하였습니다")
                                        .setPositiveButton("확인",null)
                                        .create();
                                dialog.show();
                                finish();
                            }
                            else {
                                AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                                dialog=builder.setMessage("회원가입에 실패하였습니다")
                                        .setNegativeButton("확인",null)
                                        .create();
                                dialog.show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest=new RegisterRequest(userID,userPassword,userGender,userMajor,userEmail,responseListener);
                RequestQueue queue= Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
    //회원가입창끄기
    @Override
    protected void onStop(){
        super.onStop();
        if(dialog!=null){
            dialog.dismiss();
            dialog=null;
        }
    }
}
