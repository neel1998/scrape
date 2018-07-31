package com.example.game_new.scrape;

import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    TextView text;
    ListView list;
    EditText etext;
    ImageView im;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        text=(TextView)findViewById(R.id.text);
        list=(ListView) findViewById(R.id.list);
        etext=(EditText)findViewById(R.id.etext);
        im=(ImageView)findViewById(R.id.img);
        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Title().execute();
            }
        });
    }
    private class Title extends AsyncTask<Void,Void,Void>{
        StringBuilder builder1=new StringBuilder();
//        StringBuilder builder2=new StringBuilder();
        String title;
        Element table;
        String name;
        Element image;
        String imgsrc;
        ArrayList<data> arrayList=new ArrayList<>();
        DataAdapter dataAdapter;
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                name=etext.getText().toString().replace(" ","_");
                Document doc=Jsoup.connect("https://en.wikipedia.org/wiki/"+name).get();
                Log.d("",""+doc.toString());
                title=doc.title();
                Element body=doc.body();
                table=body.getElementsByTag("table").first();
                image=table.getElementsByTag("a").first();
                imgsrc=image.getElementsByTag("img").attr("src");
                Elements rows=table.getElementsByTag("tr");
                for(Element row : rows)
                {
                    arrayList.add(new data(row.getElementsByTag("th").text(),row.getElementsByTag("td").text()));
//                    builder1.append(row.getElementsByTag("th").text()+":\t\t"+row.getElementsByTag("td").text()+"\n");
                }
//                builder.append("\n\n"+imgsrc);
                dataAdapter=new DataAdapter(MainActivity.this,arrayList);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void avoid){
//            text.setText(title+"\n\n"+builder1.toString());
            list.setAdapter(dataAdapter);
            Picasso.with(MainActivity.this).load("https:"+imgsrc).into(im);
            super.onPostExecute(avoid);
        }
    }
}
