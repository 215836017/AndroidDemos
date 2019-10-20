package com.test.demosendfiles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MyRecycAdapter extends RecyclerView.Adapter<MyRecycAdapter.ViewHolder> {

    private final String TAG = "MyRecycAdapter.java";

    private Context context;
    private File currentFile;
    private String serverIp;
    private LayoutInflater inflater;

    private Map<String, String> selectedNameMap = new HashMap<>();
    private OnUpdateListener onUpdateListener;

    private Sender sender;

    public MyRecycAdapter(Context context, File rootFile, String serverIp) {
        this.context = context;
        this.currentFile = rootFile;
        this.serverIp = serverIp;
        inflater = LayoutInflater.from(context);

        sender = new Sender(serverIp);
    }

    @NonNull
    @Override
    public MyRecycAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_file_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyRecycAdapter.ViewHolder holder, int position) {
        final File file = currentFile.listFiles()[position];
        LogUtil.i(TAG, "onBindViewHolder() -- currentFile.listFiles().length = " +
                currentFile.listFiles().length);
        holder.textView.setText(file.getName());
        final boolean isContainFile = selectedNameMap.containsKey(file.getAbsolutePath());

        if (file.isDirectory()) {
            holder.checkBox.setVisibility(View.GONE);
        } else {
            holder.checkBox.setVisibility(View.VISIBLE);
            if (isContainFile) {
                holder.checkBox.setChecked(true);
            } else {
                holder.checkBox.setChecked(false);
            }
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (file.isDirectory()) {
                    currentFile = file;
                    onUpdateListener.changeCurrentFile(file);
                    notifyDataSetChanged();
                } else {
                    if (isContainFile) {
                        selectedNameMap.remove(file.getAbsolutePath());
                        holder.checkBox.setChecked(false);
                        onUpdateListener.changeFileCount(0);
                    } else {
                        selectedNameMap.put(file.getAbsolutePath(), "file");
                        holder.checkBox.setChecked(true);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return currentFile.listFiles().length;
    }

    public void updateDatasForBack() {
        currentFile = currentFile.getParentFile();
        notifyDataSetChanged();
    }

    public void sendFiles() {
        sender.setPaths(selectedNameMap);
        sender.sendFile();

    }

    public void setOnUpdateListener(OnUpdateListener onUpdateListener) {
        this.onUpdateListener = onUpdateListener;
    }

    public interface OnUpdateListener {
        void changeCurrentFile(File file);

        void changeFileCount(int fileCount);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout layout;
        private CheckBox checkBox;
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.fileList_layout_root);
            checkBox = itemView.findViewById(R.id.fileList_checkBox);
            textView = itemView.findViewById(R.id.fileList_text_fileName);

        }
    }
}
