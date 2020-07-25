<?php
class Task {

private $conn;
private $table='tasks';

public $quantity;
public $product_id;
public $end_date;


public function __construct($db) {
    $this->conn=$db;
}
public function create_task() {
    $query='INSERT INTO ' . $this->table . '
    SET
        quantity= :quantity,
        product_id= :product_id,
        end_date= :end_date';

    $stmt=$this->conn->prepare($query);
    $this->quantity=htmlspecialchars(strip_tags($this->quantity));
    $this->product_id=htmlspecialchars(strip_tags($this->product_id));
    $this->end_date=htmlspecialchars(strip_tags($this->end_date));
    
    $stmt->bindParam(':quantity', $this->quantity);
    $stmt->bindParam(':product_id', $this->product_id);
    $stmt->bindParam(':end_date', $this->end_date);
        

    if($stmt->execute()) {
        return true;
    }

    //printf("error: %s.\n", $stmt->error);
    return false;
}

public function task_list() {
   
        $query='SELECT 
        task_id, 
        quantity,
        product_id,
        end_date 
        FROM 
        ' . $this->table;
    
        $stmt=$this->conn->prepare($query);
        $stmt->execute();
        while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
           
           $this->data[] = $row;
        }
        
        return $this->data;
       
    
    
}





}

?>


