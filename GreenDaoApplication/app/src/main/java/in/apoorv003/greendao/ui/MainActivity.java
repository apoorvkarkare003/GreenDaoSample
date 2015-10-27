package in.apoorv003.greendao.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import de.greenrobot.dao.query.DeleteQuery;
import de.greenrobot.dao.query.WhereCondition;
import in.apoorv003.greendao.DaoApplication;
import in.apoorv003.greendao.R;
import in.apoorv003.greendao.db.Actor;
import in.apoorv003.greendao.db.ActorDao;
import in.apoorv003.greendao.db.Director;
import in.apoorv003.greendao.db.DirectorDao;
import in.apoorv003.greendao.db.Movie;
import in.apoorv003.greendao.db.MovieDao;
import in.apoorv003.greendao.db.ProductionHouse;
import in.apoorv003.greendao.db.ProductionHouseDao;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DaoApplication application;
    private ProductionHouseDao productionHouseDao;
    private MovieDao movieDao;
    private DirectorDao directorDao;
    private ActorDao actorDao;
    private DeleteQuery deleteQuery;
    private TextView output;
    private Button insertData, selectFromMovies, movieDetail, whereCondition, selectiveDelete, rawQuery, clearData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        application = (DaoApplication) getApplication();
        movieDao = application.getMovieDao();
        directorDao = application.getDirectorDao();
        productionHouseDao = application.getProductionHouseDao();
        actorDao = application.getActorDao();

        initViews();
        validateViews();


    }

    private void validateViews() {

        insertData.setOnClickListener(this);
        selectFromMovies.setOnClickListener(this);
        movieDetail.setOnClickListener(this);
        whereCondition.setOnClickListener(this);
        selectiveDelete.setOnClickListener(this);
        rawQuery.setOnClickListener(this);
        clearData.setOnClickListener(this);

    }

    private void initViews() {

        output = (TextView) findViewById(R.id.output);
        insertData = (Button) findViewById(R.id.insert);
        selectFromMovies = (Button) findViewById(R.id.select_all);
        movieDetail = (Button) findViewById(R.id.detail);
        whereCondition = (Button) findViewById(R.id.where);
        selectiveDelete = (Button) findViewById(R.id.selective_del);
        rawQuery = (Button) findViewById(R.id.raw);
        clearData = (Button) findViewById(R.id.clear);

    }

    private void insertInActor() {

        Actor actor1 = new Actor();
        actor1.setName("Shah Rukh");
        actor1.setActorMovieId(movieDao.loadAll().get(0).getMovieId());
        actorDao.insertOrReplace(actor1);
        movieDao.loadAll().get(0).getActorList().add(actor1);

        Actor actor2 = new Actor();
        actor2.setName("Kareena");
        actor2.setActorMovieId(movieDao.loadAll().get(0).getMovieId());
        actorDao.insertOrReplace(actor2);
        movieDao.loadAll().get(0).getActorList().add(actor2);

        Actor actor3 = new Actor();
        actor3.setName("Amir");
        actor3.setActorMovieId(movieDao.loadAll().get(2).getMovieId());
        actorDao.insertOrReplace(actor3);
        movieDao.loadAll().get(2).getActorList().add(actor3);

        Actor actor4 = new Actor();
        actor4.setName("Hrithik");
        actor4.setActorMovieId(movieDao.loadAll().get(2).getMovieId());
        actorDao.insertOrReplace(actor4);
        movieDao.loadAll().get(2).getActorList().add(actor4);

        Actor actor5 = new Actor();
        actor5.setName("Ronit Roy");
        actor5.setActorId(movieDao.loadAll().get(3).getMovieId());
        actorDao.insertOrReplace(actor5);
        movieDao.loadAll().get(3).getActorList().add(actor5);

        Actor actor6 = new Actor();
        actor6.setName("Kajol");
        actor6.setActorMovieId(movieDao.loadAll().get(1).getMovieId());
        actorDao.insertOrReplace(actor6);
        movieDao.loadAll().get(1).getActorList().add(actor6);

        Actor actor7 = new Actor();
        actor7.setName("Mithun");
        actor7.setActorMovieId(movieDao.loadAll().get(4).getMovieId());
        actorDao.insertOrReplace(actor7);
        movieDao.loadAll().get(4).getActorList().add(actor7);

        Actor actor8 = new Actor();
        actor8.setName("Shakti kapoor");
        actor8.setActorMovieId(movieDao.loadAll().get(4).getMovieId());
        actorDao.insertOrReplace(actor8);
        movieDao.loadAll().get(4).getActorList().add(actor8);

        Actor actor9 = new Actor();
        actor9.setName("Abhishek");
        actor9.setActorMovieId(movieDao.loadAll().get(2).getMovieId());
        actorDao.insertOrReplace(actor9);
        movieDao.loadAll().get(2).getActorList().add(actor9);

    }

    private void insertInProduction() {

        ProductionHouse productionHouse1 = new ProductionHouse();
        productionHouse1.setName("Dharma Production");
        productionHouseDao.insertOrReplace(productionHouse1);

        ProductionHouse productionHouse = new ProductionHouse();
        productionHouse.setName("YRF");
        productionHouseDao.insertOrReplace(productionHouse);


        ProductionHouse productionHouse2 = new ProductionHouse();
        productionHouse2.setName("Phantom");
        productionHouseDao.insertOrReplace(productionHouse2);

        ProductionHouse productionHouse3 = new ProductionHouse();
        productionHouse3.setName("KP");
        productionHouseDao.insertOrReplace(productionHouse3);

    }

    private void getMovieDetail() {

        String detail = null;
        Movie m = movieDao.loadAll().get(0);
        detail = "Details of" + " " + m.getName() + "\n" + "Directed by: " + directorDao.load(m.getDirectorId()).getName() + "\nProduced by: " + productionHouseDao.load(m.getProductionId()).getName() + "\nactors are:\n";
        Log.d("Details of" + m.getName() + ": ", m.getName() + " " + "directed by: " + directorDao.load(m.getDirectorId()).getName() + "produced by " + productionHouseDao.load(m.getProductionId()).getName());
        for (Actor a : m.getActorList()) {
            if (detail != null) {
                detail += " " + a.getName();
            } else {
                detail = a.getName();
            }
            Log.d("actors are: ", a.getName());
        }
        output.setText(detail);
    }


    private void rawQuery() {
        ArrayList<Movie> movies = (ArrayList) movieDao.queryBuilder().where(
                new WhereCondition.StringCondition(MovieDao.Properties.Name.columnName + " Like 'U%'")).list();

        String movieNames = null;

        for (Movie m : movies) {
            if (movieNames != null) {

                movieNames += " " + m.getName();
            } else {
                movieNames = m.getName();
            }
            Log.d("movie:", m.getName());
        }
        output.setText(movieNames);
    }


    private void deleteKaranMovies() {
        deleteQuery = movieDao.queryBuilder().where(MovieDao.Properties.DirectorId.eq(directorDao.loadAll().get(0).getId())).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
        selectAllFromMovies();
    }

    private void selectFromMoviesWhereDirectorIsKaran() {
        for (Director d : directorDao.loadAll()) {
            d.resetMovieList();
        }

        Log.d("director at 0: ", directorDao.loadAll().get(0).getName() + "with no of movies: " + directorDao.loadAll().get(0).getMovieList().size());
        ArrayList<Movie> movies = (ArrayList) directorDao.loadAll().get(0).getMovieList();

        String movieList = null;
        for (Movie m : movies) {
            if (movieList != null) {

                movieList += ", " + m.getName();
            } else {
                movieList = m.getName();
            }
            Log.d("movies are: ", m.getName());
        }
        output.setText(movieList);
    }


    private void selectAllFromMovies() {

        ArrayList<Movie> movies = (ArrayList) movieDao.loadAll();
        String movieList = null;

        Log.d("total entries: ", movies.size() + "");
        for (Movie m : movies) {
            if (movieList != null) {
                movieList += ", " + m.getName();
            } else {
                movieList = m.getName();
            }
            Log.d("movies: ", m.getName());
        }
        output.setText(movieList);
    }

    private void insertInMovies() {

        Movie movie = new Movie();
        movie.setName("K3G");
        movie.setDirectorId(directorDao.loadAll().get(0).getId());
        movie.setProductionId(productionHouseDao.loadAll().get(0).getId());
        Log.d("director set as: ", directorDao.load(movie.getDirectorId()).getName());
        movieDao.insertOrReplace(movie);
        directorDao.loadAll().get(0).getMovieList().add(movie);
        productionHouseDao.loadAll().get(0).getMovieList().add(movie);

        Movie movie1 = new Movie();
        movie1.setName("Kuchh Kuchh Hota Hai");
        movie1.setDirectorId(directorDao.loadAll().get(0).getId());
        movie1.setProductionId(productionHouseDao.loadAll().get(0).getId());

        Log.d("director set as: ", directorDao.load(movie1.getDirectorId()).getName());
        movieDao.insertOrReplace(movie1);
        directorDao.loadAll().get(0).getMovieList().add(movie1);
        productionHouseDao.loadAll().get(0).getMovieList().add(movie1);

        Movie movie2 = new Movie();
        movie2.setName("Dhoom");
        movie2.setDirectorId(directorDao.loadAll().get(1).getId());
        movie2.setProductionId(productionHouseDao.loadAll().get(1).getId());

        Log.d("director set as: ", directorDao.load(movie2.getDirectorId()).getName());
        movieDao.insertOrReplace(movie2);
        directorDao.loadAll().get(1).getMovieList().add(movie2);
        productionHouseDao.loadAll().get(1).getMovieList().add(movie2);

        Movie movie3 = new Movie();
        movie3.setName("Ugly");
        movie3.setDirectorId(directorDao.loadAll().get(2).getId());
        movie3.setProductionId(productionHouseDao.loadAll().get(2).getId());

        Log.d("director set as: ", directorDao.load(movie3.getDirectorId()).getName());
        movieDao.insertOrReplace(movie3);
        directorDao.loadAll().get(2).getMovieList().add(movie3);
        productionHouseDao.loadAll().get(2).getMovieList().add(movie3);

        Movie movie4 = new Movie();
        movie4.setName("Gunda");
        movie4.setDirectorId(directorDao.loadAll().get(3).getId());
        movie4.setProductionId(productionHouseDao.loadAll().get(3).getId());

        Log.d("director set as: ", directorDao.load(movie4.getDirectorId()).getName());
        movieDao.insertOrReplace(movie4);
        directorDao.loadAll().get(3).getMovieList().add(movie4);
        productionHouseDao.loadAll().get(3).getMovieList().add(movie4);

    }

    private void insertInDirectors() {

        Director director = new Director();
        director.setName("Kran Johar");
        directorDao.insertOrReplace(director);

        Director director1 = new Director();
        director1.setName("Aditya Chopra");
        directorDao.insertOrReplace(director1);


        Director director2 = new Director();
        director2.setName("Anurag Kashyap");
        directorDao.insertOrReplace(director2);

        Director director3 = new Director();
        director3.setName("Kanti shah");
        directorDao.insertOrReplace(director3);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.insert:

                insertInDirectors();
                insertInProduction();
                insertInMovies();
                insertInActor();
                break;

            case R.id.select_all:

                selectAllFromMovies();
                break;

            case R.id.detail:

                getMovieDetail();
                break;

            case R.id.where:

                selectFromMoviesWhereDirectorIsKaran();
                break;

            case R.id.selective_del:

                deleteKaranMovies();
                break;

            case R.id.raw:

                rawQuery();
                break;

            case R.id.clear:

                directorDao.deleteAll();
                actorDao.deleteAll();
                productionHouseDao.deleteAll();
                movieDao.deleteAll();
                break;
        }
    }
}
