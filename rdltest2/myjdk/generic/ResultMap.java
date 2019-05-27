package generic;

public class ResultMap<K,V> {

    private K key;
    private V value;
    
   //省略 set ,get  方法


public void put(K key,V value){
        this.key=key;
        this.value=value;
    }
}