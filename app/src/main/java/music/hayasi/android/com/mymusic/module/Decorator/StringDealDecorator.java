package music.hayasi.android.com.mymusic.module.Decorator;

public abstract class StringDealDecorator implements IStringDeal {

    IStringDeal stringDeal;

    public StringDealDecorator(IStringDeal sd) {
        stringDeal = sd;
    }

}

