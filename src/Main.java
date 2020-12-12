import java.util.concurrent.*;
import java.util.Scanner;
import java.io.*;
class SumThread  extends Thread{
    long a,b;
    long x,sum=0;
    SumThread(long a,long b,long x) {
        this.a=a;
        this.b=b;
        this.x=x;
    }
    @Override
    public void run() {
        for(long i=a+1;i<=b;i++){
            if(String.valueOf(i).contains(String.valueOf(x))){
                sum+=i;
            };
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        long n=scanner .nextInt() ;
        long x=scanner.nextInt();
        long a=n/10;
        long ans=0;
        SumThread sumthread;
        for (int i = 0; i <10; i++) {
            if(i!=9)
                sumthread=new SumThread(a*i,a*(i+1),x);
            else
                sumthread=new SumThread(a*i,n,x);
            sumthread.start();
            try{
                sumthread.join();
            }
            catch(InterruptedException e){
                e.printStackTrace() ;
            }
            ans+=sumthread.sum;
        }
        System.out.println(ans);
    }
}

