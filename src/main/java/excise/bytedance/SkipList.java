package excise.bytedance;

import java.util.Random;

class SkipListNode<K extends Comparable<K>, V> {
    K key;
    V value;
    SkipListNode<K, V>[] forward;

    public SkipListNode(K key, V value, int level) {
        this.key = key;
        this.value = value;
        forward = new SkipListNode[level];
    }
}

public class SkipList<K extends Comparable<K>, V> {
    private static final int MAX_LEVEL = 16;
    private int level;
    private SkipListNode<K, V> head;
    private Random random;

    public SkipList() {
        level = 1;
        head = new SkipListNode<>(null, null, MAX_LEVEL);
        random = new Random();
    }

    public void insert(K key, V value) {
        int newLevel = randomLevel();
        if (newLevel > level) {
            for (int i = level; i < newLevel; i++) {
                head.forward[i] = null;
            }
            level = newLevel;
        }

        SkipListNode<K, V>[] update = new SkipListNode[level];
        SkipListNode<K, V> current = head;

        for (int i = level - 1; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].key.compareTo(key) < 0) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        current = new SkipListNode<>(key, value, newLevel);
        for (int i = 0; i < newLevel; i++) {
            current.forward[i] = update[i].forward[i];
            update[i].forward[i] = current;
        }
    }

    public V search(K key) {
        SkipListNode<K, V> current = head;
        for (int i = level - 1; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].key.compareTo(key) < 0) {
                current = current.forward[i];
            }
        }

        if (current.forward[0] != null && current.forward[0].key.compareTo(key) == 0) {
            return current.forward[0].value;
        }

        return null;
    }

    public void delete(K key) {
        SkipListNode<K, V>[] update = new SkipListNode[level];
        SkipListNode<K, V> current = head;

        for (int i = level - 1; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].key.compareTo(key) < 0) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        if (current.forward[0] != null && current.forward[0].key.compareTo(key) == 0) {
            SkipListNode<K, V> deleteNode = current.forward[0];
            for (int i = 0; i < level; i++) {
                if (update[i].forward[i] != deleteNode) {
                    break;
                }
                update[i].forward[i] = deleteNode.forward[i];
            }

            while (level > 1 && head.forward[level - 1] == null) {
                level--;
            }
        }
    }

    private int randomLevel() {
        int newLevel = 1;
        while (random.nextDouble() < 0.5 && newLevel < MAX_LEVEL) {
            newLevel++;
        }
        return newLevel;
    }
}
