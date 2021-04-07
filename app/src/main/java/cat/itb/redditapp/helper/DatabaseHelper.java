package cat.itb.redditapp.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.storage.StorageManager;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import cat.itb.redditapp.model.Community;
import cat.itb.redditapp.model.Post;
import id.zelory.compressor.Compressor;

public class DatabaseHelper {
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference communityRef = database.getReference("Community");
    public static DatabaseReference postRef = database.getReference("Post");
    static StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    static StorageReference storageRefCommunity = storageRef.child("community_pictures");
    static StorageReference storageRefPost = storageRef.child("post_pictures");
    static Bitmap thum_bitmap;
    static byte[] thumb_byte;
    static String imageUrl;
    static Community c;


    public static void insertCommunity(Community c, String title ,String picture) throws IOException {
        String key = communityRef.push().getKey();
        c.setCommunityId(key);
        c.setName(title);
        imageUrl = picture;
        c.setPicture(imageUrl);
        communityRef.child(key).setValue(c);
    }

    public static void insertPost(Post p){
        String key = postRef.push().getKey();
        p.setPostId(key);
        postRef.child(key).setValue(p);
    }
    public static void subirImagenCommunity(File f, Context context,String title){
        comprimirImagen(f, context);
        storageRefCommunity.child(title);
        UploadTask uploadTask = storageRef.putBytes(thumb_byte);
        Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    System.out.println("Imposible");
                }
                return storageRefCommunity.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                Uri downloadUri = task.getResult();
                imageUrl = downloadUri.toString();

            }
        });
    }
    public static void comprimirImagen(File f, Context c){
        try {
            thum_bitmap = new Compressor(c)
                    .compressToBitmap(f);
        } catch (IOException e) {
            System.out.println("Este es el fallo: "+ e);
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        thum_bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
        thumb_byte = byteArrayOutputStream.toByteArray();
    }
    public static DatabaseReference getCommunityRef(){
        return communityRef;
    }
    public static Community getCommunity(String id){

        communityRef.child("/"+id).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                c = dataSnapshot.getValue(Community.class);
            }
        });
        return c;
    }
}
