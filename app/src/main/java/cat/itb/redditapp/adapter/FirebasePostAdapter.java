package cat.itb.redditapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import cat.itb.redditapp.MainActivity;
import cat.itb.redditapp.R;
import cat.itb.redditapp.fragments.CommentFragment;
import cat.itb.redditapp.helper.DatabaseHelper;
import cat.itb.redditapp.model.Community;
import cat.itb.redditapp.model.Post;
import de.hdodenhof.circleimageview.CircleImageView;

public class FirebasePostAdapter extends FirebaseRecyclerAdapter<Post, FirebasePostAdapter.PostHolder> {

    int layout;
    Context context;
    FragmentActivity activity;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FirebasePostAdapter(@NonNull FirebaseRecyclerOptions<Post> options, int layout, FragmentActivity activity) {
        super(options);
        this.layout = layout;
        this.activity = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull PostHolder holder, int position, @NonNull Post model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new PostHolder(v);
    }

    public class PostHolder extends RecyclerView.ViewHolder{

        CircleImageView comPicture;
        TextView community;
        TextView user;

        TextView title;
        TextView optionalText;
        TextView likes;
        TextView comments;

        ImageView commentImage;


        public PostHolder(@NonNull View itemView) {
            super(itemView);
            comPicture = itemView.findViewById(R.id.com_picture_post);
            community = itemView.findViewById(R.id.post_community);
            user = itemView.findViewById(R.id.post_user);
            title = itemView.findViewById(R.id.post_title);
            optionalText = itemView.findViewById(R.id.post_optional_text);
            likes = itemView.findViewById(R.id.post_likes);
            comments = itemView.findViewById(R.id.post_comments);

            commentImage = itemView.findViewById(R.id.imageView3);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Post post){

            user.setText("Posted by u/"+ post.getUser());
            title.setText(post.getTitle());
            String optText = post.getContentText();
            if (optText!=null && !optText.isEmpty() && layout != R.layout.item_compact_view){
                optionalText.setText(optText);
            }
            likes.setText(String.valueOf(post.getVotes()));
            comments.setText(String.valueOf(post.getNumComments()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            commentImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new CommentFragment();
                    FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, fragment);
                    MainActivity.currentFragment = fragment;
                    MainActivity.loginHide();
                    transaction.commit();
                }
            });
        }
    }
}
