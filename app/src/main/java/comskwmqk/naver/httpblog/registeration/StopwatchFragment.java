package comskwmqk.naver.httpblog.registeration;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchFragment extends Fragment implements View.OnClickListener {

    private boolean running;
    private int seconds = 0;
    private boolean wasRunning = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
            if(wasRunning) {
                running = true;
            }
        }
    }


    public StopwatchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        restoreState();
        runTimer(layout);
        Button startButton = (Button)layout.findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        Button stopButton = (Button)layout.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);
        Button resetButton = (Button)layout.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);
        return layout;
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }
    protected void restoreState() {
        SharedPreferences pref = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        seconds=pref.getInt("seconds",0);
        running=pref.getBoolean("running", false);
        wasRunning=pref.getBoolean("wasRunning", false);
    }

    @Override
    public void onStop() {
        super.onStop();
        saveState();
    }

    protected void saveState() {
        SharedPreferences pref = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("seconds", seconds);
        editor.putBoolean("running", running);
        editor.putBoolean("wasRunning", wasRunning);
        editor.commit();
    }
    public void onClickStart (View view) {
        running = true;
    }

    public void onClickStop (View view) {
        running = false;
    }

    public void onClickReset (View view) {
        running = false;
        seconds = 0;
    }

    private void runTimer (View view) {
        final TextView timeView = (TextView) view.findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_button:
                onClickStart(v);
                break;
            case R.id.stop_button:
                onClickStop(v);
                break;
            case R.id.reset_button:
                onClickReset(v);
                break;
        }
    }
}
