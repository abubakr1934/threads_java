classsumthread implements Runnable
{
int[] a;

int total;
Thread t;
sumthread(int[] ob){
t=new Thread(this);
a=ob;
}
@Override
public void run()
{
total=0;
for(int i=0;i<a.length;i++)
{
total+= a[i];
}
System.out.println(t.getName()+ " completed");
}
}
public class threaddemo3 {
public static void main(String ar[]) throws InterruptedException {
int[][] a={{1,2,3},{2,3,4},{4,5,6}};
sumthread t1=new sumthread(a[0]);
sumthread t2=new sumthread(a[1]);
sumthread t3=new sumthread(a[2]);
t1.t.start();
t2.t.start();
t3.t.start();
if(t1.t.isAlive())
{
System.out.println("waiting for thread0 to join");
t1.t.join();
System.out.println("thread 0 joined");
}
if(t2.t.isAlive())
{
System.out.println("waiting for thread1 to join");
t2.t.join();
System.out.println("thread 1 joined");
}
if(t3.t.isAlive())
{
System.out.println("waiting for thread2 to join");
t3.t.join();
System.out.println("thread 2 joined");
}
int sum=t1.total +t2.total+t3.total;
System.out.println("sum of matrix is" + sum);
}
}