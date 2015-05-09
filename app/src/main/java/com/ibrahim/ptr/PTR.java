package com.ibrahim.ptr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


public class PTR extends ActionBarActivity {

    Context context = this;
    int check = 1000;
    ListView lv;
    ImageButton bCevir;
    ArrayList<String> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        lv = (ListView) findViewById(R.id.listView1);
        bCevir = (ImageButton) findViewById(R.id.bCevir);
        bCevir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                CeviriBaslat();
            }
        });

        CeviriBaslat();
    }

    private void CeviriBaslat() {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Konuş Benimle!");
        startActivityForResult(i, check);
    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == check && resultCode == RESULT_OK) {
            results = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            lv.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_expandable_list_item_1, results));
        }
        switch (results.get(0).toLowerCase()) {
            case "nasılsın":
                try {
                    MediaPlayer mediaPlayer = MediaPlayer.create(PTR.this, R.raw.nasilsin);
                    mediaPlayer.start();
                } catch (Exception e) {
                    searchWeb(results.get(0));
                }
                break;
            case "teşekkür ederim":
                try {
                    MediaPlayer mediaPlayer = MediaPlayer.create(PTR.this, R.raw.tesekkur);
                    mediaPlayer.start();
                } catch (Exception e) {
                    searchWeb(results.get(0));
                }
                break;
            case "facebook":
                try {
                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
                    startActivity(LaunchIntent);
                } catch (Exception e) {
                    searchWeb(results.get(0));
                }
                break;
            case "twitter":
                try {
                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.twitter.android");
                    startActivity(LaunchIntent);
                } catch (Exception e) {
                    searchWeb(results.get(0));
                }
                break;
            case "rehber":
                try {
                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.android.contacts");
                    startActivity(LaunchIntent);
                } catch (Exception e) {
                    searchWeb(results.get(0));
                }
                break;
            case "alarm":
                try {
                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.sec.android.app.clockpackage");
                    startActivity(LaunchIntent);
                } catch (Exception e) {
                    searchWeb(results.get(0));
                }
                break;
            case "galeri":
                try {
                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.sec.android.gallery3d");
                    startActivity(LaunchIntent);
                } catch (Exception e) {
                    searchWeb(results.get(0));
                }
                break;
            case "play store":
                try {
                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.android.vending");
                    startActivity(LaunchIntent);
                } catch (Exception e) {
                    searchWeb(results.get(0));
                }
                break;
            case "not tut":
                try {
                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.sec.android.widgetapp.diotek.smemo");
                    startActivity(LaunchIntent);
                } catch (Exception e) {
                    searchWeb(results.get(0));
                }
                break;
            case "telefon":
                try {
                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.android.phone");
                    startActivity(LaunchIntent);
                } catch (Exception e) {
                    searchWeb(results.get(0));
                }
                break;
            case "müzik":
                try {
                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.sec.android.app.music");
                    startActivity(LaunchIntent);
                } catch (Exception e) {
                    searchWeb(results.get(0));
                }
                break;
            case "kamera":
                try {
                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.sec.android.app.camera");
                    startActivity(LaunchIntent);
                } catch (Exception e) {
                    searchWeb(results.get(0));
                }
                break;
            case "hesap makinesi":
                try {
                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.sec.android.app.popupcalculator");
                    startActivity(LaunchIntent);
                } catch (Exception e) {
                    searchWeb(results.get(0));
                }
                break;
            case "hava durumu":
                try {
                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.sec.android.widgetapp.ap.hero.accuweather");
                    startActivity(LaunchIntent);
                } catch (Exception e) {
                    searchWeb(results.get(0));
                }
                break;
            default:
                //Arama sesinden sonra arama yapılsınmı yapımasınmı sorusu
                MediaPlayer mediaPlayer = MediaPlayer.create(PTR.this, R.raw.bulamiyorum);
                mediaPlayer.start();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        AlertDialog.Builder alertdialog = new AlertDialog.Builder(context);
                        alertdialog.setMessage("Arama yapılsın mı?");
                        alertdialog
                                .setCancelable(false)
                                .setPositiveButton("Hayır", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .setNegativeButton("Evet", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        searchWeb(results.get(0));
                                    }
                                });
                        AlertDialog alert = alertdialog.create();
                        alert.show();
                    }
                }, 2500);
                break;
        }
    }

    private void searchWeb(String s) {
        String url = "http://www.google.com.tr/search?q=" + s;
        Intent ii = new Intent(Intent.ACTION_VIEW);
        ii.setData(Uri.parse(url));
        startActivity(ii);
    }

    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PTR.this);
        alertDialog.setMessage("Uygulama kapatılsın mı?");
        alertDialog
                .setCancelable(false)
                .setPositiveButton("Hayır",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Evet",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                finish();
                            }
                        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ptr, menu);
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
           setContentView(R.layout.klavuz);
        }
        if (id == R.id.action_hakkinda) {
            setContentView(R.layout.hakkinda);
        }

        return super.onOptionsItemSelected(item);
    }
}
