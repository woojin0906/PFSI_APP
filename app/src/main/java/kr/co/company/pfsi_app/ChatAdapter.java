package kr.co.company.pfsi_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<ChatItem> chatItems;

    public ChatAdapter(List<ChatItem> chatItems) {
        this.chatItems = chatItems;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatItem chatItem = chatItems.get(position);

        holder.senderName.setText(chatItem.getSenderName());
        holder.messageText.setText(chatItem.getMessage());
        holder.timestamp.setText(formatTimestamp(chatItem.getTimestamp()));

        if (chatItem.getProfileImageResId() != 0) {
            holder.profileImage.setImageResource(chatItem.getProfileImageResId());
        }
    }

    @Override
    public int getItemCount() {
        return chatItems.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        public ImageView profileImage;
        public TextView senderName;
        public TextView messageText;
        public TextView timestamp;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profileImage);
            senderName = itemView.findViewById(R.id.senderName);
            messageText = itemView.findViewById(R.id.messageText);
            timestamp = itemView.findViewById(R.id.timestamp);
        }
    }

    private String formatTimestamp(Date timestamp) {
        // 원하는 형식에 맞게 시간을 포맷팅하는 로직을 작성하세요.
        return "4:20";
    }
}