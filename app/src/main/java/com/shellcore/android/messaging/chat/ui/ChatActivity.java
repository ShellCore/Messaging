package com.shellcore.android.messaging.chat.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shellcore.android.messaging.R;
import com.shellcore.android.messaging.chat.ChatPresenter;
import com.shellcore.android.messaging.chat.ChatPresenterImpl;
import com.shellcore.android.messaging.chat.ui.adapters.ChatAdapter;
import com.shellcore.android.messaging.domain.AvatarHelper;
import com.shellcore.android.messaging.entities.ChatMessage;
import com.shellcore.android.messaging.libs.GlideImageLoader;
import com.shellcore.android.messaging.libs.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity implements ChatView {

    // Constantes
    public static final String EMAIL_KEY = "email";
    public static final String ONLINE_KEY = "online";

    // Variables
    private ChatAdapter adapter;

    // Servicios
    private ChatPresenter presenter;

    // Componentes
    @Bind(R.id.imgContact)
    CircleImageView imgContact;
    @Bind(R.id.txtUser)
    TextView txtUser;
    @Bind(R.id.txtStatus)
    TextView txtStatus;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recMessages)
    RecyclerView recMessages;
    @Bind(R.id.edtMessage)
    EditText edtMessage;
    @Bind(R.id.btnSendMessage)
    ImageButton btnSendMessage;
    @Bind(R.id.container)
    CoordinatorLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        setupAdapter();
        setupRecyclerView();

        presenter = new ChatPresenterImpl(this);
        presenter.onCreate();

        setupToolbar(getIntent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupToolbar(Intent intent) {
        String recipient = intent.getStringExtra(EMAIL_KEY);
        boolean online = intent.getBooleanExtra(ONLINE_KEY, false);

        presenter.setChatRecipient(recipient);

        String status = online ? "online" : "offline";
        int color = online ? Color.GREEN : Color.RED;

        txtUser.setText(recipient);
        txtStatus.setText(status);
        txtStatus.setTextColor(color);

        ImageLoader imageLoader = new GlideImageLoader(getApplicationContext());
        imageLoader.load(imgContact, AvatarHelper.getAvatarUrl(recipient));
    }

    private void setupAdapter() {

    }

    private void setupRecyclerView() {
        recMessages.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onMessageReceived(ChatMessage msg) {
        adapter.add(msg);
        recMessages.scrollToPosition(adapter.getItemCount() - 1);
    }
}
