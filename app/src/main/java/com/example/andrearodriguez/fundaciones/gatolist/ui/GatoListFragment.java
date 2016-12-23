package com.example.andrearodriguez.fundaciones.gatolist.ui;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.example.andrearodriguez.fundaciones.FundacionesApp;
import com.example.andrearodriguez.fundaciones.R;
import com.example.andrearodriguez.fundaciones.addgato.ui.AddGatoFragment;
import com.example.andrearodriguez.fundaciones.entities.Paticas;
import com.example.andrearodriguez.fundaciones.gatolist.GatoListPresenter;
import com.example.andrearodriguez.fundaciones.gatolist.ui.adapter.GatoListAdapater;
import com.example.andrearodriguez.fundaciones.gatolist.ui.adapter.OnItemClickListener;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class GatoListFragment extends Fragment implements GatoLisView, OnItemClickListener {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.recyclerViewGato)
    RecyclerView recyclerViewGato;
    @Bind(R.id.progresBarAddGato)
    ProgressBar progresBarAddGato;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    @Inject
    GatoListAdapater adapter;

    @Inject
    GatoListPresenter presenter;

    public GatoListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupInjection();
        presenter.onCreate();
    }



    private void setupInjection() {
        FundacionesApp app = (FundacionesApp) getActivity().getApplication();
        app.getGatoListComponent(this, this, this).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gato_list, container, false);
        ButterKnife.bind(this, v);
        setupToolbar();
        setupRecyclerView();
        presenter.subscribe();
        return v;



    }

    private void setupRecyclerView() {
        recyclerViewGato.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewGato.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private void setupToolbar() {
        toolbar.setTitle("gatos");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void showList() {
        recyclerViewGato.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerViewGato.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progresBarAddGato.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progresBarAddGato.setVisibility(View.GONE);
    }

    @Override
    public void addGato(Paticas paticas) {
        adapter.addGato(paticas);

    }

    @Override
    public void removeGato(Paticas paticas) {
        adapter.removeGato(paticas);
    }

    @Override
    public void onGatoError(String error) {
        Snackbar.make(mainContent, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onGatoClick(Paticas paticas) {

    }

    @Override
    public void onShareclick(Paticas paticas, ImageView img) {
        Bitmap bitmap = ((GlideBitmapDrawable) img.getDrawable()).getBitmap();
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, null, null);
        Uri imageUri = Uri.parse(path);

        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(share, getString(R.string.photolist_message_share)));
    }

    @Override
    public void onDeleteClick(Paticas paticas) {
        presenter.removeGato(paticas);
    }

    @OnClick(R.id.fab)
    public void addGato() {
        new AddGatoFragment().show(getActivity().getSupportFragmentManager(), getString(R.string.addcontact_messagge_title));
    }



}
