package 자료구조;

public abstract class AbstractHashTable<K, V> implements HashTable<K, V> {
    static class Entry<K, V> implements HashTable.Entry<K, V> {
        K key;
        V value;
        boolean isDeleted;

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            isDeleted = false;
        }

        public Entry() {
            isDeleted = true;
        }
    }
}
