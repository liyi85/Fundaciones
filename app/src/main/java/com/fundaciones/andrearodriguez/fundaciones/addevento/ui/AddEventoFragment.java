package com.fundaciones.andrearodriguez.fundaciones.addevento.ui;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.fundaciones.andrearodriguez.fundaciones.FundacionesApp;
import com.fundaciones.andrearodriguez.fundaciones.R;
import com.fundaciones.andrearodriguez.fundaciones.addevento.AddEventoPresenter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddEventoFragment extends DialogFragment implements DialogInterface.OnShowListener, AddEventoView, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @Bind(R.id.imgEvent)
    ImageButton imgEvent;
    @Bind(R.id.txtNombreE)
    EditText txtNombreE;
    @Bind(R.id.wrapernameperro)
    TextInputLayout wrapernameperro;
    @Bind(R.id.txtLugar)
    EditText txtLugar;
    @Bind(R.id.wraperlugar)
    TextInputLayout wraperlugar;
    @Bind(R.id.etFecha)
    EditText etFecha;
    @Bind(R.id.txtTipo)
    TextView txtTipo;
    @Bind(R.id.spinnerTipo)
    Spinner spinnerTipo;
    @Bind(R.id.containerAddE)
    FrameLayout containerAddE;
    @Bind(R.id.etHora)
    EditText etHora;

    private String photoPath;
    private FundacionesApp app;
    Calendar myCalendar = Calendar.getInstance();

    ArrayAdapter<CharSequence> adapterTipo;


    @Inject
    AddEventoPresenter addEventoPresenter;

    private static final int REQUEST_PICTURE = 1;

    public AddEventoFragment() {
        // Required empty public constructor
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

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_row_eventos_get_data, null);
        ButterKnife.bind(this, view);

        app = (FundacionesApp) getActivity().getApplication();
        setupinjection();
        adapterClase();

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);
        return dialog;

    }


    private void adapterClase() {
        adapterTipo = ArrayAdapter.createFromResource(getContext(), R.array.SelectTipo, android.R.layout.simple_spinner_item);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapterTipo);
        spinnerTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        addEventoPresenter.onCreate();
        super.onActivityCreated(savedInstanceState);
    }

    private void setupinjection() {
        app.getAddEventoComponent(this).inject(this);
    }


    @Override
    public void onDestroy() {
        addEventoPresenter.onDestroy();
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
                    String nombre = txtNombreE.getText().toString();
                    String fecha = etFecha.getText().toString();
                    String hora = etHora.getText().toString();
                    String lugar = txtLugar.getText().toString();
                    String tipoe = spinnerTipo.getSelectedItem().toString();
                    String path = photoPath;

                    if (nombre == "" || fecha == "" || hora == "" || lugar == "" || tipoe == "" || path == null) {
                        Snackbar.make(containerAddE, "Todos los datos son necesarios", Snackbar.LENGTH_LONG).show();
                    } else if (nombre != "" && fecha != "" && hora != "" && lugar != "" && tipoe != "" && path != null) {
                        addEventoPresenter.uploadPhoto(nombre, fecha, lugar, hora, photoPath, tipoe);
                        Log.i("que sale del evento", "" + nombre + " " + fecha + " " + hora + " " + lugar + " " + photoPath + " " + tipoe);
                    }

                    dismiss();
                }
            });

            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
        int targetW = imgEvent.getWidth();
        int targetH = imgEvent.getHeight();

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
        imgEvent.setImageBitmap(bitmap);
    }

    @OnClick(R.id.imgEvent)
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
            Snackbar.make(containerAddE, R.string.main_error_dispatch_camera, Snackbar.LENGTH_SHORT).show();
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
        Snackbar.make(containerAddE, msg, Snackbar.LENGTH_SHORT).show();
    }

    private void showSnackbar(int strResult) {
        Snackbar.make(containerAddE, strResult, Snackbar.LENGTH_SHORT).show();
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


    @OnClick({R.id.etFecha, R.id.etHora})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.etFecha:
                showDatePickerDialog();
                break;

            case R.id.etHora:
                showTimePickerDialog();
                break;

        }
    }

    private void showTimePickerDialog() {

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), time,
                                                myCalendar.get(Calendar.HOUR),
                                                myCalendar.get(Calendar.MINUTE),
                                                false);
        timePickerDialog.show();
    }

    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalendar.set(Calendar.HOUR, hourOfDay);
            myCalendar.set(Calendar.MINUTE, minute);
            etHora.setText(hourOfDay + ":" + minute);
        }
    };

    public void showDatePickerDialog() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date,
                                                myCalendar.get(Calendar.YEAR),
                                                myCalendar.get(Calendar.MONTH),
                                                myCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            etFecha.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
            String date = etFecha.getText().toString();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy"); // input date
            Date outDate = null;
            try {
                outDate = dateFormatter.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Locale esLocale = new Locale("es", "ES");

            SimpleDateFormat dayFormatter = new SimpleDateFormat("dd"); // output day
            SimpleDateFormat monthFormatter = new SimpleDateFormat("MMM", esLocale); // output month
            SimpleDateFormat yearFormatter = new SimpleDateFormat("yyyy"); // output year

            String day = dayFormatter.format(outDate);
            String monthy = monthFormatter.format(outDate);
            String years = yearFormatter.format(outDate);

            etFecha.setText(day + " " + monthy + " " + years);
        }

    };


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }
}
