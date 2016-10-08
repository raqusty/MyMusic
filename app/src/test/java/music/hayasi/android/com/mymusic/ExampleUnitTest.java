package music.hayasi.android.com.mymusic;

import org.junit.Test;

interface fruit {
    void eat();
}

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect1() throws Exception {
        System.out.println("ss");

        fruit f = Factory.getInstance("music.hayasi.android.com.mymusic.Orange");
        if (f != null) {
            f.eat();
        }
    }
}

class Apple implements fruit {
    public void eat() {
        System.out.println("Apple");
    }
}

class Orange implements fruit {
    public void eat() {
        System.out.println("Orange");
    }
}

class Factory {
    public static fruit getInstance(String ClassName) {
        fruit f = null;
        try {
            f = (fruit) Class.forName(ClassName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
