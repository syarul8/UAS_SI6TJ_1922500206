package ac.id.atmaluhur.mhs.syahrul;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;

public class EdosenActivity extends AppCompatActivity {
    private EditText txtnidn ,txtnmdosen, txtjabatan, txtgolpang, txtkeahlian, txtprogstud;
    private Button dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edosen);
        dashboard = findViewById(R.id.dashboard);
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                Intent i = new Intent(EdosenActivity.this,
                        MainActivity.class);
                startActivity(i);
            }
        });

        txtnidn = (EditText) findViewById(R.id.et_nidn);
        txtnmdosen = (EditText) findViewById(R.id.et_namadosen);
        txtjabatan = (EditText) findViewById(R.id.et_jabatan);
        txtgolpang = (EditText) findViewById(R.id.et_golpag);
        txtkeahlian = (EditText) findViewById(R.id.et_keahlian);
        txtprogstud = (EditText) findViewById(R.id.et_progstud);
    }

    public void tambahdosen(View View) {
        final String nidn = txtnidn.getText().toString().trim();
        final String nmdosen = txtnmdosen.getText().toString().trim();
        final String jabatan = txtjabatan.getText().toString().trim();
        final String golpang = txtgolpang.getText().toString().trim();
        final String keahlian = txtkeahlian.getText().toString().trim();
        final String progstud = txtprogstud.getText().toString().trim();

        class tambahdosen extends AsyncTask<Void, Void, String> {
            ProgressDialog load;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                load = ProgressDialog.show(
                        EdosenActivity.this, "Add...", "Wait..",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put("nidn", nidn);
                params.put("nama_dosen", nmdosen);
                params.put("jabatan", jabatan);
                params.put("gol_pang", golpang);
                params.put("keahlian", keahlian);
                params.put("program_studi", progstud);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest("http://192.168.30.108/UASSI6TJ1922500206/tambah_dosen.php", params);
                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                load.dismiss();
                Toast.makeText(EdosenActivity.this, s, Toast.LENGTH_LONG).show();
            }
        }
        tambahdosen tb = new tambahdosen();
        tb.execute();
    }
}