package ru.yalymar.banktask;

import java.util.*;

/**
 * @author slavalymar
 * @since 27.03.2017
 * @version 1
 */
public class Counter {

    /**
     * set of customers
     */
    private Set<Customer> customers;

    public Counter() {
        this.customers = new HashSet<>();
    }

    /** add customer into set
     * @param c
     * @return boolean
     */
    public boolean addCustomer(Customer c){
        return this.customers.add(c);
    }

    public Set<Customer> getCustomers() {
        return this.customers;
    }

    /** method for calculate
     * @return Result
     */
    public Result calculateCustomers(){
        Result result = null;
        int count = 0;

        Iterator<Customer> it1 = this.customers.iterator();
        Customer firstLine;

        //external loop
        while(it1.hasNext()){
            firstLine = it1.next();
            Iterator<Customer> it2 = it1;
            List<Customer> list = new ArrayList<>(Arrays.asList(firstLine));

            //internal loop
            while(it2.hasNext()){
                Customer nextLine = it2.next();
                list.add(nextLine);
                if(nextLine.getEnter().getTime() > firstLine.getExit().getTime()){
                    break;
                }
            }

            Result r = this.countCustomersInTime(list);
            if(r.countOfCustomers > count) result = r;
        }

        return result;
    }

    /** calculate time of (come in, time of come out, count of customers) = Result
     * @param list
     * @return Result
     */
    private Result countCustomersInTime(List<Customer> list) {
        int count = 0;
        Date maxEnter = list.get(0).getEnter();
        Date minExit = list.get(0).getExit();

        for(int i = 1; i<list.size(); i++){
            if(list.get(i).getEnter().getTime() > maxEnter.getTime()){
                maxEnter = list.get(i).getEnter();
            }
            if(list.get(i).getExit().getTime() < minExit.getTime()){
                minExit = list.get(i).getExit();
            }
        }

        /** increase count if maxEnter and minExit are between time of come in and time of come out
         *  for each element of list
         */
        for(Customer c: list){
            if(
                    (maxEnter.getTime() >= c.getEnter().getTime() && maxEnter.getTime() <= c.getExit().getTime())
            &&(minExit.getTime() >= c.getEnter().getTime() && minExit.getTime() <= c.getExit().getTime())
                    ){
                count++;
            }
        }

        return new Result(count, maxEnter, minExit);
    }

    // class that describe time of come in, time of come out, count of customers
    public class Result{

        int countOfCustomers;
        Date enter;
        Date exit;

        public Result(int countOfCustomers, Date enter, Date exit) {
            this.countOfCustomers = countOfCustomers;
            this.enter = enter;
            this.exit = exit;
        }

        public int getCountOfCustomers() {
            return this.countOfCustomers;
        }
    }

}
