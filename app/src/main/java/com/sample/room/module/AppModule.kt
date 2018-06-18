package com.sample.room.module

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.content.Context
import com.sample.room.MyApplication
import com.sample.room.qualifier.DatabaseName
import com.sample.room.repository.database.DbHelper
import com.sample.room.repository.database.DbHelperImpl
import com.sample.room.repository.database.MyDatabase
import com.sample.room.repository.network.MyNetworkHelper
import com.sample.room.repository.network.NetworkHelper
import com.sample.room.scope.ApplicationScope
import com.sample.room.utility.DATABASE_NAME
import dagger.Module
import dagger.Provides
import timber.log.Timber

@Module
class AppModule {

    companion object {
        // migration for going from 1st to 2nd version
        val migration_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Timber.d("Migration from 1st to 2nd Room version")
                // you have to put on the sql queries here
                database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, " + "`name` TEXT, PRIMARY KEY(`id`))")
            }
        }

        // migration for going from 2nd to 3rd version
        val migration_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Timber.d("Migration from 2nd to 3rd Room version")
            }
        }

        // migration for going from 3rd to 4th version
        val migration_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Timber.d("Migration from 3rd to 4th Room version")
            }
        }

        // migration for going from 1st to 4th version directly
        val migration_1_4: Migration = object : Migration(1, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Timber.d("Migration from 1st to 4th Room version")
            }
        }
    }

    // creating instance of database
    // if you don't provide the migration, room rebuilds the database instead,
    // which means you'll lose all of your data in the database
    // all the migrations are run if needed, user can directly migrate from 1 to 4 version
    @Provides
    @ApplicationScope
    fun provideDatabase(context: Context, @DatabaseName dbName: String): MyDatabase {
        return Room.databaseBuilder(context, MyDatabase::class.java, dbName)
                //.fallbackToDestructiveMigration() // Wipes and rebuilds instead of migrating if no Migration object.
                .addMigrations(migration_1_2, migration_2_3, migration_3_4, migration_1_4)// you can add migrations here
                .build()
    }

    // provide the instance of the database implementation file
    @Provides
    @ApplicationScope
    fun provideDbHelper(dbHelperImpl: DbHelperImpl): DbHelper = dbHelperImpl;

    // provide the database name to be used
    @Provides
    @DatabaseName
    fun providesDatabaseName(): String = DATABASE_NAME;

    // provides the context
    @Provides
    @ApplicationScope
    fun providesContext(application: MyApplication): Context {
        return application.applicationContext

    }

    // provides the network helper
    @Provides
    @ApplicationScope
    fun providesNetworkHelper(myNetworkHelper: MyNetworkHelper): NetworkHelper {
        return myNetworkHelper
    }
}
