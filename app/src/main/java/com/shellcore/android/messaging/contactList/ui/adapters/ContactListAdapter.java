package com.shellcore.android.messaging.contactList.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shellcore.android.messaging.R;
import com.shellcore.android.messaging.domain.AvatarHelper;
import com.shellcore.android.messaging.entities.User;
import com.shellcore.android.messaging.libs.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Cesar on 31/01/2017.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    private List<User> contacts;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    public ContactListAdapter(List<User> contacts, ImageLoader imageLoader, OnItemClickListener clickListener) {
        this.contacts = contacts;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = contacts.get(position);
        holder.setClickListener(user, clickListener);

        boolean online = user.isOnline();
        String email = user.getEmail();
        String status = online ? "ONLINE" : "OFFLINE";
        int color = online ? Color.GREEN : Color.RED;

        holder.txtUser.setText(email);
        holder.txtStatus.setText(status);
        holder.txtStatus.setTextColor(color);

        imageLoader.load(holder.imgContact, AvatarHelper.getAvatarUrl(email));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void add(User user) {
        if (!contacts.contains(user)) {
            contacts.add(user);
            notifyDataSetChanged();
        }
    }

    public void update(User user) {
        if (contacts.contains(user)) {
            int pos = contacts.indexOf(user);
            contacts.set(pos, user);
            notifyDataSetChanged();
        }
    }

    public void remove(User user) {
        if (contacts.contains(user)) {
            contacts.remove(user);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View view;

        @Bind(R.id.imgContact)
        CircleImageView imgContact;
        @Bind(R.id.txtUser)
        TextView txtUser;
        @Bind(R.id.txtStatus)
        TextView txtStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        private void setClickListener(final User user, final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(user);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(user);
                    return false;
                }
            });
        }
    }
}
