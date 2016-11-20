package mbd.org.roadtrip.Views.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mbd.org.roadtrip.R;
import mbd.org.roadtrip.Views.Fragments.ConversationFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addConversationFragment();
    }

    private void addConversationFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main, new ConversationFragment())
                .commit();
    }


}
