package tw.org.iii.iiiandroid006;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.strictmode.UnbufferedIoViolation;
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
    private ListView lapList;
    private boolean isRunning;
    private Button btnLeft,btnRight;
    private Timer timer;
    private int i;
    private UIHandler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clock = findViewById(R.id.colok);
        lapList = findViewById(R.id.lapList);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);

        btnLeft.setText(isRunning?"lap":"Reset");
        btnRight.setText(isRunning?"Stop":"Start");

        Log.v("brad","time");
        uiHandler= new UIHandler();
        timer= new Timer();
        timer.schedule(new MyTask(),0,10);
        //timer.schedule(new MyTask(),0,10);

    }

    @Override
    public void finish(){
        if(timer!=null){
            timer.cancel();
            timer.purge();
            timer = null;

        }
        super.finish();
    }

    private class MyTask extends TimerTask{
        @Override
        public void run(){
            if (isRunning) {
                i++;
               // Log.v("brad", "i==>" + i);
                uiHandler.sendEmptyMessage(0);
                //clock.setText(""+i);
            }
        }

    }

    private class UIHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg){
         super.handleMessage(msg);
         clock.setText(toClockString());
        }


    }

    private  String toClockString(){
        int hs = i % 100;  //
        int ts = i/100;   //總秒數
        int hh = ts/(60*60);  //小時
        int mm = (ts-hh*60*60)/60;//分鐘
        int ss = ts%60;//秒
        return hh+":"+mm+":"+ss+"."+hs;

    }

    public void clickLeft(View view){
        //測試練習 看不到但在背景中執行
        //電腦背景要看前景的臉色過活
        //安卓 不一定

        if (isRunning){
            //Lap
            doLap();

        }
        else{//Reset
            doReset();

        }
    }
    //方法 暫停 重置
    private void doLap(){
//在ListView 顯示數字
        //snackerbar(可與user互動) 與Toast(通知用)不同


    }
    private void doReset(){
        i=0;
        uiHandler.sendEmptyMessage(0);

    }

//改變狀態會改變內容  不能只是按鈕觸發
    public void clickRight(View view) {
        isRunning =!isRunning;
        btnLeft.setText(isRunning?"lap":"Reset");
        btnRight.setText(isRunning?"Stop":"Start");



    }
    //小明媽媽炒菜，沒有醬油~叫小明(去撈資料)出去買，媽媽繼續炒菜
    //週期性執行
}

