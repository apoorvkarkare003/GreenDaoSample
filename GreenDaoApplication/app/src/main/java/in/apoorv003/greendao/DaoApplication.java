package in.apoorv003.greendao;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import in.apoorv003.greendao.db.ActorDao;
import in.apoorv003.greendao.db.DaoMaster;
import in.apoorv003.greendao.db.DaoSession;
import in.apoorv003.greendao.db.DirectorDao;
import in.apoorv003.greendao.db.MovieDao;
import in.apoorv003.greendao.db.ProductionHouseDao;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by apoorv on 19/10/15.
 */
public class DaoApplication extends Application {

    private SQLiteDatabase db;

    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private MovieDao moviesDao;
    private DirectorDao directorDao;
    private ActorDao actorDao;
    private ProductionHouseDao productionHouseDao;

    @Override
    public void onCreate() {
        super.onCreate();
        initDb();
    }

    private void initDb() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, BuildConfig.APPLICATION_ID + "-" + BuildConfig.VERSION_NAME,
                null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

        moviesDao = daoSession.getMovieDao();
        directorDao = daoSession.getDirectorDao();
        actorDao = daoSession.getActorDao();
        productionHouseDao = daoSession.getProductionHouseDao();

        if (BuildConfig.DEBUG) {
            QueryBuilder.LOG_SQL = true;
            QueryBuilder.LOG_VALUES = true;
        }
    }

    public MovieDao getMovieDao() {
        if (moviesDao == null) {
            initDb();
        }
        return moviesDao;
    }

    public DirectorDao getDirectorDao() {
        if (directorDao == null) {
            initDb();
        }

        return directorDao;
    }

    public ActorDao getActorDao() {
        if (actorDao == null) {
            initDb();
        }

        return actorDao;
    }

    public ProductionHouseDao getProductionHouseDao() {
        if (productionHouseDao == null) {
            initDb();
        }

        return productionHouseDao;
    }

}
