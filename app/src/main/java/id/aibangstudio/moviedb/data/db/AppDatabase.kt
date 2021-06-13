package id.aibangstudio.moviedb.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import id.aibangstudio.moviedb.data.db.dao.MovieDao
import id.aibangstudio.moviedb.data.db.entity.MovieModel

@Database(
    entities = [MovieModel::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun movieDao() : MovieDao

}