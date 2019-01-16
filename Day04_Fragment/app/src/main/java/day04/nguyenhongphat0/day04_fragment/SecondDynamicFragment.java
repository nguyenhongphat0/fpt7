package day04.nguyenhongphat0.day04_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondDynamicFragment extends Fragment {


    public SecondDynamicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        Button btnSub = getActivity().findViewById(R.id.btnSub);
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtResult = getActivity().findViewById(R.id.txtResult);
                EditText editNumber1 = getActivity().findViewById(R.id.editNumber1);
                EditText editNumber2 = getActivity().findViewById(R.id.editNumber2);
                int result = Integer.parseInt(editNumber1.getText().toString()) - Integer.parseInt(editNumber2.getText().toString());
                txtResult.setText("Result = " + result);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_dynamic, container, false);
    }

}
