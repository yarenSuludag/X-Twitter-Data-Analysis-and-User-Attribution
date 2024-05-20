import java.util.Collection;

import java.util.LinkedList;
import java.util.Set;

public class InterestHashTable<K, V> extends LinkedListHashMap<K, V> {
    private LinkedList<Entry<K, V>>[] buckets;
    private int size;
    private static int capacity;
    public InterestHashTable(int capacity) {
        super(capacity);
        this.capacity=capacity;
        buckets = new LinkedList[capacity];
        size = 0;
    }

    @Override
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

    public LinkedList<Entry<K, V>>[] getBuckets() {
        return buckets;
    }

    public void setBuckets(LinkedList<Entry<K, V>>[] buckets) {
        this.buckets = buckets;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
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

    @Override
    public V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        return (value != null) ? value : defaultValue;
    }

    @Override
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

    @Override
    public Collection<V> values() {
        return super.values();
    }

    @Override
    public Set<LinkedListHashMap.Entry<K, V>> entrySet() {
        return super.entrySet();
    }

    public void addUserInterest(User user, K interest) {
        if (!containsInterest(interest)) {
            put(interest, (V) new LinkedList<User>());
        }

        ((LinkedList<User>) get(interest)).add(user);
    }

    public void removeUserInterest(User user, K interest) {
        if (containsInterest(interest)) {
            // ((LinkedList<User>) get(interest)).removeUser(User user,);
        }
    }

    public LinkedList<User> getUsersByInterest(K interest) {
        if (containsInterest(interest)) {

            return ((LinkedList<User>) get(interest));
        }
        return new LinkedList<>();
    }

    private boolean containsInterest(K interest) {
        int index = getIndex(interest);
        if (buckets[index] != null) {
            for (Entry<K, V> entry : buckets[index]) {
                if (entry.getKey().equals(interest)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getIndex(K key) {
        return Math.abs(key.hashCode() % capacity);
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

        public void setValue(V value) {
            this.value = value;
        }
    }


}