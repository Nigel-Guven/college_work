package home.nigel.innovativecinemalistingsappv01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView link_one = findViewById(R.id.textView2);                   // Open link to Blog page
        link_one.setMovementMethod(LinkMovementMethod.getInstance());

        TextView link_two = findViewById(R.id.textView5);
        link_two.setMovementMethod(LinkMovementMethod.getInstance());       // Open link to Youtube Walkthrough

        TextView link_three = findViewById(R.id.textView4);
        link_three.setMovementMethod(LinkMovementMethod.getInstance());     // Open link to Gitlab Repository

    }
}
