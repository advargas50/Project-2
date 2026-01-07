package util;

import clinic.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The class that stores an array of any object and performs many functions with it
 * @author Angel Vargas, Maxim Trofimchuk
 */
public class List<E> implements Iterable<E> {
    private E[] elements;
    private int size = 0;
    private static final int INITIAL_CAPACITY = 4;

    /**
     * Default constructor for list
     */
    public List() {
        elements = (E[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * This command adds an object at the end of the list
     * @param element the object to be added
     */
    public void add(E element) {
        if (size == elements.length) {
            grow();
        }
        elements[size++] = element;
    }


    /**
     * Removes the element at the given index and fills the newly created gap
     * @param e the element to remove
     */
    public void remove(E e){
        if(contains(e))
        {
            int index = find(e);
            elements[index] = null;
            for (int i = index; i < size - 1; i++) {
                elements[i] = elements[i + 1];
            }
            elements[--size] = null;
        }
    }

    /**
     * grows the array to fit more elements
     */
    private void grow() {
        E[] newElements = (E[]) new Object[elements.length +4];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    /**
     * Makes the list of patients and their associated visits
     * @return the patients list
     */
    public List makePatients(){
        List patients = new List();
        for (int i=0; i < size; i++){
            Appointment app = (Appointment) elements[i];
            if (app != null) {
                Person person = app.getPatient();
                Profile profile=person.getProfile();
                Visit visit=new Visit(app);
                Patient pat=new Patient(profile,visit);
                patients.add(pat);
            }

        }
        return patients;
    }

    /**
     * Sets the index of the list to the given element
     * @param index the index to be changed
     * @param element the element to change the index to
     */
    public void set(int index, E element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        elements[index] = element;
    }

    /**
     * gets the element at the given index
     * @param index the element to return
     * @return the element at the given index
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return elements[index];
    }

    /**
     * Checks if the list is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty(){

        return size==0;
    }

    /**
     * Finds the index of an element in the list
     * @param element the element to be found
     * @return the index of the element
     */
    private int find(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Check if the list contains a given element
     * @param element the element to see if the list contains
     * @return true if yes false otherwise
     */
    public boolean contains(E element) {
        return find(element) != -1;
    }

    /**
     * returns the size of the array
     * @return the size
     */
    public int size() {
        return size;
    }

    /**
     * The iterator constructor
     */
    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }


    /**
     * Iterator class implemented in List class
     */
    private class MyListIterator implements Iterator<E> {
        private int currentIndex = 0;

        /**
         * checks to see if the list contains another element
         * @return true if yes, false otherwise
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        /**
         * retrieves the next element in the array
         * @return next element
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements[currentIndex++];
        }
    }

}