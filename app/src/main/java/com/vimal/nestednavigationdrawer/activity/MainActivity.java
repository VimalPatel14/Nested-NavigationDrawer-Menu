package com.vimal.nestednavigationdrawer.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.vimal.nestednavigationdrawer.R;
import com.vimal.nestednavigationdrawer.adapter.DrawerExpandableAdapter;
import com.vimal.nestednavigationdrawer.fragment.FirstFragment;
import com.vimal.nestednavigationdrawer.fragment.SecondFragment;
import com.vimal.nestednavigationdrawer.models.MenuModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar; 
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    public static ImageView settings, chat;
    public static TextView stdheader, toolbattxtbuyer, count;
    LinearLayout editprofile;
    DrawerExpandableAdapter expandableListAdapter;
    LinearLayout signout;
    ExpandableListView expandableListView;
    List<MenuModel> headerList;
    HashMap<MenuModel, List<MenuModel>> childList;
    long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbarmain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        settings = findViewById(R.id.settings);
        chat = findViewById(R.id.chat);
        toolbattxtbuyer = findViewById(R.id.toolbattxtbuyer);
        count = findViewById(R.id.count);

        navigationView = findViewById(R.id.navigation_view);
        View headerview = navigationView.getHeaderView(0);
        editprofile = headerview.findViewById(R.id.buyereditprofile);
        stdheader = headerview.findViewById(R.id.stdheader);
        stdheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                setFragment(new FirstFragment());
            }
        });
        stdheader.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        initView();
        drawerLayout = findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.hamburger);
        actionBarDrawerToggle.syncState();

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private void initView() {
        headerList = new ArrayList<>();
        childList = new HashMap<>();
        expandableListView = findViewById(R.id.expandableListView);
        signout = findViewById(R.id.signout);
        prepareMenuData();
        populateExpandableList();

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("")
                        .setMessage("Are you sure you want to Logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

    private void prepareMenuData() {

        MenuModel menuModel = new MenuModel("Home", "1", true, false, "");
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new MenuModel("Test 1", "2", true, false, "");
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }




        menuModel = new MenuModel("Test 2", "3", true, true, ""); //Menu of Java Tutorials
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("Sub Test 1", "", false, false, "");
        childModelsList.add(childModel);
        childModel = new MenuModel("Sub Test 2", "", false, false, "");
        childModelsList.add(childModel);
        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }



    }

    private void populateExpandableList() {

        expandableListAdapter = new DrawerExpandableAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (headerList.get(groupPosition).menuId.equals("1")) {
                    drawerLayout.closeDrawers();
                    setFragment(new FirstFragment());
                } else if (headerList.get(groupPosition).menuId.equals("2")) {
                    drawerLayout.closeDrawers();
                    setFragment(new SecondFragment());
                } else if (headerList.get(groupPosition).menuId.equals("3")) {
//                    setFragment(new MyPackageFragment());
//                    Toast.makeText(MainActivity.this,"In development",Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Log.e("response",groupPosition+" groupPosition");
                Log.e("response",childPosition+" childPosition");


                if (groupPosition == 2) {
                    if (childPosition == 0) {
                        Log.e("response"," FirstFragment");
                        setFragment(new FirstFragment());
                        drawerLayout.closeDrawers();
                    } else if (childPosition == 1) {
                        Log.e("response"," SecondFragment");
                        setFragment(new SecondFragment());
                        drawerLayout.closeDrawers();
                    }
                }
                return false;
            }
        });
    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelay, fragment);
        fragmentTransaction.commit();
    }

    public String getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.framelay).getClass().getSimpleName();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            drawerLayout.closeDrawers();
            if (getCurrentFragment().equals("FirstFragment")) {
                setFragment(new FirstFragment());
                return;
            } else if (getCurrentFragment().equals("SecondFragment")) {
                setFragment(new FirstFragment());
                return;
            }  else {
                if (back_pressed + 1000 > System.currentTimeMillis()) {
                    finishAffinity(); // Close all activites
                    System.exit(0);  // Releasing resources
                } else {
                    Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
                }
                back_pressed = System.currentTimeMillis();

            }
        }
    }
}