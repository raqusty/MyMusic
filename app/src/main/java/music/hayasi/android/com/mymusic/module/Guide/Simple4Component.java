package music.hayasi.android.com.mymusic.module.Guide;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.module.Guide.Guide.Component;


/**
 * Created by binIoter on 16/6/17.
 */
public class Simple4Component implements Component {

    @Override
    public View getView(LayoutInflater inflater) {

        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.layer_frends, null);
        return ll;
    }

    @Override
    public int getAnchor() {
        return Component.ANCHOR_RIGHT;
    }

    @Override
    public int getFitPosition() {
        return Component.FIT_END;
    }

    @Override
    public int getXOffset() {
        return 0;
    }

    @Override
    public int getYOffset() {
        return 0;
    }
}
