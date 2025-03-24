package com.createfuture.takehome.data.encryption

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.createfuture.takehome.domain.repository.SecureRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class EncryptedSharedPrefsRepository @Inject constructor(
    @ApplicationContext context: Context
) : SecureRepository {

    private val sharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            FILE_NAME,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun saveToken(key: String, token: String) {
        sharedPreferences.edit { putString(key, token) }
    }

    override fun getToken(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    override fun clearToken(key: String) {
        sharedPreferences.edit { remove(key) }
    }

    private companion object {
        const val FILE_NAME = "encrypted_shared_prefs"
    }
}
