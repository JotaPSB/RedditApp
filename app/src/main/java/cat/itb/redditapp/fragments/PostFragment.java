package cat.itb.redditapp.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

import cat.itb.redditapp.MainActivity;
import cat.itb.redditapp.R;
import cat.itb.redditapp.helper.DatabaseHelper;
import cat.itb.redditapp.model.Community;
import cat.itb.redditapp.model.Post;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class PostFragment extends Fragment {
    Community c;
    TextView comPicker;
    TextView post;
    CircleImageView comPicture;
    TextInputEditText titlePost;
    TextInputEditText optionalPost;
    TextInputLayout textInputLayout;
    MaterialToolbar toolbar;
    String type;
    ImageView addImage;
    File url;
    Post p;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_post, container, false);
        comPicker = v.findViewById(R.id.com_picker);
        comPicture = v.findViewById(R.id.com_picture);
        post = v.findViewById(R.id.post);
        titlePost = v.findViewById(R.id.post_title);
        optionalPost = v.findViewById(R.id.post_optional_text);
        toolbar =v.findViewById(R.id.top_app_bar_community);
        textInputLayout = v.findViewById(R.id.textField2);
        addImage = v.findViewById(R.id.image_post);
        Bundle args = getArguments();

        if(args!=null){
            c = (Community) args.getSerializable("community");
            type = args.getString("type");


            if(c!=null) {
                Picasso.with(getContext()).load(c.getPicture()).into(comPicture);
                comPicker.setText("r/" + c.getName());
            }
        }
        comPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommunityListFragment fragment = new CommunityListFragment();
                Bundle args = new Bundle();
                args.putString("type", type);
                fragment.setArguments(args);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });
        if (type.equals("text")){
            textInputLayout.setVisibility(View.VISIBLE);
            addImage.setVisibility(View.GONE);
        }
        if(type.equals("image")){
            textInputLayout.setVisibility(View.GONE);
            addImage.setVisibility(View.VISIBLE);
        }
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.startPickImageActivity(getContext(), PostFragment.this);
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c==null){
                    Toast.makeText(getContext(),"Select a community", Toast.LENGTH_SHORT).show();

                }else{
                    final String title = titlePost.getText().toString();
                    String optional = null;
                    if(type.equals("text")) {
                        optional = optionalPost.getText().toString();
                    }
                    if(type.equals("image")){
                        if (url!=null){
                            DatabaseHelper.comprimirImagen(getContext(), url);
                            optional = DatabaseHelper.imageUrl;
                        }else {
                            Toast.makeText(getContext(), "You need to pick an image", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (title==null){
                        Toast.makeText(getContext(), "The title is obligatory", Toast.LENGTH_SHORT).show();
                    }else{

                        final String finalOptional = optional;
                        DatabaseHelper.readData(DatabaseHelper.usersRef, DatabaseHelper.mAuth.getCurrentUser().getUid(), null, new DatabaseHelper.MyCallback() {
                            @Override
                            public void onCallback(Object value) {
                                p = new Post(c.getCommunityId(), "anonymous", title, finalOptional, 0, 0, type);
                                HashMap<String,Object> hashMap = (HashMap<String, Object>) value;
                                p.setUser((String) hashMap.get("username"));


                                String key = DatabaseHelper.insertPost(p);

                                if(type.equals("image")){
                                    DatabaseHelper.comprimirImagen(getContext(), url);
                                    DatabaseHelper.subirImagen(key);
                                }
                                if (c.getPosts() == null) {
                                    c.setPosts(new ArrayList<Post>());
                                }
                                c.addPost(p);
                                DatabaseHelper.communityRef.child(c.getCommunityId()).setValue(c);
                                returnToMain();
                            }
                        });



                    }
                }
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMain();
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(getContext(), data);
            recortarImagen(imageUri, getContext(), PostFragment.this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                url = new File(resultUri.getPath());
                Picasso.with(getContext()).load(url).into(addImage);
            }
        }

    }

    public void recortarImagen(Uri imageUri, Context context, Fragment fragment) {
        CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(6, 8).start(context, fragment);
    }
    public void returnToMain(){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.remove(MainActivity.currentFragment);
        transaction.replace(R.id.fragment_container, new CardFragment());
        MainActivity.loginShow();
        transaction.commit();
    }

}