/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.google.gson.Gson;
import static deploy.DeploymentConfiguration.PU_NAME;
import entity.Airline;
import entity.Flight;
import entity.Url;
import facades.UrlFacade;
import interfaces.AirlinesIF;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.persistence.Persistence;
import static util.AirLineHelper.httpGetFromJSON;

/**
 *
 * @author simon
 */
// Implements multithreaded fetching of Airlines
public class AirlineFetcher implements AirlinesIF {

    private UrlFacade uf;
    private List<String> urlStrings;

    public AirlineFetcher() {
//        uf = new UrlFacade(Persistence.createEntityManagerFactory(PU_NAME));
          urlStrings = new ArrayList();
//        for (Url u : uf.getUrls()) {
//            urlStrings.add(u.getUrlString());
//        }
        urlStrings.add("http://angularairline-plaul.rhcloud.com");

    }

    // TODO : implement facade and use JPA instead of List
    // this is a thread capable of doing the FROM search 
    private class FromWorkerUnit implements Callable<Airline> {

        private String url;
        private String from;
        private String date;
        private int persons;

        public FromWorkerUnit(String url, String from, String date, int persons) {
            this.url = url;
            this.from = from;
            this.date = date;
            this.persons = persons;
        }

        // 
        @Override
        public Airline call() throws Exception {

            String json = AirLineHelper.httpGetFromJSON(url, from, date, persons);

            Gson gson = new Gson();

            Airline result = gson.fromJson(json, Airline.class);

            return result;
        }

    }

    // this is a thread capable of doing the FROM TO search 

    private class FromToWorkerUnit implements Callable<Airline> {

        private String url;
        private String from;
        private String to;
        private String date;
        private int persons;

        public FromToWorkerUnit(String url, String from, String to, String date, int persons) {
            this.url = url;
            this.from = from;
            this.to = to;
            this.date = date;
            this.persons = persons;
        }

        @Override
        public Airline call() throws Exception {

            String json = AirLineHelper.httpGetFromToJSON(url, from, to, date, persons);

            Gson gson = new Gson();

            Airline result = gson.fromJson(json, Airline.class);

            return result;
        }

    }

    // returns all the airlines given a FROM search
    public List<Airline> getAirlines(String from, String timeDate, int persons) throws InterruptedException, ExecutionException {

        AirlineFetcher airfetch = new AirlineFetcher();

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        List<Future<Airline>> futures = new ArrayList();

        for (String url : airfetch.urlStrings) {
            Future fut = threadPool.submit(airfetch.new FromWorkerUnit(url, from, timeDate, persons));
            futures.add(fut);
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.DAYS);

        List<Airline> airlines = new ArrayList();

        for (Future<Airline> fut : futures) {
            Airline next = fut.get();
            airlines.add(next);
            //System.out.println("Adding airline" + next.getAirline());
        }

        return airlines;

    }

    // returns all the airlines given a FROM TO search
    public List<Airline> getAirlines(String from, String to, String timeDate, int persons) throws InterruptedException, ExecutionException {

        AirlineFetcher airfetch = new AirlineFetcher();

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        List<Future<Airline>> futures = new ArrayList();

        for (String url : airfetch.urlStrings) {
            Future fut = threadPool.submit(airfetch.new FromToWorkerUnit(url, from, to, timeDate, persons));
            futures.add(fut);
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.DAYS);

        List<Airline> airlines = new ArrayList();

        for (Future<Airline> fut : futures) {
            Airline next = fut.get();
            airlines.add(next);
            //System.out.println("Adding airline" + next.getAirline());
        }

        return airlines;

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        AirlineFetcher af = new AirlineFetcher();
        List<Airline> airlines = af.getAirlines("CPH", "2016-01-04T23:00:00.000Z", 3);

    }
}
