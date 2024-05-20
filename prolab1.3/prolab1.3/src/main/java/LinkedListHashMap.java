import java.util.*;

public class LinkedListHashMap<K, V> {
    private LinkedList<Entry<K, V>>[] buckets;
    private int capacity;
    private int size;

    public LinkedListHashMap(int capacity) {
        this.capacity = capacity;
        this.buckets = new LinkedList[capacity];
        this.size = 0;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }

        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        bucket.add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = getIndex(key);
        if (buckets[index] == null) {
            return null;
        }

        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }

        return null;
    }
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    values.add(entry.getValue());
                }
            }
        }
        return values;
    }
    public V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        return (value != null) ? value : defaultValue;
    }

    public LinkedList<K> keySet() {
        LinkedList<K> keys = new LinkedList<>();
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    keys.add(entry.getKey());
                }
            }
        }
        return keys;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % capacity);
    }
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new HashSet<>();
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    entrySet.add(entry);
                }
            }
        }
        return entrySet;
    }

    public int size() {
        return size;
    }

    static class Entry<K, V> {
        private K key;
        private V value;



        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

}