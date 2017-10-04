package com.fundaciones.andrearodriguez.fundaciones.addperro.ui;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.fundaciones.andrearodriguez.fundaciones.FundacionesApp;
import com.fundaciones.andrearodriguez.fundaciones.R;
import com.fundaciones.andrearodriguez.fundaciones.addperro.AddPerroPresenter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPerroFragment extends DialogFragment implements DialogInterface.OnShowListener, AddPerroView {


    @Bind(R.id.imgDog)
    ImageButton imgDog;
    @Bind(R.id.txtNombre)
    EditText txtNombre;
    @Bind(R.id.wrapernameperro)
    TextInputLayout wrapernameperro;
    @Bind(R.id.txtEdad)
    EditText txtEdad;
    @Bind(R.id.wraperedad)
    TextInputLayout wraperedad;
    @Bind(R.id.txtSexo)
    TextView txtSexo;
    @Bind(R.id.spinnerSexo)
    Spinner spinnerSexo;
    @Bind(R.id.txtTamano)
    TextView txtTamano;
    @Bind(R.id.spinnerTamano)
    Spinner spinnerTamano;
    @Bind(R.id.txtEsteril)
    TextView txtEsteril;
    @Bind(R.id.spinnerEsteril)
    Spinner spinnerEsteril;
    @Bind(R.id.txtVacunado)
    TextView txtVacunado;
    @Bind(R.id.spinnerVacuna)
    Spinner spinnerVacuna;
    @Bind(R.id.containerAddP)
    FrameLayout containerAddP;
    @Bind(R.id.spinnerEdad)
    Spinner spinnerEdad;
    @Bind(R.id.txtDiscapacidad)
    TextView txtDiscapacidad;
    @Bind(R.id.spinnerDiscapacidad)
    Spinner spinnerDiscapacidad;

    private String photoPath;
    private FundacionesApp app;

    ArrayAdapter<CharSequence> adapterEsteril;
    ArrayAdapter<CharSequence> adapterTamano;
    ArrayAdapter<CharSequence> adapterVacunado;
    ArrayAdapter<CharSequence> adapterSexo;
    ArrayAdapter<CharSequence> adapterEdad;
    ArrayAdapter<CharSequence> adapterDiscapacidad;


    @Inject
    AddPerroPresenter addPerroPresenter;

    private static final int REQUEST_PICTURE = 1;

    public AddPerroFragment() {

    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.addcontact_messagge_title)
                .setPositiveButton(R.string.addcontact_messagge_add, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setNegativeButton(R.string.addcontact_messagge_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_row_perro_get_data, null);
        ButterKnife.bind(this, view);


        app = (FundacionesApp) getActivity().getApplication();
        setupinjection();
        adapterEstrilizacion();
        adapterEstatura();
        adapterGenero();
        adapterVacunacion();
        adapterAños();
        adapterDiscapacitado();
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);
        return dialog;

    }

    private void adapterAños() {
        adapterEdad = ArrayAdapter.createFromResource(getContext(), R.array.SelectEdad, android.R.layout.simple_spinner_item);
        adapterEdad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEdad.setAdapter(adapterEdad);
        spinnerEdad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void adapterVacunacion() {
        adapterVacunado = ArrayAdapter.createFromResource(getContext(), R.array.SelectVacuna, android.R.layout.simple_spinner_item);
        adapterVacunado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVacuna.setAdapter(adapterVacunado);
        spinnerVacuna.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void adapterGenero() {
        adapterSexo = ArrayAdapter.createFromResource(getContext(), R.array.SelectSexo, android.R.layout.simple_spinner_item);
        adapterSexo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSexo.setAdapter(adapterSexo);
        spinnerSexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void adapterEstatura() {
        adapterTamano = ArrayAdapter.createFromResource(getContext(), R.array.SelectTamano, android.R.layout.simple_spinner_item);
        adapterTamano.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTamano.setAdapter(adapterTamano);
        spinnerTamano.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void adapterEstrilizacion() {

        adapterEsteril = ArrayAdapter.createFromResource(getContext(), R.array.SelectEsteril, android.R.layout.simple_spinner_item);
        adapterEsteril.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEsteril.setAdapter(adapterEsteril);
        spinnerEsteril.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void adapterDiscapacitado() {

        adapterDiscapacidad = ArrayAdapter.createFromResource(getContext(), R.array.SelectDiscapa, android.R.layout.simple_spinner_item);
        adapterDiscapacidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDiscapacidad.setAdapter(adapterDiscapacidad);
        spinnerDiscapacidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        addPerroPresenter.onCreate();
        super.onActivityCreated(savedInstanceState);
    }

    private void setupinjection() {
        app.getAddPerroComponent(this).inject(this);
    }


    @Override
    public void onDestroy() {
        addPerroPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {
        final AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog != null) {
            final Button positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
            Button negativeButton = dialog.getButton(Dialog.BUTTON_NEGATIVE);

            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String nombre = txtNombre.getText().toString();
                    String edad = txtEdad.getText().toString() + " " + spinnerEdad.getSelectedItem().toString().trim();
                    String sexo = spinnerSexo.getSelectedItem().toString();
                    String tamano = spinnerTamano.getSelectedItem().toString();
                    String esteril = spinnerEsteril.getSelectedItem().toString();
                    String vacuna = spinnerVacuna.getSelectedItem().toString();
                    String discapacidad = spinnerDiscapacidad.getSelectedItem().toString();
                    String path = photoPath;

                    if (nombre == "" || edad == " Años" || sexo == "Hembra" || path == null) {
                        Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.ingresar_datos, 3000).show();
                    } else if (nombre != "" && edad != " Años" && sexo != "Hembra" && tamano != null && esteril != null && vacuna != null && path != null) {
                        addPerroPresenter.uploadPhoto(nombre, edad, sexo, tamano, photoPath, esteril, vacuna, discapacidad);
                        Log.i("datosPerro", "" + nombre + " " + edad + " " + sexo + " " + tamano + " " + photoPath + " " + esteril + " " + vacuna);
                    }


                    dismiss();
                }
            });

            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                    Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.cancelado , 3000).show();
                    dismiss();
                }
            });
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PICTURE) {
            if (resultCode == getActivity().RESULT_OK) {
                boolean fromCamera = (data == null || data.getData() == null);
                if (fromCamera) {
                    addToGallery();
                    setpic();
                } else {
                    photoPath = getRealPathFromURI(data.getData());
                    setpic();
                }

            }

        }
    }

    private void setpic() {
        int targetW = imgDog.getWidth();
        int targetH = imgDog.getHeight();

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(photoPath, bmOptions);
        imgDog.setImageBitmap(bitmap);
    }


    @OnClick(R.id.imgDog)
    public void takePicture() {

        Intent chooserIntent = null;

        List<Intent> intentList = new ArrayList<>();
        Intent pickIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        Intent cameraInten = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraInten.putExtra("return-data", true);

        File photoFile = getFile();
        if (photoFile != null) {
            cameraInten.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));

            if (cameraInten.resolveActivity(getActivity().getPackageManager()) != null) {
                intentList = addIntentsToList(intentList, cameraInten);
            }
        }
        if (pickIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            intentList = addIntentsToList(intentList, pickIntent);
        }
        if (intentList.size() > 0) {
            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1),
                    getString(R.string.main_message_picture_source));
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
        }
        if (chooserIntent != null) {
            startActivityForResult(chooserIntent, REQUEST_PICTURE);
        }

    }

    private File getFile() {
        File photoFile = null;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        try {
            photoFile = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException e) {
            Snackbar.make(containerAddP, R.string.main_error_dispatch_camera, Snackbar.LENGTH_SHORT).show();
        }
        photoPath = photoFile.getAbsolutePath();
        return photoFile;
    }

    private List<Intent> addIntentsToList(List<Intent> list, Intent intent) {
        List<ResolveInfo> resInfo = getActivity().getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resInfo) {
            String packageName = resolveInfo.activityInfo.packageName;
            Intent targetIntent = new Intent(intent);
            targetIntent.setPackage(packageName);
            list.add(targetIntent);
        }
        return list;
    }

    private void showSnackbar(String msg) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), msg, 3000).show();
    }

    private void showSnackbar(int strResult) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), strResult, 3000).show();
    }

    private void addToGallery() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File file = new File(photoPath);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);


    }

    private String getRealPathFromURI(Uri contentURI) {
        String result = null;
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            if (contentURI.toString().contains("mediaKey")) {
                cursor.close();

                try {
                    File file = File.createTempFile("tempImgP", ".jpg", getActivity().getCacheDir());
                    InputStream input = getActivity().getContentResolver().openInputStream(contentURI);
                    OutputStream output = new FileOutputStream(file);

                    try {
                        byte[] buffer = new byte[4 * 1024];
                        int read;

                        while ((read = input.read(buffer)) != -1) {
                            output.write(buffer, 0, read);
                        }
                        output.flush();
                        result = file.getAbsolutePath();
                    } finally {
                        output.close();
                        input.close();
                    }

                } catch (Exception e) {
                }
            } else {
                cursor.moveToFirst();
                int dataColumn = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                result = cursor.getString(dataColumn);
                cursor.close();
            }

        }
        return result;
    }


    @Override
    public void onUploadInit() {
        showSnackbar(R.string.main_notice_upload_init);
    }

    @Override
    public void onUploadComplete() {
        showSnackbar(R.string.main_notice_upload_complete);
    }

    @Override
    public void onUploadError(String error) {
        showSnackbar(error);
    }


}