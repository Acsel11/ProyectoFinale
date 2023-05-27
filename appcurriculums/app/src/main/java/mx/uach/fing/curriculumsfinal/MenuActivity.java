package mx.uach.fing.curriculumsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private Button axl;
    private Button zaid;
    private Button dnl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        axl = findViewById(R.id.axl);
        zaid = findViewById(R.id.zaid);
        dnl = findViewById(R.id.dnl);

        axl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCurriculum("axl");
            }
        });

        zaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCurriculum("zaid");
            }
        });

        dnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCurriculum("dnl");
            }
        });
    }

    private void navigateToCurriculum(String memberId) {
        Intent intent = new Intent(MenuActivity.this, CurriculumActivity.class);
        intent.putExtra("memberId", memberId);
        startActivity(intent);
    }

}