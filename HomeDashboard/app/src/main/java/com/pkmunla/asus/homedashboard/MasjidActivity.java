package com.pkmunla.asus.homedashboard;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.ByteArrayOutputStream;

public class MasjidActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masjid);

        //action bar
        ActionBar actionBar = getSupportActionBar();

        //title action bar
        actionBar.setTitle("MASJID");

        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView3);
        mRecyclerView.setHasFixedSize(true);

        //set layout as linearlayout
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //send query to firebasedatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Data");
    }

    //search data
    private void firebaseSearch(String searchText){
        Query firebaseSearchQuery = mRef.orderByChild("masjid").startAt(searchText).endAt(searchText+"\uf8ff");

        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.row3,
                        ViewHolder.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getTitle(), model.getImage(), model.getLocation(), model.getDescription(), model.getUstad(), model.getMasjid());
                    }
                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder =  super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //view
                                TextView mMasjidTv = view.findViewById(R.id.rMasjidTV);
                                TextView mTitleTv = view.findViewById(R.id.rTitleTV);
                                TextView mDescTV = view.findViewById(R.id.rDescriptionTV);
                                TextView mUstadTV = view.findViewById(R.id.rUstadzTV);
                                TextView mLocationTv = view.findViewById(R.id.rLocationTV);
                                ImageView mImageTv = view.findViewById(R.id.rImageView);
                                //get data from view
                                String mMasjid = mMasjidTv.getText().toString();
                                String mTitle = mTitleTv.getText().toString();
                                String mDesc = mDescTV.getText().toString();
                                String mUstad = mUstadTV.getText().toString();
                                String mLocation = mLocationTv.getText().toString();
                                Drawable mDrawable = mImageTv.getDrawable();
                                Bitmap mBitmap = ((BitmapDrawable)mDrawable).getBitmap();

                                //pass this data to new Intent
                                Intent intent = new Intent(view.getContext(), MasjidDetailsActivity.class);
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                mBitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
                                byte[] bytes = stream.toByteArray();
                                intent.putExtra("masjid", mMasjid);
                                intent.putExtra("title", mTitle);
                                intent.putExtra("description", mDesc);
                                intent.putExtra("ustad", mUstad);
                                intent.putExtra("location", mLocation);
                                intent.putExtra("image", bytes);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });
                        return viewHolder;
                    }

                };
        //set adapter to recyclerview
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu; this adds item to action bar if it prsent
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //filter as you type
                firebaseSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //handle action bar item click here
        if (id==R.id.setting){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.row3,
                        ViewHolder.class,
                        mRef
                ){
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position){
                        viewHolder.setDetails(getApplicationContext(), model.getTitle(), model.getImage(), model.getLocation(), model.getDescription(), model.getUstad(), model.getMasjid());
                    }
                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder =  super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //view
                                TextView mMasjidTv = view.findViewById(R.id.rMasjidTV);
                                TextView mTitleTv = view.findViewById(R.id.rTitleTV);
                                TextView mDescTV = view.findViewById(R.id.rDescriptionTV);
                                TextView mUstadTV = view.findViewById(R.id.rUstadzTV);
                                TextView mLocationTv = view.findViewById(R.id.rLocationTV);
                                ImageView mImageTv = view.findViewById(R.id.rImageView);
                                //get data from view
                                String mMasjid = mMasjidTv.getText().toString();
                                String mTitle = mTitleTv.getText().toString();
                                String mDesc = mDescTV.getText().toString();
                                String mUstad = mUstadTV.getText().toString();
                                String mLocation = mLocationTv.getText().toString();
                                Drawable mDrawable = mImageTv.getDrawable();
                                Bitmap mBitmap = ((BitmapDrawable)mDrawable).getBitmap();

                                //pass this data to new Intent
                                Intent intent = new Intent(view.getContext(), MasjidDetailsActivity.class);
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                mBitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
                                byte[] bytes = stream.toByteArray();
                                intent.putExtra("masjid", mMasjid);
                                intent.putExtra("title", mTitle);
                                intent.putExtra("description", mDesc);
                                intent.putExtra("ustad", mUstad);
                                intent.putExtra("location", mLocation);
                                intent.putExtra("image", bytes);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });
                        return viewHolder;
                    }

                };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

}
