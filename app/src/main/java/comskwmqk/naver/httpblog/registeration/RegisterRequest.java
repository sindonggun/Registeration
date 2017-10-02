package comskwmqk.naver.httpblog.registeration;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-08-08.
 */

public class RegisterRequest extends StringRequest{
    final static private String URL="http://zlskqk.cafe24.com/UserRegister.php";
    private Map<String,String> parameters;

    public RegisterRequest(String userID,String userPassword,String userGender,String userMajor,String userEmail,Response.Listener<String> listener) {
        //해당요청숨겨서 보내준다
        super(Method.POST,URL,listener,null);
        parameters=new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPassword",userPassword);
        parameters.put("userGender",userGender);
        parameters.put("userMajor",userMajor);
        parameters.put("userEmail",userEmail);
    }

    @Override
    public Map<String,String>getParams(){
        return parameters;
    }
}
