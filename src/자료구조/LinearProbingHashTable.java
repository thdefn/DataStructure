package 자료구조;

public class LinearProbingHashTable<K, V> extends AbstractHashTable<K, V> {
    private Entry<K, V>[] table;
    private int numberOfItems;


    public LinearProbingHashTable(int capacity) {
        table = new Entry[capacity];
        numberOfItems = 0;
    }

    @Override
    public void put(K key, V value) {
        int index = hash(key);
        while (table[index] != null) {
            if (table[index].isDeleted) break;
            index++;
        }
        table[index] = new Entry<>(key, value);
        numberOfItems++;
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        while (table[index] != null) {
            if (table[index].key.equals(key))
                return table[index].getValue();
            index++;
        }
        return null;
    }

    @Override
    public int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    @Override
    public HashTable.Entry<K, V> remove(K key) {
        int index = hash(key);
        while (table[index] != null) {
            if (table[index].key.equals(key)) {
                Entry<K, V> entry = table[index];
                table[index] = new Entry<>();
                numberOfItems--;
                return entry;
            }
            index++;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return this.numberOfItems == 0;
    }
}
