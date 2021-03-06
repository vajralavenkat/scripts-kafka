package org.acme.kafkaconsumer;

import java.io.StringReader;
import java.math.BigDecimal;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonValue;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

// import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import io.reactivex.Flowable;
import io.reactivex.functions.Predicate;


@ApplicationScoped
@Path("/")
public class MyStreamSubscriber {
    private static final Logger LOG = Logger.getLogger(MyStreamSubscriber.class);

    @GET
    @Path("reset")
    public String reset() {
      return "This is here to cause live reload: curl localhost:8081/reset";
    }
 
    @Incoming("input")
    public void process(String msg) {
        LOG.info("\nBurr INPUT 1: " + msg);
    }     

    // @Incoming("input")
    // public CompletionStage<Void> process(KafkaMessage<String,String> msg) {
    //     LOG.info("INPUT2: " + msg.getPayload());
    //     return msg.ack();
    // }

    // you see input3 logged 1 time

    // @Incoming("input")
    // @Outgoing("output")
    // public Flowable<String> process(Flowable<String> input) {      
    //   System.out.println("\n\n\nINPUT3: " + input.toString());
    //   return input;
    // }

    // Simple mapping of input to output

    // @Incoming("input")
    // @Outgoing("output")
    // public String process(String input) {
    //   LOG.info("INPUT4: " + input);
    //   return input;
    // }
    
    // Allows you to see any errors
    // @Incoming("input")
    // @Outgoing("output")
    // public Flowable<String> process(Flowable<String> input) {      
    //   // return input;
    //   return input        
    //     .doOnNext(json -> System.out.println("INPUT5: " + json + "\n"))
    //     .doOnError(e -> System.out.println("ERROR5: " + e + "\n"))
    //     ;
    // }

    // skip messages that have a customer id ending in '8'
    // @Incoming("input")
    // @Outgoing("output")
    // public Flowable<String> process(Flowable<String> input) {      
    //   // return input;
    //   return input  
    //     .filter(stuff -> (Json.createReader(new StringReader(stuff)).readObject().getString("id")).endsWith("8"))        
    //     .doOnNext(json -> System.out.println("INPUT6: " + json + "\n"))
    //     .doOnError(e -> System.out.println("ERROR6: " + e + "\n"))
    //     ;
    // }

    // Add a Customer Name to the output message
    // @Incoming("input")
    // @Outgoing("output")
    // public String transform(String input) {
    //   LOG.info("INPUT7: " + input);

    //   JsonReader jsonReader = Json.createReader(new StringReader(input));
    //   JsonObject myJsonObject = jsonReader.readObject();   
    
    //   // now rebuild the jsonobject, with the new entry
    //   JsonObjectBuilder builder = Json.createObjectBuilder();   
    //   builder.add("custName", "Special K");
    //   myJsonObject.entrySet().forEach(e -> builder.add(e.getKey(),e.getValue()));
    //   JsonObject newJson = builder.build();
      
    //   String newJsonAsString = newJson.toString();
    //   LOG.info("OUTPUT7: " + newJsonAsString);

    //   return newJsonAsString;
    // }    
    
    
    // @Incoming("input")
    // @Outgoing("output")
    // public String transform(String input) {
    //   LOG.info("INPUT8: " + input);

    //   JsonReader jsonReader = Json.createReader(new StringReader(input));
    //   JsonObject myJsonObject = jsonReader.readObject();   
    
      
    //   JsonObjectBuilder mainBuilder = Json.createObjectBuilder();   
    //   mainBuilder.add("id",myJsonObject.getString("id"));
    //   // new classification
    //   mainBuilder.add("class", "Special K Customer");

    //   JsonArrayBuilder orderArrayBuilder = Json.createArrayBuilder();
    //   JsonObjectBuilder orderBuilder = Json.createObjectBuilder();
            

    //   JsonArray theOrders = myJsonObject.getJsonArray("orders");
    //   LOG.info("theOrders: " + theOrders);
      
    //   double orderTotal = 0;

    //   for(int i= 0; i < theOrders.size(); i++) {
    //     JsonValue item = theOrders.get(i);
    //     int qty = item.asJsonObject().getInt("qty");
    //     System.out.print("qty:" + qty);
    //     JsonNumber priceAsJson = item.asJsonObject().getJsonNumber("price");
    //     BigDecimal price = priceAsJson.bigDecimalValue();
    //     System.out.println(" price: " + price);
    //     orderBuilder
    //       .add("id", item.asJsonObject().getInt("id"))
    //       .add("itemid", item.asJsonObject().getString("itemid"))
    //       .add("description", item.asJsonObject().getString("description"))
    //       .add("qty", item.asJsonObject().getInt("qty"))
    //       .add("price",item.asJsonObject().getJsonNumber("price"));
    //       // new extended price
    //       double extendedPrice = qty * price.doubleValue();
    //     orderBuilder  
    //       .add("extended", extendedPrice);
      
    //     orderArrayBuilder.add(orderBuilder.build());
    //     orderTotal = orderTotal + extendedPrice;
        
    //   }
      
    //   LOG.info("TWO");

    //   mainBuilder.add("orderTotal", orderTotal);
    //   mainBuilder.add("orders",orderArrayBuilder.build());

    //   JsonObject newJson = mainBuilder.build();
      
    //   String newJsonAsString = newJson.toString();
    //   LOG.info("OUTPUT8: " + newJsonAsString);

    //   return newJsonAsString;
    // }    

}

