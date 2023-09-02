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
            if (table[index].isDeleted) break;
            if (key.equals(table[index].key))
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
        if (isEmpty()) throw new RuntimeException("Hash Table is Empty");
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

    public static void main(String[] args) {
        LinearProbingHashTable<String, String> hashTable = new LinearProbingHashTable<>(10);
        hashTable.put("강호동", "010-1111-1111");
        hashTable.put("유재석", "010-2222-2222");
        hashTable.put("이수근", "010-3333-3333");

        System.out.println(hashTable.get("강호동"));
        System.out.println(hashTable.get("유재석"));
        System.out.println(hashTable.get("이수근"));
        hashTable.remove("이수근");
        hashTable.remove("강호동");
        hashTable.remove("유재석");
        System.out.println(hashTable.get("이수근"));
        System.out.println(hashTable.get("강호동"));
        System.out.println(hashTable.get("유재석"));
    }
}
