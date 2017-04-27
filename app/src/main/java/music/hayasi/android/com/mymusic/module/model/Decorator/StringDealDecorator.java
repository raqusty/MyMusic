package music.hayasi.android.com.mymusic.module.model.Decorator;

public abstract class StringDealDecorator implements IStringDeal {

    IStringDeal stringDeal;

    public StringDealDecorator(IStringDeal sd) {
        stringDeal = sd;
    }

}

