/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.settings.deviceinfo;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.SystemProperties;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;
import android.text.TextUtils;

import com.android.settings.R;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.DeviceInfoUtils;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.Objects;

public class PotatoVersionPreferenceController extends AbstractPreferenceController implements
        PreferenceControllerMixin {

    private static final String KEY_POTATO_VERSION = "potato_version";

    private final Fragment mHost;

    public PotatoVersionPreferenceController(Context context, Fragment host) {
        super(context);
        mHost = host;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);
        final Preference pref = screen.findPreference(KEY_POTATO_VERSION);
        if (pref != null) {
            String summary;
            if (Objects.equals(SystemProperties.get("persist.potato.community"), "true")) {
            summary = SystemProperties.get("persist.potato.dish") + " (Chips) - v"
                            + SystemProperties.get("persist.potato.version");
            } else {
            summary = SystemProperties.get("persist.potato.dish") + " - v"
                            + SystemProperties.get("persist.potato.version");
            }
            pref.setSummary(summary);
        }
    }

    @Override
    public String getPreferenceKey() {
        return KEY_POTATO_VERSION;
    }

    @Override
    public boolean handlePreferenceTreeClick(Preference preference) {
        return false;
    }
}
