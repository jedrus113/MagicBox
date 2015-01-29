/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canuhackme;

/**
 *
 * @author Andrzej
 * @param <T>
 */
public class Queue<T> {
    public Queue next, prev;
        T item;

        public Queue first(){
            Queue t = this;

            while(t.prev != null)
                t = t.prev;

            return t;
        }

        @Override
        public String toString(){
            Queue t = this;
            String ret = (String) t.item;

            while(t.next != null){
                t = t.next;
                ret += " " + t.item;
            }

            return ret;
        }

        public Queue add(T p){
            return next = new Queue(p, this, next);
        }
        public Queue(T p){
            next = null;
            prev = null;

            item = p;
        }
        public Queue(T p, Queue prev, Queue next){
            this.next = next;
            this.prev = prev;
            item = p;
        }
}
