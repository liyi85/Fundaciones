package com.fundaciones.andrearodriguez.fundaciones.eventoslist.ui;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.fundaciones.andrearodriguez.fundaciones.FundacionesApp;
import com.fundaciones.andrearodriguez.fundaciones.R;
import com.fundaciones.andrearodriguez.fundaciones.addevento.ui.AddEventoFragment;
import com.fundaciones.andrearodriguez.fundaciones.entities.Eventos;
import com.fundaciones.andrearodriguez.fundaciones.eventoslist.EventosListPresenter;
import com.fundaciones.andrearodriguez.fundaciones.eventoslist.ui.adapter.EventosListAdapter;
import com.fundaciones.andrearodriguez.fundaciones.eventoslist.ui.adapter.OnItemClickListenerEvento;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventosListFragment extends Fragment implements EventosListView, OnItemClickListenerEvento {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.recyclerViewEventos)
    RecyclerView recyclerViewEventos;
    @Bind(R.id.progresBarAddEvento)
    ProgressBar progresBarAddEvento;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    @Inject
    EventosListAdapter adapter;

    @Inject
    EventosListPresenter presenter;


    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1 ;

    public EventosListFragment() {
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
        app.getEventoListComponent(this, this, this).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_eventos_list, container, false);
        ButterKnife.bind(this, v);
        setupToolbar();
        setupRecyclerView();
        presenter.subscribe();
        return v;
    }

    private void setupRecyclerView() {
        recyclerViewEventos.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewEventos.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void setupToolbar() {
        toolbar.setTitle(R.string.main_titles_eventos);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void showList() {
        recyclerViewEventos.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerViewEventos.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progresBarAddEvento.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progresBarAddEvento.setVisibility(View.GONE);

    }

    @Override
    public void addEvento(Eventos eventos) {
        adapter.addEvento(eventos);
    }

    @Override
    public void removeEvento(Eventos eventos) {
        adapter.removeEvento(eventos);
    }

    @Override
    public void onEventoError(String error) {
        Snackbar.make(mainContent, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onEventoClick(Eventos eventos) {

    }

    @Override
    public void onShareclick(Eventos eventos, ImageView img) {
        try{
            Drawable dr = ((ImageView) img).getDrawable();
            Bitmap bitmap =  ((GlideBitmapDrawable)dr.getCurrent()).getBitmap();
            //Bitmap bitmap = ((GlideBitmapDrawable)img.getDrawable()).getBitmap();
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/jpeg");

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, null, null);
            Uri imageUri =  Uri.parse(path);

            share.putExtra(Intent.EXTRA_STREAM, imageUri);
            startActivity(Intent.createChooser(share, getString(R.string.photolist_message_share)));
        }catch (Exception e){
            Toast.makeText(getActivity(), R.string.cargando_fotos, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onDeleteClick(Eventos eventos) {

        presenter.removeEvento(eventos);
    }

    @OnClick(R.id.fab)
    public void addEvento() {
        checkCameraPermission();
        new AddEventoFragment().show(getActivity().getSupportFragmentManager(), getString(R.string.addcontact_messagge_title));
    }

    private void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CAMERA)) {

            } else {

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);

            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }
        }
    }
}
