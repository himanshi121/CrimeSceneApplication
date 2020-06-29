package com.example.CrimeSceneApplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ReceiverCallNotAllowedException;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {
    private RecyclerView galleryRecyclerView;
    private StorageReference storage;
    private List<GalleryItemModal> galleryItemModalList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        galleryRecyclerView=findViewById(R.id.gallery_recycler_view);
        GridLayoutManager layoutManager =new GridLayoutManager(this,4);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        galleryRecyclerView.setLayoutManager(layoutManager);
        storage = FirebaseStorage.getInstance().getReference();

        final StorageReference ref = storage.child("images/");

        ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    galleryItemModalList.add(new GalleryItemModal(task.getResult()));
                }
                GalleryAdapter galleryAdapter=new GalleryAdapter(galleryItemModalList);
                galleryRecyclerView.setAdapter(galleryAdapter);
                galleryAdapter.notifyDataSetChanged();

            } else {
                    String error = task.getException().getMessage();
                    Toast.makeText(GalleryActivity.this, error, Toast.LENGTH_SHORT).show();
                }

            }
        });






        /*FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();
        firebaseFirestore.collection("images").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                galleryItemModalList.add(new GalleryItemModal(documentSnapshot.get("images").toString()));
                            }
                            GalleryAdapter galleryAdapter=new GalleryAdapter(galleryItemModalList);
                            galleryRecyclerView.setAdapter(galleryAdapter);
                            galleryAdapter.notifyDataSetChanged();


                        }else{
                            String error= task.getException().getMessage();
                            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/
    }


}
