package simple.fortests.Holders;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by starzhinskij on 31.01.2018.
 */

public class ViewHolder {
    public ViewHolder() {
        imageViews = new ArrayList<>();
    }

    public void setImageViews(ImageView imageview) {
        imageViews.add(imageview);
    }

    public List<ImageView> imageViews;
}
