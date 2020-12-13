package com.vimal.nestednavigationdrawer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.vimal.nestednavigationdrawer.R;
import com.vimal.nestednavigationdrawer.models.MenuModel;

import java.util.HashMap;
import java.util.List;

public class DrawerExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<MenuModel> listHeaderData;
    private HashMap<MenuModel, List<MenuModel>> listChildData;


    public DrawerExpandableAdapter(Context context, List<MenuModel> listHeader, HashMap<MenuModel, List<MenuModel>> listChildData) {
        this.context = context;
        this.listHeaderData = listHeader;
        this.listChildData = listChildData;
    }

    public MenuModel getChild(int groupPosition, int childPosition) {
        return this.listChildData.get(this.listHeaderData.get(groupPosition)).get(childPosition);
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        final String childText = getChild(groupPosition, childPosition).menuName;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group_child, null);
        }

        TextView txtChildList = convertView.findViewById(R.id.lblListItem);
        txtChildList.setText(childText);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        if (this.listChildData.get(this.listHeaderData.get(groupPosition)) == null) {
            return 0;
        } else {
            return this.listChildData.get(this.listHeaderData.get(groupPosition)).size();
        }

    }


    @Override
    public MenuModel getGroup(int groupPosition) {
        return this.listHeaderData.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listHeaderData.size();
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = getGroup(groupPosition).menuName;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group_header, null);
        }

        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setText(headerTitle);
        ImageView img_header_menu = convertView.findViewById(R.id.img_header_menu);
        if (getGroup(groupPosition).hasChildren) {
            img_header_menu.setVisibility(View.VISIBLE);
        } else {
            img_header_menu.setVisibility(View.GONE);
        }
        return convertView;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
