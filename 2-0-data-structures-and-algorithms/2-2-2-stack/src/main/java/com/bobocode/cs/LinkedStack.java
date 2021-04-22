package com.bobocode.cs;

import com.bobocode.cs.exception.EmptyStackException;
import com.bobocode.util.ExerciseNotCompletedException;

/**
 * {@link LinkedStack} represents a last-in-first-out (LIFO) stack of objects that is based on singly linked generic nodes.
 * A node is implemented as inner static class {@link Node<T>}.
 *
 * @param <T> generic type parameter
 */
public class LinkedStack<T> implements Stack<T> {

    private Node<T> head;
    private int size = 0;

    /**
     * This method creates a stack of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new stack of elements that were passed as method parameters
     */
    public static <T> LinkedStack<T> of(T... elements) {
        if (elements.length == 0) {
            return new LinkedStack<>();
        }
        Node<T> currentElement = null;
        for (T element : elements) {
            if (currentElement == null) {
                currentElement = new Node<>(element);
            }
            Node<T> newElement = new Node<>(element);
            newElement.setNext(currentElement);
            currentElement = newElement;
        }
        LinkedStack<T> linkedStack = new LinkedStack<>();
        linkedStack.setHead(currentElement);
        linkedStack.setSize(elements.length);
        return linkedStack;
    }

    /**
     * The method pushes an element onto the top of this stack. This has exactly the same effect as:
     * addElement(item)
     *
     * @param element elements to add
     */
    @Override
    public void push(T element) {
        if ( element == null) {
            throw new NullPointerException();
        }
        Node<T> newElement = new Node<>(element);
        newElement.setNext(this.head);
        this.size = this.size + 1;
        this.head = newElement;
    }

    /**
     * This method removes the object at the top of this stack
     * and returns that object as the value of this function.
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException - if this stack is empty
     */
    @Override
    public T pop() {
        if (head == null) {
            throw new EmptyStackException();
        }
        T result = this.head.getElement();
        this.head = this.head.getNext();
        this.size = this.size - 1;
        return result;
    }

    /**
     * Returns the number of elements in the stack
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if a stack is empty
     *
     * @return {@code true} if a stack is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private static class Node<T> {

        private T element;
        private Node<T> next;

        public Node(T element) {
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
