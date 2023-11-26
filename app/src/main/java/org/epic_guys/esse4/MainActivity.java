package org.epic_guys.esse4;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentActivity;
import org.epic_guys.esse4.API.API;

import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.Persona;

import de.adorsys.android.securestoragelibrary.SecurePreferences;

public class MainActivity extends FragmentActivity {
    public MainActivity() {
        super(R.layout.activity_main);
    }
}
