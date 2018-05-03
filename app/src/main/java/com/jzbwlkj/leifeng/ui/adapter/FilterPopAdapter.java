package com.jzbwlkj.leifeng.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.MySelfModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/9.
 */

public class FilterPopAdapter extends BaseAdapter {
    private Context context;
    private List<MySelfModel> mList;
    private int pos = -1;
    private ClickInterface clickInterface;

    public FilterPopAdapter(Context context, List<MySelfModel> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.pop_filter, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position == pos) {
            viewHolder.tvPopFilter.setTextColor(context.getResources().getColor(R.color.global));
        } else {
            viewHolder.tvPopFilter.setTextColor(context.getResources().getColor(R.color.text_black));
        }

        viewHolder.tvPopFilter.setText(mList.get(position).getName());

        viewHolder.tvPopFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickInterface.click(position);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_pop_filter)
        TextView tvPopFilter;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setSelect(int p) {
        pos = p;
        notifyDataSetChanged();
    }

    public void setOnClickInterface(ClickInterface onClickInterface) {
        clickInterface = onClickInterface;
    }

    public interface ClickInterface {
        void click(int position);
    }
}
