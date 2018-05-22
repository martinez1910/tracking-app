package com.abamobile.empleodigital.trackingapp.design.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bvapp.arcmenulibrary.ArcMenu;
import com.bvapp.arcmenulibrary.widget.FloatingActionButton;
import com.bvapp.arcmenulibrary.widget.ObservableScrollView;
import com.abamobile.empleodigital.trackingapp.R;

public class BaseActivity extends AppCompatActivity {

    private ArcMenu menu;
    private ObservableScrollView scrollView;


    public void putMenu(final Context context){
//        scrollView = findViewById(R.id.sv_main);

        menu = findViewById(R.id.arcMenu);
        menu.setIconSize(115);
        menu.showTooltip(true);
        menu.setToolTipBackColor(getResources().getColor(R.color.colorInfo));
        menu.setToolTipCorner(8f);
        menu.setToolTipPadding(5f);
        menu.setToolTipTextSize(15);
        menu.setMinRadius(10);
        menu.setToolTipTextColor(getResources().getColor(R.color.colorPrimaryDark));
//        menu.setAnim(300, 300, ArcMenu.ANIM_MIDDLE_TO_RIGHT, ArcMenu.ANIM_MIDDLE_TO_RIGHT,
//                ArcMenu.ANIM_INTERPOLATOR_DECLERATE, ArcMenu.ANIM_INTERPOLATOR_DECLERATE);
//        menu.attachToScrollView(scrollView);

        int[] colors = getResources().getIntArray(R.array.menu_colors);
        String[] names = getResources().getStringArray(R.array.menu_names);
        TypedArray drawables = getResources().obtainTypedArray(R.array.menudrawables);

        for (int i = 0; i < colors.length; i++) {
            FloatingActionButton item = new FloatingActionButton(this);

            item.setSize(FloatingActionButton.SIZE_MINI);
            item.setIcon(drawables.getResourceId(i, -1));
            item.setBackgroundColor(colors[i]);
            item.setTag(i);
            menu.setChildSize(item.getIntrinsicHeight());

            menu.addItem(item, names[i], new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch ((Integer) v.getTag()) {
                        case 2:
                            Intent intent1 = new Intent();
                            intent1.setClass(context, ClockInActivity.class);
                            startActivity(intent1);
                            break;
                        case 1:
                            Intent intent2 = new Intent();
                            intent2.setClass(context, ShiftLogActivity.class);
                            startActivity(intent2);
                            break;
                        case 0:
                            new AlertDialog.Builder(context)
                                    .setTitle(R.string.info)
                                    .setMessage(R.string.info_message)
                                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    })
                                    .show();
                            break;
                    }
                }
            });
        }
    }


}
