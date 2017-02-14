package com.shellcore.android.messaging.addContact.ui;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.shellcore.android.messaging.R;
import com.shellcore.android.messaging.addContact.AddContactPresenter;
import com.shellcore.android.messaging.addContact.AddContactPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddContactFragment extends DialogFragment implements AddContactView, DialogInterface.OnShowListener {

    // Servicios
    private AddContactPresenter presenter;

    // Componentes
    @Bind(R.id.edtAddEmail)
    EditText edtAddEmail;
    @Bind(R.id.tilAddEmail)
    TextInputLayout tilAddEmail;
    @Bind(R.id.barAddContact)
    ProgressBar barAddContact;

    public AddContactFragment() {
        this.presenter = new AddContactPresenterImpl(this);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.addcontact_message_title)
                .setPositiveButton(R.string.addcontact_message_add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(R.string.addcontact_message_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_contact, null);
        ButterKnife.bind(this, view);

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);

        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onShow(DialogInterface dialog) {
        final AlertDialog alertDialog = (AlertDialog) getDialog();
        if (dialog != null) {
            Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
            Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);

            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = edtAddEmail.getText().toString();
                    if (!email.isEmpty()) {
                        presenter.addContact(email);
                    }
                }
            });

            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
        presenter.onShow();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
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
        dismiss();
    }

    @Override
    public void contactNotAdded() {
        edtAddEmail.setText("");
        edtAddEmail.setError(getString(R.string.addcontact_error_message));
    }
}
