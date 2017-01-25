package com.fundaciones.andrearodriguez.fundaciones.otroslist.ui;


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
import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.otroslist.OtrosListPresenter;
import com.fundaciones.andrearodriguez.fundaciones.otroslist.ui.adapter.OnItemClickListenerOtros;
import com.fundaciones.andrearodriguez.fundaciones.otroslist.ui.adapter.OtrostListAdapter;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtrosListFragment extends Fragment implements OtrosListView, OnItemClickListenerOtros {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.recyclerViewOtros)
    RecyclerView recyclerViewOtros;
    @Bind(R.id.progresBarAddOtros)
    ProgressBar progresBarAddOtros;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    @Inject
    OtrostListAdapter adapter;

    @Inject
    OtrosListPresenter presenter;

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1 ;

    public OtrosListFragment() {
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
        app.getOtrosListComponent(this, this, this).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_otros_list, container, false);
        ButterKnife.bind(this, v);
        setupToolbar();
        setupRecyclerView();
        presenter.subscribe();
        return v;
    }
    private void setupRecyclerView() {
        recyclerViewOtros.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewOtros.setAdapter(adapter);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void setupToolbar() {
        toolbar.setTitle(R.string.main_titles_otros);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void showList() {
        recyclerViewOtros.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerViewOtros.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progresBarAddOtros.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progresBarAddOtros.setVisibility(View.GONE);

    }

    @Override
    public void addOtros(Paticas paticas) {
        adapter.addOtros(paticas);
    }

    @Override
    public void removeOtros(Paticas paticas) {
        adapter.removeOtros(paticas);
    }

    @Override
    public void onOtrosError(String error) {
        Snackbar.make(mainContent, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onOtrosClick(Paticas paticas) {
//        Snackbar.make(mainContent, R.string.perroClick, Snackbar.LENGTH_SHORT).show();
    }
    @Override
    public void onShareclick(Paticas paticas, ImageView img) {
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
    public void onDeleteClick(Paticas paticas) {
        presenter.removeOtros(paticas);
    }

    @OnClick(R.id.fab)
    public void addOtros() {
        checkCameraPermission();
//        new AddOtrosFragment().show(getActivity().getSupportFragmentManager(), getString(R.string.addcontact_messagge_title));
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