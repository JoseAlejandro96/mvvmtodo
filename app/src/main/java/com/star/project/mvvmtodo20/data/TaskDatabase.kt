package com.star.project.mvvmtodo20.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.star.project.mvvmtodo20.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope : CoroutineScope
    ): RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            // db Operation
            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Wash the dishes"))
                dao.insert(Task("Wash the dishes2"))
                dao.insert(Task("Wash the dishes3"))
                dao.insert(Task("Wash the dishes4"))
                dao.insert(Task("Wash the dishes5", important = true))
                dao.insert(Task("Wash the dishes6", completed = true))
                dao.insert(Task("Wash the dishes7"))
                dao.insert(Task("Wash the dishes8", completed = true))
                dao.insert(Task("Wash the dishes9"))
                dao.insert(Task("Wash the dishes10"))
                dao.insert(Task("Wash the dishes11"))
            }
        }
    }
}