package com.example.miguelcatalino.lab4b_modernartui;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends ActionBarActivity {
    static private final String URL = "http://www.moma.org";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar sk1 = (SeekBar) this.findViewById(R.id.skb1);
        final LinearLayout ly1 = (LinearLayout) this.findViewById(R.id.ly01);
        final LinearLayout ly2 = (LinearLayout) this.findViewById(R.id.ly02);
        final LinearLayout ly4 = (LinearLayout) this.findViewById(R.id.ly04);
        //Arrays for save the HSV of every color
        final float[] hsv1 = new float[3];
        final float[] hsv2 = new float[3];
        final float[] hsv4 = new float[3];
        //Fill de arrays with de HSV of evary color of each linearLayout which color is no-gray and no-white
        Color.colorToHSV(Color.parseColor("#8C1715"), hsv1);
        Color.colorToHSV(Color.parseColor("#105410"), hsv2);
        Color.colorToHSV(Color.parseColor("#7A5F15"), hsv4);
        //Take the difference of each HUE
        final float vh1 = 3.6f - hsv1[0];
        final float vh2 = 3.6f - hsv2[0];
        final float vh4 = 3.6f - hsv4[0];
        //Save the origial HUE value
        final float bh1 = hsv1[0];
        final float bh2 = hsv2[0];
        final float bh4 = hsv4[0];
        sk1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int max = seekBar.getMax();
                //Set new value for the HUE, this way, the color of every Layout change
                hsv1[0] = (bh1 + (((progress * bh1 * 100) / vh1) / max));
                hsv2[0] = (bh2 + (((progress * bh2 * 100) / vh2) / max));
                hsv4[0] = (bh4 + (((progress * bh4 * 100) / vh4) / max));
                //Set the new background color
                ly1.setBackgroundColor(Color.HSVToColor(hsv1));
                ly2.setBackgroundColor(Color.HSVToColor(hsv2));
                ly4.setBackgroundColor(Color.HSVToColor(hsv4));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Create an AlertDialog
            AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.diagLook).create();
            alertDialog.setTitle("More Information");
            String msg = "Inspired by the works of artists such as Piet Mondrian and Ben Nicholson "
                    + "\n" + "\n"
                    + "Clic below to learn more!";
            alertDialog.setMessage(msg);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Visit MOMA", new android.content.DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialoginterface, int i) {
                    startImplicitActivation();

                }
            });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Not Now", new android.content.DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialoginterface, int i) {
                    dialoginterface.dismiss();

                }
            });
            alertDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//Create a Intent that call the MOMA website
    private void startImplicitActivation() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        startActivity(intent);
    }
}
