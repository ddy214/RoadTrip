package mbd.org.roadtrip.Views.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mbd.org.roadtrip.R;
import mbd.org.roadtrip.Utils.FirebaseUtil;
import mbd.org.roadtrip.ViewModels.CreateGroupViewModel;
import mbd.org.roadtrip.ViewModels.GroupListViewModel;
import mbd.org.roadtrip.Views.Fragments.ConversationFragment;
import mbd.org.roadtrip.Views.Fragments.CreateGroupFragment;
import mbd.org.roadtrip.Views.Fragments.GroupsFragment;
import mbd.org.roadtrip.Views.Fragments.ScheduleFragment;

import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;

public class MainActivity extends BaseActivity implements GroupListViewModel.HandleTripButton, CreateGroupViewModel.HandleCreateTrip {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    RC_SIGN_IN
            );
        } else {
            addGroupsFragment();
            if(FirebaseAuth.getInstance().getCurrentUser().getDisplayName() == null){
                Toast.makeText(this,
                        "Welcome " + "Bing Xu",
                        Toast.LENGTH_LONG)
                        .show();
            }
            else {
                Toast.makeText(this,
                        "Welcome " + FirebaseAuth.getInstance()
                                .getCurrentUser()
                                .getDisplayName(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        }

    }


    private void addGroupsFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main, new GroupsFragment())
                .commit();
    }

    private void addCreateGroupFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main, new CreateGroupFragment())
                .addToBackStack(null).commit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN) {
            if(resultCode == RESULT_OK) {
                FirebaseUtil.getInstance().addUserInfo(FirebaseAuth.getInstance().getCurrentUser().getEmail());

                Toast.makeText(this,
                        "Successfully signed in. Welcome!",
                        Toast.LENGTH_LONG)
                        .show();
                addGroupsFragment();

            } else {
                Toast.makeText(this,
                        "We couldn't sign you in. Please try again later.",
                        Toast.LENGTH_LONG)
                        .show();

                // Close the app
                finish();
            }
        }
    }

    @Override
    public void handleButtonClick() {
        addCreateGroupFragment();
    }

    @Override
    public void handleTripButton() {
        addGroupsFragment();
        Toast.makeText(this, "Trip Created", Toast.LENGTH_LONG).show();
    }
}
