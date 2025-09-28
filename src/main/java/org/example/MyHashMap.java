package org.example;

import java.util.Objects;

public class MyHashMap<K, V> {

    static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private Node<K, V>[] buckets;
    public MyHashMap() {
        buckets = new Node[DEFAULT_CAPACITY];
    }

    public void put(K key, V value) {

        if (key == null) {
            return;
        }
        int bucketIndex = key.hashCode() % buckets.length;
        Node<K, V> existingNode = buckets[bucketIndex];
        if (existingNode == null) {
            buckets[bucketIndex] = new Node<>(key, value, null);
        } else {
            while (true) {
                if (existingNode.key.equals(key)) {
                    existingNode.value = value;
                    return;
                }

                if (existingNode.next == null) {
                    existingNode.next = new Node<>(key, value, null);
                    return;
                }

                existingNode = existingNode.next;
            }
        }
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }
        int bucketIndex = key.hashCode() % buckets.length;
        Node<K, V> currentNode = buckets[bucketIndex];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode.value;
            }

            currentNode = currentNode.next;
        }

        return null;
    }


    public V remove(K key) {
        if (key == null) {
            return null;
        }

        int bucketIndex = key.hashCode() % buckets.length;
        Node<K, V> currentNode = buckets[bucketIndex];
        Node<K, V> prevNode = null;

        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                if (prevNode == null) {
                    buckets[bucketIndex] = currentNode.next;
                } else {
                    prevNode.next = currentNode.next;
                }
                return currentNode.value;
            }
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
        return null;
    }
}