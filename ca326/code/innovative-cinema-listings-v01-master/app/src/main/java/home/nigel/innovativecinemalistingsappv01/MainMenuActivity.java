package home.nigel.innovativecinemalistingsappv01;



import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

    Button cinemamapButton;
    Button helpButton;

    ArrayList<String> film_image_links = new ArrayList<>();
    ArrayList<String> film_name = new ArrayList<>();
    ArrayList<String> film_user_rating_imdb = new ArrayList<>();
    ArrayList<String> film_age_rating = new ArrayList<>();
    ArrayList<String> film_runtime = new ArrayList<>();
    ArrayList<String> film_genre = new ArrayList<>();
    ArrayList<String> film_release_date = new ArrayList<>();
    ArrayList<String> film_description = new ArrayList<>();
    ArrayList<String> film_cast = new ArrayList<>();
    ArrayList<String> film_directors = new ArrayList<>();
    Document doc = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        cinemamapButton = findViewById(R.id.buttonToMap);
        cinemamapButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                new Thread(new Runnable()
                {

                    @Override
                    public void run()
                    {
                        try {

                            doc = Jsoup.connect("https://www.imdb.com/showtimes/location?ref_=inth_shp").get();     // System.out.println();

                            String imdb_soup = doc.select("[class=lister-item mode-grid]").toString();        // Select information by CSS class

                            imdb_soup = imdb_soup.replaceAll("<div>", "");                          // clean HTML
                            imdb_soup = imdb_soup.replaceAll("</div>", "");
                            imdb_soup = imdb_soup.replaceAll("<p>", "");
                            imdb_soup = imdb_soup.replaceAll("</p>", "");
                            imdb_soup = imdb_soup.replaceAll("&nbsp;", "");

                            imdb_soup = imdb_soup.replaceAll("", "");


                            String[] film_array = imdb_soup.split("  ");
                            for (int index = 0; index < film_array.length; index++) {
                                if (film_array[index].contains("alpha")) {

                                    film_array[index] = film_array[index].replaceAll("\"></span>", "");
                                    film_array[index] = film_array[index].replaceAll("<span name=\"alpha\" data-value=\"", "");
                                    film_array[index] = Jsoup.clean(film_array[index], Whitelist.none());
                                    film_array[index] = film_array[index].replaceAll("&amp;", "&");
                                    film_array[index] = film_array[index].trim();
                                    film_name.add(film_array[index]);

                                } else if (film_array[index].contains("name=\"user_rating\"")) {
                                    film_array[index] = film_array[index].replaceAll("\"></span>", "");
                                    film_array[index] = film_array[index].replaceAll("<span name=\"user_rating\" data-value=\"", "");
                                    film_array[index] = Jsoup.clean(film_array[index], Whitelist.none());
                                    film_user_rating_imdb.add(film_array[index]);
                                } else if (film_array[index].contains("name=\"release_date")) {
                                    film_array[index] = film_array[index].replaceAll("\"></span>", "");
                                    film_array[index] = film_array[index].replaceAll("<span name=\"release_date\" data-value=\"", "");
                                    film_array[index] = Jsoup.clean(film_array[index], Whitelist.none());
                                    film_release_date.add(film_array[index]);
                                } else if (film_array[index].contains("text-muted text-small")) {
                                    film_array[index] = film_array[index].replaceAll("</span>", "\n");
                                    String[] tmp_array = film_array[index].split("\n");

                                    for (int innerIndex = 0; innerIndex < tmp_array.length; innerIndex++) {


                                        if (tmp_array[innerIndex].contains("<span class=\"certificate\">")) {

                                            tmp_array[innerIndex] = tmp_array[innerIndex].replaceAll("<p class=\"text-muted text-small\"> <span class=\"certificate\">", "");
                                            tmp_array[innerIndex] = Jsoup.clean(tmp_array[innerIndex], Whitelist.none());
                                            film_age_rating.add(tmp_array[innerIndex]);
                                        } else if (tmp_array[innerIndex].contains("<span class=\"runtime\">")) {

                                            tmp_array[innerIndex] = tmp_array[innerIndex].replaceAll(" <span class=\"runtime\">", "");
                                            tmp_array[innerIndex] = tmp_array[innerIndex].replaceAll(" min", "");
                                            tmp_array[innerIndex] = Jsoup.clean(tmp_array[innerIndex], Whitelist.none());
                                            film_runtime.add(tmp_array[innerIndex]);
                                        } else if (tmp_array[innerIndex].contains("<span class=\"genre\">")) {

                                            tmp_array[innerIndex] = tmp_array[innerIndex].replaceAll(" <span class=\"genre\">", "");
                                            tmp_array[innerIndex] = tmp_array[innerIndex].replaceAll(", ", " | ");
                                            tmp_array[innerIndex] = Jsoup.clean(tmp_array[innerIndex], Whitelist.none());
                                            film_genre.add(tmp_array[innerIndex]);
                                        } else if (tmp_array[innerIndex].contains("Director")) {
                                            tmp_array[innerIndex] = Jsoup.clean(tmp_array[innerIndex], Whitelist.none());
                                            tmp_array[innerIndex] = tmp_array[innerIndex].replaceAll(" \\|", "");
                                            tmp_array[innerIndex] = tmp_array[innerIndex].replaceAll("Director: ", "");
                                            film_directors.add(tmp_array[innerIndex]);
                                        } else if (tmp_array[innerIndex].contains("Stars")) {
                                            tmp_array[innerIndex] = Jsoup.clean(tmp_array[innerIndex], Whitelist.none());
                                            tmp_array[innerIndex] = tmp_array[innerIndex].replaceAll("Stars: ", "");
                                            tmp_array[innerIndex] = tmp_array[innerIndex].replaceAll(", ", "/");
                                            film_cast.add(tmp_array[innerIndex]);
                                        }


                                    }
                                } else if (film_array[index].contains("<p class=\"\">")) {
                                    film_array[index] = film_array[index].replaceAll("<p class=\"\">", "");
                                    film_array[index] = Jsoup.clean(film_array[index], Whitelist.none());
                                    film_description.add(film_array[index]);
                                }
                            }
                            Elements page_urls = doc.getElementsByTag("img");
                            List<Element> image_urls = page_urls.subList(4, page_urls.size() - 1);
                            for (int i = 0; i < image_urls.size(); i++) {
                                Element item = image_urls.get(i);
                                String url_link = item.absUrl("loadlate");

                                film_image_links.add(url_link);
                            }
                            Intent intentToMaps = new Intent(MainMenuActivity.this,MapsActivity.class);
                            Bundle args = new Bundle();

                            args.putSerializable("FILM_NAME_LIST", film_name);                      //List of arrays of data to send to following activities
                            args.putSerializable("FILM_IMAGE_LINK_LIST", film_image_links);
                            args.putSerializable("FILM_SCORE_LIST", film_user_rating_imdb);
                            args.putSerializable("FILM_AGE_RATING_LIST", film_age_rating);
                            args.putSerializable("FILM_RUNTIME_LIST", film_runtime);
                            args.putSerializable("FILM_RELEASE_DATE", film_release_date);
                            args.putSerializable("FILM_GENRE_LIST", film_genre);
                            args.putSerializable("FILM_DESCRIPTION_LIST", film_description);
                            args.putSerializable("FILM_DIRECTOR_LIST", film_directors);
                            args.putSerializable("FILM_CAST_LIST", film_cast);
                            intentToMaps.putExtra("FILM_BUNDLE", args);
                            startActivity(intentToMaps);

                        }
                        catch (IOException e) {
                            e.printStackTrace();
                            Looper.prepare();
                            Toast.makeText(MainMenuActivity.this,"This app requires an active Internet connection. Please enable Wifi in Settings. ",Toast.LENGTH_LONG).show();

                        }
                        catch(NullPointerException f) {
                            f.printStackTrace();
                            Looper.prepare();
                            Toast.makeText(MainMenuActivity.this,"This app requires an active Internet connection. Please enable Wifi in Settings. ",Toast.LENGTH_LONG).show();
                        }

                    }
                }).start();

            }
        });

        helpButton = findViewById(R.id.buttonToHelp);
        helpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentToHelp = new Intent(MainMenuActivity.this,HelpActivity.class);
                startActivity(intentToHelp);
            }
        });
    }
}
