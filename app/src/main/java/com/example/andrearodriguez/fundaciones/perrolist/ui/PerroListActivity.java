package com.example.andrearodriguez.fundaciones.perrolist.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.example.andrearodriguez.fundaciones.FundacionesApp;
import com.example.andrearodriguez.fundaciones.R;
import com.example.andrearodriguez.fundaciones.addperro.ui.AddPerroFragment;
import com.example.andrearodriguez.fundaciones.entities.Paticas;
import com.example.andrearodriguez.fundaciones.perrolist.PerroListPresenter;
import com.example.andrearodriguez.fundaciones.perrolist.ui.adapter.OnItemClickListener;
import com.example.andrearodriguez.fundaciones.perrolist.ui.adapter.PerroListAdapter;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by andrearodriguez on 9/25/16.
 */
public class PerroListActivity extends AppCompatActivity implements PerroListView, OnItemClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.recyclerViewPerro)
    RecyclerView recyclerViewPerro;
    @Bind(R.id.progresBarAddPerro)
    ProgressBar progresBarAddPerro;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    @Inject
    PerroListAdapter adapter;

    @Inject
    PerroListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perro);
        ButterKnife.bind(this);
        setupToolbar();
        setupInjection();
        setupRecyclerView();
        presenter.onCreate();
        presenter.subscribe();
    }

    private void setupRecyclerView() {
        recyclerViewPerro.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPerro.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        presenter.unsubscribe();
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {
        FundacionesApp app = (FundacionesApp) getApplication();
        app.getPerroLisComponent(this, this, this).inject(this);
    }

    private void setupToolbar() {
        toolbar.setTitle("perros");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fav, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @OnClick(R.id.fab)
    public void addPerro() {
        new AddPerroFragment().show(getSupportFragmentManager(), getString(R.string.addcontact_messagge_title));
    }

    @Override
    public void showList() {
        recyclerViewPerro.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerViewPerro.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progresBarAddPerro.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progresBarAddPerro.setVisibility(View.GONE);

    }

    @Override
    public void addPerro(Paticas paticas) {
        adapter.addPerro(paticas);
    }

    @Override
    public void removePerro(Paticas paticas) {
        adapter.removePerro(paticas);
    }

    @Override
    public void onPerroError(String error) {
        Snackbar.make(mainContent, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onPerroClick(Paticas paticas) {
        Snackbar.make(mainContent, R.string.perroClick, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onShareclick(Paticas paticas, ImageView img) {
        Bitmap bitmap = ((GlideBitmapDrawable) img.getDrawable()).getBitmap();
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null);
        Uri imageUri = Uri.parse(path);

        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(share, getString(R.string.photolist_message_share)));
    }

    @Override
    public void onDeleteClick(Paticas paticas) {
        presenter.removePerro(paticas);
    }
}
