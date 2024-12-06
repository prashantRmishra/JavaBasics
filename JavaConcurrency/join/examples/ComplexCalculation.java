package JavaConcurrency.join.examples;

import java.math.BigInteger;


 class ComplexCalculation {
    public static void main(String[] args) {
        ComplexCalculation calculation = new ComplexCalculation();
        try {
            BigInteger result  = calculation.calculateResult(BigInteger.valueOf(23), BigInteger.valueOf(10), BigInteger.valueOf(10), BigInteger.valueOf(33));
            System.out.println(result);
        } catch (InterruptedException e) {
            System.out.println("Interrupted  exception has occurred !!");
            e.printStackTrace();
        }
    }
    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) throws InterruptedException {
        BigInteger result;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
            
        */
            PowerCalculatingThread thread1 = new PowerCalculatingThread(base1,power1);
            PowerCalculatingThread thread2 = new PowerCalculatingThread(base2,power2);
            thread1.start();
            thread2.start();
            //making main thread wait for thread1 and thread2 before return the result 
            thread1.join();
            thread2.join();
            result = thread1.getResult().add(thread2.getResult());
        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;
    
        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }
    
        @Override
        public void run() {
           /*
           Implement the calculation of result = base ^ power
           */
           power();
        }
        public void power(){
            for(BigInteger i = BigInteger.ONE;i.compareTo(power)<1;i = i.add(BigInteger.ONE)){
                 result  = result.multiply(base);
            }
        }
    
        public BigInteger getResult() { return result; }
    }
}
