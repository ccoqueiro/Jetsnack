/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetsnack.ui

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.splunk.rum.Config
import com.splunk.rum.SplunkRum
import com.splunk.rum.StandardAttributes
import io.opentelemetry.api.common.Attributes

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = SplunkRum.newConfigBuilder()
            .realm("REALM") // Splunk Realm
            .rumAccessToken("TOKEN") // Your RUM token
            .applicationName("APP") // Name of your app
            .deploymentEnvironment("dev") // Environment
            .slowRenderingDetectionEnabled(false)
            .debugEnabled(true)
            .globalAttributes(
                Attributes.builder() // Add the application version. Alternatively, you
                    // can pass BuildConfig.VERSION_NAME as the value.
                    .put(StandardAttributes.APP_VERSION, "VERSION")
                    .build()
            ).build()
        SplunkRum.initialize(config, this.application)
        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            JetsnackApp()
        }
    }
}
