package music.hayasi.android.com.mymusic.module.Decorator;

public class LeftStringDealDecorator extends StringDealDecorator {


    public LeftStringDealDecorator(IStringDeal sd) {
        super(sd);
    }

    @Override
    public String handleString( ) {
        return "left"+stringDeal.handleString();
    }
}

