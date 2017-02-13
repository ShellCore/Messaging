package com.shellcore.android.messaging.contactList.ui;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.shellcore.android.messaging.R;
import com.shellcore.android.messaging.contactList.ContactListPresenter;
import com.shellcore.android.messaging.contactList.ui.adapters.ContactListAdapter;
import com.shellcore.android.messaging.contactList.ui.adapters.OnItemClickListener;
import com.shellcore.android.messaging.entities.User;
import com.shellcore.android.messaging.libs.GlideImageLoader;
import com.shellcore.android.messaging.libs.ImageLoader;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactListActivity extends AppCompatActivity implements ContactListView, OnItemClickListener {

    // Variables
    private ContactListAdapter adapter;

    // Servicios
    private ContactListPresenter presenter;

    // Componentes
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recContactList)
    RecyclerView recContactList;
    @Bind(R.id.activity_contact_list)
    CoordinatorLayout activityContactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);

        setupAdapter();
        setupRecyclerView();
        presenter.onCreate();
        setupToolbar();
    }

    private void setupAdapter() {
        ImageLoader imageLoader = new GlideImageLoader(this.getApplicationContext());
        adapter = new ContactListAdapter(new ArrayList<User>(), imageLoader, this);
    }

    private void setupRecyclerView() {
        recContactList.setLayoutManager(new LinearLayoutManager(this));
        recContactList.setAdapter(adapter);
    }

    private void setupToolbar() {
        toolbar.setTitle(presenter.getCurrentUserEmail());
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.btnAdd)
    public void addContact() {

    }

    @Override
    public void onContactAdded(User user) {

    }

    @Override
    public void onContactChanged(User user) {

    }

    @Override
    public void onContactRemoved(User user) {

    }

    @Override
    public void onItemClick(User user) {

    }

    @Override
    public void onItemLongClick(User user) {

    }
}
