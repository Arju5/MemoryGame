package iss.workshop.sa4108memorygame;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String action = intent.getAction();
        if (action == null)
            return;

        if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            Toast.makeText(context, action, Toast.LENGTH_LONG).show();
        }




        throw new UnsupportedOperationException("Not yet implemented");
    }
}
