package julienrc.permissionviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    public static String PERMISSION = "";

    private int STORAGE_PERMISSION_CODE = 1;
    private int READ_SMS_CODE = 2;
    private int READ_CALL_LOG = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    ////////////////////////////////////////////////////////////////////////
    ////////////////////// READ EXTERNAL STORAGE ///////////////////////////
    ////////////////////////////////////////////////////////////////////////

    /**
     * Function call when the button READ EXTERNAL STORAGE will be push
     * @param view View
     */
    public void read_external_storage(View view){
        // The permission is already granted
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            //Toast.makeText(MainActivity.this, "You already granted this permission !", Toast.LENGTH_SHORT).show();
            PERMISSION = "READ_EXTERNAL_STORAGE";
            changePage(view);
        } else {
            // We ask for the permission
            request_read_external_storage();
        }
    }

    private void request_read_external_storage(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed !")
                    .setMessage("This permission is needed because it's the goal of this application")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create()
                    .show();
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    ////////////////////////////////////////////////////////////////////////
    ///////////////////////////// READ SMS /////////////////////////////////
    ////////////////////////////////////////////////////////////////////////

    /**
     * Function call when the button READ SMS will be push
     * @param view View
     */
    public void read_sms(View view){
        // The permission is already granted
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED){
            //Toast.makeText(MainActivity.this, "You already granted this permission !", Toast.LENGTH_SHORT).show();

            PERMISSION = "READ_SMS";
            changePage(view);
        } else {
            // We ask for the permission
            request_read_sms();
        }

    }

    private void request_read_sms(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed !")
                    .setMessage("This permission is needed because it's the goal of this application")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.READ_SMS}, READ_SMS_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create()
                    .show();
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_SMS}, READ_SMS_CODE);
        }
    }

    ////////////////////////////////////////////////////////////////////////
    ///////////////////////////// READ PHONE CALL///////////////////////////
    ////////////////////////////////////////////////////////////////////////

    /**
     * Function call when the button READ CALL LOG will be push
     * @param view View
     */
    public void read_phone_log(View view){
        // The permission is already granted
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED){
            //Toast.makeText(MainActivity.this, "You already granted this permission !", Toast.LENGTH_SHORT).show();

            PERMISSION = "READ_CALL_LOG";
            changePage(view);
        } else {
            // We ask for the permission
            request_phone_log();
        }

    }

    private void request_phone_log(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CALL_LOG)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed !")
                    .setMessage("This permission is needed because it's the goal of this application")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.READ_CALL_LOG}, READ_CALL_LOG);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create()
                    .show();
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CALL_LOG}, READ_CALL_LOG);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == STORAGE_PERMISSION_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
            }
        } else if(requestCode == READ_SMS_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
            }
        } else if(requestCode == READ_CALL_LOG) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void changePage(View view){
        Intent intent = new Intent(this, PermissionActivity.class);

        intent.putExtra(PERMISSION, PERMISSION);
        startActivity(intent);
    }
}