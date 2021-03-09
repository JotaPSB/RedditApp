package cat.itb.redditapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import cat.itb.redditapp.adapter.RecyclerAdapter;
import cat.itb.redditapp.helper.PostViewModel;
import cat.itb.redditapp.model.Post;

public class CardFragment extends Fragment {

    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;
    List<Post> posts;
    RecyclerAdapter adapter;
    PostViewModel postViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postViewModel = new ViewModelProvider(getActivity()).get(PostViewModel.class);
        posts = PostViewModel.posts;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        recyclerView = v.findViewById(R.id.recycler_view);
        adapter = new RecyclerAdapter(posts,R.layout.item_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);



        return v;
    }


}