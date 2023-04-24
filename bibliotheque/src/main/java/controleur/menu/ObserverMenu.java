package controleur.menu;

public interface ObserverMenu {
    public void updateProgressBar(int progression);
    public void loadingFailed();
    public void loadingSuccess();
    public void selectioner();
}
