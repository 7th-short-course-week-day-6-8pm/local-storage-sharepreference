package com.rathana.local_storage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class FileActivity extends AppCompatActivity {

    TextView tvResult;
    Button btnSaveFile,btnReadFile,btnRemove;

    static final String fileName="file1.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        tvResult=findViewById(R.id.tvResult);
        btnSaveFile=findViewById(R.id.btnWriteFile);
        btnReadFile=findViewById(R.id.btnReadFile);
        btnRemove=findViewById(R.id.btnRemoveFile);

        btnSaveFile.setOnClickListener(v->{
            //write to file
            saveFile();

        });

        btnReadFile.setOnClickListener(v->{
            readFile();
        });

        btnRemove.setOnClickListener(v->{
            removeFile();
        });
    }

    public void saveFile(){
        //OutputStream outputStream=null;
        String text="write data to file with openOutStream object";
        try(OutputStream outputStream = openFileOutput(fileName, Context.MODE_PRIVATE)) {

            outputStream.write(text.getBytes());
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void readFile(){
        try (InputStream inputStream = openFileInput(fileName)){
            String text="";
            int ch;
            while ( (ch=inputStream.read())!= -1 ){
                text=text+ (char) ch ;
            }

            tvResult.setText(text);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeFile(){
        File file = new File(getFilesDir(),fileName);
        if(file.exists()){
            file.delete();
            Toast.makeText(this, "removed", Toast.LENGTH_SHORT).show();
        }
    }


}
