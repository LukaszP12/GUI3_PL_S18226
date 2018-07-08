package zad1;


import java.util.ArrayList;

public class Letters {
    String letters;

    public Letters(String letters) {
        this.letters = letters;
    }

    public ArrayList<Thread> getThreads() {
        ArrayList<Thread> list = new ArrayList<Thread>(this.letters.length());
        for (Character c: this.letters.toCharArray()) {
            Thread t = new Thread();
            t.setName(c.toString());
            list.add(t);
        }
        return list;
    }

}

