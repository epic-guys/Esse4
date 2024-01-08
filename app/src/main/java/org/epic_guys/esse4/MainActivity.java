package org.epic_guys.esse4;
import androidx.appcompat.app.AppCompatActivity;
import org.epic_guys.esse4.API.JWTRefresher;

public class MainActivity extends AppCompatActivity {

    private JWTRefresher jwtRefresher;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        jwtRefresher = new JWTRefresher(this);
        jwtRefresher.start();
    }
}