package mbd.org.roadtrip.ViewModels;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

import mbd.org.roadtrip.BR;
import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.Utils.DialogGenerator;
import mbd.org.roadtrip.Utils.FirebaseUtil;
import mbd.org.roadtrip.databinding.FragmentConversationBinding;
import mbd.org.roadtrip.databinding.FragmentCreateGroupBinding;

/**
 * Created by derekyu on 12/14/16.
 */

public class CreateGroupViewModel extends BaseObservable implements DatePickerDialog.OnDateSetListener{
    private Context mContext;
    private FragmentCreateGroupBinding mBinding;
    private Calendar tripStart = Calendar.getInstance();
    private Calendar tripEnd = Calendar.getInstance();

    public CreateGroupViewModel(Context context, FragmentCreateGroupBinding binding){
        mContext = context;
        mBinding = binding;

    }

    private DatePickerDialog.OnDateSetListener getDateListener(){
        return this;
    }

    private DatePickerDialog.OnDateSetListener getDateEndListener(final Calendar endTrip){
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                endTrip.set(Calendar.YEAR, year);
                endTrip.set(Calendar.MONTH, month);
                endTrip.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                notifyPropertyChanged(BR.endDate);
            }
        };
    }


    public View.OnClickListener chooseDate(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogGenerator.createDateDialog(mContext, getDateListener(), null).show();
            }
        };
    }

    public View.OnClickListener chooseEndDate(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogGenerator.createDateDialog(mContext, getDateEndListener(tripEnd), null).show();
            }
        };
    }


    @Bindable
    public String getDate(){
        return (tripStart.get(Calendar.MONTH)+1)
                +"/"+(tripStart.get(Calendar.DAY_OF_MONTH)
                +"/"+tripStart.get(Calendar.YEAR));
    }

    @Bindable
    public String getEndDate(){
        return (tripEnd.get(Calendar.MONTH)+1)
                +"/"+(tripEnd.get(Calendar.DAY_OF_MONTH)
                +"/"+tripEnd.get(Calendar.YEAR));
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        tripStart.set(Calendar.YEAR, year);
        tripStart.set(Calendar.MONTH, month);
        tripStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        notifyPropertyChanged(BR.date);
    }

    public View.OnClickListener createGroup(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mBinding.editText4.getText().toString();
                String destination = mBinding.editText5.getText().toString();
                Chats chats = new Chats();
                chats.title = name;
                chats.destination = destination;
                chats.start = tripStart.getTimeInMillis();
                chats.end = tripEnd.getTimeInMillis();
                FirebaseUtil.getInstance().addNewGroup(chats);
                ((HandleCreateTrip) mContext).handleTripButton();
            }
        };
    }

    public interface HandleCreateTrip{
        public void handleTripButton();
    }

}
