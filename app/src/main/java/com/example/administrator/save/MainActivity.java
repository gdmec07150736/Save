package com.example.administrator.save;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button) this.findViewById(R.id.button1);
        Button b2 = (Button) this.findViewById(R.id.button2);
        Button b3 = (Button) this.findViewById(R.id.button3);
        Button b4 = (Button) this.findViewById(R.id.button4);
        View.OnClickListener listen = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button1:
                        romread("a");
                        break;
                    case R.id.button2:
                        romwrite("a");
                        break;
                    case R.id.button3:
                        sdread("a");
                        break;
                    case R.id.button4:
                        //sdwrite();
                        break;
                }
            }
        };
    }
    public void romread(String file){
        try {
            FileInputStream in=openFileInput(file);
            InputStreamReader inread=new InputStreamReader(in);
            BufferedReader bread=new BufferedReader(inread);
            String s;
            while((s=bread.readLine())!=null){
                Toast.makeText(this,s,Toast.LENGTH_LONG).show();
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void romwrite(String file){
        String s="test";
        s=s+"\n"+"over";
        try {
            FileOutputStream out=openFileOutput(file,0);
            OutputStreamWriter owrite=new OutputStreamWriter(out);
            BufferedWriter bwrite=new BufferedWriter(owrite);
            bwrite.write(s);
            bwrite.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sdread(String file){
        String spath=android.os.Environment.getExternalStorageDirectory().getPath();
        String path=spath+"/"+file;
        Toast.makeText(this,path,Toast.LENGTH_SHORT).show();
        File f=new File(path);
        int length=(int)file.length();
        byte[] b=new byte[length];
        try {
            FileInputStream in=new FileInputStream(f);
            in.read(b,0,length);
            String data="";
            for(byte element:b){
                data+=element;
            }
            Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sdwrite(String file,byte data){
        String path=android.os.Environment.getExternalStorageDirectory().getPath()+"/"+file;
        Toast.makeText(this,path,Toast.LENGTH_SHORT).show();
        File f=new File(path);
        try {
            FileOutputStream out=new FileOutputStream(f);
            out.write(data);
            out.flush();
            out.close();
        } catch (Exception e) {
            Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
