package app.designmode.future;

public class FutureData implements Data {
    RealData realData = null; // FutureData是RealData的封装
    boolean isReady = false; // 是否已经准备好
    private FutureTaskCallBack callback;

    public synchronized void setRealData(RealData realData) {
        if (isReady)
            return;
        this.realData = realData;
        isReady = true;
        notifyAll(); // RealData已经被注入到FutureData中了，通知getResult()方法
    }

    @Override
    public synchronized String getResult() throws InterruptedException {
        if (!isReady) {
            wait(); // 一直等到RealData注入到FutureData中
        }
        return realData.getResult();
    }

    public  synchronized String getCallBackResult() throws InterruptedException {
        if (!isReady) {
            wait(); // 一直等到RealData注入到FutureData中
        }
        return callback.messageCallback(this.realData);
    }

    /**
     * @param callback the callback to set
     */
    public void setCallback(FutureTaskCallBack callback) {
        this.callback = callback;
    }


}