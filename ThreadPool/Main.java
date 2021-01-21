public class Main {

    public static void main(String[] args) {
        ThreadPool.ThreadPool threadPool = new ThreadPool.ThreadPool(1);

        Runnable task1 = () -> {
            System.out.println(Thread.currentThread().getName() + " A");
        };

        Runnable task2 = () -> {
            System.out.println(Thread.currentThread().getName() + " B");
        };


        Runnable task3 = () -> {
            System.out.println(Thread.currentThread().getName() + " C");
        };

        threadPool.submit(task1);
        threadPool.submit(task2);
        threadPool.submit(task3);


    }
}
