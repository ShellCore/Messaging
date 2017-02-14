package com.shellcore.android.messaging.addContact.ui;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.shellcore.android.messaging.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddContactFragment extends DialogFragment implements AddContactView {

    // Componentes
    @Bind(R.id.edtAddEmail)
    EditText edtAddEmail;
    @Bind(R.id.tilAddEmail)
    TextInputLayout tilAddEmail;
    @Bind(R.id.barAddContact)
    ProgressBar barAddContact;

    public AddContactFragment() {
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_contact, null);
        ButterKnife.bind(this, view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        AlertDialog dialog = builder.create();

        return dialog;
    }

    @Override
    public void showInput() {
        tilAddEmail.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInput() {
        tilAddEmail.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        barAddContact.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        barAddContact.setVisibility(View.GONE);
    }

    @Override
    public void contactAdded() {
        Toast.makeText(getActivity(), R.string.addcontact_message_contactAdded, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void contactNotAdded() {
        edtAddEmail.setText("");
        edtAddEmail.setError(getString(R.string.addcontact_error_message));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
