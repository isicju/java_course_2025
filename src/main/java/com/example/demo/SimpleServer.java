package com.example.demo;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.stream.Stream;

import static com.example.demo.WebUtils.HTML_TEMPLATE;

public class SimpleServer {
    private static HttpServer server;
    private static TaxService taxService = new TaxService();

    public static void main(String[] args) {
        SimpleServer simpleServer = new SimpleServer();
        simpleServer.startServer(80);
    }

    public void stop(){
        server.stop(0);
    }

    public void startServer(int port) {
        try {
            System.out.println("Starting Simple Server on port " + port + "...");
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/", new WebTemplate());
            server.createContext("/tax", new ApiHandler());
            server.createContext("/stop", new StopHandler());
            server.start();
            System.out.println("Server started on port " + port + "...");
        } catch (Exception e) {
            System.out.println("Server failed to start " + e.getMessage());
        }
    }

    static class ApiHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            List<TaxPair> taxPairs =
                    Stream.iterate(0, netSalary -> netSalary + 10000)
                            .limit(15)
                            .map(grossSalary -> new TaxPair(grossSalary, taxService.getNetMonthlySalary(grossSalary)))
                            .toList();
            String taxValues = (new Gson()).toJson(taxPairs);
            exchange.sendResponseHeaders(200, taxValues.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(taxValues.getBytes());
            }
        }
    }

    static class WebTemplate implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.sendResponseHeaders(200, HTML_TEMPLATE.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(HTML_TEMPLATE.getBytes());
            }
        }
    }

    static class StopHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String message = "Stopping server...";
            exchange.sendResponseHeaders(200, message.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(message.getBytes());
            }
            server.stop(0);
            System.out.println("Server was stopped on port 80");
        }
    }

}