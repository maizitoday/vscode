package app.designmode.twophasetermination;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-06 22:20:40
 * @LastEditTime: 2019-12-06 22:30:15
 * @LastEditors: 麦子
 */

public class RepairModelImpl implements IRepairModel {

    private IGameSys ir;

    public RepairModelImpl(IGameSys ir) {
        this.ir = ir;
    }

    @Override
    public void run() {
        System.out.println(ir.name() + "已启动");
        try {
            while (true) {
                Thread.sleep(ir.time());
                if (SystemState.getInstance().isState()) {
                    break;
                }
                ir.event();
            }
        } catch (ThreadOverException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            ir.overEvent();
        }
        return;
    }
}