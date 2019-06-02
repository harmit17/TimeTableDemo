package com.example.timetabledemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIview();
        initToolbar();
        setUpListview();
    }

    private void setupUIview(){

        toolbar = (Toolbar)findViewById(R.id.toolbarname);
        listView = (ListView)findViewById(R.id.lvmain);
    }

    private void initToolbar(){

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Timetable App");
    }

    private void setUpListview(){

        String[] title = getResources().getStringArray(R.array.Main);
        String[] description = getResources().getStringArray(R.array.Description);

        SimpleAdaptor simpleAdaptor = new SimpleAdaptor(this, title, description);
        listView.setAdapter(simpleAdaptor);

    }

    public class SimpleAdaptor extends BaseAdapter{

        private Context context;
        private LayoutInflater layoutInflater;
        private TextView title, description;
        private String[] titleArray;
        private String[] descriptionArray;
        private ImageView imageView;

        public SimpleAdaptor(Context context, String[] title, String[] description){

            context = context;
            titleArray = title;
            descriptionArray =description;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int i) {
            return titleArray[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override                                               //we have main_activity.xml how can we access main_activity_single_item.xml
        public View getView(int i, View view, ViewGroup viewGroup) {    //handles activity from activity_main.xml to main_activity_single_item.xml

            if(view == null)
                view = layoutInflater.inflate(R.layout.main_activity_single_item, null);

            title = (TextView)view.findViewById(R.id.tvmain);           //tvmain and tvDescription are in main_activity_single_item.xml
            description = (TextView)view.findViewById(R.id.tvDescription);
            imageView = (ImageView)view.findViewById(R.id.ivmain);

            title.setText(titleArray[i]);
            description.setText(descriptionArray[i]);

            if(titleArray[i].equalsIgnoreCase("Timetable")){    //check title if it is Timetable then set image to timetable
                imageView.setImageResource(R.drawable.timetable);
            }
            else if(titleArray[i].equalsIgnoreCase("Subjects")){
                imageView.setImageResource(R.drawable.book);
            }
            else if(titleArray[i].equalsIgnoreCase("Faculty")){
                imageView.setImageResource(R.drawable.contact);
            }
            else if(titleArray[i].equalsIgnoreCase("Resources")){
                imageView.setImageResource(R.drawable.setting);
            }
            return view;
        }
    }

}
