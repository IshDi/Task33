public class ClientInfo {
    protected String title;
    protected String date;
    protected int sum;

    public ClientInfo() {

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String toString() {
        return
                "title: " + title + ", date: " + date + ", sum: " + sum;
    }
}
