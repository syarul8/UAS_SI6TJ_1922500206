package ac.id.atmaluhur.mhs.syahrul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView dosen_textview;
    private FloatingActionButton btn_tambah;
    private Dosenjsonplaceholderapi jsonplaceholderapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_tambah = findViewById(R.id.tambah);
        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                Intent i = new Intent(MainActivity.this,
                        EdosenActivity.class);
                startActivity(i);
            }
        });
        dosen_textview = findViewById(R.id.text_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.30.108/UASSI6TJ1922500206/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonplaceholderapi = retrofit.create(Dosenjsonplaceholderapi.class);
        getPosts();
    }

    private void getPosts() {
        Map<String, String> parameters = new HashMap<>();
        Call<List<Dosenpost>> call = jsonplaceholderapi.getPosts();
        call.enqueue(new Callback<List<Dosenpost>>() {
            @Override
            public void onResponse(Call<List<Dosenpost>> call, Response<List<Dosenpost>> response) {
                if (!response.isSuccessful()) {
                    dosen_textview.setText("code: " + response.code());
                    return;
                }
                List<Dosenpost> posts = response.body();
                for (Dosenpost post : posts) {
                    String content = "";
                    content += "nidn         : " + post.getNidn() + "\n";
                    content += "nama_dosen   : " + post.getNama_dosen() + "\n";
                    content += "jabatan      : " + post.getJabatan() + "\n";
                    content += "gol_pang     : " + post.getGol_pang() + "\n";
                    content += "keahlian     : " + post.getKeahlian() + "\n";
                    content += "program_studi: " + post.getProgram_studi() + "\n\n";
                    dosen_textview.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Dosenpost>> call, Throwable t) {
                dosen_textview.setText(t.getMessage());
            }
        });
    }
}