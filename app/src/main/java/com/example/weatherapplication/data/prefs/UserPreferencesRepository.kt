package com.example.weatherapplication.data.prefs

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.weatherapplication.util.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

data class UserPreferences (
    val currentCity: String
)

private object PreferencesKeys {
    val CURRENT_CITY = stringPreferencesKey("city")
}

class UserPreferencesRepository @Inject constructor(
    private val context: Context
) {

    val userPreferencesFlow: Flow<UserPreferences> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val currCity = preferences[PreferencesKeys.CURRENT_CITY]?: "berlin"
            UserPreferences(currCity)
        }

    suspend fun updateCity(newCity: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.CURRENT_CITY] = newCity
        }
    }

}