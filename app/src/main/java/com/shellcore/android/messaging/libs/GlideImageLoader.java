package com.shellcore.android.messaging.libs;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

/**
 * Created by Cesar on 13/02/2017.
 */

public class GlideImageLoader implements ImageLoader {

    private RequestManager requestManager;

    public GlideImageLoader(Context context) {
        this.requestManager = Glide.with(context);
    }

    @Override
    public void load(ImageView imgContact, String url) {
        requestManager.load(url)
                .into(imgContact);
    }
}
