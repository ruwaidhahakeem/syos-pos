package report;

public abstract class baseReport {
    public void generateReport() {
        getRequiredData();
        processData();
        reportFormat();
        reportDisplay();
    }

    protected abstract void getRequiredData();
    protected abstract void processData();
    protected abstract void reportFormat();
    protected abstract void reportDisplay();


}
