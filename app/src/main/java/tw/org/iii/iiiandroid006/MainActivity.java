package tw.org.iii.iiiandroid006;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView clock;
    private ListView laplist;
    private Boolean isRunning;
    private Button btnright,btnleft;
    private Timer timer;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clock = findViewById(R.id.colok);
        laplist = findViewById(R.id.laplist);
        btnleft = findViewById(R.id.btnleft);
        btnright = findViewById(R.id.btnright);

        btnleft.setText(isRunning?"lap":"Reset");
        btnright.setText(isRunning?"Stop":"Start");

        Log.v("brad","time");
        timer= new Timer();
        //timer.schedule(new MyTask(),0,10);

    }
    private class MyTask extends TimerTask{
        @Override
        public void run(){
            i++;
            Log.v("brad","i==>"+i);
            clock.setText(""+i);

        }

    }

    public void clickleft(View view){
        //測試練習 看不到但在背景中執行
        //電腦背景要看前景的臉色過活
        //安卓 不一定




    }
//改變狀態會改變內容  不能只是按鈕觸發
    public void clickright(View view) {
        isRunning =!isRunning;
        btnleft.setText(isRunning?"lap":"Reset");
        btnright.setText(isRunning?"Stop":"Start");

        timer.schedule(new MyTask(),0,10);

    }
    //小明媽媽炒菜，沒有醬油~叫小明(去撈資料)出去買，媽媽繼續炒菜
    //週期性執行
}
