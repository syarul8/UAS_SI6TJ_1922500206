package ac.id.atmaluhur.mhs.syahrul;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface Dosenjsonplaceholderapi {
    @GET("dosen.php")
    Call<List<Dosenpost>> getPosts(

    );
    @GET("dosen.php")
    Call<List<Dosenpost>> getPosts(@QueryMap Map<String, String> parameters);
}
