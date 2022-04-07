/**
 *   Minh Nguyen
 *   MyStack.java
 *   project 4
 *   CMSC256 Spring semester
 *   This project is an implementation of linked stacks
 *   and a method that checks if an html tag is balanced
 */

package cmsc256;
import bridges.base.SLelement;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * Methods are from project 4 discussion
 *
 */
public class MyStack<E> implements StackInterface<E> {
    private SLelement<E> topNode;
    public MyStack() {
        topNode = null;
    }
    @Override
    public void push(E newEntry) {
        if (newEntry == null){
            throw new IllegalArgumentException();
        }
        SLelement<E> newNode = new SLelement<E>(newEntry, topNode);
        topNode = newNode;
    }
    @Override
    public E pop() {
        E it;
        if(isEmpty()){
            throw new EmptyStackException();
        }
        else {
            assert (topNode != null);
            it = topNode.getValue();
            topNode = topNode.getNext();
        }
        return it;
    }
    @Override
    public E peek() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return topNode.getValue();
    }
    @Override
    public boolean isEmpty() {
        return topNode == null;
    }
    @Override
    public void clear() {
        topNode = null;
    }
    public static boolean isBalanced(File webpage) throws FileNotFoundException{
        Scanner in;
        MyStack Stack1 = new MyStack();
        try {
            in = new Scanner(webpage);
        } catch (Exception e) {
            throw new FileNotFoundException();
        }
        /**
         * While there are still lines to be read this method will keep running
         */
        while (in.hasNext()) {
            //declaration of variables
            String line = in.nextLine();
            String last = "";
            String first = "";
            String top = "";
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '<' && line.charAt(i + 1) != '/') {
                    for (int j = i + 1;line.charAt(j) != '>'&& j < line.length() ; j++) {
                        first += line.charAt(j);
                    }
                }
                if (first != "") {
                    Stack1.push(first);
                    first = "";
                }
                if (line.charAt(i) == '<' && line.charAt(i + 1) == '/') {
                    for (int j = i + 2; line.charAt(j) != '>' && j < line.length()-1; j++) {
                        last += line.charAt(j);
                    }
                    if (Stack1.isEmpty() == false) {
                        top = String.valueOf(Stack1.peek());
                    }
                    if (Stack1.isEmpty() || top.equals(last) == false){
                        return false;
                    }
                    else{
                        Stack1.pop();
                    }
                }
            }
        }
        return Stack1.isEmpty();
    }
}

