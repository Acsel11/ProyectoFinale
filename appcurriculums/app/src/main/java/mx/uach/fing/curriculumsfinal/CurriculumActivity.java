package mx.uach.fing.pruebasdeppp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CurriculumActivity extends AppCompatActivity {

    private TextView textViewFullName;
    private TextView textViewTitle;
    private TextView textViewPhone;
    private TextView textViewEmail;
    private TextView textViewLocation;
    private TextView textViewExperience;
    private TextView textViewEducation;
    private TextView textViewSkills;
    private TextView textViewLanguages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum);

        // Obtener el identificador de miembro del intent
        String memberId = getIntent().getStringExtra("memberId");

        textViewFullName = findViewById(R.id.textViewFullName);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewPhone = findViewById(R.id.textViewPhone);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewLocation = findViewById(R.id.textViewLocation);
        textViewExperience = findViewById(R.id.textViewExperience);
        textViewEducation = findViewById(R.id.textViewEducation);
        textViewSkills = findViewById(R.id.textViewSkills);
        textViewLanguages = findViewById(R.id.textViewLanguages);

        // Definir la URL según el identificador de miembro
        String url = "";
        if (memberId.equals("axl")) {
            url = "https://raw.githubusercontent.com/acsel11/ProyectoFinale/master/CurriculumAxel.json";
        } else if (memberId.equals("dnl")) {
            url = "https://raw.githubusercontent.com/acsel11/ProyectoFinale/master/CurriculumDaniel.json";
        } else if (memberId.equals("zaid")) {
            url = "https://raw.githubusercontent.com/acsel11/ProyectoFinale/master/CV.json";
        }

        // Realiza la solicitud HTTP para obtener los datos del currículum desde el enlace correspondiente
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Obtiene los datos del currículum del objeto JSON de respuesta
                            JSONObject member = response.getJSONObject("member");

                            // Verifica si se encontró el miembro y muestra los datos correspondientes
                            if (member != null) {
                                // Asigna los datos a los TextViews correspondientes
                                textViewFullName.setText(member.getString("name"));
                                textViewTitle.setText(member.getString("title"));
                                textViewPhone.setText(member.getString("phone"));
                                textViewEmail.setText(member.getString("email"));
                                textViewLocation.setText(member.getString("location"));
                                textViewExperience.setText(member.getString("experience"));
                                textViewEducation.setText(member.getString("education"));
                                textViewSkills.setText(getSkillsAsString(member.getJSONArray("skills")));
                                textViewLanguages.setText(getLanguagesAsString(member.getJSONArray("languages")));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        // Agrega la solicitud a la cola de solicitudes de Volley
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }



    // Método para convertir un JSONArray en una cadena de texto separada por comas
    private String getSkillsAsString(JSONArray skills) throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < skills.length(); i++) {
            stringBuilder.append(skills.getString(i));
            if (i < skills.length() - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

    // Método para convertir un JSONArray en una cadena de texto separada por comas
    private String getLanguagesAsString(JSONArray languages) throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < languages.length(); i++) {
            stringBuilder.append(languages.getString(i));
            if (i < languages.length() - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}