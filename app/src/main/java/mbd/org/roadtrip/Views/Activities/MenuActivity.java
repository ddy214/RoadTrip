package mbd.org.roadtrip.Views.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.R;
import mbd.org.roadtrip.ViewModels.MenuViewModel;
import mbd.org.roadtrip.Views.Fragments.ConversationFragment;
import mbd.org.roadtrip.Views.Fragments.MenuFragment;
import mbd.org.roadtrip.Views.Fragments.ScheduleFragment;
import mbd.org.roadtrip.Views.Fragments.SettingsFragment;

import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;

public class MenuActivity extends AppCompatActivity implements MenuViewModel.ButtonPress{
    private Chats chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        chat = getIntent().getParcelableExtra("Chat");
        Log.d("chat", chat.destination);
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    RC_SIGN_IN
            );
        } else {
            addMenuFragment();

        }
    }

    private void addConversationFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_menu, ConversationFragment.getInstance(chat))
                .addToBackStack(null)
                .commit();
    }


    private void addMenuFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_menu, MenuFragment.getInstance(chat))
                .commit();
    }

    private void addSettingsFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_menu, SettingsFragment.getInstance(chat))
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void handleConversation() {
        addConversationFragment();
    }

    @Override
    public void handleSettings() {
        addSettingsFragment();
    }

    public static Intent getMenuActivity(Context context, Chats chat){
        Intent intent = new Intent(context, MenuActivity.class);
        intent.putExtra("Chat", chat);
        return intent;
    }
}
