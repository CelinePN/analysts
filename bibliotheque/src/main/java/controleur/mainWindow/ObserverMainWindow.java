package controleur.mainWindow;

public interface ObserverMainWindow {
    public void updateProgressBar(int progression);
    public void loadingFailed();
    public void loadingSuccess();
    public void retry();
}
