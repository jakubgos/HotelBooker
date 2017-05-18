package com.jgos.hotelBooker.map;

import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.jgos.hotelBooker.R;

/**
 * Created by KUBACM on 2017-03-31.
 */

public class BottomSheetDialog extends BottomSheetDialogFragment {
    private String title;
    @Override
    public void setupDialog(final Dialog dialog, int style){
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet,null);
        final String strtext = getArguments().getString("Parkingname");
        final Long parkingID = getArguments().getLong("Id");
        TextView txt = ((TextView) contentView.findViewById(R.id.sheet_parking));
        txt.setText(strtext);
        dialog.setContentView(contentView);

        final Button button = (Button) contentView.findViewById(R.id.button_add_sheet);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d("MyApp_","Wcisnieto przycisk parkingu: "+strtext+". Id: "+parkingID);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;

        window.setAttributes(windowParams);

    }

    public void setTitle(String title){
        this.title = title;
    }
}
