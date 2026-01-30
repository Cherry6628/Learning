class Counter{
    static int count;
    int instanceNumber;
}
public class static_{
    public static void main(String[] args){
        Counter[] counters = new Counter[5];
        for(int i =0; i<5; i++){
            Counter t = new Counter();
            t.count = i;
            t.instanceNumber = i;
            counters[i] = t;
        }
        for(Counter t: counters){
            System.out.println(t.count+" "+t.instanceNumber);
        }

    }
}