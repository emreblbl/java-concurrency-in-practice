package concurrency.chapter2.concept3;


public class UnsafePublish {
    private String state = "safe";


    public UnsafePublish(){
        // Start a new thread that reads the state variable
        new Thread(() -> System.out.println(state)).start();

        //Set the state after the thread has started
        state= "modified";
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new UnsafePublish();
        }
    }
}
