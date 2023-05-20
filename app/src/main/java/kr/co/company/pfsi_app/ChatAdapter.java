package kr.co.company.pfsi_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
        // 한국 서울 시간대로 설정
        TimeZone seoulTimeZone = TimeZone.getTimeZone("Asia/Seoul");
        TimeZone.setDefault(seoulTimeZone);

        // 현재 시간 가져오기
        Date currentTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String formattedTime = dateFormat.format(currentTime);
        return formattedTime;
    }
}