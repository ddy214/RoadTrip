package mbd.org.roadtrip.Utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import java.util.Calendar;

import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.R;
import mbd.org.roadtrip.ViewModels.DialogCEViewModel;
import mbd.org.roadtrip.Views.Activities.BaseActivity;
import mbd.org.roadtrip.databinding.DialogLayoutBinding;


/**
 * Created by derekyu on 12/12/16.
 */

public class DialogGenerator {

    public static Dialog createEventDialog(Context context, String message, Chats chats){
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle(R.string.create_event_title);
        DialogLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_layout, null, false);

        final DialogCEViewModel viewModel = new DialogCEViewModel(context, message, chats);
        binding.setDialogViewModel(viewModel);
        builder.setView(binding.getRoot());
        builder.setNegativeButton(R.string.negative_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(R.string.positive_create, viewModel);
        return builder.create();
    }

    public static Dialog createTimeDialog(Context context, TimePickerDialog.OnTimeSetListener listener){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(context, listener, hour, minute, false);
    }

    public static Dialog createDateDialog(Context context, DatePickerDialog.OnDateSetListener listener, Chats chats){
        final Calendar c = Calendar.getInstance();
        Log.d("chats", chats+"");
        if(chats != null) {
            Log.d("hello", "hello");
            c.setTimeInMillis(chats.start);
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(context, listener, year, month, day);
    }
}
