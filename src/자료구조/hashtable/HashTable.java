package 자료구조.hashtable;

public interface HashTable<K, V> {
    void put(K key, V value);
    V get(K key);
    int hash(K key);
    Entry<K, V> remove(K key);
    boolean isEmpty();


    interface Entry<K, V> {

    }
}
