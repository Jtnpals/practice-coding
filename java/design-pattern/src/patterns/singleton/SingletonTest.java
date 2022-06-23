package patterns.singleton;

import java.util.ArrayList;

public class SingletonTest {
    public static void main(String[] args) throws InterruptedException {

                System.out.println("Start main method.");
                ArrayList<Thread> threadList = new ArrayList<Thread>();

                for(int i = 0 ; i < 5 ; i++ ){
                    Thread test = new Thread(new UserThread("thread" + i));
                    test.start();
                    threadList.add(test);
                }

                for(Thread t : threadList){
                    t.join(); // main method가 쓰레드의 처리가 끝날때까지 기다리게함
                }

                System.out.println("End main method.");
            }
        }



