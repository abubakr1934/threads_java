importjava.util.*;
public class producerconsumer1 {
public static void main(String[] args) throws InterruptedException {
ArrayList<Integer> queue=new ArrayList<>();
int size=5;
consumer c=new consumer(queue, size);
Thread pthread=new Thread(new producer(queue, size), "producer");
Thread cthread=new Thread(c, "consumer");
pthread.start();
Thread.currentThread().sleep(10);
cthread.start();
}
}
class producer implements Runnable{
ArrayList<Integer> queue=new ArrayList<>();
final int size;
public producer(ArrayList<Integer> queue, int size) {
this.queue=queue;
this.size=size;
}
@Override
public void run() {
for(int i=0;i<7;i++) {
if(queue.size()==size) // checks for queue full
{
synchronized(queue)
{
System.out.println("queue is full producer is waiting" );
try {
queue.wait();
} catch (InterruptedException ex) {
System.out.println("interrupted"); }
}
}
synchronized(queue)
{
System.out.println("produced " + i + queue.add(i));
queue.notifyAll();
}
}
}
}
class consumer implements Runnable{
ArrayList<Integer> queue;
final int size;
boolean flag=true;
public consumer(ArrayList<Integer> queue, int size) {
this.queue=queue;
this.size=size;

}
@Override
public void run() {
while(true){
while(queue.isEmpty())
{
synchronized(queue){
try {
queue.wait();
} catch (InterruptedException ex) {
System.out.println("interrupted");
}
}
}
synchronized(queue)
{
queue.notifyAll();
int k=queue.remove(0);
System.out.println("consumed " + k);
}
}} }