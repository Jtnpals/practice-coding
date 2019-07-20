package patterns.singleton;

import java.util.Random;

public class UserThread implements Runnable {
    private String name;
//    private int index = 0;
    private static final Random random = new Random();
    public UserThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("- " + threadName + " has been started");
        int delay = random.nextInt(3000); //3초이내로 끝냄
        for (int i = 0; i < 1; i++) {
            LazyHolderSingleton.getInstance().print(name + ": using -for = " + delay);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("- " + threadName + " has been ended (" + delay + "ms)");

//        addIndex();
    }

    // 내부 변수를 동기화해서 사용
//    private synchronized void addIndex(){
//        index++;
//        System.out.println("current index value: " + index);
//    }
}
