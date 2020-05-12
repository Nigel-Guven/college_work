package home.nigel.innovativecinemalistingsappv01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;



public class CinemaActivity extends AppCompatActivity
{
    // Film data
    ArrayList<String> film_image_links = new ArrayList<>();
    ArrayList<String> film_name = new ArrayList<>();
    ArrayList<String> film_user_rating_imdb = new ArrayList<>();
    ArrayList<String> film_age_rating = new ArrayList<>();
    ArrayList<String> film_runtime = new ArrayList<>();
    ArrayList<String> film_genre = new ArrayList<>();
    ArrayList<String> filmNameData = new ArrayList<>();
    ArrayList<String> filmTimeData = new ArrayList<>();
    ArrayList<FilmIdentity> film_identity_list = new ArrayList<>();
    ArrayList<String> films_Capitalized = new ArrayList<>();
    ArrayList<String> film_description = new ArrayList<>();
    ArrayList<String> film_directors = new ArrayList<>();
    ArrayList<String> film_cast = new ArrayList<>();
    ArrayList<String> film_release_date = new ArrayList<>();

    Bundle args;
    Bundle args2;
    @SuppressWarnings("unchecked")      //Suppress warning errors to do with Serializables
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);
        Intent intent = getIntent();
        args = intent.getBundleExtra("FILM_PACKAGE");
        args2 = intent.getBundleExtra("FILM_TIME_PACKAGE");
        film_image_links = (ArrayList<String>) args.getSerializable("FILM_IMAGE_LINK_LIST");    //Transfer bundle data into arraylists
        film_name =  (ArrayList<String>) args.getSerializable("FILM_NAME_LIST");
        film_user_rating_imdb =  (ArrayList<String>) args.getSerializable("FILM_SCORE_LIST");
        film_age_rating =  (ArrayList<String>) args.getSerializable("FILM_AGE_RATING_LIST");
        film_runtime =  (ArrayList<String>) args.getSerializable("FILM_RUNTIME_LIST");
        film_genre =  (ArrayList<String>) args.getSerializable("FILM_GENRE_LIST");
        film_description =(ArrayList<String>) args.getSerializable("FILM_DESCRIPTION_LIST");
        film_directors =(ArrayList<String>) args.getSerializable("FILM_DIRECTOR_LIST");
        film_cast =(ArrayList<String>) args.getSerializable("FILM_CAST_LIST");
        film_release_date = (ArrayList<String>) args.getSerializable("FILM_RELEASE_DATE");

        filmNameData = (ArrayList<String>) args2.getSerializable("FILM_ID_LIST");
        filmTimeData = (ArrayList<String>) args2.getSerializable("FILM_TIME_LIST");

        for( String word : film_name ){
                films_Capitalized.add(word.toUpperCase());

        }

        if(film_age_rating.size()< film_name.size())
        {
            while(film_age_rating.size()!= film_name.size())
            {
               film_age_rating.add("12A");
            }
        }
        if(film_runtime.size()<film_name.size())
        {
           while(film_runtime.size()!= film_name.size())
           {
               film_runtime.add("125");
           }
        }

        ListView listView = findViewById(R.id.listView1);
        TextView tView1 = findViewById(R.id.textView1);
        tView1.setText(intent.getStringExtra("CINEMA_NAME"));

        for(int i = 0; i < film_name.size();i++)
        {
            for(int j = 0;j < filmNameData.size();j++)

                if(film_name.get(i).toUpperCase().contains(filmNameData.get(j).toUpperCase()))
                {
                    FilmIdentity film_identity = new FilmIdentity(film_name.get(i), film_genre.get(i), "IMDB: " + film_user_rating_imdb.get(i) + "/10" + " | MPAA:" + film_age_rating.get(i) + " | " + film_runtime.get(i) + " mins");
                    film_identity_list.add(film_identity);
                    break;
                }
        }

        FilmListAdapter filmlistadapter = new FilmListAdapter(this, R.layout.listview_custom,film_identity_list);   //Display film data in list view
        listView.setAdapter(filmlistadapter);

        //Item onClick Listener to view film information
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {

                FilmIdentity film = film_identity_list.get(position);
                String FILMNAME = film.getName().toUpperCase();
                int pos = films_Capitalized.indexOf(FILMNAME);

                String this_film = film_name.get(pos);
                String release_date = film_release_date.get(pos);
                String genre = film_genre.get(pos);
                String user_rating = film_user_rating_imdb.get(pos);
                String age_rating = film_age_rating.get(pos);
                String runtime = film_runtime.get(pos);
                String image_link = film_image_links.get(pos);
                String film_times = filmTimeData.get(position);
                String description = film_description.get(pos);
                String director = film_directors.get(pos);
                String cast = film_cast.get(pos);
                Intent intentToFilm = new Intent(CinemaActivity.this,FilmActivity.class);

                intentToFilm.putExtra("FILM_NAME",this_film);
                intentToFilm.putExtra("FILM_IMAGE_URL",image_link);
                intentToFilm.putExtra("FILM_USER_SCORE",user_rating);
                intentToFilm.putExtra("FILM_AGE", age_rating);
                intentToFilm.putExtra("FILM_RUNTIME",runtime);
                intentToFilm.putExtra("FILM_RELEASE_DATE",release_date);
                intentToFilm.putExtra("FILM_GENRE",genre);
                intentToFilm.putExtra("FILM_DESCRIPTION", description);
                intentToFilm.putExtra("FILM_DIRECTOR",director);
                intentToFilm.putExtra("FILM_CAST", cast);
                intentToFilm.putExtra("FILM_TIMES",film_times);

                startActivity(intentToFilm);
            }
        });
    }
}
