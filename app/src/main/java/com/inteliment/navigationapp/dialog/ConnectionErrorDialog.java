package com.inteliment.navigationapp.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.inteliment.navigationapp.R;

public class ConnectionErrorDialog extends DialogFragment {

    View mView;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        mView=inflater.inflate(R.layout.alertdialog,null);
        ((TextView)mView.findViewById(R.id.messageText)).setText(R.string.please_check_internet_connection);
        ((TextView)mView.findViewById(R.id.headText)).setText(R.string.connection_error);
        builder.setView(mView)
                .setPositiveButton(R.string.okey, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    dismiss();
                    }
                });

        return builder.create();
    }
}
