package com.example.enliven

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import com.google.android.material.chip.ChipDrawable.Delegate
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.offline.model.message.attachments.UploadAttachmentsNetworkType
import io.getstream.chat.android.offline.plugin.configuration.Config
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory

class StreamChatApp: Application() {
    override fun onCreate() {
        super.onCreate()

        val offlinePluginFactory = StreamOfflinePluginFactory(
            config = Config(
                backgroundSyncEnabled = true,
                userPresence = true,
                persistenceEnabled = true,
                uploadAttachmentsNetworkType = UploadAttachmentsNetworkType.NOT_ROAMING,
                useSequentialEventHandler = false,
            ),
            appContext = applicationContext,
        )
        ChatClient.Builder("efvau2adu3pn", applicationContext).withPlugin(offlinePluginFactory).build()
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
    }
}