package com.example.listhw;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {
    public static final String NUMCOUNT = "Numcount";
    private ArrayList<String> mNumbers;
    private int mDataCount;
    private RecyclerView mRecyclerView;
    private TextView mCountContainer;
    private Button mAddButton;


    public void fillList() {
        for (int i = 1; i <= mDataCount; i++) {
            mNumbers.add(Integer.toString(i));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNumbers = new ArrayList<>();
        mDataCount = (savedInstanceState == null) ? 100 :
                savedInstanceState.getInt(NUMCOUNT, 100);
        fillList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.numbers_list);

        mAddButton = view.findViewById(R.id.add_number);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumbers.add((mNumbers.size() + 1) + "");
                mRecyclerView.getAdapter().notifyItemInserted(mNumbers.size());
                mCountContainer.setText(String.valueOf(++mDataCount));
            }
        });

       mCountContainer = view.findViewById(R.id.num_count);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final int currentOrient = getResources().getConfiguration().orientation;
        final int spanCount = (currentOrient == Configuration.ORIENTATION_PORTRAIT) ? 3 : 4;
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new NumAdapter(mNumbers));
        mCountContainer.setText(String.valueOf(mRecyclerView.getAdapter().getItemCount()));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NUMCOUNT, mNumbers.size());
    }


    class NumViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        NumViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.num);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.numberOnClick(Integer.parseInt(mTextView.getText().toString()));
                }
            });
        }
    }

    class NumAdapter extends RecyclerView.Adapter<NumViewHolder> {

        private List<String> mNumbers;

        NumAdapter(List<String> numbers) {
            mNumbers = numbers;
        }

        @NonNull
        @Override
        public NumViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View view = inflater.inflate(R.layout.list_element, viewGroup, false);
            return new NumViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NumViewHolder numViewHolder, int i) {
            if (i % 2 == 0) {
                numViewHolder.mTextView.setTextColor(getResources().getColor(R.color.blue));
            } else numViewHolder.mTextView.setTextColor(getResources().getColor(R.color.red));

            numViewHolder.mTextView.setText(mNumbers.get(i));

        }

        @Override
        public int getItemCount() {
            return mNumbers.size();
        }
    }
}
