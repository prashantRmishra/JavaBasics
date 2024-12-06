package JavaConcurrency.join;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Long> list = List.of(0l,20l,454l,767l,3333333l);

        //crating separate thread for each long value
        List<FactorialThread> threads = new ArrayList<>();
        for(Long n : list){
            threads.add(new FactorialThread(n));
        }
        //running each thread in threads list
        for(Thread t : threads){
            t.setDaemon(true);//to make the application exit even when the threads are not finished
            //we can also think of interrupting the thread 
            t.start();
        }
        //forcing the main thread to wait till all the Factorial threads and finished
        // by the time main thread finished this loop all the Threads would guaranteed to have been finished
        for(Thread t : threads){
            t.join(2000);// throws InterruptedException
        }
        for(int i =0;i<list.size();i++){
            if(threads.get(i).isFinished()) System.out.println("factorial of "+ list.get(i) + " "+ threads.get(i).getResult());
            else System.out.println("factorial of "+ list.get(i) + " is still in progress");
        }
    }
}
class FactorialThread extends Thread{
    private long inputNumber = 0;
    private BigInteger result = BigInteger.ZERO;
    private boolean isFinished = false;
    public FactorialThread(long n){
       this.inputNumber = n;
    }
    @Override
    public void run(){
        result = factorial();
        this.isFinished =true;
    }
    public BigInteger factorial(){
        BigInteger multiply = BigInteger.ONE; //base for the multiplication
        for(long i  = inputNumber;i>0;i--){
            multiply = multiply.multiply(new BigInteger(String.valueOf(i)));
        }
        return multiply;
    }
    public BigInteger getResult(){
        return this.result;
    }
    public boolean isFinished(){
        return this.isFinished;
    }
}
