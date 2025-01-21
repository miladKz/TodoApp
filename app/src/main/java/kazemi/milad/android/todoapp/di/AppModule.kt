package kazemi.milad.android.todoapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kazemi.milad.android.todoapp.data.settings.SettingPreferences
import kazemi.milad.android.todoapp.data.settings.SettingRepository
import kazemi.milad.android.todoapp.data.settings.SettingRepositoryImpl
import kazemi.milad.android.todoapp.data.todo.TodoDatabase
import kazemi.milad.android.todoapp.data.todo.TodoRepository
import kazemi.milad.android.todoapp.data.todo.TodoRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    val databaseName = "todo_db"

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(app, TodoDatabase::class.java, databaseName).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(database: TodoDatabase): TodoRepository {
        return TodoRepositoryImpl(database.dao)
    }

    @Provides
    @Singleton
    fun provideSettingPreferences(@ApplicationContext context: Context): SettingPreferences {
        return SettingPreferences(context)
    }

    @Provides
    @Singleton
    fun provideSettingRepository(settingPreferences: SettingPreferences): SettingRepository {
        return SettingRepositoryImpl(settingPreferences);
    }

}