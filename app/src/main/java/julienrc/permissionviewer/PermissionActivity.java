package julienrc.permissionviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;

import android.net.Uri;

import android.os.Bundle;
import android.os.Environment;

import android.widget.TableLayout;
import android.widget.TextView;

import java.io.File;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class PermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        switch(MainActivity.PERMISSION){
            case "READ_EXTERNAL_STORAGE":
                readExternalStorage();
                break;
            case "READ_SMS":
                readSms();
                break;
            case "READ_CALL_LOG":
                readCall();
                break;
            default:
                break;
        }
    }

    /**
     * Method to read file from the root
     */
    private void readExternalStorage() {
        String path = Environment.getExternalStorageDirectory().toString()+"/";
        File directory = new File(path);
        File[] files = directory.listFiles();

        TableLayout tableLayout = findViewById((R.id.table_layout));

        if(files != null){
            for (File file : files) {
                TextView textView = new TextView(this);
                textView.setText(file.getName());
                tableLayout.addView(textView);

            }
        }

    }

    /**
     * Method to read every SMS received
     */
    private void readSms(){
        Uri uriSms = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"},null,null,null);

        TableLayout tableLayout = findViewById((R.id.table_layout));

        if(cursor != null){
            cursor.moveToFirst();
            while  (cursor.moveToNext())
            {
                String address = cursor.getString(1);

                long milliSeconds = cursor.getLong(2);
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(milliSeconds);
                String finalDateString = formatter.format(calendar.getTime());

                String body = cursor.getString(3);

                TextView textView = new TextView(this);
                textView.setText("Date: " + finalDateString + "\nSource: " + address + "\nContent: " + body + "\n");
                tableLayout.addView(textView);
            }
            cursor.close();
        }


    }

    /**
     * Method to read every call log
     */
    private void readCall(){
        Uri uriCall = Uri.parse("content://call_log/calls");
        Cursor cursor = getContentResolver().query(uriCall, new String[]{"_id", "date", "duration", "number", "type"}, null, null, null);

        TableLayout tableLayout = findViewById((R.id.table_layout));

        if(cursor != null){
            cursor.moveToFirst();
            while  (cursor.moveToNext())
            {
                long milliSeconds = cursor.getLong(1);
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(milliSeconds);
                String finalDateString = formatter.format(calendar.getTime());

                long duration = cursor.getLong(2);
                long hours = duration / 3600;
                long minutes = (duration % 3600) / 60;
                long seconds = duration % 60;
                String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);

                String number = cursor.getString(3);

                TextView textView = new TextView(this);
                textView.setText("Date: " + finalDateString + "\nNumber: " + number + "\nDuration: " + timeString + "\n");
                tableLayout.addView(textView);
            }
            cursor.close();
        }

    }


}