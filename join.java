class threadjoin implements Runnable
{
Thread t;
public threadjoin()
{
t= new Thread(this);
}
public void run() {
System.out.println("r1");
try{
t.sleep(200);
} catch(InterruptedException e) {
System.out.println("thrread interrupted"); }
System.out.println("r2");
}
}
public class Thread3 {
public static void main(String[] args) {
threadjoin t1=new threadjoin();
threadjoin t2=new threadjoin();
try{
t1.t.start();
t2.t.start();
if(t1.t.isAlive())
t1.t.join();
if(t2.t.isAlive())
t2.t.join();
}catch(InterruptedException e)
{
System.out.println("interrupted");
}
System.out.println("t1-->"+ t1.t.isAlive());
System.out.println("t2-->"+ t2.t.isAlive());
}
}