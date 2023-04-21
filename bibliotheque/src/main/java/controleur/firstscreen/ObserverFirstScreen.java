package controleur.firstscreen;

public interface ObserverFirstScreen {
    public void updateProgressBar(int progression);
    public void loadingFailed();
    public void loadingSuccess();
    public void retry();
}
