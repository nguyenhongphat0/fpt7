package com.example.day10_database;

import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PhatNHPreferenceActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.day10_database_preferences", MODE_PRIVATE);
        for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
            initData(getPreferenceScreen().getPreference(i), sharedPreferences);
        }
    }

    public void updatePreference(Preference p, SharedPreferences sharedPreferences) {
        if (p instanceof EditTextPreference) {
            EditTextPreference editTextPreference = (EditTextPreference)p;
            p.setSummary(editTextPreference.getText());
        }
    }

    public void initData(Preference p, SharedPreferences sharedPreferences) {
        if (p instanceof PreferenceCategory) {
            PreferenceCategory category = (PreferenceCategory)p;
            for (int i = 0; i < category.getPreferenceCount(); i++) {
                initData(category.getPreference(i), sharedPreferences);
            }
        } else {
            updatePreference(p, sharedPreferences);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        updatePreference(findPreference(key), sharedPreferences);
    }
}
