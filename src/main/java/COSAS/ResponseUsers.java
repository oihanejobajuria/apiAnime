package COSAS;

import com.example.anime.domain.model.Users;
import java.util.List;

public class ResponseUsers {
    public List<Users> result;

    public ResponseUsers(List<Users> result) {
        this.result = result;
    }
}
