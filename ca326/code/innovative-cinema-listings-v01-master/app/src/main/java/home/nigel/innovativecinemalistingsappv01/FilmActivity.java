package home.nigel.innovativecinemalistingsappv01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FilmActivity extends AppCompatActivity {

    ImageView imageView;

    String timestamp;
    String year;
    String month;
    String day;
    String [] times_array;
    String[] str = {"January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        Intent intentFrom = getIntent();
        TextView tView1 = findViewById(R.id.textView1); //Name
        TextView tView2 = findViewById(R.id.textView2); //Release Date

        String image_url = intentFrom.getStringExtra("FILM_IMAGE_URL");
        imageView = findViewById(R.id.imageView3);
        loadImageFromUrl(image_url);


        TextView tView4 = findViewById(R.id.textView4); //genre
        TextView tView6 = findViewById(R.id.textView6); //Directors
        TextView tView7 = findViewById(R.id.textView7); //Cast
        TextView tView8 = findViewById(R.id.textView8); //Description
        TextView tView3 = findViewById(R.id.textView3); //Times

        TextView tView9 = findViewById(R.id.textView9);

        tView1.setText(intentFrom.getStringExtra("FILM_NAME"));
        tView3.append("\n");
        times_array = intentFrom.getStringExtra("FILM_TIMES").split(",");


        Date currentTime = Calendar.getInstance().getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");              // Display time
        String formatted_current_time = sdf.format(currentTime);
        String [] array_current = formatted_current_time.split(":");
        int current_hour = Integer.parseInt(array_current[0]);
        int current_min = Integer.parseInt(array_current[1]);
        int current_minutes = current_min + (current_hour*60);

        for(int j = 0;j < times_array.length;j++)                                  // Function to find the nearest time
        {
            try {
                String [] array_next = times_array[j].split(":");
                int next_hour = Integer.parseInt(array_next[0]);
                int next_min = Integer.parseInt(array_next[1]);
                int next_minutes = next_min + (next_hour*60);
                if (next_minutes > current_minutes)
                {

                    tView9.setText( "  The Next Showing starts at: " + times_array[j] +".");

                    if(next_minutes<current_minutes+30)
                    {
                        tView9.append("\n\n" + "  Don't Be Late!\n" + " Next Showing starts in " + (next_minutes-current_minutes)+" minutes.");
                    }
                    //
                    break;
                }
            }
            catch(NumberFormatException e){e.printStackTrace();}
        }

        for(int i = 0;i < times_array.length;i++)           //Add all times available on given day
        {

            if(i%3==0)
            {

                tView3.append("\n");
                tView3.append(times_array[i] + "\t\t");
            }
            else tView3.append(times_array[i] + "\t\t");
        }

        timestamp = intentFrom.getStringExtra("FILM_RELEASE_DATE");         // Display release date
        year = timestamp.substring(0,4);
        month = str[Integer.parseInt(timestamp.substring(4,6))];
        day = timestamp.substring(6,8);
        tView2.append(month + "-" + day + " " + year);
        tView4.append("\n" + intentFrom.getStringExtra("FILM_GENRE"));

        TextView tViewIMDB = findViewById(R.id.textViewIMDB);
        TextView tViewMPAA = findViewById(R.id.textViewMPAA);
        TextView tViewRUN = findViewById(R.id.textViewRUN);

        tViewIMDB.append(intentFrom.getStringExtra("FILM_USER_SCORE"));
        tViewMPAA.append(intentFrom.getStringExtra("FILM_AGE"));
        tViewRUN.append(intentFrom.getStringExtra("FILM_RUNTIME"));



        tView6.append(intentFrom.getStringExtra("FILM_DIRECTOR"));
        String [] cast_array =  intentFrom.getStringExtra("FILM_CAST").split("/");
        for(int j = 0; j < cast_array.length;j++)
        {
            if(j%2==0)
            {
                tView7.append("\n");
                tView7.append(cast_array[j] + "\t\t");
            }
            else tView7.append("\t\t" +cast_array[j]);
        }
        tView8.append("\n" + intentFrom.getStringExtra("FILM_DESCRIPTION"));
    }
    private void loadImageFromUrl(String url) {         // Picasso library to download and display an image URL in ImageView
        Picasso.with(this).load(url)
                .into(imageView,new com.squareup.picasso.Callback()
                {
                   @Override
                   public void onSuccess(){}
                   @Override
                   public void onError(){}
                });

    }

}

