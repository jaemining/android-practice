package kr.co.fastcampus.android.chat;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String MESSAGES_CHILD = "messages";
    public static final String USERNAME = "jaemin";


    private RecyclerView mMessageRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private FirebaseRecyclerAdapter<FMessage, MessageViewHolder> mFirebaseAdapter;
    private ProgressBar mProgressBar;
    private DatabaseReference mFirebaseDatabaseReference;
    private EditText mMessageEditText;
    private Button mSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mMessageRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setStackFromEnd(true);

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<FMessage, MessageViewHolder>(
                FMessage.class,
                R.layout.item_message,
                MessageViewHolder.class,
                mFirebaseDatabaseReference.child(MESSAGES_CHILD))
        {
            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, FMessage message, int position) {
                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                viewHolder.messageTextView.setText(message.getText());
                viewHolder.messengerTextView.setText(message.getName());
                viewHolder.messengerImageView.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_account_circle_black_36dp));
            }
        };

        mMessageRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMessageRecyclerView.setAdapter(mFirebaseAdapter);

    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView messageTextView;
        public TextView messengerTextView;
        public CircleImageView messengerImageView;

        public MessageViewHolder(View v) {
            super(v);
            messageTextView = (TextView) itemView.findViewById(R.id.message_content);
            messengerTextView = (TextView) itemView.findViewById(R.id.sender);
            messengerImageView = (CircleImageView) itemView.findViewById(R.id.profile_image_view);
        }
    }
}
