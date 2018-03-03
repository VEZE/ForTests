package simple.fortests.Holders;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by starzhinskij on 31.01.2018.
 */

public class TextHolderTop {
    public TextHolderTop() {
        textViews = new ArrayList<>();
    }

    public void setTextViews(TextView textView) {
        textViews.add(textView);
    }

    public List<TextView> textViews;
}
