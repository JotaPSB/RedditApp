package cat.itb.redditapp.helper;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import cat.itb.redditapp.model.Post;

public class PostViewModel extends ViewModel {
    public static List<Post> posts = new ArrayList<Post>(){
        {
            add(new Post("Memes","Jota", "Post super realista", null, 155, 45, null));
            add(new Post("Pokemon","Jota", "No me parece correcto el diseño del remake de la cuarta generación", "Una basura total, con el dinero que tiene nintendo y hace eso, patetico", 520, 469, null));
            add(new Post("Memes","Jota", "Probando probando", "esto no es un meme", 155, 45, null));
            add(new Post("shposting", "Jota", "ARA ARA", null, 12, 6, null));

        }
    };

    public PostViewModel() {

    }

}
