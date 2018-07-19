package citiMerchant.showData;

import org.springframework.stereotype.Component;

@Component
public class Prepare_info implements Runnable {

    TestService testService = new TestService();

    private String merchantID;

    public void set(String merchantID) {
        this.merchantID = merchantID;
    }

    public void run() {
        if (testService.isPrapared(merchantID))
            return;

        synchronized (this) {
            while (!testService.isPrapared(merchantID)) {
                testService.prepare(merchantID);
            }
            //System.out.println("prepare finished\n");
        }

    }//end run();

}
