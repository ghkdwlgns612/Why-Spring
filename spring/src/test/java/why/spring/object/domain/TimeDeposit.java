package why.spring.object.domain;

public class TimeDeposit {

    private int duration; // 기간
    private boolean closed; //해지여부

    public TimeDeposit(int duration, boolean closed) {
        this.duration = duration;
        this.closed = closed;
    }

    Account account = new Account(1235,1234);

    public void CalculateInterest(int when) {
        //계좌에 있는함수호출
        account.calculateInterest(duration);
    }
}
