package com.kyle.demo.view.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kyle.demo.R;
import com.kyle.demo.model.User;

import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "UserAdapter";

    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == User.STUDENT) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent,
                    false);
            return new UserVH(v);
        } else if (viewType == User.TEACHER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user2,
                    parent, false);
            return new UserVH2(v);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof UserVH) {
            UserVH userVH = (UserVH) holder;
            userVH.name.setText(userList.get(position).getName());
            userVH.age.setText(String.valueOf(userList.get(position).getAge()));
            userVH.desc.setText(userList.get(position).getDesc());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "position: " + position + "onClick: " + userList.get(position)
                            .toString());

                    userList.remove(position);
                    notifyDataSetChanged();
                }
            });
        } else if (holder instanceof UserVH2) {
            UserVH2 userVH2 = (UserVH2) holder;
            userVH2.name.setText(userList.get(position).getName());
            userVH2.age.setText(String.valueOf(userList.get(position).getAge()));
            userVH2.desc.setText(userList.get(position).getDesc());
        } else if (holder instanceof UserVH3) {
            UserVH3 userVH3 = (UserVH3) holder;
        }


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return userList.get(position).getType();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (User.TEACHER == getItemViewType(position)) {
                        return 1;
                    } else {
                        return gridLayoutManager.getSpanCount();
                    }
                }
            });
        }
    }

    public void addUser(User user) {
        if (null != user) {
            userList.add(user);
            notifyItemInserted(userList.size() - 1);
        }
    }

    static class UserVH extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView age;
        private TextView desc;


        public UserVH(View view) {
            super(view);

            name = view.findViewById(R.id.tv_user_name);
            age = view.findViewById(R.id.tv_user_age);
            desc = view.findViewById(R.id.tv_user_desc);
        }

    }

    static class UserVH2 extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView age;
        private TextView desc;


        public UserVH2(View view) {
            super(view);

            name = view.findViewById(R.id.tv_user_name);
            age = view.findViewById(R.id.tv_user_age);
            desc = view.findViewById(R.id.tv_user_desc);

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            age.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            desc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });lalalala
        }

    }

    static class UserVH3 extends RecyclerView.ViewHolder {
        private TextView coder0;
        private TextView coder1;
        private TextView coder2;


        public UserVH3(View view) {
            super(view);

            coder0 = view.findViewById(R.id.tv0);
            coder1 = view.findViewById(R.id.tv1);
            coder2 = view.findViewById(R.id.tv2);
        }

    }
}
