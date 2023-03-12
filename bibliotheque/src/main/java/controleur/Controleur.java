package controleur;

import dao.Database;

import java.io.IOException;

public class Controleur {

    Database db;

    public Controleur() {
        db = new Database();
    }

    public void getLangues() throws IOException {
        /*ListeningExecutorService service = MoreExecutors.listeningDecorator(threadpool);
        AsyncCallable<Long> asyncCallable = Callables.asAsyncCallable(new Callable<Long>() {
            public Long call() {
                return factorial(number);
            }
        }, service);
        ListenableFuture<Long> guavaFuture = Futures.submitAsync(asyncCallable, service);*/
        db.getLanguages();
    }
}
