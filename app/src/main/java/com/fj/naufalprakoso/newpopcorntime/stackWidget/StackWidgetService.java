package com.fj.naufalprakoso.newpopcorntime.stackWidget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by NaufalPrakoso on 19/03/18.
 */

public class StackWidgetService extends RemoteViewsService{
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}
