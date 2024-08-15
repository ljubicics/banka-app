package data.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import domain.model.user.User
import domain.repository.IUserPreference
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UserPreference(
    private val preferences: DataStore<Preferences>
) : IUserPreference {

    private val tokenKey = stringPreferencesKey("TOKEN")
    private val activeUser = stringPreferencesKey("ACTIVE_USER")

    override suspend fun storeToken(token: String) {
        preferences.edit { preferences ->
            preferences[tokenKey] = token
        }

    }

    override suspend fun getToken(): String {
        return preferences.data.map { preferences ->
            preferences[tokenKey] ?: ""
        }.first()
    }

    override suspend fun storeUserData(user: User) {
        preferences.edit { preferences ->
            preferences[activeUser] = Json.encodeToString(user)
        }
    }

    override suspend fun getUserData(): User {
        val user = preferences.data.map { preferences ->
            preferences[activeUser] ?: ""
        }.first()

        return if (user != "") {
            Json.decodeFromString(user)
        } else {
            User()
        }
    }
}