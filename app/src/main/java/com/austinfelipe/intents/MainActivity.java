package com.austinfelipe.intents;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.actions));
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Uri uri = null;
        Intent intent = null;

        switch (position) {
            // Open url
            case 0:
                uri = Uri.parse("http://austinfelipe.wordpress.com");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                dispatchIntent(intent);
                break;
            case 1:
                uri = Uri.parse("tel:986951002");
                intent = new Intent(Intent.ACTION_CALL, uri);
                dispatchIntent(intent);
                break;
            case 2:
                uri = Uri.parse("geo:0,0?q=Av+Manuel+Antonio+Goncalves,Sao+Paulo");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                dispatchIntent(intent);
                break;
            case 3:
                uri = Uri.parse("file://mnt/sdcard/musica.mp3");
                intent = new Intent()
                        .setAction(Intent.ACTION_VIEW)
                        .setDataAndType(uri, "audio/mp3");
                dispatchIntent(intent);
                break;
            case 4:
                uri = Uri.parse("sms:986951002");
                intent = new Intent(Intent.ACTION_VIEW, uri)
                        .putExtra("sms_body", "Nice SMS body!");
                dispatchIntent(intent);
                break;
            case 5:
                intent = new Intent()
                        .setAction(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_TEXT, "Sharing via intent...")
                        .setType("text/plain");
                dispatchIntent(intent);
                break;
            case 6:
                ArrayList<Integer> days = new ArrayList<Integer>();
                days.add(Calendar.MONDAY);
                days.add(Calendar.WEDNESDAY);
                days.add(Calendar.FRIDAY);
                intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, "Study Android")
                        .putExtra(AlarmClock.EXTRA_HOUR, 19)
                        .putExtra(AlarmClock.EXTRA_MINUTES, 0)
                        .putExtra(AlarmClock.EXTRA_SKIP_UI, true)
                        .putExtra(AlarmClock.EXTRA_DAYS, days);
                dispatchIntent(intent);
                break;
            case 7:
                intent = new Intent(Intent.ACTION_SEARCH)
                        .putExtra(SearchManager.QUERY, "O'Reilly");
                dispatchIntent(intent);
                break;
            case 8:
                intent = new Intent(Settings.ACTION_SETTINGS);
                dispatchIntent(intent);
                break;
            case 9:
                intent = new Intent("com.austinfelipe.orientation");
                dispatchIntent(intent);
                break;
            case 10:
                uri = Uri.parse("produto://notebook/slim");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                dispatchIntent(intent);
                break;
            default:
                finish();
        }
    }

    private void dispatchIntent(Intent intent) {
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.intent_error, Toast.LENGTH_SHORT).show();
        }
    }

}
