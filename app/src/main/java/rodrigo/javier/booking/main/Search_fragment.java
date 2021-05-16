package rodrigo.javier.booking.main;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import rodrigo.javier.booking.R;
import rodrigo.javier.booking.fieldSearch.view.FieldSearchActivity;

public class Search_fragment extends Fragment {

    private View view;
    private Button btSearch;
    private EditText edtSearchCity;
    private EditText edtSearchNumberPeople, edtSearchDateIn, edtSearchDateOut;
    private DatePickerDialog pickerDialog;
    private TextInputLayout textInputLayoutCity, textInputLayoutPeople;

    private static String TAG = Search_fragment.class.getSimpleName();

    public Search_fragment() {

    }

    public static Search_fragment newInstance() {
        Search_fragment fragment = new Search_fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        initComponents();

        setDate(edtSearchDateIn);
        setDate(edtSearchDateOut);
        textInputLayoutCity.setErrorEnabled(false);

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtSearchCity.getText().toString().isEmpty() && !edtSearchNumberPeople.getText().toString().isEmpty()) {
                    String city = edtSearchCity.getText().toString();
                    String dateIn = edtSearchDateIn.getText().toString();
                    String dateOut = edtSearchDateOut.getText().toString();
                    String numPeople = edtSearchNumberPeople.getText().toString();
                    Intent intent = new Intent(getContext(), FieldSearchActivity.class);
                    intent.putExtra("city", city);
                    intent.putExtra("dateIn", dateIn);
                    intent.putExtra("dateOut", dateOut);
                    intent.putExtra("numPeople", numPeople);
                    startActivity(intent);
                }  else {
                    if (edtSearchCity.getText().toString().isEmpty() && edtSearchNumberPeople.getText().toString().isEmpty()){
                        textInputLayoutCity.setErrorEnabled(true);
                        textInputLayoutCity.setError("Introduzca ciudad");
                        textInputLayoutPeople.setError("Cantidad de personas");
                        Log.d(TAG, "[setError]->" + "Introduzca ciudad");
                    }
                    if (edtSearchCity.getText().toString().isEmpty() && !edtSearchNumberPeople.getText().toString().isEmpty()){
                        textInputLayoutCity.setError("Introduzca ciudad");
                        textInputLayoutPeople.setErrorEnabled(false);
                    }

                    if (!edtSearchCity.getText().toString().isEmpty() && edtSearchNumberPeople.getText().toString().isEmpty()){
                        textInputLayoutCity.setErrorEnabled(false);
                        textInputLayoutPeople.setError("Introduzca cantidad de personas");
                    }


                    }
            }
        });

        return view;
    }

    public void initComponents() {
        textInputLayoutCity = view.findViewById(R.id.search_activity_city);
        textInputLayoutPeople = view.findViewById(R.id.search_activity_people);
        btSearch = view.findViewById(R.id.btSearch);
        edtSearchCity = view.findViewById(R.id.edtSearchCity);
        edtSearchNumberPeople = view.findViewById(R.id.edtSearchNumberPeople);
        edtSearchDateIn = view.findViewById(R.id.edtSearchDateIn);
        edtSearchDateIn.setInputType(InputType.TYPE_NULL);
        edtSearchDateOut = view.findViewById(R.id.edtSearchDateOut);
        edtSearchDateOut.setInputType(InputType.TYPE_NULL);
    }

    public void setDate(EditText text) {
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                // DatePickerDialog
                pickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (dayOfMonth < 10 && monthOfYear < 9)
                            text.setText(year + "-0" + (monthOfYear + 1) + "-0" + dayOfMonth);

                        else if (dayOfMonth < 10 && monthOfYear > 8)
                            text.setText(year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth);

                        else if (dayOfMonth > 9 && monthOfYear < 9)
                            text.setText(year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);

                        else
                            text.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, year, month, day);
                pickerDialog.show();
            }
        });
    }


}