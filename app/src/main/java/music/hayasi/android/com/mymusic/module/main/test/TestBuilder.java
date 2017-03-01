package music.hayasi.android.com.mymusic.module.main.test;

import android.util.Log;

public class TestBuilder {

    public static class Builder {

        private int text1;
        private int text2;
        private int text3;


        public Builder() {

        }

        public TestBuilder.Builder test1(int text1) {
            this.text1 = text1;
            return this;
        }

        public TestBuilder.Builder test2(int text2) {
            this.text2 = text2;
            return this;
        }

        public TestBuilder.Builder test31(int text3) {
            this.text3 = text3;
            return this;
        }

        public TestBuilder.Builder print() {
            Log.i("linzehao", "all " + this.text1 + this.text2 + this.text3);
            return this;
        }
    }
}
