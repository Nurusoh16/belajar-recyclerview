package com.rajapulau.belajarrecyclerview;

import android.content.Context;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.rajapulau.belajarrecyclerview.R;

import java.util.ArrayList;

import java.util.List;

public class ContactsAdapter extends ListAdapter<Contact, ContactsAdapter.ViewHolder> {

    private List<Contact> mContacts = new ArrayList<>();

    public static final DiffUtil.ItemCallback<Contact> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Contact>() {
                @Override
                public boolean areItemsTheSame(Contact oldItem, Contact newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(Contact oldItem, Contact newItem) {
                    return (oldItem.getName().equals(newItem.getName()) && oldItem.isOnline() == newItem.isOnline());
                }
            };

    public ContactsAdapter() {
        super(DIFF_CALLBACK);
    }

    public void addMoreContacts(List<Contact> newContacts) {
        mContacts.addAll(newContacts);
        submitList(mContacts); // DiffUtil takes care of the chekc

        int insertionPosition = mContacts.size();
        notifyItemRangeInserted(insertionPosition, newContacts.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_contact, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Contact contact = getItem(position);

        TextView textView = viewHolder.nameTextView;
        textView.setText(contact.getName());

        Button button = viewHolder.messageButton;

        if (contact.isOnline()) {
            button.setText("Online");
            button.setEnabled(true);
        }
        else {
            button.setText("Offline");
            button.setEnabled(false);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public Button messageButton;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.contact_name);
            messageButton = itemView.findViewById(R.id.message_button);
        }
    }
}
