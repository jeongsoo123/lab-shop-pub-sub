package labshoppubsub.domain;

import labshoppubsub.domain.OrderPlaced;
import labshoppubsub.OrderApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.time.LocalDate;


@Entity
@Table(name="Order_table")
@Data

//<<< DDD / Aggregate Root
public class Order  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    private Long id;
    
    
    
    
    private String productId;
    
    
    
    
    private Integer qty;
    
    
    
    
    private String customerId;
    
    
    
    
    private Double amount;

    @PostPersist
    public void onPostPersist(){


        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();

    
    }
    @PrePersist
    public void onPrePersist(){
    labshoppubsub.external.InventoryQuery inventoryQuery = new labshoppubsub.external.InventoryQuery();
    // inventoryQuery.set??()        
      = OrderApplication.applicationContext
        .getBean(labshoppubsub.external.Service.class)
        .inventory(inventoryQuery);
    
    }

    public static OrderRepository repository(){
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(OrderRepository.class);
        return orderRepository;
    }






}
//>>> DDD / Aggregate Root
