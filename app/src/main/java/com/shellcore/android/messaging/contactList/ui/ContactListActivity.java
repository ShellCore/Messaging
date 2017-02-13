package com.shellcore.android.messaging.contactList.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.shellcore.android.messaging.R;
import com.shellcore.android.messaging.contactList.ContactListPresenter;
import com.shellcore.android.messaging.entities.User;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactListActivity extends AppCompatActivity implements ContactListView {

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

        presenter.onCreate();
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
}
