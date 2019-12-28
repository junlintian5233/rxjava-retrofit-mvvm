package com.youth.banner.loader;

import android.content.Context;
import android.view.View;

import java.io.Serializable;


public interface ViewLoaderInterface extends Serializable {

    void displayImage(Context context, Object object, View view);

    View createImageView(Context context);
}
