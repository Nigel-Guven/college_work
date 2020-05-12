package home.nigel.innovativecinemalistingsappv01;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;

import com.google.android.gms.maps.GoogleMap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowLongClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnInfoWindowClickListener, OnInfoWindowLongClickListener
{
    private GoogleMap mMap;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    ZoomControls zoom;
    double mylatitude;
    double mylongitude;
    Location location;
    Button satView;
    Button clear;
    LatLng coordinate;
    Document doc = null;
    Intent intent;
    Bundle args;
    //List of Irish cinemas IE/NI based on XML and Google Data
    ArrayList<String> cinema_names = new ArrayList<>(
            Arrays.asList("IMC Ballymena"
                    , "Movie House City Side Belfast"
                    , "Movie House Dublin Road"
                    , "Movie House Glengormley"
                    , "ODEON Belfast"
                    , "Odyssey Cinema Belfast"
                    , "Omniplex Antrim"
                    , "Omniplex Belfast Kennedy Centre"
                    , "Omniplex Carrickfergus"
                    , "Omniplex Dundonald"
                    , "Omniplex Larne"
                    , "Omniplex Lisburn"
                    , "Queens Film Theatre"
                    , "Strand Arts Centre Belfast"
                    , "Omniplex Armagh"
                    , "Omniplex Craigavon"
                    , "IMC Carlow"
                    , "Omniplex Carlow"
                    , "ODEON Cavan Town"
                    , "Eclipse Cinemas Lifford/Strabane"
                    , "Empire Movieplex"
                    , "Bantry Cinemax"
                    , "Cinema World Douglas"
                    , "Gate Multiplex Cork"
                    , "Gate Multiplex Mallow"
                    , "Gate Multiplex Midleton"
                    , "Omniplex Cork Mahon Point Shopping Centre"
                    , "Park Cinema Clonakilty"
                    , "Regal Cinema"
                    , "The Reel Picture Ballincollig"
                    , "The Reel Picture Blackpool"
                    , "Triskel Christchurch Arts Centre"
                    , "Brunswick Moviebowl"
                    , "Movie House Coleraine"
                    , "Movie House Maghera"
                    , "Omniplex Derry Quayside Shopping Centre"
                    , "Century Cinemas Letterkenny"
                    , "Eclipse Cinemas Bundoran"
                    , "IMC Banbridge"
                    , "IMC Newtownards"
                    , "Omniplex Banbridge"
                    , "Omniplex Bangor"
                    , "Omniplex Downpatrick"
                    , "Omniplex Newry"
                    , "Cineworld Dublin"
                    , "IFI Irish Film Institute"
                    , "IMC Dun Laoghaoire"
                    , "IMC Santry"
                    , "IMC Savoy Cinema"
                    , "IMC Tallaght"
                    , "Light House Cinema Dublin"
                    , "Movies at Dundrum"
                    , "Movies at Swords"
                    , "ODEON Blanchardstown"
                    , "ODEON Charlestown"
                    , "ODEON Coolock"
                    , "ODEON Point Square"
                    , "ODEON Stillorgan"
                    , "Omniplex Balbriggan"
                    , "Omniplex Rathmines"
                    , "The Stella Theatre Ranelagh"
                    , "The Stella Theatre Rathmines"
                    , "Vue Liffey Valley"
                    , "IMC Enniskillen"
                    , "Eye Cinema Galway"
                    , "IMC Galway"
                    , "IMC Oranmore"
                    , "Palas"
                    , "Cinema Killarney"
                    , "Listowel Classic Movieplex"
                    , "Omniplex Tralee"
                    , "Phoenix Cinema Dingle"
                    , "ODEON Naas"
                    , "ODEON Newbridge"
                    , "IMC Kilkenny"
                    , "ODEON Portlaoise"
                    , "Carrick Cineplex"
                    , "ODEON Limerick"
                    , "Omniplex Limerick Crescent Shopping Centre"
                    , "Vue Limerick"
                    , "Omniplex Longford"
                    , "IMC Dundalk"
                    , "Omniplex Dundalk"
                    , "The Arc Cinema Drogheda"
                    , "IMC Ballina"
                    , "Mayo Movieworld"
                    , "The W Cinema"
                    , "Vue Ashbourne"
                    , "Omniplex Monaghan"
                    , "IMC Tullamore"
                    , "C and L Plex"
                    , "Omniplex Sligo"
                    , "The Model"
                    , "IMC Clonmel"
                    , "IMC Thurles"
                    , "Ormond Cineplex Nenagh"
                    , "Tipperary Excel Centre"
                    , "IMC Omagh"
                    , "Omniplex Dungannon"
                    , "Omniplex Omagh"
                    , "The Ritz Cookstown"
                    , "ODEON Waterford"
                    , "SGC Dungarvan"
                    , "IMC Athlone"
                    , "IMC Mullingar"
                    , "Movies @ Gorey"
                    , "Omniplex Wexford"
                    , "The Arc Cinema Wexford"
                    , "Omniplex Arklow"
            )
    );
    ArrayList<Double> cinema_longitudes = new ArrayList<>(
            Arrays.asList(
                    -6.2675810000001
                    , -5.9269419
                    , -5.931523
                    , -5.957043
                    , -5.92598590000
                    , -5.9156209000
                    , -6.229731000000015
                    , -5.82334179999998
                    , -5.812584499999957
                    , -5.823341799
                    , -5.810134699999
                    , -6.0516609999
                    , -5.933951200000
                    , -5.879735699999969
                    , -6.653967100000045
                    , -6.397582899999975
                    , -6.924232500000016
                    , -6.927127599999949
                    , -7.358783000000017
                    , -7.476694
                    , -8.984930500000019
                    , -9.455944400000021
                    , -8.435115499999938
                    , -8.479589000000033
                    , -8.640698799999996
                    , -8.179496699999959
                    , -8.394239500000026
                    , -8.894777400000066
                    , -7.845136
                    , -8.588421899999958
                    , -8.47284579999996
                    , -8.475955
                    , -7.320961
                    , -6.678014899999994
                    , -6.673185999999987
                    , -7.321652800000038
                    , -7.735903699999994
                    , -8.274164299999939
                    , -6.2713103999999475
                    , -5.710079700000051
                    , -6.275984900000026
                    , -5.663559200000009
                    , -5.714059099999986
                    , -6.340147000000002
                    , -6.267897800000014
                    , -6.264867900000013
                    , -6.13971319999996
                    , -6.250413299999991
                    , -6.260238
                    , -6.372666900000013
                    , -6.279071400000021
                    , -6.242228800000021
                    , -6.219172999999955
                    , -6.391169300000001
                    , -6.3042242000000215
                    , -6.19288830000005
                    , -6.227728999999954
                    , -6.1982803999999305
                    , -6.183148899999992
                    , -6.2652137999999695
                    , -6.247637
                    , -6.265858900000012
                    , -6.393136
                    , -7.634803099999999
                    , -9.03192260000003
                    , -9.050758900000005
                    , -8.927789299999972
                    , -9.052554
                    , -9.506220600000006
                    , -9.486104
                    , -9.708125099999961
                    , -10.269410
                    , -6.63927000000001
                    , -6.797646999999984
                    , -7.256633299999976
                    , -7.300447599999984
                    , -8.104422900000031
                    , -8.552551300000005
                    , -8.64411999999993
                    , -8.659812200000033
                    , -7.802051399999982
                    , -6.4037739999999985
                    , -6.377359599999977
                    , -6.351947
                    , -9.163788000000068
                    , -9.286900999999943
                    , -9.525000999999975
                    , -6.414173300000016
                    , -7.001164099999983
                    , -7.494434299999966
                    , -8.179701
                    , -8.47781299999997
                    , -8.467839
                    , -7.701640999999995
                    , -7.810047
                    , -8.196578600000066
                    , -8.159528
                    , -7.330187499999965
                    , -6.761641999999938
                    , -7.301115399999958
                    , -6.745960299999979
                    , -7.110941499999967
                    , -7.622323000000051
                    , -7.93286379999995
                    , -7.312527799999998
                    , -6.270864
                    , -6.45608500000003
                    , -6.464715
                    , -6.149025700000038
            )
    );

    ArrayList<Double> cinema_latitudes = new ArrayList<>(
            Arrays.asList(
                    54.861564
                    , 54.6084711
                    , 54.59222
                    , 54.668939
                    , 54.598515
                    , 54.6038086
                    , 54.731948
                    , 54.5887936
                    , 54.7121941
                    , 54.5887936
                    , 54.8471802
                    , 54.507506
                    , 54.585444
                    , 54.60058169999999
                    , 54.34810270000001
                    , 54.450898
                    , 52.8335284
                    , 52.8350146
                    , 53.98972299999999
                    , 54.8342026
                    , 52.8438991
                    , 51.6801572
                    , 51.87831079999999
                    , 51.90093599999999
                    , 52.1377481
                    , 51.91724869999999
                    , 51.8858814
                    , 51.6168054
                    , 51.951721
                    , 51.88703460000001
                    , 51.91698830000001
                    , 51.897486
                    , 55.013054
                    , 55.119561
                    , 54.844043
                    , 55.0001207
                    , 54.9443347
                    , 54.47763089999999
                    , 54.3503295
                    , 54.59083949999999
                    , 54.3310462
                    , 54.65478450000001
                    , 54.3234118
                    , 54.16867500000001
                    , 53.3503541
                    , 53.344646
                    , 53.2934093
                    , 53.39332460000001
                    , 53.351441
                    , 53.2869531
                    , 53.3487928
                    , 53.28807570000001
                    , 53.45506
                    , 53.39247830000001
                    , 53.4043573
                    , 53.3912116
                    , 53.348489
                    , 53.2878885
                    , 53.61031109999999
                    , 53.3237813
                    , 53.324195
                    , 53.32237379999999
                    , 53.352457
                    , 54.3519675
                    , 53.28120800000001
                    , 53.2796514
                    , 53.2784222
                    , 53.270047
                    , 52.057696
                    , 52.449761
                    , 52.26351039999999
                    , 52.139892
                    , 53.2323692
                    , 53.1774866
                    , 52.6497973
                    , 53.03509589999999
                    , 53.9428697
                    , 52.6649258
                    , 52.64015200000001
                    , 52.6681069
                    , 53.73026040000001
                    , 54.0076585
                    , 53.9917327
                    , 53.714164
                    , 54.115904
                    , 53.856665
                    , 53.800297
                    , 53.5213674
                    , 54.2424334
                    , 53.274585
                    , 53.631992
                    , 54.272371
                    , 54.273025
                    , 52.35514800000001
                    , 52.676111
                    , 52.8654867
                    , 52.475051
                    , 54.6112103
                    , 54.51237099999999
                    , 54.5963224
                    , 54.64668950000001
                    , 52.2546386
                    , 52.091016
                    , 53.4204714
                    , 53.53283769999999
                    , 52.671763
                    , 52.31261019999999
                    , 52.343733
                    , 52.79879139999999
            )
    );
    //Irish cinema URLs which contain cinema times data
    ArrayList<String> cinema_urls = new ArrayList<>(
            Arrays.asList(
                    "https://www.rte.ie/entertainment/listings/cinema/venue-imc-ballymena"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-movie-house-city-side-belfast"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-movie-house-dublin-road"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-movie-house-glengormley"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-odeon-belfast"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-odyssey-cinema-belfast"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-antrim"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-belfast-kennedy-centre"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-carrickfergus"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-dundonald"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-larne"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-lisburn"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-queens-film-theatre"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-strand-arts-centre-belfast"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-armagh"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-craigavon"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-carlow"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-carlow"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-odeon-cavan-town"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-eclipse-cinemas-liffordstrabane"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-empire-movieplex"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-bantry-cinemax"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-cinema-world-douglas"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-gate-multiplex-cork"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-gate-multiplex-mallow"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-gate-multiplex-midleton"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-cork-mahon-point-shopping-centre"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-park-cinema-clonakilty"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-regal-cinema"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-the-reel-picture-ballincollig"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-the-reel-picture-blackpool"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-triskel-christchurch-arts-centre"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-brunswick-moviebowl"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-movie-house-coleraine"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-movie-house-maghera"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-derry-quayside-shopping-centre"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-century-cinemas-letterkenny"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-century-cinemas-bundoran"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-banbridge"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-newtownards"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-banbridge"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-bangor"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-downpatrick"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-newry"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-cineworld-dublin"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-ifi-irish-film-institute"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-dun-laoghaire "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-santry "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-savoy-cinema"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-tallaght "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-light-house-cinema-dublin"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-movies-at-dundrum "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-movies-at-swords "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-odeon-blanchardstown"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-odeon-charlestown"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-odeon-coolock "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-odeon-point-square"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-odeon-stillorgan "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-balbriggan"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-rathmines"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-the-stella-theatre-ranelagh "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-the-stella-theatre-rathmines "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-vue-liffey-valley  "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-enniskillen"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-eye-cinema-galway "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-galway"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-oranmore "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-pls"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-cinema-killarney  "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-listowel-classic-movieplex  "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-tralee "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-phoenix-cinema-dingle "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-odeon-naas"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-odeon-newbridge "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-kilkenny "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-odeon-portlaoise "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-carrick-cineplex "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-odeon-limerick"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-limerick-crescent-shopping-centre"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-vue-limerick "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-longford "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-dundalk "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-dundalk "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-the-arc-cinema-drogheda"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-ballina"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-mayo-movieworld"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-the-w-cinema"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-vue-ashbourne"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-monaghan"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-tullamore"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-cl-plex "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-sligo"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-the-model "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-clonmel"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-thurles"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-ormond-cineplex-nenagh"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-tipperary-excel-centre"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-omagh"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-dungannon"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-omagh"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-the-ritz-cookstown"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-odeon-waterford"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-sgc-dungarvan"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-athlone"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-imc-mullingar"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-movies-a-gorey "
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-wexford"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-the-arc-cinema-wexford"
                    , "https://www.rte.ie/entertainment/listings/cinema/venue-omniplex-arklow"
            )
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        intent = getIntent();
        args = intent.getBundleExtra("FILM_BUNDLE");
    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        zoom = findViewById(R.id.zcZoom);               //Zoom button controls
        zoom.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.animateCamera(CameraUpdateFactory.zoomOut());

            }
        });
        zoom.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });

        satView = findViewById(R.id.btSatellite);          //Switch between satellite and default sites
        satView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    satView.setText("DEFAULT");
                } else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    satView.setText("SAT");
                }

            }
        });

        clear = findViewById(R.id.btClear);                 //clear and show all data
        clear.setOnClickListener(new View.OnClickListener()
        {   @Override
            public void onClick(View view)
            {
                if(clear.getText().equals("SHOW"))
                {

                    clear.setText("HIDE");
                    for (int i = 0; i < cinema_urls.size(); i++) {
                        double x = mylatitude;
                        double y = mylongitude;
                        LatLng MY_POSITION = new LatLng(x, y);      //Get My Position//

                        MarkerOptions options = new MarkerOptions();
                        LatLng tmp = new LatLng(cinema_latitudes.get(i), cinema_longitudes.get(i));      ///Create a coordinate from data

                        options.position(tmp);

                        options.title(cinema_names.get(i));
                        options.snippet(cinema_urls.get(i));

                        if ((tmp.latitude <= (MY_POSITION.latitude + 0.03)) && (tmp.latitude >= (MY_POSITION.latitude - 0.03)) && (tmp.longitude <= (MY_POSITION.longitude + 0.03) && tmp.longitude >= (MY_POSITION.longitude - 0.03))) //IMMEDIATE AREA
                        {

                            options.icon(BitmapDescriptorFactory.defaultMarker(0));
                            mMap.addMarker(options);

                        } else if ((tmp.latitude <= (MY_POSITION.latitude + 0.07)) && (tmp.latitude >= (MY_POSITION.latitude - 0.07)) && (tmp.longitude <= (MY_POSITION.longitude + 0.07) && tmp.longitude >= (MY_POSITION.longitude - 0.07))) // DISTRICT AREA
                        {
                            options.icon(BitmapDescriptorFactory.defaultMarker(0));
                            mMap.addMarker(options);

                        } else if ((tmp.latitude <= (MY_POSITION.latitude + 0.12)) && (tmp.latitude >= (MY_POSITION.latitude - 0.12)) && (tmp.longitude <= (MY_POSITION.longitude + 0.12) && tmp.longitude >= (MY_POSITION.longitude - 0.12))) //COUNTY WIDE
                        {
                            options.icon(BitmapDescriptorFactory.defaultMarker(0));
                            mMap.addMarker(options)
                                    .setAlpha(0.8f);
                        }
                        else if((tmp.latitude <= (MY_POSITION.latitude+0.15))&& (tmp.latitude >= (MY_POSITION.latitude-0.15)) && (tmp.longitude <= (MY_POSITION.longitude+0.15) && tmp.longitude >= (MY_POSITION.longitude-0.15))) //PROVINCE WIDE
                        {
                            options.icon(BitmapDescriptorFactory.defaultMarker(170));
                            options.zIndex(0.5f);
                            mMap.addMarker(options)
                                    .setAlpha(0.6f);
                        }
                        else {
                            options.icon(BitmapDescriptorFactory.defaultMarker(170)); // EVERYTHING ELSE OUT OF RANGE
                            options.zIndex(0.4f);
                            mMap.addMarker(options)
                                    .setAlpha(0.5f);
                        }
                    }

                }
                else
                {
                    mMap.clear();
                    for (int j = 0; j < cinema_urls.size(); j++)
                    {
                        double x = location.getLatitude();
                        double y = location.getLongitude();
                        LatLng MY_POSITION = new LatLng(x, y);      //Get My Position//

                        MarkerOptions options = new MarkerOptions();
                        LatLng tmp = new LatLng(cinema_latitudes.get(j), cinema_longitudes.get(j));      ///Create a coordinate from data

                        options.position(tmp);
                        options.title(cinema_names.get(j));
                        options.snippet(cinema_urls.get(j));
                        ///Set name, position and description
                        if((tmp.latitude <= (MY_POSITION.latitude+0.03))&& (tmp.latitude >= (MY_POSITION.latitude-0.03)) && (tmp.longitude <= (MY_POSITION.longitude+0.03) && tmp.longitude >= (MY_POSITION.longitude-0.03))) //IMMEDIATE AREA
                        {

                            options.icon(BitmapDescriptorFactory.defaultMarker(0));
                            options.zIndex(1.0f);
                            mMap.addMarker(options);

                        }
                        else if((tmp.latitude <= (MY_POSITION.latitude+0.07))&& (tmp.latitude >= (MY_POSITION.latitude-0.07)) && (tmp.longitude <= (MY_POSITION.longitude+0.07) && tmp.longitude >= (MY_POSITION.longitude-0.07))) // DISTRICT AREA
                        {
                            options.icon(BitmapDescriptorFactory.defaultMarker(0));
                            options.zIndex(0.8f);
                            mMap.addMarker(options)
                                    .setAlpha(0.8f);


                        }
                        else if((tmp.latitude <= (MY_POSITION.latitude+0.11))&& (tmp.latitude >= (MY_POSITION.latitude-0.11)) && (tmp.longitude <= (MY_POSITION.longitude+0.11) && tmp.longitude >= (MY_POSITION.longitude-0.11))) //COUNTY WIDE
                        {
                            options.icon(BitmapDescriptorFactory.defaultMarker(0));
                            options.zIndex(0.6f);
                            mMap.addMarker(options)
                                    .setAlpha(0.7f);

                        }
                    }
                    clear.setText("SHOW");
                }
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) { //"north border tech training" tutorials for google maps, Also for XML parsing for map markers  on "tutorialspoint"
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnInfoWindowLongClickListener(this);
        //Enable user location permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            mMap.setMyLocationEnabled(true); //Known as a dangerous permissions. Dangerous permissions are those classified as something with regards to personal data
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        }

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            mylatitude = location.getLatitude();
            mylongitude = location.getLongitude();
            coordinate = new LatLng(mylatitude, mylongitude);
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 10);
            mMap.animateCamera(yourLocation);
        }

        for (int j = 0; j < cinema_urls.size(); j++) {

            double x = mylatitude;
            double y = mylongitude;
            LatLng MY_POSITION = new LatLng(x, y);      //Get My Position//

            MarkerOptions options = new MarkerOptions();
            LatLng tmp = new LatLng(cinema_latitudes.get(j), cinema_longitudes.get(j));      ///Create a coordinate from data

            options.position(tmp);
            options.title(cinema_names.get(j));
            options.snippet(cinema_urls.get(j));
            ///Set name, position and description
            if ((tmp.latitude <= (MY_POSITION.latitude + 0.03)) && (tmp.latitude >= (MY_POSITION.latitude - 0.03)) && (tmp.longitude <= (MY_POSITION.longitude + 0.03) && tmp.longitude >= (MY_POSITION.longitude - 0.03))) //IMMEDIATE AREA
            {

                options.icon(BitmapDescriptorFactory.defaultMarker(0));
                options.zIndex(1.0f);
                mMap.addMarker(options);

            } else if ((tmp.latitude <= (MY_POSITION.latitude + 0.07)) && (tmp.latitude >= (MY_POSITION.latitude - 0.07)) && (tmp.longitude <= (MY_POSITION.longitude + 0.07) && tmp.longitude >= (MY_POSITION.longitude - 0.07))) // DISTRICT AREA
            {
                options.icon(BitmapDescriptorFactory.defaultMarker(0));
                options.zIndex(0.8f);
                mMap.addMarker(options)
                        .setAlpha(0.8f);

            } else if ((tmp.latitude <= (MY_POSITION.latitude + 0.11)) && (tmp.latitude >= (MY_POSITION.latitude - 0.11)) && (tmp.longitude <= (MY_POSITION.longitude + 0.11) && tmp.longitude >= (MY_POSITION.longitude - 0.11))) //COUNTY WIDE
            {
                options.icon(BitmapDescriptorFactory.defaultMarker(0));
                options.zIndex(0.6f);
                mMap.addMarker(options)
                        .setAlpha(0.7f);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    {
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "This app requires location permissions to be granted. Please Enable Location Information in Settings", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }
    @Override
    public void onInfoWindowClick(Marker marker)
    {
        final String title = marker.getTitle();
        final String url = marker.getSnippet();
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {                   //Thread to capture HTML data

                try {
                    ArrayList<String> filmNameData = new ArrayList<>();
                    ArrayList<String> filmTimeData = new ArrayList<>();
                    Intent intentToCinema = new Intent(MapsActivity.this, CinemaActivity.class);

                    int tmpIndex1 = 0;
                    int tmpIndex2 = 1;
                    int tmpIndex3 = 2;

                    doc = Jsoup.connect(url).get(); //Capture Cinema times data from cinema URL

                    String html_soup = doc.select("div[class=large-8 cinema_single listing columns]").toString();
                    html_soup = html_soup.replace("  ", "");
                    html_soup = Jsoup.clean(html_soup,Whitelist.none());

                    String [] film_array = html_soup.split("  ");
                    ArrayList<String> tmpList = new ArrayList<>();

                    for (int i = 5;i < film_array.length;i++)
                    {
                        tmpList.add(film_array[i]);
                    }
                    while (tmpIndex3<tmpList.size())
                    {
                        String tmp1 = tmpList.get(tmpIndex1);
                        String tmp2 = tmpList.get(tmpIndex2).replaceAll("[^0-9:,]", "");

                        if(!filmNameData.contains(tmp1))
                        {
                            tmp1 = tmp1.trim();
                            filmNameData.add(tmp1);
                        }
                        if(!filmTimeData.contains(tmp2))
                        {
                            tmp2 = tmp2.trim();
                            filmTimeData.add(tmp2);
                        }

                        tmpIndex1+=3;
                        tmpIndex2+=3;
                        tmpIndex3+=3;
                    }

                    Bundle args2 = new Bundle();
                    args2.putSerializable("FILM_TIME_LIST",filmTimeData);   //Put film data into a bundle
                    args2.putSerializable("FILM_ID_LIST", filmNameData);
                    intentToCinema.putExtra("FILM_PACKAGE",args);
                    intentToCinema.putExtra("FILM_TIME_PACKAGE",args2);
                    intentToCinema.putExtra("CINEMA_NAME", title);
                    intentToCinema.putExtra("CINEMA_URL", url);

                    startActivity(intentToCinema);
                }
                catch (IOException e) { e.printStackTrace(); Toast.makeText(MapsActivity.this,"An error has occurred while obtaining data. Please message the support e-mail in the Help menu.",Toast.LENGTH_LONG).show(); }
                catch(NullPointerException f) { f.printStackTrace(); Toast.makeText(MapsActivity.this,"An error has occurred while obtaining data. Please message the support e-mail in the Help menu.",Toast.LENGTH_LONG).show(); }
            }
        }).start();

    }
    @Override
    public void onInfoWindowLongClick(Marker marker) {

        String linkToUrl = marker.getSnippet();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkToUrl));
        startActivity(intent);

    }
}
