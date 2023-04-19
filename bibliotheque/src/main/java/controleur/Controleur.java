package controleur;

import dao.DatabaseSpeed;

import java.io.IOException;

public class Controleur {

    DatabaseSpeed db;

    public Controleur() {
        db = new DatabaseSpeed();
    }

    public void getLangues() throws IOException {
        /*ListeningExecutorService service = MoreExecutors.listeningDecorator(threadpool);
        AsyncCallable<Long> asyncCallable = Callables.asAsyncCallable(new Callable<Long>() {
            public Long call() {
                return factorial(number);
            }
        }, service);
        ListenableFuture<Long> guavaFuture = Futures.submitAsync(asyncCallable, service);*/
        //db.getByLanguages();
    }
}
