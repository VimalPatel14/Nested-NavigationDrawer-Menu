package com.vimal.nestednavigationdrawer.models;

public class MenuModel {

    public String menuName, menuId, url;
    public boolean hasChildren, isGroup;

    public MenuModel(String menuName, String menuId, boolean isGroup, boolean hasChildren, String url) {
        this.menuName = menuName;
        this.menuId = menuId;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
        this.url = url;
    }
}
