package comskwmqk.naver.httpblog.registeration;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by Administrator on 2017-08-13.
 */

public class Schedule {
    //월요일부터 토요일까지 오전9시부터 오후9시까지
    private String monday[]=new String[14];
    private String tuesday[]=new String[14];
    private String wednesday[]=new String[14];
    private String thursday[]=new String[14];
    private String friday[]=new String[14];
    private String satday[]=new String[14];
    public Schedule(){
        for (int i=1;i<14;i++){
            monday[i]="";
            tuesday[i]="";
            wednesday[i]="";
            thursday[i]="";
            friday[i]="";
            satday[i]="";
        }
    }
    public void addSchedule(String scheduleText){
        int temp;
        //월[3][4]일때 34만꺼내기
        if((temp=scheduleText.indexOf("월"))>-1){
            temp+=1;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp;i<scheduleText.length()&&scheduleText.charAt(i)!=':';i++){
                //다른날짜 만나면 break
                if((scheduleText.charAt(i)=='화'||scheduleText.charAt(i)=='수'||scheduleText.charAt(i)=='목'||scheduleText.charAt(i)=='금'||scheduleText.charAt(i)=='토')) {
                    break;
                }

                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        monday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                    }

                }

        }
        if((temp=scheduleText.indexOf("화"))>-1){
            temp+=1;
            int startPoint=temp;
            int endPoint=temp;

            for(int i=temp;i<scheduleText.length()&&scheduleText.charAt(i)!=':';i++){
                if((scheduleText.charAt(i)=='월'||scheduleText.charAt(i)=='수'||scheduleText.charAt(i)=='목'||scheduleText.charAt(i)=='금'||scheduleText.charAt(i)=='토')) {
                    break;
                }

                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                    }

            }
        }
        if((temp=scheduleText.indexOf("수"))>-1){
            temp+=1;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp;i<scheduleText.length()&&scheduleText.charAt(i)!=':';i++){
                if((scheduleText.charAt(i)=='월'||scheduleText.charAt(i)=='화'||scheduleText.charAt(i)=='목'||scheduleText.charAt(i)=='금'||scheduleText.charAt(i)=='토')) {
                    break;
                }
                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                    }

            }
        }
        if((temp=scheduleText.indexOf("목"))>-1){
            temp+=1;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp;i<scheduleText.length()&&scheduleText.charAt(i)!=':';i++){
                if((scheduleText.charAt(i)=='월'||scheduleText.charAt(i)=='화'||scheduleText.charAt(i)=='수'||scheduleText.charAt(i)=='금'||scheduleText.charAt(i)=='토')) {
                    break;
                }
                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        thursday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                    }

            }
        }
        if((temp=scheduleText.indexOf("금"))>-1){
            temp+=1;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp;i<scheduleText.length()&&scheduleText.charAt(i)!=':';i++){
                if((scheduleText.charAt(i)=='월'||scheduleText.charAt(i)=='화'||scheduleText.charAt(i)=='수'||scheduleText.charAt(i)=='목'||scheduleText.charAt(i)=='토')) {
                    break;
                }
                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                    }

            }
        }
        if((temp=scheduleText.indexOf("토"))>-1){
            temp+=1;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp;i<scheduleText.length()&&scheduleText.charAt(i)!=':';i++){
                if((scheduleText.charAt(i)=='월'||scheduleText.charAt(i)=='화'||scheduleText.charAt(i)=='수'||scheduleText.charAt(i)=='목'||scheduleText.charAt(i)=='금')) {
                    break;
                }
                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        satday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                    }

            }
        }
    }
    public void addSchedule(String scheduleText,String courseTitle,String courseProfessor){
        String professor;
        if(courseProfessor.equals("")){
            professor="";
        }
        else{
            professor="";
        }
        int temp;
        //월34일때 34만꺼내기
        if((temp=scheduleText.indexOf("월"))>-1){
            temp+=1;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp;i<scheduleText.length()&&scheduleText.charAt(i)!=':';i++){
                if((scheduleText.charAt(i)=='화'||scheduleText.charAt(i)=='수'||scheduleText.charAt(i)=='목'||scheduleText.charAt(i)=='금'||scheduleText.charAt(i)=='토')) {
                    break;
                }

                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        //강의제목과 교수님이름 넣기
                        monday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor;
                    }

            }
        }
        if((temp=scheduleText.indexOf("화"))>-1){
            temp+=1;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp;i<scheduleText.length()&&scheduleText.charAt(i)!=':';i++){
                if((scheduleText.charAt(i)=='월'||scheduleText.charAt(i)=='수'||scheduleText.charAt(i)=='목'||scheduleText.charAt(i)=='금'||scheduleText.charAt(i)=='토')) {
                    break;
                }

                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor;
                    }

            }
        }
        if((temp=scheduleText.indexOf("수"))>-1){
            temp+=1;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp;i<scheduleText.length()&&scheduleText.charAt(i)!=':';i++){
                if((scheduleText.charAt(i)=='월'||scheduleText.charAt(i)=='화'||scheduleText.charAt(i)=='목'||scheduleText.charAt(i)=='금'||scheduleText.charAt(i)=='토')) {
                    break;
                }

                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor;
                    }

            }
        }
        if((temp=scheduleText.indexOf("목"))>-1){
            temp+=1;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp;i<scheduleText.length()&&scheduleText.charAt(i)!=':';i++){
                if((scheduleText.charAt(i)=='월'||scheduleText.charAt(i)=='화'||scheduleText.charAt(i)=='수'||scheduleText.charAt(i)=='금'||scheduleText.charAt(i)=='토')) {
                    break;
                }

                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        thursday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor;
                    }

            }
        }
        if((temp=scheduleText.indexOf("금"))>-1){
            temp+=1;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp;i<scheduleText.length()&&scheduleText.charAt(i)!=':';i++){
                if((scheduleText.charAt(i)=='월'||scheduleText.charAt(i)=='화'||scheduleText.charAt(i)=='수'||scheduleText.charAt(i)=='목'||scheduleText.charAt(i)=='토')) {
                    break;
                }

                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor;
                    }

            }
        }
        if((temp=scheduleText.indexOf("토"))>-1){
            temp+=1;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp;i<scheduleText.length()&&scheduleText.charAt(i)!=':';i++){
                if((scheduleText.charAt(i)=='월'||scheduleText.charAt(i)=='화'||scheduleText.charAt(i)=='수'||scheduleText.charAt(i)=='목'||scheduleText.charAt(i)=='금')) {
                    break;
                }

                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        satday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor;
                    }

            }
        }
    }
    public void setting(AutoResizeTextView[] monday, AutoResizeTextView[] tuesday, AutoResizeTextView[] wednesday, AutoResizeTextView[] thursday, AutoResizeTextView[] friday, AutoResizeTextView[] satday, Context context)
    {
        //값삽입
        int maxLength=0;
        String maxString="";
        for(int i=1;i<14;i++){
            if(this.monday[i].length()>maxLength){
                maxLength=this.monday[i].length();
                maxString=this.monday[i];
            }
            if(this.tuesday[i].length()>maxLength){
                maxLength=this.tuesday[i].length();
                maxString=this.tuesday[i];
            }
            if(this.wednesday[i].length()>maxLength){
                maxLength=this.wednesday[i].length();
                maxString=this.wednesday[i];
            }
            if(this.thursday[i].length()>maxLength){
                maxLength=this.thursday[i].length();
                maxString=this.thursday[i];
            }
            if(this.friday[i].length()>maxLength){
                maxLength=this.friday[i].length();
                maxString=this.friday[i];
            }
            if(this.satday[i].length()>maxLength){
                maxLength=this.satday[i].length();
                maxString=this.satday[i];
            }
        }
        for(int i=1;i<14;i++){
            if(!this.monday[i].equals("")){
                //특정한강의가 해당시간에있다면
                monday[i].setText(this.monday[i]);
                monday[i].setTextColor(context.getResources().getColor(R.color.colorPrimary));
            }
            else {
                monday[i].setText(maxString);
            }
            if(!this.tuesday[i].equals("")){
                //특정한강의가 해당시간에있다면
                tuesday[i].setText(this.tuesday[i]);
                tuesday[i].setTextColor(context.getResources().getColor(R.color.colorPrimary));
            }
            else {
                tuesday[i].setText(maxString);
            }
            if(!this.wednesday[i].equals("")){
                //특정한강의가 해당시간에있다면
                wednesday[i].setText(this.wednesday[i]);
                wednesday[i].setTextColor(context.getResources().getColor(R.color.colorPrimary));
            }
            else {
                wednesday[i].setText(maxString);
            }
            if(!this.thursday[i].equals("")){
                //특정한강의가 해당시간에있다면
                thursday[i].setText(this.thursday[i]);
                thursday[i].setTextColor(context.getResources().getColor(R.color.colorPrimary));
            }
            else {
                thursday[i].setText(maxString);
            }
            if(!this.friday[i].equals("")){
                //특정한강의가 해당시간에있다면
                friday[i].setText(this.friday[i]);
                friday[i].setTextColor(context.getResources().getColor(R.color.colorPrimary));
            }
            else {
                friday[i].setText(maxString);
            }
            if(!this.satday[i].equals("")){
                //특정한강의가 해당시간에있다면
                satday[i].setText(this.satday[i]);
                satday[i].setTextColor(context.getResources().getColor(R.color.colorPrimary));
            }
            else {
                satday[i].setText(maxString);
            }
            monday[i].resizeText();
            tuesday[i].resizeText();
            wednesday[i].resizeText();
            thursday[i].resizeText();
            friday[i].resizeText();
            satday[i].resizeText();
        }
    }
    public boolean validate(String scheduleText){
        if (scheduleText.equals("")){
            return true;
        }
        int temp;
        if((temp=scheduleText.indexOf("월"))>-1){
            temp+=1;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp;i<scheduleText.length()&&scheduleText.charAt(i)!=':';i++){
                if((scheduleText.charAt(i)=='화'||scheduleText.charAt(i)=='수'||scheduleText.charAt(i)=='목'||scheduleText.charAt(i)=='금'||scheduleText.charAt(i)=='토')) {
                    break;
                }
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    if(!monday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }
        if((temp=scheduleText.indexOf("화"))>-1){
            temp+=1;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp;i<scheduleText.length()&&scheduleText.charAt(i)!=':';i++){
                if((scheduleText.charAt(i)=='월'||scheduleText.charAt(i)=='수'||scheduleText.charAt(i)=='목'||scheduleText.charAt(i)=='금'||scheduleText.charAt(i)=='토')) {
                    break;
                }
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    if(!tuesday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }
        if((temp=scheduleText.indexOf("수"))>-1){
            temp+=1;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp;i<scheduleText.length()&&scheduleText.charAt(i)!=':';i++){
                if((scheduleText.charAt(i)=='월'||scheduleText.charAt(i)=='화'||scheduleText.charAt(i)=='목'||scheduleText.charAt(i)=='금'||scheduleText.charAt(i)=='토')) {
                    break;
                }
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    if(!wednesday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }
        if((temp=scheduleText.indexOf("목"))>-1){
            temp+=1;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp;i<scheduleText.length()&&scheduleText.charAt(i)!=':';i++){
                if((scheduleText.charAt(i)=='월'||scheduleText.charAt(i)=='화'||scheduleText.charAt(i)=='수'||scheduleText.charAt(i)=='금'||scheduleText.charAt(i)=='토')) {
                    break;
                }
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    if(!thursday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }
        if((temp=scheduleText.indexOf("금"))>-1){
            temp+=1;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp;i<scheduleText.length()&&scheduleText.charAt(i)!=':';i++){
                if((scheduleText.charAt(i)=='월'||scheduleText.charAt(i)=='화'||scheduleText.charAt(i)=='수'||scheduleText.charAt(i)=='목'||scheduleText.charAt(i)=='토')) {
                    break;
                }
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    if(!friday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }
        if((temp=scheduleText.indexOf("토"))>-1){
            temp+=1;
            int startPoint=temp;
            int endPoint=temp;
            for(int i=temp;i<scheduleText.length()&&scheduleText.charAt(i)!=':';i++){
                if((scheduleText.charAt(i)=='월'||scheduleText.charAt(i)=='화'||scheduleText.charAt(i)=='수'||scheduleText.charAt(i)=='목'||scheduleText.charAt(i)=='금')) {
                    break;
                }
                if(scheduleText.charAt(i)=='['){
                    startPoint=i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint=i;
                    if(!satday[Integer.parseInt(scheduleText.substring(startPoint+1,endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
