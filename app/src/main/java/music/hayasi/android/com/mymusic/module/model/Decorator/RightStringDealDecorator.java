package music.hayasi.android.com.mymusic.module.model.Decorator;

public class RightStringDealDecorator extends StringDealDecorator {

    public RightStringDealDecorator(IStringDeal sd) {
        super(sd);
    }

    @Override
    public String handleString() {
        return stringDeal.handleString() + "right";
    }
}

