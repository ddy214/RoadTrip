package mbd.org.roadtrip;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void queryFirebaseDate() throws Exception {
        Query query = FirebaseDatabase.getInstance().getReference().child("73w8UeiZSjcBSyN486oDpBuDToJ2");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Test
    public void testCalendar() throws Exception {
        Long l1 = Long.valueOf("1481680874089");
        Long l2 = Long.valueOf("1481853674089");
        Calendar cal = Calendar.getInstance();
        System.out.println(Integer.parseInt(((l2-l1)/(1000 * 60 * 60 * 24))+""));
        Calendar cal2 = Calendar.getInstance();
        cal.setTimeInMillis(l1);
        cal2.setTimeInMillis(l2);
        String months =  cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US);
        System.out.println(months);
        System.out.println(cal.get(Calendar.DAY_OF_MONTH)+"" + cal.get(Calendar.MONTH)+"");
        System.out.println(cal2.get(Calendar.DAY_OF_MONTH)+""+ cal2.get(Calendar.MONTH)+"");
    }
}