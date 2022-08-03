package com.example.android_external_storage_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText edtFileName, edtDataValues;
    Button btnSave, btnRead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtFileName = (EditText) findViewById(R.id.edtFileName);
        edtDataValues = (EditText) findViewById(R.id.edtDataValues);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnRead = (Button) findViewById(R.id.btnRead);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fileName = edtFileName.getText().toString();
                String data = edtDataValues.getText().toString();

                FileOutputStream fos;

                try{
                    File path = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DOCUMENTS);

                    File myFile = new File(path,"/" +fileName);
                    myFile.createNewFile();

                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutStream = new OutputStreamWriter(fOut);
                    myOutStream.append(data);
                    myOutStream.close();
                    fOut.close();

                    Toast.makeText(MainActivity.this, "Saved To External Storage", Toast.LENGTH_LONG).show();
                }
                catch(FileNotFoundException e){
                    e.printStackTrace();
                }
                catch(IOException e){
                    e.printStackTrace();
                }

            }
        });


        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fileName = edtFileName.getText().toString();
                StringBuffer stringBuffer = new StringBuffer();
                String dataRow = " ";
                String buffer = " ";

                try{
                    File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                    File MyFile = new File(path,"/"+fileName);
                    FileInputStream fIn = new FileInputStream(MyFile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));

                    while((dataRow = myReader.readLine()) != null){

                        buffer += dataRow + "\n";

                    }
                    myReader.close();
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(MainActivity.this, buffer, Toast.LENGTH_LONG).show();
                edtDataValues.setText(buffer);
            }
        });
    }
}