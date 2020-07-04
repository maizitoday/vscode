package app.designmode.future;

public class RealData implements Data {
    protected String data;
  
    public RealData handleBusiness(String data){
        try {
            System.out.println(Thread.currentThread().getName()+" 长时间业务处理中.......");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.data = data;
        return this;
    }


    @Override
    public String getResult() throws InterruptedException {
        return data;
    }
}