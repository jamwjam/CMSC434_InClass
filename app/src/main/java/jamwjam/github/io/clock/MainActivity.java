package jamwjam.github.io.clock;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {

    private int _counter = 0;
    private Timer _timerCount = new Timer();

    private Handler _uiHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onButtonHelloWorld(View view) {
        TextView textView = (TextView)findViewById(R.id.textView_helloWorld);

        if(textView.getText().equals("Hello world!")){
            textView.setText("Goodbye, cruel world!");
        } else {
            textView.setText("Hello world!");
        }
    }

    public void onButtonStartTimer(View view) {
        view.setEnabled(false);

        // Anonymous classes
        _timerCount.schedule(new TimerTask() {

            @Override
            public void run() {
                _counter++;

                // Use the handler to marshal/invoke the Runnable code on the UI thread
                _uiHandler.post(new Runnable(){
                    @Override
                    public void run(){
                        TextView textView = (TextView)findViewById(R.id.textView_counter);
                        textView.setText(_counter + "");
                    }
                });
            }
        }, 0, 1000);
    }
}
